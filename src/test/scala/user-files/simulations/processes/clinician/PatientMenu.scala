import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

object PatientMenu {
  def graphs(patientId: String) = exec(http("Patient graphs").get("/patient/" + patientId + "/graphs"))
}
