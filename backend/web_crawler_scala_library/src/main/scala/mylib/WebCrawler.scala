package mylib

import java.sql.{Connection, DriverManager, PreparedStatement, ResultSet}
import scala.jdk.CollectionConverters._
import java.util

case class CrawlLog(id: Int, request: String, response: String)

object Main {
  // Change these as needed
  val dbUrl = "jdbc:postgresql://db:5433/crawlerdb"
  val dbUser = "postgres"
  val dbPassword = "toor"

  def crawl(urlToCrawl:String): String = {
//    val urlToCrawl = "https://quotes.toscrape.com/"
    val postUrl = "http://host.docker.internal:8000/crawl/"
    val payload = ujson.Obj("url" -> urlToCrawl)

    try {
      // Send POST request

      val response = requests.post(
        postUrl,
        data = ujson.write(payload),
        headers = Seq("Content-Type" -> "application/json")
      )
      val responseBody = response.text()

      // Save to PostgreSQL
      insertLog(payload.render(), responseBody)

      return (s"Received response: $responseBody")

    } catch {
      case ex: Exception =>
        return (s"Something went wrong: ${ex.getMessage}")
    }
  }

  def getAllLogs(): List[CrawlLog] = {
    var conn: Connection = null
    var stmt: PreparedStatement = null
    var rs: ResultSet = null
    val logs = scala.collection.mutable.ListBuffer[CrawlLog]()

    try {
      conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)
      val sql = "SELECT id, request_payload, response_data FROM crawl_logs"
      stmt = conn.prepareStatement(sql)
      rs = stmt.executeQuery()

      while (rs.next()) {
        logs += CrawlLog(
          rs.getInt("id"),
          rs.getString("request_payload"),
          rs.getString("response_data")
        )
      }

    } catch {
      case e: Exception =>
        println("DB error: " + e.getMessage)
    } finally {
      if (rs != null) rs.close()
      if (stmt != null) stmt.close()
      if (conn != null) conn.close()
    }

    logs.toList
  }
  def getAllLogsAsJava(): util.List[CrawlLog] = {
    getAllLogs().asJava
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
