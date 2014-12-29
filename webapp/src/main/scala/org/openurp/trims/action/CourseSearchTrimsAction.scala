package org.openurp.trims.action

import org.openurp.edu.teach.Course
import org.beangle.webmvc.entity.action.RestfulAction
import org.beangle.data.jpa.dao.OqlBuilder
import org.openurp.base.Department
import org.beangle.data.model.dao.Condition
import org.openurp.edu.base.code.service.BaseCodeService
import org.openurp.edu.base.code.StdType
import org.openurp.edu.teach.code.ExamMode
import org.openurp.base.code.Education
import org.openurp.edu.teach.code.CourseCategory
import org.openurp.edu.teach.code.CourseHourType
import org.beangle.webmvc.entity.helper.QueryHelper
import org.beangle.commons.lang.Strings
import org.openurp.edu.teach.lesson.Lesson
import org.beangle.commons.collection.Order
import org.openurp.edu.teach.plan.PlanCourse
import org.openurp.edu.teach.plan.CoursePlan
import org.openurp.edu.teach.plan.MajorPlan
import org.openurp.edu.base.Teacher
import org.openurp.trims.service.CecService

class CourseSearchTrimsAction extends AbsEamsAction[Course] {

  var baseCodeService: BaseCodeService = _

  var cecService: CecService = _

  def index(): String = {
    val query = OqlBuilder.from(classOf[Department], "depart")
    query.where(new Condition("depart.parent is null"))
    query.where(new Condition("depart.teaching=true"))
//    query.where(new Condition("depart.enabled=true"))
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

  /**
   * 查找课程
   */
  def search(): String = {
    //    super.search()
    val project = getProject()
    val entityQuery = OqlBuilder.from(classOf[Course], "course")
    entityQuery.where("course.enabled=true")
    QueryHelper.populateConditions(entityQuery)
    put("courses", entityDao.search(getQueryBuilder()))
    put("courseHourTypes", baseCodeService.getCodes(project, classOf[CourseHourType]))

    put("type", get("type").orNull)
    return forward()
  }

  /**
   * 课程详情
   *
   * @throws Exception
   */
  def detail(): String = {
    val type_ = get("type").getOrElse("")

    if (Strings.isEmpty(type_)) {
      detailDefault()
    } else if (type_.equals("detailInMajor")) {
      detailInMajor()
    } else if (type_.equals("detailInOutline")) {
      detailInOutline()
    } else if (type_.equals("detailInCourseware")) {
      detailInCourseware()
    }

    //    val outlineQuery = OqlBuilder(classOf[TeachOutline], "outline")
    //    outlineQuery.add(new Condition("outline.course.id=:id", getLong("course.id")))
    //    outlineQuery.add(new Condition("outline.isPassed=:isPassed", Boolean.TRUE))
    //    outlineQuery.addOrder(new Order("outline.updateAt", false))
    //    List outlines = entityDao.search(outlineQuery)
    //    put("outlines", outlines)

    val project = getProject()
    put("courseHourTypes", baseCodeService.getCodes(project, classOf[CourseHourType]))
    put("type", type_)
    val courseid = get("course.id").get
    val course = getModel[Course](entityName,convertId(courseid))
    put("courseSiteUrl", cecService.getUrl(course.code).orNull)
    put(shortName, course)
    return forward()
  }

  private def detailDefault() {
//    val teachObjectsQuery = OqlBuilder.from(classOf[Lesson], "task")
//    teachObjectsQuery.select("select distinct major, majorField")
//    teachObjectsQuery.join("task.teachClass.major", "major")
//    teachObjectsQuery.join("left outer", "task.teachClass.majorField", "majorField")
//    teachObjectsQuery.where(new Condition("task.course.id=:courseId", getLong("course.id")))
//    teachObjectsQuery.where(new Condition("major.department.college=true"))
//    teachObjectsQuery.where(new Condition("major.enabled=true"))
//    teachObjectsQuery.orderBy(new Order("major.code", true))
//    teachObjectsQuery.orderBy(new Order("majorField.code", false))

    val teacherQuery = OqlBuilder.from(classOf[Lesson], "task")
    teacherQuery.select("select distinct teacher")
    teacherQuery.join("task.teachers", "teacher")
    teacherQuery.where(new Condition("task.course.id=:courseId", getInt("course.id").get))
//    teacherQuery.where(new Condition("teacher.virtual=:virtual", false))

//    val textBookQuery = OqlBuilder.from(classOf[Lesson], "task")
//    textBookQuery.select("select distinct textbook")
//    textBookQuery.join("task.requirement.textbooks", "textbook")
//    textBookQuery.where(new Condition("task.course.id =(:ids)",
//      getLong("course.id")))
//    textBookQuery.orderBy(new Order("textbook.name"))
//    val textbooks = entityDao.search(textBookQuery)

//    put("readings", getReadingBooks(getInt("course.id").get))
//    put("teachObjects", entityDao.search(teachObjectsQuery))
    put("teachers", entityDao.search(teacherQuery))
//    put("textbooks", textbooks)
  }

  private def detailInMajor() {
    val planCourse = entityDao.get(
      classOf[PlanCourse], new java.lang.Long(getLong("planCourse.id").get))
    val teachPlan = entityDao.get(classOf[MajorPlan], new Integer(getInt("teachPlan.id").get))

    val teacherQuery = OqlBuilder.from(classOf[Lesson], "task")
    teacherQuery.select("select distinct teacher")
    teacherQuery.join("task.teachers", "teacher")
    teacherQuery.where(new Condition("task.teachClass.major.id=:majorId", teachPlan.program.major.id))
    // MajorField is removed 
    //    if (teachPlan.getMajorField() == null)
    //      teacherQuery
    //          .add(new Condition("task.teachClass.majorField is null"))
    //    else
    //      teacherQuery.where(new Condition(
    //          "task.teachClass.majorField.id=:majorFieldId", teachPlan
    //              .getMajorField().getId()))
    teacherQuery.where(new Condition("task.course.id=:courseId", planCourse.course.id))
    teacherQuery.where(new Condition("teacher.virtual=:virtual", false))

    val textBookQuery = OqlBuilder.from(classOf[Lesson], "task")
    textBookQuery.select("select distinct textbook")
    textBookQuery.join("task.requirement.textbooks", "textbook")
    textBookQuery.where(new Condition("task.course.id =(:ids)", planCourse.course.id))
    textBookQuery.orderBy(new Order("textbook.name"))

    val textbooks = entityDao.search(textBookQuery)
    put("textbooks", textbooks)

//    put("readings", getReadingBooks(planCourse.course.id))
    put("teachers", entityDao.search(teacherQuery))
    put("planCourse", planCourse)
    put("teachPlan", teachPlan)
    val project = getProject()
    put("courseHourTypes", baseCodeService.getCodes(project, classOf[CourseHourType]))
  }

  private def detailInOutline() {
    val courseid = get("course.id")
    super.info(courseid.get)
    val teachObjectsQuery = OqlBuilder.from(classOf[Lesson], "task")
    teachObjectsQuery.select("select distinct major, majorField")
    teachObjectsQuery.join("task.teachClass.major", "major")
    teachObjectsQuery.join("left outer", "task.teachClass.majorField",
      "majorField")
    teachObjectsQuery.where(new Condition("task.course.id=:courseId",
      getLong("course.id")))
    teachObjectsQuery.where(new Condition("major.department.college=true"))
    teachObjectsQuery.where(new Condition("major.enabled=true"))
    teachObjectsQuery.orderBy(new Order("major.code", true))
    teachObjectsQuery.orderBy(new Order("majorField.code", false))

    val textBookQuery = OqlBuilder.from(classOf[Lesson], "task")
    textBookQuery.select("select distinct textbook")
    textBookQuery.join("task.requirement.textbooks", "textbook")
    textBookQuery.where(new Condition("task.course.id =(:ids)",
      getLong("course.id")))
    textBookQuery.orderBy(new Order("textbook.name"))
    val textbooks = entityDao.search(textBookQuery)

//    put("readings", getReadingBooks(getInt("course.id").get))
    put("textbooks", textbooks)
    put("teachObjects", entityDao.search(teachObjectsQuery))
  }

//  private def getReadingBooks(courseId: Int): Seq[ReadingBook] = {
//    val readingQuery = OqlBuilder.from(classOf[ReadingBook], "reading")
//    readingQuery.select("select distinct reading")
//    readingQuery.join("reading.courses", "course")
//    readingQuery.where(new Condition("course.id=:id", courseId))
//    entityDao.search(readingQuery)
//  }

  private def detailInCourseware(): String = {
    val courseid = getInt("course.id")
    val course = entityDao.get(classOf[Course], new java.lang.Integer(courseid.get))
    put("name", (course.name.trim()))
    //    val coursewares = entityDao.load(classOf[Courseware], "course.id", courseid)   
    //    put("coursewares", coursewares)    
    return forward()
  }

}