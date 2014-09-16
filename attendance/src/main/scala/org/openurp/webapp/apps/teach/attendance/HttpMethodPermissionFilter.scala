package org.openurp.webapp.apps.teach.attendance

import org.beangle.commons.web.util.RequestUtils
import org.beangle.security.web.access.AuthorizationFilter
import org.beangle.security.web.access.HttpActions
import org.beangle.security.mgt.SecurityManager
import javax.servlet.ServletRequest
import javax.servlet.http.HttpServletRequest
import org.beangle.webmvc.context.ActionContextHelper
import org.beangle.webmvc.api.context.ContextHolder

class HttpMethodPermissionFilter(sm: SecurityManager) extends AuthorizationFilter(sm) {

  def getResource(request: ServletRequest): Any = {
    val mapping = ActionContextHelper.getMapping(ContextHolder.context)
    mapping.action.config.name + ".action"
  }

  import HttpActions._
  def getOperation(request: ServletRequest): Any = methodActions(request.asInstanceOf[HttpServletRequest].getMethod().toUpperCase())
}