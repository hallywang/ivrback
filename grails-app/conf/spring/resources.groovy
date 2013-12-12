import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil

// Place your Spring DSL code here
beans = {
    // httpRequestAdapter org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter
//    switch (GrailsUtil.environment) {
//        case GrailsApplication.ENV_DEVELOPMENT:
//            middleDataSource(org.apache.commons.dbcp.BasicDataSource) {
//                println 'Creating development middle datasource'
//                driverClassName = 'oracle.jdbc.driver.OracleDriver'
//                url = 'jdbc:oracle:thin:@192.168.167.125:1521:emagtest'
//                username = 'fileinter'
//                password = 'fileinter'
//            }
//
//            wapDataSource(org.apache.commons.dbcp.BasicDataSource) {
//                println 'Creating development wap datasource'
//                driverClassName = 'com.mysql.jdbc.Driver'
//                url = 'jdbc:mysql://192.168.167.125:3306/manosync'
//                username = 'sns'
//                password = 'emagtestsns'
//            }
//
//            break
//        case GrailsApplication.ENV_TEST:
//            middleDataSource(org.springframework.jndi.JndiObjectFactoryBean) {
//                println 'Creating test middle datasource'
//                jndiName = 'java:comp/env/jdbc/middleoracle'
//            }
//
//            wapDataSource(org.springframework.jndi.JndiObjectFactoryBean) {
//                println 'Creating production wap datasource'
//                jndiName = 'java:comp/env/jdbc/wap'
//            }
//
//            break
//
//        case GrailsApplication.ENV_PRODUCTION:
//            middleDataSource(org.springframework.jndi.JndiObjectFactoryBean) {
//                println 'Creating production middle datasource'
//                jndiName = 'jdbc/middleoracle'
//            }
//
//            wapDataSource(org.springframework.jndi.JndiObjectFactoryBean) {
//                println 'Creating production wap datasource'
//                jndiName = 'jdbc/gamecms'
//            }
//
//            break
//    }


}