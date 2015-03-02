import io.gatling.core.Predef._
import io.gatling.http.Predef._

object PatientOverview {

  def choosePatient(patientId: String, offset: Int = 0, max: Int = 10) =
  exec(http("Patient Overview")
  .get("/patientOverview/index?offset=" + offset + "&max=" + max))
  .pause(2)
  .exec(http("Choose Patient")
  .get("/patient/questionnaires/" + patientId))
}
