val Http4sVersion = "0.23.6"
val CirceVersion = "0.14.1"
val MunitVersion = "0.7.29"
val LogbackVersion = "1.2.6"
val MunitCatsEffectVersion = "1.0.6"

lazy val root = (project in file("."))
  .settings(
    organization := "com.example",
    name := "quickstart",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "3.1.0",
    libraryDependencies ++= Seq(
      "org.http4s"      %% "http4s-ember-server" % Http4sVersion,
      "org.http4s"      %% "http4s-ember-client" % Http4sVersion,
      "org.http4s"      %% "http4s-circe"        % Http4sVersion,
      "org.http4s"      %% "http4s-dsl"          % Http4sVersion,
      "io.circe"        %% "circe-generic"       % CirceVersion,
      "org.scalameta"   %% "munit"               % MunitVersion           % Test,
      "org.typelevel"   %% "munit-cats-effect-3" % MunitCatsEffectVersion % Test,
      "ch.qos.logback"  %  "logback-classic"     % LogbackVersion,
      "org.scalameta"   %  "svm-subs"            % "101.0.0"
    ),
    nativeImageOptions ++= Seq(
      "-H:+ReportExceptionStackTraces",
      "-H:+AddAllCharsets",
      "--allow-incomplete-classpath",
      "--no-fallback",
      "--initialize-at-build-time",
      "--enable-http",
      "--enable-https",
      "--enable-all-security-services",
      "--verbose"
    ),
    nativeImageJvmIndex := "jabba",
    nativeImageJvm := "graalvm-ce-java11",
    nativeImageVersion := "21.1.0",
  //  addCompilerPlugin("org.typelevel" %% "kind-projector"     % "0.13.0" cross CrossVersion.full),
  //  addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.1"),
    testFrameworks += new TestFramework("munit.Framework")
  ).enablePlugins(NativeImagePlugin)
