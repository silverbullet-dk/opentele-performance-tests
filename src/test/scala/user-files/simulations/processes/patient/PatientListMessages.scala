import io.gatling.core.Predef._
import io.gatling.http.Predef._

object PatientListMessages {
  def listMessages(userName: String, password: String) =
    exec(http("List messages")
      .get("/rest/message/list")
      .basicAuth(userName, password))
}
