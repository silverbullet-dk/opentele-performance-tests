
import io.gatling.core.Predef._
import io.gatling.http.Predef._


object CreatePatient {
  def create_patient() = exec(http("CreatePatient")
    .post("/patient/createPatient?execution=e1s1")
    //.headers(headers_0)
    .formParam("""execution""", """e1s1""")
    .formParam("""cpr""", """2107531400""")
    .formParam("""firstName""", """Anders""")
    .formParam("""lastName""", """Perdersen""")
    .formParam("""sex""", """MALE""")
    .formParam("""address""", """Vestergade 22""")
    .formParam("""postalCode""", """8000""")
    .formParam("""city""", """Århus C""")
    .formParam("""phone""", """86222222""")
    .formParam("""mobilePhone""", """22222222""")
    .formParam("""email""", """anders@mail.dk""")
    .formParam("""_eventId_next""", """Næste"""))
    .pause(10)

  def user_name() = exec(http("UserName")
    .post("/patient/createPatient?execution=e2s2")
    //.headers(headers_0)
    .formParam("""execution""", """e2s2""")
    .formParam("""username""", """UN1400""")
    .formParam("""cleartextPassword""", """PW1400BC""")
    .formParam("""_eventId_next""", """Næste"""))
    .pause(10)

  def save() = exec(http("Save")
    .post("/patient/createPatient?execution=e2s3")
    //.headers(headers_0)
    .formParam("""execution""", """e2s3""")
    .formParam("""groupIds""", """1""")
    .formParam("""_eventId_saveAndGotoMonplan""", """Gem + gå til monitoreringsplan"""))
    .pause(10)
}
