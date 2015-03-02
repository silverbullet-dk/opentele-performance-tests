import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

/**
 * Runs a patient simulation involving request to the opentele-citizen-server
 * REST API for listing and submitting both messages and questionnaires.
 *
 * @author Torben Nielsen
 * @version 2015-01-16
 */
class CommonPatientSimulation extends Simulation {

  val host:String = System.getProperty("server")
  val httpConf = http.baseURL("http://" + host)
  val nbUsers:Int = java.lang.Integer.getInteger("users", 1)
  val myRamp:Long = java.lang.Long.getLong("ramp", 0L)

  val feeder = csv("patients.csv").circular

  val scn = scenario("Common Patient Scenario")
    .feed(feeder)
	.exitBlockOnFail {
		exec(
		  Login.patient("${username}","${password}"),
		  PatientListMessages.listMessages("${username}","${password}"),
		  pause(10,30),
		  PatientSubmitMessage.submitMessage("${username}","${password}", "${message}"),
		  pause(5),
		  PatientListQuestionnaires.listQuestionnaires("${username}","${password}"),
		  pause(10),
		  PatientRetrieveQuestionnaire.retrieveQuestionnaire("${username}","${password}", "27")
		  //pause(10,30),
		  //PatientSubmitQuestionnaire.submitQuestionnaire("${username}","${password}", "${skema}")
		)
	}

  setUp(
    scn.inject(rampUsers(nbUsers) over (myRamp seconds))
  ).protocols(httpConf)

}

