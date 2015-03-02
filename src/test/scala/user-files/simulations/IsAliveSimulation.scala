import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class IsAliveSimulation extends Simulation {

  val host:String = System.getProperty("server")
  val httpConf = http.baseURL("http://" + host)
  val nbUsers:Int = java.lang.Integer.getInteger("users", 1)
  val myRamp:Long = java.lang.Long.getLong("ramp", 0L)

  val clinicians = scenario("IsAlive").exec(isAlive.run())

  setUp(
    clinicians.inject(rampUsers(nbUsers) over (myRamp seconds))
  ).protocols(httpConf)
}
