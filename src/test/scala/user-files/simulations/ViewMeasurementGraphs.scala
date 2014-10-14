
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._


class ViewMeasurementGraphs extends Simulation {
  //val httpConf = http.baseURL("http://localhost:4567")
  val httpConf = http.baseURL("http://localhost:4567")

  val feeder = csv("patients.csv").random
  val clinicians = scenario("Clinicians").feed(feeder).exec(Login.login, PatientOverview.choosePatient("${patientId}"), PatientQuestionnaires.showFullYear, PatientMenu.graphs("${patientId}"))
  val patients = scenario("Patients").feed(feeder).exec(PatientSubmitQuestionnaire.submitQuestionnaire("${username}", "${password}", "${skema}"))

  setUp(
    clinicians.inject(rampUsers(1) over (10 seconds)),
    patients.inject(rampUsers(1) over (10 seconds))
  ).protocols(httpConf)

}
