import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class LookupPatientGroupsSimulation extends Simulation {

  val host:String = System.getProperty("server")
  val httpConf = http.baseURL("http://" + host)

  val feeder = csv("patients.csv").random
  val clinicians = scenario("Clinicians").feed(feeder).exec(Login.login,  LookupPatientGroups.lookup())

  val nbUsers:Int = java.lang.Integer.getInteger("users", 1)
  val myRamp:Long = java.lang.Long.getLong("ramp", 0L)

  setUp(
    clinicians.inject(rampUsers(nbUsers) over (myRamp seconds))
  ).protocols(httpConf)
}

