import io.gatling.core.Predef._
import io.gatling.http.Predef._

object PatientSubmitMessage {

  def submitMessage(userName: String, password: String,
                    requestBodyFile: String) =
    exec(http("Submit message")
      .post("/rest/message/list")
      .basicAuth(userName, password)
      .body(RawFileBody(requestBodyFile))
      .asJSON)
}
