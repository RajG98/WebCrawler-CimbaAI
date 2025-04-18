import java.sql.{Connection, DriverManager, PreparedStatement}
import requests._

object Main {
  // Change these as needed
  val dbUrl = "jdbc:postgresql://localhost:5432/crawlerdb"
  val dbUser = "postgres"
  val dbPassword = "toor"

  def main(args: Array[String]): Unit = {
    val urlToCrawl = "http://cimba.ai"
    val postUrl = "http://localhost:8000/crawl/"
    val payload = ujson.Obj("url" -> urlToCrawl)

    try {
      // Send POST request
      val response = requests.post(
        postUrl,
        data = ujson.write(payload),
        headers = Seq("Content-Type" -> "application/json")
      )

      val responseBody = response.text()
      println(s"Received response: $responseBody")

      // Save to PostgreSQL
      insertLog(payload.render(), responseBody)

    } catch {
      case ex: Exception =>
        println(s"Something went wrong: ${ex.getMessage}")
    }
  }

  def insertLog(requestPayload: String, responseData: String): Unit = {
    var conn: Connection = null
    var stmt: PreparedStatement = null

    try {
      conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)

      val sql = "INSERT INTO crawl_logs (request_payload, response_data) VALUES (?, ?)"
      stmt = conn.prepareStatement(sql)
      stmt.setString(1, requestPayload)
      stmt.setString(2, responseData)

      stmt.executeUpdate()
      println("Log inserted into database.")

    } catch {
      case e: Exception =>
        println("DB error: " + e.getMessage)
    } finally {
      if (stmt != null) stmt.close()
      if (conn != null) conn.close()
    }
  }
}
