OpenTele Server performance tests
===============
Project containing [Gatling.io](http://gatling.io/) based performance tests for the OpenTele-server

How to build and run
--------------------
Make sure you have [Vagrant](http://www.vagrantup.com/) and [VirtualBox](http://virtualbox.org) installed.
Create a Vagrant box using the opentele-performace-tests-vagrant project.
Copy the created box into the root folder of this project (make sure it's named 'opentele-performance-test.box').
Get at copy of OpenTele-server packaged as a war file (run `grails war` in the OpenTele-server project).
Rename the war file to ROOT.war.

Run `vagrant up`
After `vagrant up` finishes you can run `vagrant ssh -c 'tail -f /srv/tomcat/opentele/logs/catalina.out'` to follow the progress of the deployment of OpenTele.
Once OpenTele has deployed it can be found at `http://localhost:4567`

You can then run integration tests with `mvn gatling:execute -Dgatling.simulationClass=[Test-class to run]`

After running the tests run `vagrant destroy` to reset the test system. 

Important
----------------
When using a new version of the box created by the opentele-performance-tests-vagrant project remember remove the old version of the box from Vagrant:
`vagrant box remove Performance-test`.