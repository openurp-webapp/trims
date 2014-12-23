package org.openurp.trims.security

import org.beangle.commons.codec.digest.Digests
import org.beangle.commons.lang.Strings
import org.beangle.data.jpa.dao.OqlBuilder
import org.beangle.data.model.dao.EntityDao
import org.beangle.security.authc.{ AbstractAccountRealm, Account, AccountStore, AuthenticationToken, BadCredentialsException, DefaultAccount }
import org.openurp.base.Person

class DaoUserStore(entityDao: EntityDao) extends AccountStore {
  def load(principal: Any): Option[Account] = {
    val persons = entityDao.findBy(classOf[Person], "code", List(principal.toString))
    if (persons.isEmpty) return None
    val person = persons(0)
    val account = new DefaultAccount(person.code, person.id)
    account.accountExpired = false
    account.accountLocked = false
    account.credentialExpired = false
    account.category = person.category
    account.disabled = false
    //    account.authorities = user.roles.map(role => new org.beangle.security.authz.Role(role.name))
    Some(account)
  }
}