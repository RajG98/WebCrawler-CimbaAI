ThisBuild / scalaVersion := "2.13.13"
ThisBuild / version := "0.1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .settings(
    name := "WebCrawler",
    libraryDependencies ++= Seq(
      "com.lihaoyi"   %% "requests"       % "0.8.0",
      "com.lihaoyi" %% "ujson"    % "3.1.2",
      "org.postgresql" % "postgresql"     % "42.7.1"
    )
  )
