package com.emag.gamecms.controllers.ivr

import com.emag.gamecms.domain.ivr.IvrBlackUser
import com.emag.gamecms.domain.ivr.IvrServiceInfo
import org.springframework.dao.DataIntegrityViolationException

class IvrBlackUserController {

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  def index() {
    redirect(action: "list", params: params)
  }

  def batch() {

  }

  def batchSave() {

    String scope = params.scope

    String blackMobiles = params.blackMobiles

    def file = params.blackFile

    int textLine = 0;
    int errorcount = 0;
    int fileLine = 0;
    int errorFileCount = 0;
    StringBuilder errors = new StringBuilder()
    StringBuilder errorsfile = new StringBuilder()


    if (blackMobiles) {
      blackMobiles.split("\r\n").each {
        textLine++
        IvrBlackUser blackUser = new IvrBlackUser()
        blackUser.scope = scope
        blackUser.msisdn = it
        blackUser.save()
        if (blackUser.hasErrors()) {
          errorcount++
          errors.append(textLine)
          errors.append("行：").append(it).append("<br/>")
        }

      }
    }

    if (!file.empty) {
      BufferedReader reader = null;

      String filePath = System.getProperty("java.io.tmpdir")

      File readfile = new File(filePath+System.currentTimeMillis()+".txt")



      try {
        file.transferTo(readfile)
        reader = new BufferedReader(new FileReader(readfile));
        String tempString = null;

        while ((tempString = reader.readLine()) != null) {

          // 显示行号
          fileLine++;
          log.info("line " + fileLine + ": " + tempString);

          IvrBlackUser blackUser = new IvrBlackUser()
          blackUser.scope = scope
          blackUser.msisdn = tempString
          blackUser.save()

          if (blackUser.hasErrors()) {
            errorFileCount++
            errorsfile.append(fileLine)
            errorsfile.append("行：").append(tempString).append("<br/>")
          }


        }
        reader.close();

        readfile.delete()
      } catch (Exception e) {
        log.error(e)
        readfile.deleteOnExit()

      } finally {
        if (reader != null) {
          try {
            reader.close();
            readfile.deleteOnExit()
          } catch (IOException e1) {
          }
        }
      }
    }

    flash.message = "输入框中共${textLine}个号码，成功导入${textLine-errorcount}个，错误:<br/>${errors.toString()}<br/><br/>" +
            "文件中共${fileLine}个号码，成功导入${fileLine-errorFileCount}个，错误:<br/>" +
            "${errorsfile.toString()}"
    redirect(action: "batch", params: params)

  }


  def list(Integer max) {
    params.max = Math.min(max ?: 10, 100)

    def users = IvrBlackUser.createCriteria().list(params) {
      //添加查询条件
      if (params.msisdn) {
        like('msisdn', "%${params.msisdn}%")
      }
      if (params.scope) {
        eq('scope', "${params.scope}")
      }
      if (params.status) {
        eq('status', Integer.parseInt(params.status))
      }
    }

    List serviceList = new ArrayList()
    serviceList.add(['serviceId': '0', 'serviceName': '全局']) //全局
    serviceList.addAll(IvrServiceInfo.list())

    [serviceList: serviceList, ivrBlackUserInstanceList: users, ivrBlackUserInstanceTotal: users.totalCount]
  }

  def create() {
    [ivrBlackUserInstance: new IvrBlackUser(params)]
  }

  def save() {
    def ivrBlackUserInstance = new IvrBlackUser(params)
    if (!ivrBlackUserInstance.save(flush: true)) {
      render(view: "create", model: [ivrBlackUserInstance: ivrBlackUserInstance])
      return
    }

    flash.message = message(code: 'default.created.message', args: [message(code: 'ivrBlackUser.label', default: 'IvrBlackUser'), ivrBlackUserInstance.id])
    redirect(action: "show", id: ivrBlackUserInstance.id)
  }

  def show(Long id) {
    def ivrBlackUserInstance = IvrBlackUser.get(id)
    if (!ivrBlackUserInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'ivrBlackUser.label', default: 'IvrBlackUser'), id])
      redirect(action: "list")
      return
    }

    [ivrBlackUserInstance: ivrBlackUserInstance]
  }

  def edit(Long id) {
    def ivrBlackUserInstance = IvrBlackUser.get(id)
    if (!ivrBlackUserInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'ivrBlackUser.label', default: 'IvrBlackUser'), id])
      redirect(action: "list")
      return
    }

    [ivrBlackUserInstance: ivrBlackUserInstance]
  }

  def update(Long id, Long version) {
    def ivrBlackUserInstance = IvrBlackUser.get(id)
    if (!ivrBlackUserInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'ivrBlackUser.label', default: 'IvrBlackUser'), id])
      redirect(action: "list")
      return
    }

    if (version != null) {
      if (ivrBlackUserInstance.version > version) {
        ivrBlackUserInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                [message(code: 'ivrBlackUser.label', default: 'IvrBlackUser')] as Object[],
                "Another user has updated this IvrBlackUser while you were editing")
        render(view: "edit", model: [ivrBlackUserInstance: ivrBlackUserInstance])
        return
      }
    }

    ivrBlackUserInstance.properties = params

    if (!ivrBlackUserInstance.save(flush: true)) {
      render(view: "edit", model: [ivrBlackUserInstance: ivrBlackUserInstance])
      return
    }

    flash.message = message(code: 'default.updated.message', args: [message(code: 'ivrBlackUser.label', default: 'IvrBlackUser'), ivrBlackUserInstance.id])
    redirect(action: "show", id: ivrBlackUserInstance.id)
  }

  def delete(Long id) {
    def ivrBlackUserInstance = IvrBlackUser.get(id)
    if (!ivrBlackUserInstance) {
      flash.message = message(code: 'default.not.found.message', args: [message(code: 'ivrBlackUser.label', default: 'IvrBlackUser'), id])
      redirect(action: "list")
      return
    }

    try {
      ivrBlackUserInstance.delete(flush: true)
      flash.message = message(code: 'default.deleted.message', args: [message(code: 'ivrBlackUser.label', default: 'IvrBlackUser'), id])
      redirect(action: "list")
    }
    catch (DataIntegrityViolationException e) {
      flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ivrBlackUser.label', default: 'IvrBlackUser'), id])
      redirect(action: "show", id: id)
    }
  }
}
