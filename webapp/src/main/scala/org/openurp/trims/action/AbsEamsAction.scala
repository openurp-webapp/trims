package org.openurp.trims.action

import org.beangle.webmvc.entity.action.RestfulAction
import org.beangle.data.model.Entity
import org.openurp.teach.core.model.ProjectBean
import org.openurp.teach.core.Project

abstract class AbsEamsAction[T <: Entity[_ <: java.io.Serializable]] extends RestfulAction[T] {
  
  protected def getProject() = {
    entityDao.get(classOf[Project], new Integer(1))
  }

}