import sbt.Keys._
import sbt._
import uk.gov.hmrc.SbtAutoBuildPlugin
import uk.gov.hmrc.versioning.SbtGitVersioning

object HmrcBuild extends Build {

  val nameApp = "performance-test-runner"

  val appDependencies = Seq(
    "uk.gov.hmrc" %% "logback-json-logger" % "3.1.0",
    "uk.gov.hmrc" %% "hmrctest" % "1.4.0" % "test",
    "org.pegdown" % "pegdown" % "1.5.0" % "test",
    "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.2.5" % "provided",
    "com.typesafe.play" %% "play-json" % "2.6.2"
  )

  lazy val addressModel = Project(nameApp, file("."))
    .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning)
    .settings(
      scalaVersion := "2.11.7",
      libraryDependencies ++= appDependencies,
      crossScalaVersions := Seq("2.11.7"),
      resolvers := Seq(
        Resolver.url("hmrc-releases",
          url("https://dl.bintray.com/hmrc/releases")),
        Resolver.bintrayRepo("hmrc", "releases"),
        "typesafe-releases" at "http://repo.typesafe.com/typesafe/releases/"
      )
    )
}

