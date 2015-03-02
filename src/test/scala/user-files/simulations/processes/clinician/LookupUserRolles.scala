
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object LookupUserRolles {
  def lookup() = exec(http("lookup")
    .get("""/role/list"""))
    .pause(10)
}
