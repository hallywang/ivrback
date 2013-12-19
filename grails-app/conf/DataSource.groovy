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
      url = "jdbc:mysql://192.168.167.125:3306/wapbase"
      username = "sns"
      password = "emagtestsns"
    }
  }
  test {
    dataSource {
      jndiName = "jdbc/appname"
    }
  }
  production {
    dataSource {
      jndiName = "jdbc/appname"
    }
  }
}