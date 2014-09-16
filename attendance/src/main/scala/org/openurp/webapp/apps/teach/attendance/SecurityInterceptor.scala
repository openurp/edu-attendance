package org.openurp.webapp.apps.teach.attendance

import org.beangle.security.web.access.SecurityFilter
import org.beangle.webmvc.api.context.ActionContext
import org.beangle.webmvc.execution.Handler
import org.beangle.webmvc.execution.Interceptor

import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

class SecurityInterceptor extends Interceptor {
  var filter: SecurityFilter = _

  class NullFilterChain(var result: Boolean) extends FilterChain {
    override def doFilter(request: ServletRequest, response: ServletResponse): Unit = {
      result = true
    }
  }
  def preHandle(context: ActionContext, handler: Handler): Boolean = {
    val nullChain = new NullFilterChain(false)
    try {
      filter.doFilter(context.request, context.response, nullChain)
    } catch {
      case t: Throwable => println(t, "xxxxxxxxxxxxxxxxxx"); throw t
    }
    nullChain.result
  }

  def postHandle(context: ActionContext, handler: Handler, result: Any): Unit = {

  }
}