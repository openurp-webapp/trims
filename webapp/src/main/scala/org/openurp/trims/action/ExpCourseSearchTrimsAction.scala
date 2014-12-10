package org.openurp.trims.action

import org.beangle.data.jpa.dao.OqlBuilder
import org.openurp.base.Department
import com.sun.org.apache.bcel.internal.generic.ClassObserver
import org.openurp.teach.code.StdType
import org.openurp.teach.code.ExamMode
import org.openurp.base.code.Education
import org.openurp.teach.code.CourseHourType
import org.openurp.teach.code.CourseCategory

class ExpCourseSearchTrimsAction extends CourseSearchTrimsAction {

  //	private LabService labService;

  def expCourses(): String = {
    val query = OqlBuilder.from(classOf[Department], "depart")
    query.where("depart.level=1")
    query.where("depart.teaching=true")
    query.where("depart.enabled=true")
    val departs = entityDao.search(query)
    put("departments", departs)
    val project = getProject()
    put("stdTypes", baseCodeService.getCodes(project, classOf[StdType]))
    put("examModes", baseCodeService.getCodes(project, classOf[ExamMode]))
    put("educationTypes", baseCodeService.getCodes(project, classOf[Education]))
    put("categorys", baseCodeService.getCodes(project, classOf[CourseCategory]))
    put("courseHourTypes", baseCodeService.getCodes(project, classOf[CourseHourType]))
    forward()
  }

  //
  //	def  labs() :String={
  //		val labs = entityService.loadAll(classOf[Lab].class)
  //		put("labs", labs)
  //		return forward()
  //	}

  //	public LabService getLabService() {
  //		return labService
  //	}
  //
  //	public void setLabService(LabService labService) {
  //		this.labService = labService
  //	}

}