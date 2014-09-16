package org.openurp.webapp.apps.teach.attendance

import javax.servlet.ServletContext
import org.beangle.webmvc.spring.ContextListener
import org.beangle.webmvc.dispatch.DispatcherServlet
import org.beangle.commons.web.session.HttpSessionEventPublisher
import org.beangle.commons.web.resource.StaticResourceServlet
import org.beangle.commons.web.filter.DelegatingFilterProxy
import org.beangle.commons.web.filter.CharacterEncodingFilter

class WebInitializer extends org.beangle.commons.web.init.Initializer {

  override def onStartup(sc: ServletContext) {
//    val filter = sc.addFilter("securityFilter", classOf[DelegatingFilterProxy])
//    filter.setInitParameter("beanName", "org.beangle.security.web.access.SecurityFilter")
//    
//    var charset = sc.addFilter("characterEncoding", classOf[CharacterEncodingFilter])
  }
}