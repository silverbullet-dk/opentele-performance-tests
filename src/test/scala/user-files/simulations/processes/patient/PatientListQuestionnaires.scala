import io.gatling.core.Predef._
import io.gatling.http.Predef._

object PatientListQuestionnaires{
  def listQuestionnaires(userName: String, password: String) =
    exec(http("List questionnaires")
      .get("/rest/questionnaire/listing")
      .basicAuth(userName, password))
}
