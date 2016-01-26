name := "shopping-checkout"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.1.4",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test",
  "org.typelevel" %% "scalaz-scalatest" % "0.3.0" % "test"
)