import io.gatling.core.Predef._
import io.gatling.http.Predef._

object PatientRetrieveQuestionnaire {
  def retrieveQuestionnaire(userName: String, password: String,
                            questionnaireId: String) =
    exec(http("Retrieve questionnaire")
      .get("/rest/questionnaire/download/" + questionnaireId)
      .basicAuth(userName, password))
}
