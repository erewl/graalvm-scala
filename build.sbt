scalaVersion := "2.12.10"

enablePlugins(GraalVMNativeImagePlugin)

lazy val root = (project in file("."))
  .settings(
    name := "apply-at-vdb",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core" % "2.1.1",
      "org.typelevel" %% "cats-effect" % "2.1.3",
      "org.http4s" %% "http4s-blaze-server" % "0.21.3",
      "org.http4s" %% "http4s-dsl" % "0.21.3",
      "com.softwaremill.sttp.tapir" %% "tapir-core" % "0.14.3",
      "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % "0.14.3",
      "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % "0.14.3",
      "io.circe" %% "circe-core" % "0.12.3",
      "io.circe" %% "circe-generic" % "0.12.3",
      "io.circe" %% "circe-parser" % "0.12.3",
      "ch.qos.logback" % "logback-classic" % "1.2.3" % Runtime,
      "ch.qos.logback" % "logback-core" % "1.2.3" % Runtime,
      "com.github.pureconfig" %% "pureconfig" % "0.12.3"
    )
  )

graalVMNativeImageOptions ++= Seq(
  "-H:ConfigurationResourceRoots=../../configs/",
  "-H:ConfigurationFileDirectories=../../configs/",
  "-H:ResourceConfigurationFiles=../../configs/resource-config.json",
  "-H:ReflectionConfigurationFiles=../../configs/reflect-config.json",
  "-H:+PrintClassInitialization",
  // "-H:IncludeResources=.*\\.conf,.*\\.xml,.*\\.properties",
  "-H:+ReportExceptionStackTraces",
  "--no-fallback",
  "--allow-incomplete-classpath"
  // "--report-unsupported-elements-at-runtime"
)
