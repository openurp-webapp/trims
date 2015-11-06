package org.openurp.edu.trims.action

import org.beangle.data.dao.OqlBuilder
import org.openurp.hr.base.model.Staff
import org.openurp.code.job.model.ProfessionalTitle
import scala.collection.mutable.ListBuffer

class TeacherTitleAllAction extends AbsEamsAction {

  def index(): String = {
    forward()
  }

  def search(): String = {
    val teaching = getBoolean("teaching")
    val query = OqlBuilder.from(classOf[Staff], "staff")
    query.select("staff.post.head.department.id, staff.post.head.title.id, count(*) as num")
    query.where("staff.state.id = 1")
    if(teaching.isDefined){
      query.where(s"staff.post.head.department.teaching = ${teaching.get}")
    }
    query.where("staff.post.head.title.id is not null")
    query.groupBy("staff.post.head.department.id, staff.post.head.title.id")
    query.orderBy("count(*) desc")
    val datas = entityDao.search(query).asInstanceOf[Seq[Array[Any]]]
    val titles = entityDao.getAll(classOf[ProfessionalTitle])
    val ititles = new collection.mutable.HashSet[String]
    val idsSet = new collection.mutable.HashSet[Integer]
    datas.foreach(d => {
      idsSet += d(0).asInstanceOf[Integer]
    })
    val ids = idsSet.toList
    val names = new ListBuffer[String]
    val departs = getDepartmentMap()
    ids.foreach(id =>{
      names += departs(id.toString())
    })
    val values = new ListBuffer[Any]()
    titles.foreach(t => {
      val value = new ListBuffer[Any]()
      value += t.name
      var sum = 0L
      ids.foreach(id => {
        val data = datas.find(o => 
          id.equals(o(0)) && t.id.equals(o(1)))
        val num = (if (data.isDefined) { 
          ititles.add(t.name)
          (data.get)(2).asInstanceOf[java.lang.Long]
        } else new java.lang.Long(0))
        value += num
        sum += num
      })
      if(sum > 0) values += value
    })
    put("names", names)
    put("values", values)
    put("titles", ititles)
    forward()
  }

}