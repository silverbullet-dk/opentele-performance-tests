import io.gatling.core.Predef._
import io.gatling.http.Predef._
/**
 * Created by nielsen on 24-10-2014.
 */
object LookupQuestionSchema {
  def lookup(patientId: String) = exec(http("QuestionSchema")
    .get("/patient/questionnaire/" + patientId))
}