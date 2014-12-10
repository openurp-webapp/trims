package org.openurp.trims.action

import org.openurp.teach.core.Major
import scala.collection.immutable.TreeMap
import org.beangle.data.jpa.dao.OqlBuilder
import org.openurp.teach.plan.MajorPlan
import org.openurp.teach.core.Major
import org.openurp.base.Department
import org.beangle.webmvc.entity.helper.QueryHelper
import javax.security.auth.Subject
import org.openurp.teach.code.service.BaseCodeService
import scala.collection.mutable.ListBuffer

class MajorSearchTrimsAction extends AbsEamsAction[Major] {

  var baseCodeService: BaseCodeService = _

  override def index(): String = {
    //		val s :String=getSystemConfig().getItems().get("trims.url.nation_master")//获取全国硕博士点设置的后台维护
//    		put("nation_master_url",s)
    forward()
  }

  /**
   * 学校专业
   *
   * @return
   */
  def majorsOfSchool(): String = {

    val hql = new StringBuilder()
    hql
      .append("select distinct plan.program.department, plan.program.major, majorField\n")
      .append(" from " + classOf[MajorPlan].getName() + " plan \n")
      .append(
        " left outer join plan.majorField majorField where plan.isConfirm=true\n")
      .append(
        " order by plan.department.code, major.code, majorField.code desc")
    val res = entityDao.search(OqlBuilder.oql[Department](hql.toString()))
    val departMajorFieldMap = new collection.mutable.HashMap[String, ListBuffer[Object]]
    for (i <- 0 until res.size) {
      val data = res(i).asInstanceOf[Array[Any]]
      val depart = data(0).asInstanceOf[Department]
      val major = data(1).asInstanceOf[Major]
      //val majorField =data(2).asInstanceOf[MajorField]
      val majorField = null
      if (departMajorFieldMap.get(depart.code) == null) {
        departMajorFieldMap.put(depart.code, new ListBuffer[Object])
      }
      departMajorFieldMap.get(depart.code).get ++= Array(depart, major, majorField)
    }

    put("departMajorFieldMap", departMajorFieldMap)
    put("sum", res.size)
    put("type", get("type"))
    forward()
  }

  /**
   * 学校硕士、博士点设置
   *
   * @return
   */
//  def shuoboOfSchool(): String = {
//    	  val query = OqlBuilder.from(classOf[ShuoBoDian],"shuoBoDian")
//    		QueryHelper.populateConditions(query)
//    		query.setSelect("distinct shuoBoDian.grade")
//    		query.addOrder(new Order("shuoBoDian.grade", false))
//    		val grades = entityDao.search(query)
//    		put("grades", grades)
//    forward()
//  }
//
//  def shuoboOfSchoolInfo(): String = {
//    	  val query = OqlBuilder.from(classOf[ShuoBoDian],"shuoBoDian")
//            QueryHelper.populateConditions(query)
//            query.setLimit(getPageLimit())       
//            query.addOrder(new Order("shuoBoDian.department.code",true))
//            val shuobodians = entityDao.search(query)
//
//            val objArray=shuobodians.toArray()
//            val sbdmap = new collection.mutable.HashMap[String, List[ShuoBoDian]]
//            for (i <- 0 until objArray.length ) {
//            	val s=objArray(i)
//            	if (sbdmap.get(s.getDepartment().getCode()) == null) {
//    				sbdmap.put(s.getDepartment().getCode(),
//    						new ArrayList<ShuoBoDian>())
//    			}			
//    				sbdmap.get(s.getDepartment().getCode()).add(s)
//    		} 
//            put("shuobodiansmap",sbdmap)
//    forward()
//  }

  /**
   * 全国专业 首页
   *
   * @return
   */
//  def majorsOfNation(): String = {
//    val query = OqlBuilder.from(classOf[Subject], "subject")
//    query.where("subject.category, subject")
//    query.orderBy("subject.category.code")
//    query.orderBy("subject.code")
//    put("type", get("type"))
//    val project = getProject()
//        		put("subjectCategories", baseCodeService.getCodes(project, classOf[SubjectCategory])
//        		put("categorySubjects",entityDao .search(query))
//    forward()
//  }

  /**
   * 查询全国专业设置
   *
   * @return
   */
//  def majorsOfNationSearch(): String = {
//    put("type", get("type"))
//    		put("majors", entityDao.search(buildNationMajorQuery()))
//    return forward()
//  }

  /**
   * 某个全国专业的开设学校的信息
   *
   * @return
   */
  //	def  establishInfosOfNationMajor() :String={
  //		val nationMajor =  entityDao.get(classOf[NationMajor], getLong("major.id"))
  //		put("major", nationMajor)
  //		put("establishInfos", nationMajor.getEstablishInfos())
  //		forward()
  //	}

  //	def  descriptionOfNationMajor() :String={
  //		val nationMajor =  entityDao.get(classOf[NationMajor], getLong("major.id"))
  //		put("major", nationMajor)
  //		forward()
  //	}

  /**
   * 全国硕士、博士点
   *
   * @return
   */
//  def shuoboOfNation(): String = {
//    forward()
//  }

  /**
   * 查询开始某个专业的所有高校
   *
   * @return
   */
//  def schoolsOfMajor(): String = {
//    forward()
//  }

  /**
   * 查询全国专业用的
   *
   * @return
   */
  //	def  buildNationMajorQuery():OqlBuilder[NationMajor]= {
  //		val query = OqlBuilder.from(classOf[NationMajor],"major")
  //		QueryHelper.populateConditions(query)
  //		query.limit(getPageLimit())
  //		query.orderBy("major.code")
  //		query
  //	}
}