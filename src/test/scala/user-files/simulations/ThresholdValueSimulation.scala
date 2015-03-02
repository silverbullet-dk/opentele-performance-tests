
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class ThresholdValueSimulation extends Simulation {

  val host:String = System.getProperty("server")
  val httpConf = http.baseURL("http://" + host)
  val nbUsers:Int = java.lang.Integer.getInteger("users", 1)
  val myRamp:Long = java.lang.Long.getLong("ramp", 0L)

  val feeder = csv("patientgroups.csv").random

  var patientGroupId = "${patientGroupId}"
  // Add
  //val clinicians = scenario("Clinicians").feed(feeder).exec(Login.login, ThresholdValues.lookup(), ThresholdValues.add(patientGroupId))
  // DeleteAll
  //val clinicians = scenario("Clinicians").feed(feeder).exec(Login.login, ThresholdValues.lookup(), ThresholdValues.delete_all(patientGroupId))
  // Add, DeleteAll
  val clinicians = scenario("Clinicians").feed(feeder).exec(Login.login, ThresholdValues.lookup(), ThresholdValues.add(patientGroupId), ThresholdValues.delete_all(patientGroupId))

  setUp(
    clinicians.inject(rampUsers(nbUsers) over (myRamp seconds))
  ).protocols(httpConf)
}
