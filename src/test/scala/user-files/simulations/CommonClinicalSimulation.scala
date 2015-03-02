import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

/**
 * Runs a clinician simulation involving request to the opentele-server API for
 * login, checking questionnaires, showing a patient and the different screens
 * and actions which can be done in the patient menu.
 *
 * @author Torben Nielsen
 * @version 2015-01-16
 */
class CommonClinicalSimulation extends Simulation {

  val host:String = System.getProperty("server")
  val httpConf = http.baseURL("http://" + host)
  val nbUsers:Int = java.lang.Integer.getInteger("users", 1)
  val myRamp:Long = java.lang.Long.getLong("ramp", 0L)

  val clinical_fedder = csv("clinicals.csv").circular
  val patient_fedder = csv("common_patients.csv").random

  // Forudsætning: klinikere og patienter eksisterer

  val scn = scenario("Common Scenario")
    .feed(clinical_fedder)
    .exec(Login.clinical("${username}","${password}"))

    .exitBlockOnFail {
    repeat(3) {
      feed(patient_fedder)
        .exec(
          PatientOverview.choosePatient("${patientId}"),
          pause(5),
          PatientMeasurements.measurements("${patientId}"),
          pause(5),
          PatientMeasurements.weekMeasurements("${patientId}"),
          pause(10),
          LookupQuestionSchema.lookup("${patientId}"),
          pause(10),
          LookupMessages.lookup("${patientId}"),
          pause(10),
          PatientMenu.graphs("${patientId}"),
          pause(10),
          FindPatient.clearAll(),
          FindPatient.findAll()
        )
        .pause(5)
    }
      .exec(Login.logout)
  }

  setUp(
    scn.inject(rampUsers(nbUsers) over (myRamp seconds))
  ).protocols(httpConf)
}
