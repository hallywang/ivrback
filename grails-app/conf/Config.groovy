import org.apache.log4j.DailyRollingFileAppender

// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if(System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.types = [html: ['text/html', 'application/xhtml+xml'],
        xml: ['text/xml', 'application/xml'],
        text: 'text-plain',
        js: 'text/javascript',
        rss: 'application/rss+xml',
        atom: 'application/atom+xml',
        css: 'text/css',
        csv: 'text/csv',
        all: '*/*',
        json: ['application/json', 'text/json'],
        form: 'application/x-www-form-urlencoded',
        multipartForm: 'multipart/form-data'
]
// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"

// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
grails.gsp.enable.reload=true

// set per-environment serverURL stem for creating absolute links
environments {
    development {
        //grails.serverURL = "http://localhost:8080"
        grails.converters.encoding = "UTF-8"
    }
    test {
        //grails.serverURL = "http://localhost:8080" //todo 上线修改
        grails.converters.encoding = "UTF-8"
    }
    production {
        //grails.serverURL = "http://g.10086.cn" //todo 上线修改
        grails.converters.encoding = "UTF-8"
    }
}

// log4j configuration
log4j = {
    appenders {
        console name: 'stdout', layout: pattern(conversionPattern: '%-d{yyyy-MM-dd HH:mm:ss} %t %c{2} %m%n')

        //记录系统运行日志
        def infoAppender = new DailyRollingFileAppender(name: "logfilesys",
                layout: pattern(conversionPattern: "%-d{yyyy-MM-dd HH:mm:ss} %c{2} %m%n"),
                file: "/apps/service/logs/appback/info.log", datePattern: "'.'yyyy-MM-dd")
        infoAppender.activateOptions()
        appender infoAppender

        //记录系统运行错误日志
        def errorAppender = new DailyRollingFileAppender(name: "stacktrace",
                layout: pattern(conversionPattern: "%-d{yyyy-MM-dd HH:mm:ss} %c{2} %m%n"),
                file: "/apps/service/logs/appback/error.log", datePattern: "'.'yyyy-MM-dd")
        errorAppender.activateOptions()
        appender errorAppender

        // 管理后台重要操作日志记录
        def importantActionAppender = new DailyRollingFileAppender(name: "importantactionloggerfile",
                layout: pattern(conversionPattern: "%-d{yyyy-MM-dd HH:mm:ss} %m%n"),
                file: "/apps/service/logs/appback/importantAction/info.log", datePattern: "'.'yyyy-MM-dd")
        importantActionAppender.activateOptions()
        appender importantActionAppender
    }

    root {
        info 'stdout', 'logfilesys'
        additivity = true
    }

    error 'org.codehaus.groovy.grails.web.servlet',  //  controllers
            'org.codehaus.groovy.grails.web.pages', //  GSP
            'org.codehaus.groovy.grails.web.sitemesh', //  layouts
            'org.codehaus.groovy.grails."web.mapping.filter', // URL mapping
            'org.codehaus.groovy.grails."web.mapping', // URL mapping
            'org.codehaus.groovy.grails.commons', // core / classloading
            'org.codehaus.groovy.grails.plugins', // plugins
            'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
            'org.springframework',
            'org.hibernate'

    warn 'org.mortbay.log'

    environments {
        development {
            warn 'org.hibernate.SQL'
        }
        production {
            warn 'org.hibernate.SQL'
        }
    }

    info logfilesys: "grails.app.service.com.emag.gamecms.service.LogService"
    info importantactionloggerfile: "grails.app.filters.ImportantOperationFilters"
}

fckeditor {
    upload {
        basedir = "/apps/service/resource/cms/fck/"  //文件上传真实路径
        baseurl = "/cms/fck/"              //文件访问的web路径

        overwrite = false
        link {
            browser = true
            upload = false
            allowed = []
            denied = ['html', 'htm', 'php', 'php2', 'php3', 'php4', 'php5', 'phtml', 'pwml', 'inc', 'asp', 'aspx', 'ascx', 'jsp',
                    'cfm', 'cfc', 'pl', 'bat', 'exe', 'com', 'dll', 'vbs', 'js', 'reg',
                    'cgi', 'htaccess', 'asis', 'sh', 'shtml', 'shtm', 'phtm']
        }
        image {
            browser = true
            upload = true
            allowed = ['jpg', 'gif', 'jpeg', 'png']
            denied = []
        }
        flash {
            browser = false
            upload = false
            allowed = ['swf']
            denied = []
        }
        media {
            browser = false
            upload = false
            allowed = ['mpg', 'mpeg', 'avi', 'wmv', 'asf', 'mov']
            denied = []
        }
    }
}

environments {
    development {
        mag.webPath = "/cms/app"              // web访问的目录，即把下面的目录配置成为web虚拟路径
        video.publishPath = "d:/fileupload/resource/cms/app/"   // 文件上传的物理路径
        video.pageDir = "/apps/service/resource/egapp/page/"

        // memcache 服务端ip地址、端口号、连接池数量配置
        memcache.server1.ip = '192.168.167.122'
        memcache.server1.port = '11211'
        memcache.server2.ip = '192.168.167.122'
        memcache.server2.port = '11211'
        memcache.connection.pool.size = 2

        // 后台登录日志邮件发送配置
        loggingmail.file.path = '/apps/service/resource/appback/loggingmail/'

        ip.file.upload.path = "d:/fileupload/resource/appback/ip/"
    }
    test {
        mag.webPath = "/cms/app"            //web访问的目录，即把上面的目录配置成为web虚拟路径

        //文件管理使用
        video.publishPath = "/apps/service/resource/cms/app/"
        video.pageDir = "/apps/service/resource/egapp/page/" //todo 改为实际目录

        // memcache 服务端ip地址、端口号、连接池数量配置
        memcache.server1.ip = '192.168.167.122'
        memcache.server1.port = '11211'
        memcache.server2.ip = '192.168.167.122'
        memcache.server2.port = '11211'
        memcache.connection.pool.size = 2

        // 后台登录日志邮件发送配置
        loggingmail.file.path = '/apps/service/resource/appback/loggingmail/'

        // ip地址导入时文件存放路径
        ip.file.upload.path = "/apps/service/resource/appback/ip/"
    }
    production {
        mag.webPath = "/cms/app"            //web访问的目录，即把上面的目录配置成为web虚拟路径

        //文件管理使用
        video.publishPath = "/apps/service/resource/cms/app/"
        video.pageDir = "/apps/service/resource/egapp/page/" //todo 改为实际目录

        // memcache 服务端ip地址、端口号、连接池数量配置
        memcache.server1.ip = '192.168.49.16'
        memcache.server1.port = '11611'
        memcache.server2.ip = '192.168.49.17'
        memcache.server2.port = '11611'
        memcache.connection.pool.size = 2

        // 后台登录日志邮件发送配置
        loggingmail.file.path = '/apps/service/resource/appback/loggingmail/'

        // ip地址导入时文件存放路径
        ip.file.upload.path = "/apps/service/resource/appback/ip/"
    }
}