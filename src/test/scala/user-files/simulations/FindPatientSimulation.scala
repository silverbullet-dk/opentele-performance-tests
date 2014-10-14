
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class FindPatientSimulation extends Simulation {
  val httpConf = http.baseURL("http://localhost:4567")

  val feeder = csv("patients.csv").random
  val clinicians = scenario("Clinicians").feed(feeder).exec(Login.login, FindPatient.findPatient("${username}"), FindPatient.clearAll(),FindPatient.findAll())

  setUp(
    clinicians.inject(rampUsers(20) over (10 seconds))
  ).protocols(httpConf)
}
