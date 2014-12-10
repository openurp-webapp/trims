package org.openurp.trims.action

import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.base.Teacher
import org.beangle.webmvc.api.annotation.mapping
import org.beangle.webmvc.api.annotation.param
import org.beangle.data.jpa.dao.SqlBuilder
import org.beangle.data.model.dao.Query
import org.openurp.teach.lesson.Lesson
import org.beangle.data.jpa.dao.OqlBuilder
import org.openurp.teach.grade.CourseGrade

/**
 * 教学情况
 */
class TeachingAction extends RestfulAction[Teacher] {
  /**
   * 上课情况
   */
  @mapping(value = "lesson/{id}")
  def lesson(@param("id") id: String): String = {
    val years = getSchoolYears(id)
    val curYear = get("year").getOrElse(if (years.length > 0) years(0).asInstanceOf[Array[Any]](0) else null).asInstanceOf[String]
    lesson(id, curYear)
  }

  @mapping(value = "lesson/{id}/{year}")
  def lesson(@param("id") id: String, @param("year") curYear: String): String = {
    val teacher = entityDao.get(classOf[Teacher], new Integer(id))
    val years = getSchoolYears(id)
    val curYear = get("year").getOrElse(if (years.length > 0) years(0).asInstanceOf[Array[Any]](0) else null)
    put("id", id)
    put("curYear", curYear)
    put("years", years)
    put(shortName, teacher)
    val builder = OqlBuilder.from(classOf[Lesson], "lesson")
    builder.join("lesson.teachers", "t")
    builder.where("t=:teacher", teacher).where("lesson.semester.schoolYear=:curYear", curYear)
    val lessons = entityDao.search(builder)
    put("lessons", lessons)
    forward()
  }

  def nav(): String = {
    val id = get("id").get
    put("id", id)
    val teacher = entityDao.get(classOf[Teacher], new Integer(id))
    put(shortName, teacher)
    forward()
  }

  private def getSchoolYears(id: String): Seq[Any] = {
    val sql = s"""select s.school_year, count(*)
		from teach.lessons l join teach.lessons_teachers lt on l.id = lt.lesson_id 
		join base.semesters s on l.semester_id = s.id
		where lt.teacher_id = ${id}
		group by s.school_year"""
    val query = SqlBuilder.sql(sql)
    entityDao.search(query)
  }
  
  /**
   * 成绩及格率
   */
  @mapping(value = "grade/{id}")
  def grade(@param("id") id: String): String = {
    lesson(id)
    putExamGrade()
    putGaGrade()
    forward()

  }
  //期末成绩examGrade.end
  private def putExamGrade() {
    val sql = s"""select id,score,count(*) from
			(select l.id, case 
			when cg.score>=60 then 1
			else 0 end  score
			from teach.course_grades cg
			join teach.exam_grades eg on eg.course_grade_id = cg.id and eg.grade_type_id=2
			join teach.lessons l on cg.lesson_id=l.id
    		join base.semesters s on l.semester_id = s.id
			join teach.lessons_teachers lt on lt.lesson_id = l.id
			where lt.teacher_id=10
			 ) t group by id,score"""
    val query = SqlBuilder.sql(sql)
    val examGrades = entityDao.search(query)
    val examTotalMap: Map[String, Int] = getTotalMap(examGrades)
    put("examGradesMap", getGradeMap(examGrades))
    put("examTotalMap", examTotalMap)
  }
  
  //总评成绩gaGrade.endGa
  private def putGaGrade() {
    val sql = s"""select id,score,count(*) from
			(select l.id, case 
			when cg.score>=60 then 1
			else 0 end  score
			from teach.course_grades cg
			join teach.ga_grades gg on gg.course_grade_id = cg.id and gg.grade_type_id=7
			join teach.lessons l on cg.lesson_id=l.id
    		join base.semesters s on l.semester_id = s.id
			join teach.lessons_teachers lt on lt.lesson_id = l.id
			where lt.teacher_id=10
			 ) t group by id,score"""
    val query = SqlBuilder.sql(sql)
    val gaGrades = entityDao.search(query)
    val gaTotalMap: Map[String, Int] = getTotalMap(gaGrades)
    put("gaGradesMap", getGradeMap(gaGrades))
    put("gaTotalMap", gaTotalMap)
  }
  
  //通过课程id和成绩得到相应的课程数量
  private def getGradeMap(datas: Seq[Any]): Map[String, Int] = {
    val map = new collection.mutable.HashMap[String, Int]
    datas.foreach(d => {
      val data = d.asInstanceOf[Array[Any]]
      map.put("" + data(0) + data(1), new Integer(data(2).toString).intValue())
    })
    map.toMap
  }
  
  //通过课程id得到相应的课程总数
  private def getTotalMap(datas: Seq[Any]): Map[String, Int] = {
    val map = new collection.mutable.HashMap[String, Int]
    datas.foreach(d => {
      val data = d.asInstanceOf[Array[Any]]
      val key = data(0).toString
      if (!map.contains(key)) {
        map.put(key, 0)
      }
      val total = map(key)
      map.put(key, total + new Integer(data(2).toString).intValue())
    })
    map.toMap
  }
}