import io.gatling.core.Predef._
import io.gatling.http.Predef._

object PatientListRecipients {
  def listRecipients(userName: String, password: String) =
    exec(http("List recipients")
      .get("/rest/message/recipients")
      .basicAuth(userName, password))
}
