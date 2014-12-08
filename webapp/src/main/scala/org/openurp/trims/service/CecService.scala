package org.openurp.trims.service

trait CecService {

  def getUrl(code: String): Option[String]

}