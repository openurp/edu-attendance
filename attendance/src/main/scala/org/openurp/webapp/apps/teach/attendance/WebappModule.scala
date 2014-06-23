package org.openurp.webapp.apps.teach.attendance

import org.beangle.commons.inject.bind.AbstractBindModule
import org.beangle.commons.io.ClasspathResourceLoader
import org.beangle.commons.web.resource.ResourceProcessor
import org.beangle.commons.web.resource.filter.ContentTypeFilter
import org.beangle.commons.web.resource.filter.HeaderFilter
import org.beangle.commons.web.resource.impl.PathResolverImpl

class WebappModule extends AbstractBindModule {

  def binding(): Unit = {
    bind(classOf[PathResolverImpl],classOf[ContentTypeFilter])
    bind(classOf[ClasspathResourceLoader]).constructor("static")
    bind(classOf[ResourceProcessor]).property("filters",list(classOf[HeaderFilter],classOf[ContentTypeFilter]))
  }
}