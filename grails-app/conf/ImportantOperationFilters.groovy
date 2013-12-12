import com.emag.gamecms.domain.log.GameCmsImportantOperation

import org.codehaus.groovy.grails.commons.DomainClassArtefactHandler
import com.emag.gamecms.domain.system.MagSysUser2

/**
 * 将用户重要的操作进行日志记录，例如
 * 1、用户想要删除某对象，在删除操作生效前先记录该对象的详细信息，再执行具体删除操作；
 * 2、用户想要更新某对象，在更新前记录对象原始数据和即将更新后的数据，再执行更新操作；
 * 本过滤器将从后台配置表中获取哪些 controller 中的哪些 action 操作需要做日志记录
 */
class ImportantOperationFilters {
  def authenticateService

  def filters = {
    all(controller: '*', action: '*') {
      before = {
        MagSysUser2 user = (MagSysUser2) authenticateService.userDomain()
        
        for (GameCmsImportantOperation operation: GameCmsImportantOperation.findAll()) {
          if (operation.controllerName == controllerName && actionName) {
            def actions = operation.actionNames.split(',') as List
            if (actions.contains(actionName)) {
              def buff = new StringBuilder()
              buff << '\r\n-----------------before-----------------\r\n'
              buff << "username:${user?.username}\r\n"
              buff << "domain:${operation.domainName}\r\n"
              buff << "id:${params.id}\r\n"
              buff << "controller.action:${controllerName}.${actionName}\r\n"
              if (params.id) {
                // 请求中存在id值，一般来说是更新和删除操作
                try {
                  def domainClass = grailsApplication.getArtefact(DomainClassArtefactHandler.TYPE, operation.domainName).getClazz()
                  def oldObj = domainClass.get(params.id)
                  buff << "old-properties:["
                  oldObj?.properties?.each {
                    if (',metaClass,constraints,attached,log,errors,class,hasMany,belongsTo,mapping,fetchMode,transients,'.indexOf(",${it.key},") < 0) {
                      buff << "${it},"
                    }
                  }
                  buff << "]\r\n"
                } catch (Exception ex) {

                }
              }
              buff << '-----------------before-----------------\r\n'
              log.info buff.toString()

              continue
            }
          }
        }

        return true
      }
      after = {
        MagSysUser2 user = (MagSysUser2) authenticateService.userDomain()
        
        for (GameCmsImportantOperation operation: GameCmsImportantOperation.findAll()) {
          if (operation.controllerName == controllerName && actionName) {
            def actions = operation.actionNames.split(',') as List
            if (actions.contains(actionName)) {
              def buff = new StringBuilder()
              buff << '\r\n-----------------after-----------------\r\n'
              buff << "username:${user?.username}\r\n"
              buff << "domain:${operation.domainName}\r\n"
              buff << "id:${params.id}\r\n"
              buff << "controller.action:${controllerName}.${actionName}\r\n"
              if (params.id) {
                // 参数中存在id值，可能是删除或更新操作
                try {
                  def domainClass = grailsApplication.getArtefact(DomainClassArtefactHandler.TYPE, operation.domainName).getClazz()
                  def newObj = domainClass.get(params.id)
                  buff << "new-properties:["
                  newObj?.properties?.each {
                    if (',metaClass,constraints,attached,log,errors,class,hasMany,belongsTo,mapping,fetchMode,transients,'.indexOf(",${it.key},") < 0) {
                      buff << "${it},"
                    }
                  }
                  buff << "]\r\n"
                } catch (Exception ex) {

                }
              }

              if (actionName == 'save') {
                // 保存操作，记录保存的值得
                buff << "params:${params}\r\n"
              }


              buff << "-----------------after-----------------\r\n"
              log.info buff.toString()

              continue
            }
          }
        }

        return true
      }
      afterView = {
        return true
      }
    }
  }

}
