
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

/*
 * This is just a test simulation for checking that all the patient API calls
 * can be executed properly.
 */
class VariousPatientTestsSimulation extends Simulation {

  val host:String = System.getProperty("server")
  val httpConf = http.baseURL("http://" + host)
  val nbUsers:Int = java.lang.Integer.getInteger("users", 1)
  val myRamp:Long  = java.lang.Long.getLong("ramp", 0L)

  val feeder = csv("patients.csv").random

  val patientListAcknowledgements = scenario("Patient list acknowledgements")
    .feed(feeder)
    .exec(PatientListAcknowledgements.listAcknowledgements("${username}",
    "${password}"))

  val patientListMessages = scenario("Patient list messages")
    .feed(feeder)
    .exec(PatientListMessages.listMessages("${username}",
    "${password}"))

  val patientListQuestionnaires = scenario("Patient list questionnaires")
    .feed(feeder)
    .exec(PatientListQuestionnaires.listQuestionnaires("${username}",
    "${password}"))

  val patientListRecipients = scenario("Patient list recipients")
    .feed(feeder)
    .exec(PatientListRecipients.listRecipients("${username}",
    "${password}"))
/*
  val patientMarkMessage = scenario("Patient mark message")
    .feed(feeder)
    .exec(PatientMarkMessage.markMessage("${username}",
    "${password}", "${message}"))
*/
  val patientRetrieveMeasurements = scenario("Patient retrieve measurements")
    .feed(feeder)
    .exec(PatientRetrieveMeasurements.retrieveMeasurements("${username}",
    "${password}"))

  val patientRetrieveQuestionnaire = scenario("Patient retrieve questionnaire")
    .feed(feeder)
    .exec(PatientRetrieveQuestionnaire.retrieveQuestionnaire("${username}",
    "${password}", "27"))

  val patientSubmitMessage = scenario("Patient submit message")
    .feed(feeder)
    .exec(PatientSubmitMessage.submitMessage("${username}",
    "${password}", "${message}"))

  val patientSubmitQuestionnaire = scenario("Patient submit questionnaire")
    .feed(feeder)
    .exec(PatientSubmitQuestionnaire.submitQuestionnaire("${username}",
    "${password}", "${skema}"))

  setUp(
    patientListAcknowledgements.inject(rampUsers(nbUsers) over (myRamp seconds)),
    patientListMessages.inject(rampUsers(nbUsers) over (myRamp seconds)),
    patientListQuestionnaires.inject(rampUsers(nbUsers) over (myRamp seconds)),
    patientListRecipients.inject(rampUsers(nbUsers) over (myRamp seconds)),
    //patientMarkMessage.inject(rampUsers(nbUsers) over (myRamp seconds)),
    patientRetrieveMeasurements.inject(rampUsers(nbUsers) over (myRamp seconds)),
    patientRetrieveQuestionnaire.inject(rampUsers(nbUsers) over (myRamp seconds)),
    patientSubmitMessage.inject(rampUsers(nbUsers) over (myRamp seconds)),
    patientSubmitQuestionnaire.inject(rampUsers(nbUsers) over (myRamp seconds))
  ).protocols(httpConf)

}
