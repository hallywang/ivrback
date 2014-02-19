dataSource {
  pooled = true
  driverClassName = "com.mysql.jdbc.Driver"
  username = "root"
  password = "123456"
  logSql = false
}

hibernate {
  cache.use_second_level_cache = true
  cache.use_query_cache = true
  cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}

// environment specific settings
environments {
  development {
    dataSource {
      dbCreate = "update" // one of 'create', 'create-drop','update'
      url = "jdbc:mysql://192.168.167.125:3306/wapbase?useunicode=true&characterEncoding=UTF-8"
      username = "sns"
      password = "emagtestsns"
    }
  }
  test {
    dataSource {
      jndiName = "java:comp/env/jdbc/ivrback"
    }
  }
  production {
    dataSource {
      jndiName = "java:comp/env/jdbc/ivrback"
    }
  }
}