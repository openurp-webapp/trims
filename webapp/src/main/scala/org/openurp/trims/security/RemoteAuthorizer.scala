package org.openurp.trims.security

import org.beangle.commons.security.Request

class RemoteAuthorizer extends org.beangle.security.authz.Authorizer{

    def isPermitted(principal: Any, request:Request): Boolean={
      !(principal.toString=="anonymous")
    }
}