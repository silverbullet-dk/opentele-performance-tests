import io.gatling.core.Predef._
import io.gatling.http.Predef._

object PatientRetrieveMeasurements {

  def retrieveMeasurements(userName: String, password: String) =
    exec(http("Retrieve measurements")
      .get("/rest/patient/measurements")
      .basicAuth(userName, password))
}
