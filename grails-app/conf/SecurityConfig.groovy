security {

  active = true
  cacheUsers = false
  // change to true to use OpenID authentication
  useOpenId = false

  // change to true and specify parameters to use LDAP/ActiveDirectory authentication
  useLdap = false
  //ldapServer = 'ldap://localhost:389' // 'ldap://ad.example.com', 'ldap://monkeymachine:389/dc=acegisecurity,dc=org'
  //ldapManagerDn = 'cn=admin,dc=example,dc=com'
  //ldapManagerPassword = 'secret'
  //ldapSearchBase = 'dc=example,dc=com' // 'ou=users,dc=example,dc=com'
  //ldapSearchFilter = '(uid={0})' //, '(mailNickname={0})'
  //ldapGroupSearchBase = 'ou=groups,dc=example,dc=com'
  //ldapGroupSearchFilter = 'uniquemember={0}'

  algorithm = 'MD5'
  //use Base64 text ( true or false )
  encodeHashAsBase64 = false
  errorPage = null

  /** rememberMeServices */
  cookieName = 'grails_remember_me_gamecms'
  alwaysRemember = false
  tokenValiditySeconds = 1 //设置为最短，也就意味着无法保存用户cookie
  parameter = '_spring_security_remember_me__gamecms'  //默认值都改掉，防止了解acegi的用户自己写个参数提交过来
  rememberMeKey = 'grailsRocks__gamecms'

  /** login user domain class name and fields  */
  loginUserDomainClass = "com.emag.gamecms.domain.system.MagSysUser2"
  userName = 'username'
  password = 'passwd'
  enabled = 'enabled'
  relationalAuthorities = 'authorities'

  afterLogoutUrl = '/'

  /*
     * You can specify method to retrieve the roles. (you need to set relationalAuthorities = null)
     */
  // getAuthoritiesMethod = null //'getMoreAuthorities'

  /**
   * Authority domain class authority field name
   * authorityFieldInList
   */
  authorityDomainClass = "com.emag.gamecms.domain.system.MagSysRole"
  authorityField = 'authority'

  /** use RequestMap from DomainClass  */
  useRequestMapDomainClass = true
  /** Requestmap domain class (if useRequestMapDomainClass = true)  */
  requestMapClass = "com.emag.gamecms.domain.system.MagSysRequestmap"
  requestMapPathField = 'url'
  requestMapConfigAttributeField = 'configAttribute'

  /**
   * if useRequestMapDomainClass is false, set request map pattern in string
   * see example below
   */

  requestMapString = """
		CONVERT_URL_TO_LOWERCASE_BEFORE_COMPARISON
		PATTERN_TYPE_APACHE_ANT

        /magcontentinfo/**=ROLE_ADMIN
		/login/**=IS_AUTHENTICATED_ANONYMOUSLY
		/admin/**=ROLE_USER
	
		/**=IS_AUTHENTICATED_ANONYMOUSLY
	"""

  /**
   * To use email notification for user registration, set the following userMail to
   * true and config your mail settings.Note you also need to run the script
   * grails generate-registration.
   */
  useMail = false
  //mailHost = 'localhost'
  //mailUsername = 'user@localhost'
  //mailPassword = 'sungod'
  //mailProtocol = 'smtp'
  //mailFrom = 'user@localhost'

  /** AJAX request header  */
  //ajaxHeader = 'X-Requested-With'

  /** default user's role for user registration  */
  defaultRole = 'ROLE_USER'

  /** use basicProcessingFilter  */
  basicProcessingFilter = false
  /** use switchUserProcessingFilter  */
  switchUserProcessingFilter = false
}
