import io.gatling.core.Predef._
import io.gatling.http.Predef._

object PatientQuestionnaires {
  var showFullYear = exec(http("Show year")
  .get("")
  .queryParam("filer", "year"))
}
