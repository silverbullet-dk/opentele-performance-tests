import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

/**
 *
 * @author Torben Nielsen
 * @version 2015-01-16
 */
class CreatePatientSimulation extends Simulation {

  val host:String = System.getProperty("server")
  val httpConf = http.baseURL("http://" + host)
  val nbUsers:Int = java.lang.Integer.getInteger("users", 1)
  val myRamp:Long = java.lang.Long.getLong("ramp", 0L)

  val feeder = csv("patients.csv").random
  val clinicians = scenario("Clinicians")
    .feed(feeder)
    .exec(Login.login, CreatePatient.create_patient(),
      CreatePatient.user_name(), CreatePatient.save())

  setUp(
    clinicians.inject(rampUsers(nbUsers) over (myRamp seconds))
  ).protocols(httpConf)
}

