package org.beangle.webmvc.view.impl

import org.beangle.commons.lang.annotation.description
import org.beangle.webmvc.config.Configurer
import org.beangle.webmvc.view.ViewRender
import org.beangle.webmvc.api.context.ActionContext
import org.beangle.webmvc.api.view.RedirectActionView
import org.beangle.webmvc.api.view.View
import org.beangle.webmvc.api.action.ToURL
import org.beangle.commons.http.HttpMethods
import org.beangle.webmvc.api.action.ToClass
import org.beangle.webmvc.api.view.ActionView
import org.beangle.webmvc.api.context.ContextHolder

@description("重定向调转渲染者")
class RedirectActionViewRender(val configurer: Configurer) extends ViewRender {

  override def supportViewClass: Class[_] = {
    classOf[RedirectActionView]
  }

  override def render(view: View, context: ActionContext): Unit = {
    val request = context.request
    val response = context.response
    val url = new StringBuilder(toURL(view))

    var redirectParams = request.getParameter("_params")
    val ajaxHead = "x-requested-with"
    if (null != redirectParams) {
      if (redirectParams.charAt(0) == '&') redirectParams = redirectParams.substring(1)
      if (null != request.getHeader(ajaxHead)) redirectParams += "&x-requested-with=1"
    } else if (null != request.getHeader(ajaxHead)) redirectParams = "x-requested-with=1"

    if (null != redirectParams) {
      if (url.contains("?")) url.append("&").append(redirectParams)
      else url.append("?").append(redirectParams)
    }
    if (url.startsWith("http://") || url.startsWith("https://")) {
      response.sendRedirect(url.toString)
    } else {
      val finalLocation = if (request.getContextPath.length > 1) request.getContextPath + url.toString else url.toString
      val encodedLocation = response.encodeRedirectURL(finalLocation)

      response.sendRedirect(encodedLocation)

    }
  }

  final def toURL(view: View): String = {
    view.asInstanceOf[ActionView].to match {
      case ca: ToClass =>
        configurer.getActionMapping(ca.clazz.getName, ca.method) match {
          case Some(am) =>
            if (am.httpMethod != HttpMethods.GET) throw new RuntimeException(s"Cannot redirect action mapping using ${am.httpMethod}")
            val ua = am.toURL(ca.parameters, ContextHolder.context.params)
            ca.parameters --= am.urlParams.values
            ua.params(ca.parameters)
            ua.url
          case None => throw new RuntimeException(s"Cannot find action mapping for ${ca.clazz.getName} ${ca.method}")
        }
      case ua: ToURL => ua.url
    }
  }
}