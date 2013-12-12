package com.emag.gamecms.controllers

import com.emag.gamecms.domain.template.TvWapPage
import com.emag.gamecms.domain.template.TvWapTopic
import org.springframework.web.multipart.commons.CommonsMultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.util.FileCopyUtils

class TvWapTopicController {
    def authenticateService
    def wapService
    def grailsApplication  // 定义grailsApplication
    def index = { redirect(action: list, params: params) }

    // the delete, save and update actions only accept POST requests
    def static allowedMethods = [save: 'POST']

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def tvWapTopicInstance = new TvWapTopic()
        bindData(tvWapTopicInstance, params)

        def tvWapTopicInstanceList = TvWapTopic.createCriteria().list(params) {
            if (tvWapTopicInstance.name) {
                or {
                    like("name", "%${tvWapTopicInstance.name}%")
                    like("description", "%${tvWapTopicInstance.name}%")
                }
            }
        }

        [tvWapTopicList: tvWapTopicInstanceList, tvWapTopicInstanceTotal: tvWapTopicInstanceList.totalCount]
    }

    def show = {

        def tvWapTopic = TvWapTopic.get(params.id)

        if (!tvWapTopic) {
            flash.message = "TvWapTopic not found with id ${params.id}"
            redirect(action: list)
        }
        else {

            def tvWapPageList = TvWapPage.createCriteria().list(params) {
                eq("topic", tvWapTopic)
            }

            return [tvWapTopic: tvWapTopic, tvWapPageList: tvWapPageList]

        }
    }

    def delete = {
        def tvWapTopic = TvWapTopic.get(params.id)
        if (tvWapTopic && !tvWapTopic.pages) {
            tvWapTopic.delete()
            flash.message = "TvWapTopic ${params.id} deleted"
            redirect(action: list)
        } else if (tvWapTopic && tvWapTopic.pages) {
            flash.message = "TvWapTopic ${params.id} can not be deleteed because of exists some pages"
            redirect(action: list)
        } else {
            flash.message = "TvWapTopic not found with id ${params.id}"
            redirect(action: list)
        }
    }

    def edit = {
        def tvWapTopic = TvWapTopic.get(params.id)

        if (!tvWapTopic) {
            flash.message = "TvWapTopic not found with id ${params.id}"
            redirect(action: list)
        }
        else {
            return [tvWapTopic: tvWapTopic]
        }
    }

    def update = {
        def tvWapTopic = TvWapTopic.get(params.id)
        if (tvWapTopic) {
            // 专题下存在模板的情况不允许修改专题名称
            if (params.name != tvWapTopic.name && tvWapTopic.pages) {
                flash.message = "TvWapTopic ${params.id} name can't be changed"
                redirect(action: edit, id: params.id)
                return
            }

            tvWapTopic.properties = params
            if (!tvWapTopic.hasErrors() && tvWapTopic.save()) {
                flash.message = "TvWapTopic ${params.id} updated"
                redirect(action: show, id: tvWapTopic.id)
            }
            else {
                render(view: 'edit', model: [tvWapTopic: tvWapTopic])
            }
        }
        else {
            flash.message = "TvWapTopic not found with id ${params.id}"
            redirect(action: edit, id: params.id)
        }
    }

    def create = {
        def tvWapTopic = new TvWapTopic()
        tvWapTopic.properties = params
        return ['tvWapTopic': tvWapTopic]
    }

    def save = {
        def tvWapTopic = new TvWapTopic(params)
        tvWapTopic.createTime = new Date()
        tvWapTopic.creator = authenticateService.userDomain()

        if (!tvWapTopic.hasErrors() && tvWapTopic.save()) {
            flash.message = "TvWapTopic ${tvWapTopic.id} created"
            redirect(action: show, id: tvWapTopic.id)
        }
        else {
            render(view: 'create', model: [tvWapTopic: tvWapTopic])
        }
    }
    /**
     * 实现批量模板上传
     */
    def upload = {
        def topicId = params?.topicId
        def tvWapTopicInstance
        def map = [:]
        def notBelowFile = []
        def csvNum = 0, moreNum = 0, errorNum = 0, suc = 0, flag = 0, fail = 0, info = "";
        if (topicId) tvWapTopicInstance = TvWapTopic.findById(topicId)
        if (!tvWapTopicInstance) {
            flash.message = message(code: "topic.not.exists")
            return render(view: 'uploadZip', params: ['path': params.path?.trim()])
        }
        if (!(request instanceof MultipartHttpServletRequest)) {
            log.warn("file control upload no multipart")
        }
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request
        CommonsMultipartFile file = (CommonsMultipartFile) multiRequest.getFile("file")
        //文件大小不能超过2兆
        def limitFile = (1024 * 1024) * 2
        if (file.size > limitFile) {
            flash.message = message(code: "zip.limit.size")
            return render(view: 'uploadZip', params: ['path': params.path?.trim()])
        }
        def path = ""
        def p = authenticateService.userDomain()
        if (file == null || file == "") {
            flash.message = message(code: "chose.a.file")
            return render(view: 'uploadZip', params: ['path': params.path?.trim()])
        }
        def pathname = grailsApplication.config.video.pageDir
        def curDir = new File(pathname + "temp/")
        if (!curDir.exists()) curDir.mkdirs()
        if (params.path) {
            path = params.path
            if (!path.endsWith("/")) path = path + "/"
        }
        def originalFileName = file.getOriginalFilename()
        def uploadFileName = curDir.getAbsolutePath() + "/" + path + file.getOriginalFilename()
        def outputDirectory = curDir.getAbsolutePath() + "/" + path
        def checkname = originalFileName?.toLowerCase()
        if (!checkname.endsWith("zip")) {
            flash.message = message(code: "chose.file.by.zip")
            return render(view: 'uploadZip', params: ['path': params.path?.trim()])
        }
        File uploadedFile = new File(uploadFileName);
        FileCopyUtils.copy(file.bytes, uploadedFile);
        File disFile
        //Uzip begins
        if ((originalFileName.toLowerCase().endsWith(".zip")) && params.autoext == "true") {
            outputDirectory.substring(outputDirectory.length() - 1)
            disFile = new File(outputDirectory)
            flag = wapService.readZipFlie(outputDirectory, uploadFileName, csvNum, moreNum, errorNum, disFile)
            if (flag <= 7 && flag >= 1) {
                flash.message = message(code: "upload.invalidate.status." + flag)
                return render(view: 'uploadZip', model: [path: params.path?.trim()])
            }
            def content = [], tempName = [], disFileName = []
            def formatError = 0
            File csv = new File(outputDirectory + "upload.csv");
            // 获取上传GSP文件名称
            disFile.eachFileRecurse {
                def name = it.name.toString().toLowerCase()
                if (name.endsWith("gsp") && (name.startsWith("_"))) {
                    disFileName.add(name.substring(1, name.indexOf('.')))
                }
                if (name.endsWith("gsp") && !(name.startsWith("_"))) {
                    notBelowFile.add(name.substring(0, name.indexOf('.')))
                }
            }
            //读取资源文件
            csv.eachLine {line ->
                if (line.toString().count("|") != 4) formatError++;
                if (line.toString().count("|") > 0) {
                    tempName.add(line.toString().substring(0, line.toString().indexOf('|')))
                    if (!notBelowFile.contains(line.toString().substring(0, line.toString().indexOf('|')))) {
                        content.add(line)
                    }
                }
            }
            //判断CSV文件每条数据的格式是否正确
            if (formatError > 0) {
                disFile.deleteDir();
                flash.message = message(code: "resource.format.error")
                return render(view: 'uploadZip', model: [path: params.path?.trim()])
            }
            if (null == content || content.isEmpty()) {
                disFile.deleteDir();
                flash.message = message(code: "upload.invalidate.status.8")
                return render(view: 'uploadZip', model: [path: params.path?.trim()])
            }
            tempName = tempName - notBelowFile
            disFileName = disFileName - notBelowFile
            //判断CSV的模板名称是否与上传的模板名称一致
            if (!wapService.isEqual(tempName, disFileName)) {
                disFile.deleteDir();
                flash.message = message(code: "file.limit.name.consistent")
                return render(view: 'uploadZip', model: [path: params.path?.trim()])
            }
            /** 移除不合法的模板文件资源内容      */
            def distLocal = grailsApplication.config.video.pageDir
            File dis = new File(distLocal + tvWapTopicInstance.name);
            if (!dis.exists()) dis.mkdir()
            def disSrc = [];
            if (dis.listFiles().size() > 0) {
                //获取专题下的文件名称
                dis.eachFileRecurse {
                    def fileName = it.name.toString().toLowerCase();
                    if (fileName.endsWith('gsp'))
                        disSrc.add(fileName.substring(1, fileName.indexOf('.')));
                }
            }
            // 入库操作
            map = wapService.result(disSrc, distLocal, outputDirectory, content, disFileName, tvWapTopicInstance)
            def sucNumber = map.get("sucNumber")
            def logSucNumber = map.get("logSucNumber")
            def failNumber = map.get("failNumber")
            def logFailNumber = map.get("logFailNumber")
            def typeNum = map.get("typeNum")
            def typeNume = map.get("typeNume")
            def errorFile = map.get("errorfile")
            def belowNum = notBelowFile.size()
            suc = sucNumber + logSucNumber
            fail = failNumber + logFailNumber + typeNum + typeNume + belowNum
            /** 获取失败记录信息 */
            info = returnMessage(failNumber, logFailNumber, typeNum, typeNume, sucNumber, logSucNumber, belowNum)
            /** 将上传的合法的模板复制到对应专题下                                                                */
            disFileName.each {
                String src = outputDirectory + "_" + it + ".gsp";
                String target = distLocal + tvWapTopicInstance.name + "/" + "_" + it + ".gsp";
                if (!(errorFile.contains(it)) && !(notBelowFile.contains(it))) {
                    wapService.copy(src, target);
                }
            }
            disFile.deleteDir();
            new File(uploadFileName).delete()
        }
        return render(view: 'uploadZip', model: ['path': params.path?.trim(), flag: "true", sucs: suc, fails: fail, infos: info])
    }

    /** *
     *
     * 返回操作失败记录信息
     */
    def returnMessage = {failNumber, logFailNumber, typeNum, typeNume, sucNumber, logSucNumber, belowNum ->
        def info = "";
        if (failNumber > 0 || logFailNumber > 0) {
            info = message(code: "database.exception")
        }
        else if ((typeNum > 0 || typeNume > 0) && belowNum > 0) {
            info = message(code: "content.type.or.required.or.not.below")
        }
        else if (typeNum > 0 || typeNume > 0) {
            info = message(code: "content.type.or.required")
        } else if (belowNum > 0) {
            info = message(code: "not.below.file")
        }
        else if ((failNumber > 0 || logFailNumber > 0) && typeNum > 0) {
            info = message(code: "database.exception.or.contentType.required")
        } else if ((failNumber > 0 || logFailNumber > 0) && typeNum > 0 || typeNume > 0) {
            info = message(code: "database.exception.or.contentType.required")
        }
        else if ((failNumber > 0 || logFailNumber > 0) && typeNum > 0 && belowNum > 0) {
            info = message(code: "database.exception.or.contentType.required.or.not.below")
        }
        else {
            info = ""
        }
        return info
    }


}
