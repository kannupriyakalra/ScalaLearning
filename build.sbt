ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "ScalaLearning"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10"
libraryDependencies += "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.0"
