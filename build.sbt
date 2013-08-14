name := "graphviz-service"

version := "0.0.1"

scalaVersion := "2.10.2"

organization := "jp.seraphr"

seq(scalatraSettings :_*)

resolvers += "seraph-repo" at "http://seraphr.github.com/maven/"

libraryDependencies ++=  Seq(
				"jp.seraphr" %% "graphviz-scala" % "0.0.1",
				"org.scalatra" %% "scalatra" % "2.2.0",
				"org.eclipse.jetty" % "jetty-webapp" % "8.1.8.v20121106" % "container",
				"org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;provided",
				"org.scalatest" %% "scalatest" % "1.9.1" % "test",
				"org.scalacheck" %% "scalacheck" % "1.10.0" % "test"
			)

publishMavenStyle := true

publishArtifact in Test := false

publishTo := Some(Resolver.file("file",  new File("""K:\git\seraphr.github.com\maven""")))

EclipseKeys.withSource := true

EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

pomExtra := (
  <url>https://github.com/seraphr/graphviz-scala</url>
  <licenses>
    <license>
      <name>BSD-style</name>
      <url>http://www.opensource.org/licenses/bsd-license.php</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:seraphr/graphviz-scala.git</url>
    <connection>scm:git:git@github.com:seraphr/graphviz-scala.git</connection>
  </scm>
  <developers>
    <developer>
      <id>seraph</id>
      <name>seraph</name>
      <url>https://github.com/seraphr/</url>
    </developer>
  </developers>)