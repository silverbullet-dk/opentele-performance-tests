import io.gatling.core.Predef._
import io.gatling.http.Predef._

object PatientSubmitQuestionnaire {
  def submitQuestionnaire(userName: String, password: String, requestBodyFile: String) = exec(http("Submit questionniare").post("/rest/questionnaire/listing").basicAuth(userName, password).body(RawFileBody(requestBodyFile)).asJSON)
}
