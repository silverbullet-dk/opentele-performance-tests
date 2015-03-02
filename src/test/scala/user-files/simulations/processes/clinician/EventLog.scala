
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object EventLog {
  def lookup() = exec(http("EventLog")
    .get("""/auditLogEntry/list"""))
    .pause(10)
}
