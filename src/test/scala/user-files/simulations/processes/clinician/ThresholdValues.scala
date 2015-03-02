
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object ThresholdValues {

  def lookup() = exec(http("Lookup")
    .get("""/standardThresholdSet/list"""))
    .pause(10)

  def add(patientGruppeId : String) = exec(http("Add")
    // Click the Add button
    .post("""/standardThresholdSet/addThreshold""")
    .formParam("""id""", patientGruppeId)
    .formParam("""chooseThreshold""", """Tilføj tærskelværdi"""))
    .pause(10)

    // Select the threshold
    .exec(http("SelectThreshold")
    .post("""/standardThresholdSet/chooseThreshold""")
    .formParam("""type""", """BLOOD_PRESSURE"""))

    // Save threshold
    .exec(http("SaveThreshold")
    .post("""/standardThresholdSet/saveThresholdToSet""")
    .formParam("""id""", patientGruppeId)
    .formParam("""type""", """BLOOD_PRESSURE""")
    .formParam("""systolicAlertHigh""", """160""")
    .formParam("""systolicWarningHigh""", """140""")
    .formParam("""systolicWarningLow""", """120""")
    .formParam("""systolicAlertLow""", """100""")
    .formParam("""diastolicAlertHigh""", """100""")
    .formParam("""diastolicWarningHigh""", """90""")
    .formParam("""diastolicWarningLow""", """70""")
    .formParam("""diastolicAlertLow""", """50""")
    .formParam("""saveThreshold""", """Opret"""))

    // Reload list
    .exec(http("ReloadList")
    .post("""/meta/registerCurrentPage""")
    .formParam("""currentController""", """standardThresholdSet""")
    .formParam("""currentAction""", """list"""))
    .pause(10)

  def delete_all(patientGruppeId : String) = exec(http("DeleteAll")
    .post("""/standardThresholdSet/index""")
    .formParam("""id""", patientGruppeId)
    .formParam("""_action_delete""", """Slet alle tærskelværdier"""))
    .pause(10)

    // Reload list
    .exec(http("ReloadList")
    .post("""/meta/registerCurrentPage""")
    .formParam("""currentController""", """standardThresholdSet""")
    .formParam("""currentAction""", """list"""))
    .pause(10)
}
