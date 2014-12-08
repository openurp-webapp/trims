package org.openurp.trims.action

import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.teach.core.Course
import org.openurp.trims.service.CecService

class CourseAction extends RestfulAction[Course] {

  var cecService: CecService = _

  override def search(): String = {
    val list = entityDao.search(getQueryBuilder())
    val map = new collection.mutable.HashMap[String, String]
    list.foreach(c => {
      val url = cecService.getUrl(c.code)
      map.put(c.id.toString(), url.orNull)
    })
    put(shortName + "s", list)
    put("urlmap", map)
    forward()
  }

}