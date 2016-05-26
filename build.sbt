name := "dca"

version := "1.0"

scalaVersion := "2.11.8"

mainClass in (Compile, run) := Some("dca.FactorialServer")

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"
    