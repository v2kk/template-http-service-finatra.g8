name := "$name;format="normalize"$"
version := "0.0.1"
scalaVersion := "$scala_version$"

// these settings will be share across subsequent projects
lazy val commonSettings = Seq(
  version := Keys.version.toString,
  scalaVersion := Keys.scalaVersion.toString,
  libraryDependencies ++= Seq(
    // put your dependencies here
    "com.twitter" %% "finatra-http" % "20.9.0",
    "com.twitter" %% "finatra-jackson" % "20.9.0",
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    
    "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % "2.9.10",  // using for [de]serialize java 8 datetime
    
    "io.github.finagle" %% "finagle-postgres" % "0.12.0",
    "io.github.finagle" %% "finagle-postgres-shapeless" % "0.12.0",

    "com.typesafe" % "config" % "1.4.0"

  ),
  resolvers ++= Seq(
    // add resolvers here
    Resolver.mavenLocal,
    "Hortonworks Maven Repository" at "http://repo.hortonworks.com/content/groups/public/"
  )
)

lazy val root = project.in(file(".")).settings(
  assemblyJarName in assembly := s"\${name.value}-\${version.value}.jar",
  commonSettings
).enablePlugins(JavaAppPackaging)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

assemblyExcludedJars in assembly := {
  val cp = (fullClasspath in assembly).value
  cp filter {_.data.getName == "compile-0.1.0.jar"}
}
