package org.openurp.trims.service.impl

import org.openurp.trims.service.CecService
import org.beangle.commons.web.util.HttpUtils
import scala.xml.XML

class CecServiceImpl extends CecService {

  def getUrl(code: String): Option[String] = {
    val cid = getCourseID(code)
    getCourseUrl(cid)
  }

  private def getCourseID(code: String): Option[String] = {
    val url = s"http://cec.shfc.edu.cn/G2S/Showsystem/CourseSearch.aspx?no=${code}"
    val html = HttpUtils.getResponseText(url)
    val xml = XML.loadString(html)
    val nodes = xml \ "DataSource" \ "CourseList" \ "fID"
    if (nodes.isEmpty) {
      None
    } else {
      Some(nodes(0).text)
    }
  }

  private def getCourseUrl(cid: Option[String]): Option[String] = {
    if (cid.isDefined) {
      val url = s"http://cec.shfc.edu.cn/G2S/Showsystem/CourseDetail.aspx?fCourseID=${cid.get}"
      val html = HttpUtils.getResponseText(url)
      val xml = XML.loadString(html)
      val nodes = xml \ "DataSource" \ "CourseList" \ "fID"
      if (nodes.isEmpty) {
        None
      } else {
        Some(s"http://cec.shfc.edu.cn/G2S/Template/View.aspx?action=view&courseType=0&courseId=${nodes(0).text}")
      }
    } else None
  }
}