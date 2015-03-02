
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class PatientMeasurementsSimulation extends Simulation {

  val host:String = System.getProperty("server")
  val httpConf = http.baseURL("http://" + host)
  val nbUsers:Int = java.lang.Integer.getInteger("users", 1)
  val myRamp:Long = java.lang.Long.getLong("ramp", 0L)

  val feeder = csv("patient.csv").random

  var patientId = "${patientId}"

  val s = scenario("Clinicians").feed(feeder).exec(
    Login.login,
    PatientMeasurements.measurements(patientId),
    PatientMeasurements.weekMeasurements(patientId),
    PatientMeasurements.monthMeasurements(patientId),
    PatientMeasurements.quaterMeasurements(patientId),
    PatientMeasurements.yearMeasurements(patientId),
    PatientMeasurements.allMeasurements(patientId)
  )

  setUp(
    s.inject(rampUsers(nbUsers) over (myRamp seconds))
  ).protocols(httpConf)
}
