
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._


class ViewMeasurementGraphsSimulation extends Simulation {

  val host:String = System.getProperty("server")
  val httpConf = http.baseURL("http://" + host)
  val nbUsers:Int = java.lang.Integer.getInteger("users", 1)
  val myRamp:Long = java.lang.Long.getLong("ramp", 0L)

  val feeder = csv("patients.csv").random
  val clinicians = scenario("Clinicians")
    .feed(feeder)
    .exec(Login.login, PatientOverview.choosePatient("${patientId}"),
      PatientQuestionnaires.showFullYear, PatientMenu.graphs("${patientId}"))

  val patients = scenario("Patients")
    .feed(feeder)
    .exec(PatientSubmitQuestionnaire.submitQuestionnaire("${username}",
    "${password}", "${skema}"))

  setUp(
    clinicians.inject(rampUsers(nbUsers) over (myRamp seconds)),
    patients.inject(rampUsers(nbUsers) over (myRamp seconds))
  ).protocols(httpConf)

}
