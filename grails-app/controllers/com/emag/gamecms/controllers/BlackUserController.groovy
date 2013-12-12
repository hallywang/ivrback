package com.emag.gamecms.controllers

import org.springframework.dao.DataIntegrityViolationException
import com.emag.gamecms.domain.system.BlackUser
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import com.vivame.util.TimeUtil
import com.vivame.util.StringUtil
class BlackUserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def grailsApplication

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [blackUserInstanceList:BlackUser.list(params), blackUserInstanceTotal: BlackUser.count()]
    }
    def create() {
        [blackUserInstance: new BlackUser(params)]
    }

    def save() {
        def blackUserInstance = new BlackUser(params)
        if(BlackUser.countByMsisdnAndFlag(blackUserInstance.msisdn,blackUserInstance.flag)>0){
            flash.message="${blackUserInstance.msisdn}已存在"
            render(view: "create", model: [blackUserInstance: blackUserInstance])
            return
        }
        if (!blackUserInstance.save(flush: true)) {
            render(view: "create", model: [blackUserInstance: blackUserInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'blackUser.label', default: 'BlackUser'), blackUserInstance.id])
        redirect(action: "show", id: blackUserInstance.id)
    }

    def show() {
        def blackUserInstance = BlackUser.get(params.id)

        if (!blackUserInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'blackUser.label', default: 'BlackUser'), params.id])
            redirect(action: "list")
            return
        }

        [blackUserInstance: blackUserInstance]
    }

    def delete() {
        def blackUserInstance = BlackUser.get(params.id)
        if (!blackUserInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'blackUser.label', default: 'BlackUser'), params.id])
            redirect(action: "list")
            return
        }

        try {
            blackUserInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'blackUser.label', default: 'BlackUser'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'blackUser.label', default: 'BlackUser'), params.id])
            redirect(action: "show", id: params.id)
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
            if ("txt".equalsIgnoreCase(extension)) {
                //第一步，将文件保存到指定路径下
                String newFileNamePath = "${grailsApplication.config.ip.file.upload.path}/${TimeUtil.getDate2(new Date())}"
                String newFileName = "${newFileNamePath}/${oriFileName}.${TimeUtil.getTime()}"
                File filePath = new File(newFileNamePath)
                if (!filePath.exists()) {
                    filePath.mkdirs()
                }

                myFile.transferTo(new File(newFileName))

                //读取文件，将黑名单导入数据库
                FileInputStream fis = new FileInputStream(newFileName); // 文件字节流
                InputStreamReader isr = new InputStreamReader(fis, "GBK"); // 字节流和字符流的桥梁，可以指定指定字符格式
                //FileReader fileReader = new FileReader(newFileName)
                BufferedReader reader = new BufferedReader(isr)
                int lineIndex = 0;
                String lineStr = ""
                while ((lineStr = reader.readLine()) != null) {
                    lineIndex++
                    if (lineStr == null || lineStr == "") {
                        error.append("第 ${lineIndex} 行数据有误：本行内容为空！<br/>")
                    } else if (lineStr.split(",").length != 3) {
                        error.append("第 ${lineIndex} 行数据有误：本行内容格式有误！<br/>")
                    } else if (StringUtil.String2Int(lineStr.split(",")[2], -1) == -1) {
                        error.append("第 ${lineIndex} 行数据有误：标志'${lineStr.split(",")[1]}'必须为数字！<br/>")
                    } else if (BlackUser.countByMsisdnAndFlag(lineStr.split(",")[0], lineStr.split(",")[2]) > 0) {
                        error.append("第 ${lineIndex} 行数据有误：号码'${lineStr.split(",")[0]}'已经存在！<br/>")
                    } else {
                        BlackUser blackUser = new BlackUser()
                        blackUser.msisdn = lineStr.split(",")[0]?.trim()
                        blackUser.description=lineStr.split(",")[1]?.trim()
                        blackUser.flag = Integer.parseInt(lineStr.split(",")[2]?.trim())
                        if (!blackUser.save(flush: true)) {
                            error.append("第 ${lineIndex} 行数据保存失败！<br/>")
                        }
                    }
                }
                error.append("文件上传完毕");
            } else {
                error.append("上传文件类型有误，请上传文本文件！<br/>")
            }
        } else {
            error.append("上传文件不能为空!<br/>")
        }
        flash.message = error.toString()
        render(view: "importFile")
    }
}
