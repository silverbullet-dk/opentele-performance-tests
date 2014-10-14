import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Login {
  val login = exec(http("Login page")
    .get("/"))
    .pause(2)
    .exec(http("Do login")
    .post("""/j_spring_security_check""")
    .formParamMap(Map("j_username" -> "helleAndersen", "j_password" -> "helleAndersen1")))
}