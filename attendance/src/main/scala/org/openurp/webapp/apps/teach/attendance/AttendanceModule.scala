package org.openurp.webapp.apps.teach.attendance

import org.beangle.commons.inject.bind.AbstractBindModule
import org.beangle.commons.io.ClasspathResourceLoader
import org.beangle.commons.web.resource.ResourceProcessor
import org.beangle.commons.web.resource.filter.ContentTypeFilter
import org.beangle.commons.web.resource.impl.PathResolverImpl
import org.beangle.commons.web.resource.filter.HeaderFilter

class AttendanceModule extends AbstractBindModule {

  def doBinding(): Unit = {
    bind(classOf[PathResolverImpl])
    bind(classOf[ClasspathResourceLoader]).constructor("static")
    
    bind(classOf[ResourceProcessor]).property("filters", list(classOf[ContentTypeFilter], classOf[HeaderFilter]))
//    bind(classOf[ResourceProcessor]).property("filters", list(new ContentTypeFilter()))
  }
}