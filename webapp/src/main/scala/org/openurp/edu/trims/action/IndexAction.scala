package org.openurp.edu.trims.action

import java.net.URL

import org.beangle.commons.codec.digest.Digests
import org.beangle.commons.io.IOs
import org.beangle.data.dao.EntityDao
import org.beangle.security.context.SecurityContext
import org.beangle.security.realm.cas.CasConfig
import org.beangle.security.session.SessionRegistry
import org.beangle.security.web.session.SessionIdPolicy
import org.beangle.webmvc.api.action.ActionSupport
import org.beangle.webmvc.api.action.ServletSupport
import org.beangle.webmvc.api.view.View
import org.openurp.base.model.Semester
import org.openurp.base.model.User
import org.openurp.edu.base.model.Project
import org.openurp.edu.trims.helper.EnvUtils
import org.openurp.platform.api.Urp
import org.openurp.platform.api.app.UrpApp

class IndexAction(entityDao: EntityDao) extends ActionSupport with ServletSupport {

  var sessionRegistry: SessionRegistry = _
  var sessionIdPolicy: SessionIdPolicy = _

  var casConfig: CasConfig = _

  def index(): String = {
    put("projects", entityDao.getAll(classOf[Project]))
    put("semesters", entityDao.getAll(classOf[Semester]))
    val projectId = addToCookie(EnvUtils.PROJECT_ID)
    val semesterId = addToCookie(EnvUtils.SEMESTER_ID)
    put(EnvUtils.PROJECT_ID, projectId)
    put(EnvUtils.SEMESTER_ID, semesterId)
    val person = entityDao.findBy(classOf[User], "code", List(getUserCode))(0)
    put("person", person)
    put("userPhotoUrl", "http://service.urp.sfu.edu.cn/sns/photo/" + Digests.md5Hex(getUserCode() + "@sfu.edu.cn") + ".jpg")
    val menusUrl = Urp.platformBase + "/security/" + UrpApp.name + "/menus.json"
    put("menuProfiles", IOs.readString(new URL(menusUrl).openStream))
    forward()
  }

  def submenus(): String = {
    forward()
  }

  def childmenus() = {
    forward()
  }

  private def addToCookie(name: String): String = {
    val value = get(name)
    value.foreach(projectId => {
      addCookie(name, projectId, Int.MaxValue)
    })
    value.getOrElse(getCookieValue(name))
  }

  def getUserCode(): String = {
    SecurityContext.session.principal.getName
  }

  def logout(): View = {
    sessionRegistry.remove(sessionIdPolicy.getSessionId(request))
    redirect(to(casConfig.casServer + "/logout"), "")
  }
}