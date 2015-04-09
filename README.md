OpenTele Server performance tests
===============
Project containing [Gatling.io](http://gatling.io/) based performance tests for the OpenTele-server and OpenTele-citizen-server projects.

How to build and run
--------------------
Make sure you have [Vagrant](http://www.vagrantup.com/) and [VirtualBox](http://virtualbox.org) installed.
Create a Vagrant box using the opentele-performace-tests-vagrant project.
Copy the created box into the root folder of this project (make sure it's named 'opentele-performance-test.box').
Get a copy of OpenTele-server and OpenTele-citizen-server packaged as war files (run `grails war` in the respective projects).

Run `vagrant up`
After `vagrant up` finishes you can run
`vagrant ssh -c 'tail -f /srv/tomcat/opentele-server/logs/catalina.out'`
and
`vagrant ssh -c 'tail -f /srv/tomcat/opentele-citizen-server/logs/catalina.out'`
to follow the progress of the deployment of OpenTele.
Once OpenTele has deployed, opentele-server can be found at `http://localhost:4567` 
and opentele-citizen-server can be found at `http://localhost:4568`.

You can then run integration tests with
`mvn gatling:execute -Dgatling.simulationClass=[Test-class to run] -Dserver=[host:port/context] -Dusers=[number of users] -Dramp=[ramp time]`
After running the tests run `vagrant destroy` to reset the test system. 

Simulations
------------------
Below is a description of the different simulations which can be performed:

- `CommonClinicalSimulation`: Runs a clinician simulation involving login, checking questionnaires and showing different patient options.
- `CommonPatientSimulation`: Runs a patient simulation involving login along with listing and submitting both messages and questionnaires.
- `CreatePatientSimulation`: Logs in a clinician and creates a patient.
- `EventLogSimulation`: Logs in a clinician and checks the event log.
- `FindPatientSimulation`: Logs in a clinician and performs a patient search.
- `IsAliveSimulation`: Check the isAlive page.
- `LookupPatientGroupsSimulation`: Looks up the patient groups menu.
- `LookupUserRolesSimulation`: Looks up the user roles menu.
- `PatientMeasurementsSimulation`: Checks a patient's measurements and goes through each time filter.
- `TeamNotesSimulation`: Looks up the team notes menu.
- `ThresholdValueSimulation`: Looks up the threshold menu, adds a threshold value and deletes the group.
- `VariousPatientTestsSimulation`: A pseudo simulation as it simply goes through all patient request.
- `ViewMeasurementGraphsSimulation`: Clicks on a patient and navigates to the graph menu. Also submits a patient questionnaire.

Collecting CPU, Memory and Disk usage
-------------------------------------
There are scripts included for collecting CPU, Memory and Disk usage.
You can use the following flow to collect data
```
vagrant ssh -c 'nohup /vagrant/scripts/start.sh'
mvn gatling:execute -Dgatling.simulationClass=[Test-class to run] -Dserver=[host:port/context] -Dusers=[number of users] -Dramp=[ramp time]
vagrant ssh -c '/vagrant/scripts/stop.sh'
vagrant ssh -c '/vagrant/scripts/report.sh'
``
The last step generates graphs and these can then be found in the `reports` folder.
The raw data can be found in the `stats` folder.

Important
----------------
When using a new version of the box created by the opentele-performance-tests-vagrant project remember remove the old version of the box from Vagrant:
`vagrant box remove Performance-test`.
