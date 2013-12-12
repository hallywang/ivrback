package com.emag.gamecms.controllers.system

import org.codehaus.groovy.grails.commons.ConfigurationHolder
import com.vivame.util.TimeUtil
import com.vivame.util.StringUtil
import com.emag.gamecms.domain.system.GameCmsIp

class GameCmsIpController {

  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

  def grailsApplication

  def index = {
    redirect(action: "list", params: params)
  }

  def list = {
    params.max = Math.min(params.max ? params.int('max') : 10, 100)
    def gameCmsIpInstanceList = GameCmsIp.createCriteria().list(params) {
      if (params.ip) {
        like('ip', "%${params.ip.trim()}%")
      }
      if (params.flag) {
        eq('flag', params.int('flag'))
      }
    }

    [gameCmsIpInstanceList: gameCmsIpInstanceList, gameCmsIpInstanceTotal: gameCmsIpInstanceList.totalCount]
  }

  def create = {
    def gameCmsIpInstance = new GameCmsIp()
    gameCmsIpInstance.properties = params
    return [gameCmsIpInstance: gameCmsIpInstance]
  }

  def save = {
    def gameCmsIpInstance = new GameCmsIp(params)
    if (gameCmsIpInstance.save(flush: true)) {
      flash.message = "${message(code: 'default.created.message', args: [message(code: 'gameCmsIp.label', default: 'GameCmsIp'), gameCmsIpInstance.id])}"

      redirect(action: "show", id: gameCmsIpInstance.id)
    }
    else {
      render(view: "create", model: [gameCmsIpInstance: gameCmsIpInstance])
    }
  }

  def show = {
    def gameCmsIpInstance = GameCmsIp.get(params.id)


    if (!gameCmsIpInstance) {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsIp.label', default: 'GameCmsIp'), params.id])}"
      redirect(action: "list")
    }
    else {

      [gameCmsIpInstance: gameCmsIpInstance]
    }
  }

  def edit = {
    def gameCmsIpInstance = GameCmsIp.get(params.id)
    if (!gameCmsIpInstance) {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsIp.label', default: 'GameCmsIp'), params.id])}"
      redirect(action: "list")
    }
    else {
      return [gameCmsIpInstance: gameCmsIpInstance]
    }
  }

  def update = {
    def gameCmsIpInstance = GameCmsIp.get(params.id)
    if (gameCmsIpInstance) {
      if (params.version) {
        def version = params.version.toLong()
        if (gameCmsIpInstance.version > version) {

          gameCmsIpInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'gameCmsIp.label', default: 'GameCmsIp')] as Object[], "Another user has updated this GameCmsIp while you were editing")
          render(view: "edit", model: [gameCmsIpInstance: gameCmsIpInstance])
          return
        }
      }
      gameCmsIpInstance.properties = params

      // 给修改时间赋值
      gameCmsIpInstance.updateDate = new Date()
      if (!gameCmsIpInstance.hasErrors() && gameCmsIpInstance.save(flush: true)) {
        flash.message = "${message(code: 'default.updated.message', args: [message(code: 'gameCmsIp.label', default: 'GameCmsIp'), gameCmsIpInstance.id])}"
        redirect(action: "show", id: gameCmsIpInstance.id)
      }
      else {
        render(view: "edit", model: [gameCmsIpInstance: gameCmsIpInstance])
      }
    }
    else {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsIp.label', default: 'GameCmsIp'), params.id])}"
      redirect(action: "list")
    }
  }

  def delete = {
    def gameCmsIpInstance = GameCmsIp.get(params.id)
    if (gameCmsIpInstance) {
      try {
        gameCmsIpInstance.delete(flush: true)
        flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'gameCmsIp.label', default: 'GameCmsIp'), params.id])}"
        redirect(action: "list")
      }
      catch (org.springframework.dao.DataIntegrityViolationException e) {
        flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'gameCmsIp.label', default: 'GameCmsIp'), params.id])}"
        redirect(action: "show", id: params.id)
      }
    }
    else {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsIp.label', default: 'GameCmsIp'), params.id])}"
      redirect(action: "list")
    }
  }

  def importFile = {
  }

  def upload = {
    StringBuilder error = new StringBuilder();
    def myFile = request.getFile('myFile')
    if (myFile && !myFile.empty) {
      String oriFileName = myFile.originalFilename //上传文件的原始文件名
      String extension = oriFileName.substring(oriFileName.indexOf(".") + 1) //扩展名
      if ("TXT".equalsIgnoreCase(extension)) {
        //第一步，将文件保存到指定路径下
        String newFileNamePath = "${grailsApplication.config.ip.file.upload.path}/${TimeUtil.getDate2(new Date())}"
        String newFileName = "${newFileNamePath}/${oriFileName}.${TimeUtil.getTime()}"
        File filePath = new File(newFileNamePath)
        if (!filePath.exists()) {
          filePath.mkdirs()
        }

        myFile.transferTo(new File(newFileName))

        //读取文件，将ip地址填入数据库
        FileReader fileReader = new FileReader(newFileName)
        BufferedReader reader = new BufferedReader(fileReader)
        int lineIndex = 0;
        String lineStr = ""
        while ((lineStr = reader.readLine()) != null) {
          lineIndex++
          if (lineStr == null || lineStr == "") {
            error.append("第 ${lineIndex} 行数据有误：本行内容为空！<br/>")
          } else if (lineStr.split(",").length != 3) {
            error.append("第 ${lineIndex} 行数据有误：本行内容格式有误！<br/>")
          } else if (StringUtil.String2Int(lineStr.split(",")[1], -1) == -1) {
            error.append("第 ${lineIndex} 行数据有误：标志'${lineStr.split(",")[1]}'必须为数字！<br/>")
          } else if (GameCmsIp.countByIpAndFlag(lineStr.split(",")[0], lineStr.split(",")[1]) > 0) {
            error.append("第 ${lineIndex} 行数据有误：ip地址'${lineStr.split(",")[0]}'已经存在！<br/>")
          } else {
            GameCmsIp ip = new GameCmsIp()
            ip.ip = lineStr.split(",")[0]?.trim()
            ip.flag = Integer.parseInt(lineStr.split(",")[1]?.trim())
            ip.description = lineStr.split(",")[2].trim()

            if (!ip.save(flush: true)) {
              error.append("第 ${lineIndex} 行数据保存失败！<br/>")
            }
          }
        }
        error.append("文件上传完毕");
      } else {
        error.append("上传文件类型有误，请上传TXT文件！<br/>")
      }
    } else {
      error.append("上传文件不能为空!<br/>")
    }

    flash.message = error.toString()
    render(view: "importFile")
  }

}