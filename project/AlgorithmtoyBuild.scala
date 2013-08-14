import sbt._
import sbt.Keys._

object AlgorithmtoyBuild extends Build {

  lazy val algorithmtoy = Project(
    id = "algorithmtoy",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "AlgorithmToy",
      organization := "AlgorithmToy",
      version := "0.1-SNAPSHOT",
      scalaVersion := "2.10.2"
      // add other settings here
    )
  )

}
