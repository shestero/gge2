name := "gge2"

version := "0.1"

scalaVersion := "2.13.3"

val AkkaVersion = "2.6.9"
val AkkaHttpVersion = "10.2.0"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-testkit" % AkkaVersion % Test,
  "com.typesafe.akka" %% "akka-http-testkit" % AkkaHttpVersion % Test
)

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2" % Test
