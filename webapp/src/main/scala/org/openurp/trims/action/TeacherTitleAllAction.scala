package org.openurp.trims.action

import org.beangle.data.jpa.dao.OqlBuilder
import org.openurp.base.Department
import org.beangle.commons.lang.Strings
import org.openurp.teach.lesson.Lesson
import org.openurp.edu.base.Teacher
import org.openurp.base.code.TeacherTitle
import scala.collection.mutable.ListBuffer

class TeacherTitleAllAction extends AbsEamsAction {

  def index(): String = {
    forward()
  }

  def search(): String = {
    val query = OqlBuilder.from(classOf[Teacher], "l")
    query.select("l.department.id, l.title.id, count(*) as num")
    query.where("l.teaching = true")
    query.groupBy("l.department.id, l.title.id")
    query.orderBy("count(*) desc")
    val datas = entityDao.search(query).asInstanceOf[Seq[Array[Any]]]
    val departs = getDepartments()
    val titles = entityDao.getAll(classOf[TeacherTitle])
    val ititles = new collection.mutable.HashSet[String]
    val names = new ListBuffer[String]
    departs.foreach(d => names += (if (d.shortName != null) d.shortName else d.name))
    val values = new ListBuffer[Any]()
    titles.foreach(t => {
      val value = new ListBuffer[Any]()
      value += t.name
      departs.foreach(d => {
        val data = datas.find(o => d.id.equals(o(0)) && t.id.equals(o(1)))
        val num = (if (data.isDefined) { ititles.add(t.name); (data.get)(2) } else 0)
        value += num
      })
      values += value
    })
    put("names", names)
    put("values", values)
    put("titles", ititles)
    forward()
  }

}