import org.openurp.parent.Dependencies.*
import org.openurp.parent.Settings.*

ThisBuild / organization := "org.openurp.edu.attendance"
ThisBuild / version := "0.0.3-SNAPSHOT"

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/openurp/edu-attendance"),
    "scm:git@github.com:openurp/edu-attendance.git"
  )
)

ThisBuild / developers := List(
  Developer(
    id = "chaostone",
    name = "Tihua Duan",
    email = "duantihua@gmail.com",
    url = url("http://github.com/duantihua")
  )
)

ThisBuild / description := "OpenURP Edu Attendance"
ThisBuild / homepage := Some(url("http://openurp.github.io/edu-attendance/index.html"))

val apiVer = "0.37.1"
val starterVer = "0.3.23"
val baseVer = "0.4.20"
val eduCoreVer = "0.0.18"
val openurp_edu_api = "org.openurp.edu" % "openurp-edu-api" % apiVer
val openurp_stater_web = "org.openurp.starter" % "openurp-starter-web" % starterVer
val openurp_base_tag = "org.openurp.base" % "openurp-base-tag" % baseVer
val openurp_edu_core = "org.openurp.edu" % "openurp-edu-core" % eduCoreVer

lazy val root = (project in file("."))
  .settings()
  .aggregate(web, webapp)

lazy val web = (project in file("web"))
  .enablePlugins(WarPlugin, TomcatPlugin)
  .settings(
    name := "openurp-edu-attendance-web",
    libraryDependencies ++= Seq(openurp_stater_web, openurp_edu_core),
    libraryDependencies ++= Seq(openurp_edu_api, beangle_ems_app, openurp_base_tag),
    common
  )

lazy val webapp = (project in file("webapp"))
  .enablePlugins(WarPlugin, TomcatPlugin)
  .settings(
    name := "openurp-edu-attendance-webapp",
    common
  ).dependsOn(web)

publish / skip := true