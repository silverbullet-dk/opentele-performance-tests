import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Login {
  val login = exec(http("Login page")
    .get("/"))
    .pause(2)
    .exec(http("Do login")
    .post("""/j_spring_security_check""")
    .formParamMap(Map("j_username" -> "helleAndersen", "j_password" -> "helleAndersen1")))
	
  def logout = exec(http("Logout").get("/logout/index"))

  def clinical(userName : String, passWord : String) = exec(http("Login page")
    .get("/"))
    .pause(2)
    .exec(http("Do login")
    .post("""/j_spring_security_check""")
    .formParamMap(Map("j_username" -> userName, "j_password" -> passWord)))

  def patient(userName : String, passWord : String) = exec(http("Patient login page")
    .get("/patient")
    .basicAuth(userName, passWord)
    .header("Client-version", "1.29.0")
    .disableFollowRedirect
  )

}