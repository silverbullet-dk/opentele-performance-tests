
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object TeamNotes {
  def lookup() = exec(http("TeamNotes")
    .get("""/patientNote/listTeam"""))
    .pause(10)
}

