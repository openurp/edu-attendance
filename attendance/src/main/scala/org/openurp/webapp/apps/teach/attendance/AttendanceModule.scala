package org.openurp.webapp.apps.teach.attendance

import org.beangle.commons.inject.bind.AbstractBindModule
import org.openurp.webapp.apps.teach.attendance.action.HelloAction

class AttendanceModule extends AbstractBindModule {

  def doBinding(): Unit = {
    bind(classOf[HelloAction])
  }
}