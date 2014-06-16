package org.openurp.webapp.apps.teach.attendance.model

object Hello {
  def apply(id: Long, name: String) = new Hello(id, name)
}

class Hello extends {

  var id: Long = _
  var name: String = _
  var value:String = _

  def this(id: Long, name: String) = {
    this()
    this.id = id
    this.name = name
    this.value = name
  }

}