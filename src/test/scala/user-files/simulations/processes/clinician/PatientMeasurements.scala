
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object PatientMeasurements {
  def measurements(patientId: String) = exec(http("Measurements")
    .get("/patient/questionnaires/" + patientId))
    .pause(10)

  def weekMeasurements(patientId: String) = exec(http("WeekMeasurements")
    .get("/patient/questionnaires/" + patientId + "?filter=WEEK"))
    .pause(10)

  def monthMeasurements(patientId: String) = exec(http("MonthMeasurements")
    .get("/patient/questionnaires/" + patientId + "?filter=MONTH"))
    .pause(10)

  def quaterMeasurements(patientId: String) = exec(http("QuaterMeasurements")
    .get("/patient/questionnaires/" + patientId + "?filter=QUARTER"))
    .pause(10)

  def yearMeasurements(patientId: String) = exec(http("YearMeasurements")
    .get("/patient/questionnaires/" + patientId + "?filter=YEAR"))
    .pause(10)

  def allMeasurements(patientId: String) = exec(http("AllMeasurements")
    .get("/patient/questionnaires/" + patientId + "?filter=ALL"))
    .pause(10)
}
