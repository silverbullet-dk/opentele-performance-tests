import io.gatling.core.Predef._
import io.gatling.http.Predef._

object FindPatient {
  def findPatient(patientName: String) = exec(http("Find patient by name").get("/patient/search"))
    .pause(10)
    .exec(http("Search by first name").post("/patient/doSearch")
    .formParam("firstName", patientName)
    .formParam("_action_doSearch", "Find patient")
    .check(regex(patientName)))
  def clearAll() = exec(http("clear all")
    .post("""/patient/doSearch""").formParam("""_action_resetSearch""", """Nulstil felter"""))
    .pause(10)
  def findAll() = exec(http("find all")
    .post("""/patient/doSearch""").formParam("""_action_doSearch""", """Find patient"""))
    .pause(10)
}
