name := "ml-model-manager-api"
scalaVersion := "2.11.8"
version := "1.0"

val sparkVersion = "2.4.0"
val mleapVersion = "0.13.0"
      
lazy val `ml_model_manager_api` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
      


libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice,


  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-mllib" % sparkVersion,

  "ml.combust.mleap" %% "mleap-base" % mleapVersion,
  "ml.combust.mleap" %% "mleap-tensor" % mleapVersion,
  "ml.combust.bundle" %% "bundle-ml" % mleapVersion,
  "ml.combust.mleap" %% "mleap-core" % mleapVersion,
  "ml.combust.mleap" %% "mleap-runtime" % mleapVersion,
  "ml.combust.mleap" %% "mleap-spark" % mleapVersion


)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

      