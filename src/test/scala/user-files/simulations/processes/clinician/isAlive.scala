import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
 * Created by nielsen on 24-10-2014.
 */
object isAlive {
  def run() = exec(http("isAlive")
    .get("/isAlive"))
}
