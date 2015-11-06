package org.openurp.edu.trims.service

trait CecService {

  def getUrl(code: String): Option[String]

}