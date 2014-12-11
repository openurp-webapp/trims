package org.openurp.trims.action

import org.beangle.data.jpa.dao.OqlBuilder
import org.beangle.data.model.Entity
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.teach.core.Project
import org.openurp.teach.lesson.Lesson
import org.openurp.base.Department
import org.beangle.webmvc.entity.action.AbstractRestfulAction

abstract class AbsEamsAction[T <: Entity[_ <: java.io.Serializable]] extends AbstractRestfulAction[T] {

  protected def getProject() = {
    entityDao.get(classOf[Project], new Integer(1))
  }
  
  protected def getDepartments() = {
    entityDao.findBy(classOf[Department], "teaching", Array(true))
  }

  protected def getLessonYears() = {
    val query = OqlBuilder.from(classOf[Lesson], "l")
    query.select("l.semester.schoolYear")
    query.groupBy("l.semester.schoolYear")
    query.orderBy("l.semester.schoolYear desc")
    entityDao.search(query)
  }

}