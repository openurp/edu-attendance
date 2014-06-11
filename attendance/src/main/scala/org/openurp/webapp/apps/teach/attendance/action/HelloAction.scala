package org.openurp.webapp.apps.teach.attendance.action

import org.beangle.struts2.action.EntityDrivenAction

class HelloAction extends EntityDrivenAction{
  
  override def index():String = {
    put("abc", "abc")
    forward()
  }

}