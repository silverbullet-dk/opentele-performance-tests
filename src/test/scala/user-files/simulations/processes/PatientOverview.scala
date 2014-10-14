import io.gatling.core.Predef._
import io.gatling.http.Predef._

object PatientOverview {

  def choosePatient(patientId: String) =
  exec(http("Patient Overview")
  .get("/patientOverview/index"))
  .pause(2)
  .exec(http("Choose Patient")
  .get("/patient/questionnaires/" + patientId))
}
