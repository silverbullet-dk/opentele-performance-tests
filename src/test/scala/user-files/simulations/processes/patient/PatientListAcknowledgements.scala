import io.gatling.core.Predef._
import io.gatling.http.Predef._

object PatientListAcknowledgements {
  def listAcknowledgements(userName: String, password: String) =
    exec(http("List acknowledgements")
      .get("/rest/questionnaire/acknowledgements")
      .basicAuth(userName, password)
     )
}
