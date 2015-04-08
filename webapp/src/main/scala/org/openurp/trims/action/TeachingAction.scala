package org.openurp.trims.action

import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.edu.base.Teacher
import org.beangle.webmvc.api.annotation.mapping
import org.beangle.webmvc.api.annotation.param
import org.beangle.data.jpa.dao.SqlBuilder
import org.beangle.data.model.dao.Query
import org.openurp.edu.teach.lesson.Lesson
import org.beangle.data.jpa.dao.OqlBuilder
import org.openurp.edu.teach.grade.CourseGrade
import java.util.HashMap
import scala.collection.mutable.ListBuffer
import org.openurp.hr.base.Staff
import org.openurp.people.base.Person

/**
 * 教学情况
 */
class TeachingAction extends RestfulAction[Person] {
  /**
   * 上课情况
   */
  @mapping(value = "lesson/{id}")
  def lessonIndex(@param("id") id: String): String = {
    val years = getSchoolYears(id)
    val curYear = get("year").getOrElse(if (years.length > 0) years(0).asInstanceOf[Array[Any]](0) else null).asInstanceOf[String]
    lesson(id, curYear)
  }

  @mapping(value = "lesson/{id}/{year}")
  def lesson(@param("id") id: String, @param(value="year",required=false) curYear: String): String = {
    val staff = entityDao.get(classOf[Staff], new java.lang.Long(id))
    val years = getSchoolYears(id)
    var curYear = get("year").getOrElse(if (years.length > 0) years(0).asInstanceOf[Array[Any]](0) else null)
    if (curYear == "0") curYear = null 
    put("id", id)
    put("curYear", curYear)
    put("years", years)
    put("staff", staff)
    val builder = OqlBuilder.from(classOf[Lesson], "lesson")
    builder.join("lesson.teachers", "t")
    builder.where("t.staff=:staff", staff).where("lesson.semester.schoolYear=:curYear", curYear)
    val lessons = entityDao.search(builder)
    put("lessons", lessons)
    forward()
  }
  /**
   * 教师平均课时折线图
   */
  def lessonLine(): String = {
    val id = get("id").get
    val staff = entityDao.get(classOf[Staff], new java.lang.Long(id))
    put("staff", staff)
    val sql = s"""select  s.code, sum(c.period)
            from edu_teach.lessons l 
            join edu_teach.lessons_teachers lt on lt.lesson_id=l.id 
            join base.semesters s on l.semester_id = s.id 
            join edu_base.courses c on c.id = l.course_id
            join edu_base.teachers t on t.id = lt.teacher_id
            where t.id = ${id} 
            group by s.code"""
    val query = SqlBuilder.sql(sql)
    val datas = entityDao.search(query)
    var sum = 0
    for (i <- 0 until datas.size) {
      sum = sum + new Integer(datas(i).asInstanceOf[Array[Any]](1).toString)
    }
    val avg = if(datas.size == 0) 0 else (sum / datas.size)
    put("avg", avg)
    put("datas", datas)
    forward()
  }

  def nav(): String = {
    val id = get("id").get
    put("id", id)
    val staff = entityDao.get(classOf[Staff], new java.lang.Long(id))
    put("person", staff.person)
    forward()
  }

  private def getSchoolYears(id: String): Seq[Any] = {
    val sql = s"""select s.school_year, count(*)
  		from edu_teach.lessons l join edu_teach.lessons_teachers lt on l.id = lt.lesson_id 
  		join base.semesters s on l.semester_id = s.id
        join edu_base.teachers t on t.id=lt.teacher_id
      join hr_base.staffs f on t.staff_id = f.id
      where f.id = ${id}
  		group by s.school_year
  		order by s.school_year"""
    val query = SqlBuilder.sql(sql)
    entityDao.search(query)
  }

  /**
   * 成绩及格率
   */
  @mapping(value = "grade/{id}")
  def grade(@param("id") id: String): String = {
    lessonIndex(id)
    val curYear = request.getAttribute("curYear").asInstanceOf[String]
    putExamGrade(id, curYear)
    putGaGrade(id, curYear)
    forward()

  }
  //期末成绩examGrade.end
  private def putExamGrade(id: String, curYear: String) {
    val sql = s"""select id,score,count(*) from
			(select l.id, case 
			when cg.score>=60 then 1
			else 0 end  score
			from edu_teach.course_grades cg
			join edu_teach.exam_grades eg on eg.course_grade_id = cg.id and eg.grade_type_id=2
			join edu_teach.lessons l on cg.lesson_id=l.id
    		join base.semesters s on l.semester_id = s.id
			join edu_teach.lessons_teachers lt on lt.lesson_id = l.id
            join edu_base.teachers t on t.id = lt.teacher_id
            where t.id = ${id} and s.school_year='${curYear}'
			 ) t group by id,score"""
    val query = SqlBuilder.sql(sql)
    val examGrades = entityDao.search(query)
    val examTotalMap: Map[String, Int] = getTotalMap(examGrades)
    put("examGradesMap", getGradeMap(examGrades))
    put("examTotalMap", examTotalMap)
  }

  //总评成绩gaGrade.endGa
  private def putGaGrade(id: String, curYear: String) {
    val sql = s"""select id,score,count(*) from
			(select l.id, case 
			when cg.score>=60 then 1
			else 0 end  score
			from edu_teach.course_grades cg
			join edu_teach.ga_grades gg on gg.course_grade_id = cg.id and gg.grade_type_id=7
			join edu_teach.lessons l on cg.lesson_id=l.id
    		join base.semesters s on l.semester_id = s.id
			join edu_teach.lessons_teachers lt on lt.lesson_id = l.id
            join edu_base.teachers t on t.id = lt.teacher_id
            where t.id = ${id} and s.school_year='${curYear}'
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

  /**
   * 评教情况
   */
  @mapping(value = "quality/{id}")
  def quality(@param("id") id: String): String = {
    val sql = s"""select s.school_year, c.id, c.name, q.score
    from edu_quality.lesson_questionnaire_stats q
    join edu_teach.lessons l on l.id = q.lesson_id
    join edu_base.courses c on c.id = l.course_id
    join base.semesters s on s.id = l.semester_id 
    join edu_teach.lessons_teachers lt on l.id = lt.lesson_id 
    join edu_base.teachers t on t.id = lt.teacher_id
    where t.id = ${id}"""
    val query = SqlBuilder.sql(sql)
    val datas = entityDao.search(query)
    val yearSet = new collection.mutable.HashSet[String]
    val lessonIdSet = new collection.mutable.HashSet[String]
    val lessonNameMap = new collection.mutable.HashMap[String, String]
    datas.foreach(d => {
      val data = d.asInstanceOf[Array[Any]]
      yearSet += data(0).toString
      lessonIdSet += data(1).toString
      lessonNameMap.put(data(1).toString, data(2).toString)
    })
    val names = yearSet.toList.sortBy(year => year)
    val lessonId = lessonIdSet.toList.sortBy(id => id)
    val values = new ListBuffer[Any]
    lessonId.foreach(lid => {
      val value = new ListBuffer[Any]
      value += lessonNameMap(lid)
      names.foreach(year => {
        val score = datas.find(data => data.asInstanceOf[Array[Any]](0).equals(year) && data.asInstanceOf[Array[Any]](1).toString.equals(lid) )
        if(score.isDefined){
          value += score.get.asInstanceOf[Array[Any]](3)
        }else{
          value += '-'
        }
      })
      values += value
    })
    put("names", names)
    put("values", values)
    put("titles", lessonNameMap.values)
    forward()
  }

  /**
   * 评教情况
   */
  @mapping(value = "quality/year/{id}")
  def qualityYear(@param("id") id: String): String = {
    lessonIndex(id)
    val curYear = request.getAttribute("curYear")
    val sql = s"""select q.lesson_id,q.score
		from edu_quality.lesson_questionnaire_stats q
		join edu_teach.lessons l on l.id = q.lesson_id
		join base.semesters s on s.id = l.semester_id 
		join edu_teach.lessons_teachers lt on l.id = lt.lesson_id 
        join edu_base.teachers t on t.id = lt.teacher_id
        where t.id = ${id} and s.school_year='${curYear}'"""
    val query = SqlBuilder.sql(sql)
    val datas = entityDao.search(query)
    val lessonScoreMap = getLessonScoreMap(datas)
    put("lessonScoreMap", lessonScoreMap)
    forward()
  }

  private def getLessonScoreMap(datas: Seq[Any]): Map[String, Float] = {
    val map = new collection.mutable.HashMap[String, Float]
    datas.foreach(d => {
      val data = d.asInstanceOf[Array[Any]]
      map.put(data(0).toString, data(1).asInstanceOf[Float])
    })
    map.toMap
  }
}