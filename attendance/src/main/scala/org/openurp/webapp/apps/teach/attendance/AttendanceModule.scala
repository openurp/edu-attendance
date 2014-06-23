package org.openurp.webapp.apps.teach.attendance

import org.beangle.commons.inject.bind.AbstractBindModule
import org.beangle.commons.io.ClasspathResourceLoader
import org.openurp.webapp.apps.teach.attendance.service.impl.AttendanceServiceImpl

class AttendanceModule extends AbstractBindModule {

  override def binding(): Unit = {
    bind(classOf[AttendanceServiceImpl])
  }
}