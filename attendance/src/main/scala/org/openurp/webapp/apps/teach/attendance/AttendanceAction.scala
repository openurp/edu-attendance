package org.openurp.webapp.apps.teach.attendance

import org.openurp.webapp.apps.teach.attendance.model.Hello
import org.beangle.struts2.action.EntityDrivenAction

class AttendanceAction extends EntityDrivenAction {

  override protected def entityType: Class[_] = classOf[Hello]

  override def index() = forward
  override def search() = forward
  override def edit() = null
  override def save() = null
  override def remove() = null

}