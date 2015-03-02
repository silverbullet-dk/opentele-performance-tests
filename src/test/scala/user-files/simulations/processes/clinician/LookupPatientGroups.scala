
  import io.gatling.core.Predef._
  import io.gatling.http.Predef._

  object LookupPatientGroups {
    def lookup() = exec(http("lookup")
      .get("/patientGroup/list"))
      .pause(10)
  }
