package com.emag.gamecms.controllers

import com.emag.gamecms.domain.city.Province
import com.emag.gamecms.domain.template.TvWapPage
import com.emag.gamecms.domain.template.TvWapPageLog
import com.emag.gamecms.domain.template.TvWapTopic
import com.vivame.util.StringUtil
import grails.util.Environment
import org.apache.commons.io.FileUtils
import com.emag.gamecms.domain.system.GameCmsTagTips

class TvWapPageController {
    def authenticateService
    def grailsApplication   // 定义grailsApplication
    def index = { redirect(action: list, params: params) }

    // the delete, save and update actions only accept POST requests
    def static allowedMethods = [save: 'POST']

    def list = {
        if (!params.max) params.max = 10
        def templeteList = TvWapPage.createCriteria().list(params) {
            if (params.topicId) {
                eq('topic', TvWapTopic.get(Long.valueOf(params.topicId)))
            }

            if (params.description) {
                or {
                    like('title', "%${params.description}%")
                    like('description', "%${params.description}%")
                    like('name', "%${params.description}%")
                }
            }
        }


        [tvWapPageList: templeteList, tvWapPageTotalCount: templeteList.totalCount]
    }

    def show = {
        def tvWapPage = TvWapPage.get(params.id)
        if (!tvWapPage) {
            flash.message = "TvWapPage not found with id ${params.id}"
            redirect(action: list)
        }
        else {
            //模板内容改从文件中读取，不直接从数据库中读取
            String pathName = grailsApplication.config.video.pageDir + tvWapPage.topic.name
            String fileName = pathName + "/_${tvWapPage.name}.gsp"
            String content = ''
            try {
                content = FileUtils.readFileToString(new File(fileName), 'UTF-8')
            } catch (IOException ex) {
                // 可能是因为文件不存在，需要直接从数据库中读取
                flash.message = "模板文件找不到，从数据库读取模板内容"
                content = tvWapPage.content
            } catch (Exception e) {
                flash.message = "获取模板内容失败"
            }

            return [tvWapPage: tvWapPage, content: content]
        }
    }

    def delete = {
        def tvWapPage = TvWapPage.get(params.id)
        if (tvWapPage) {
            tvWapPage.published = '0'
            tvWapPage.save(flush: true)

            flash.message = "TvWapPage ${params.id} status changed to unpublished"
            redirect(controller: "tvWapTopic", action: "show", id: tvWapPage.topic.id)
        }
        else {
            flash.message = "TvWapPage not found with id ${params.id}"
            redirect(controller: "tvWapTopic", action: "show", id: tvWapPage.topic.id)
        }
    }

    def edit = {
        def tvWapPage = TvWapPage.get(params.id)
        if (!tvWapPage) {
            flash.message = "TvWapPage not found with id ${params.id}"
            redirect(action: list)
        } else {
            // 模板内容改从文件中读取，不直接从数据库中读取
            String pathName = grailsApplication.config.video.pageDir + tvWapPage.topic.name
            String fileName = pathName + "/_${tvWapPage.name}.gsp"
            String content = ''
            try {
                content = FileUtils.readFileToString(new File(fileName), 'UTF-8')
            } catch (IOException ex) {
                // 可能是因为文件不存在，需要直接从数据库中读取
                flash.message = "模板文件找不到，从数据库读取模板内容"
                content = tvWapPage.content
            } catch (Exception e) {
                flash.message = "获取模板内容失败"
            }

            def tagTips = GameCmsTagTips.list()
            def tvWapPageLogs = TvWapPageLog.createCriteria().list(params) {
                eq('template', tvWapPage)
                order('updateTime', 'desc')
            }

            return [tvWapPage: tvWapPage, tagTips: tagTips, tvWapPageLogs: tvWapPageLogs, content: content]
        }
    }

    def update = {
        def tvWapPage = TvWapPage.get(params.id)
        TvWapPageLog pagelog = new TvWapPageLog()
        if (tvWapPage) {
            pagelog.properties = tvWapPage.properties
            def todir = grailsApplication.config.video.pageDir
            String envName = Environment.getCurrent().getName();
//      if (envName == 'development') {
//        String realpath = request.getRealPath("")
//        todir = realpath.substring(0, realpath.lastIndexOf("web-app") - 1) + '\\grails-app\\views\\page\\'
//      }
            def p = authenticateService.userDomain()
            def pathname = todir + "${tvWapPage.topic.name}"
            if (tvWapPage.name != params.name) {
                def tvpages = TvWapPage.executeQuery("from TvWapPage as t where t.name=:name and t.topic=:topic ", [name: params.name, topic: tvWapPage.topic])
                if (tvpages.size > 0) {
                    flash.message = "名称与所在专题另一模板重复"
                    redirect(action: edit, id: params.id)
                    return
                }
            }

            tvWapPage.properties = params
            if (!tvWapPage.hasErrors() && tvWapPage.save()) {
                pathname = todir + "${tvWapPage.topic.name}"
                def path = new File(pathname)
                if (!path.exists()) {
                    path.mkdirs()
                }
                def file = new File(pathname + "/_${tvWapPage.name}.gsp")
                if (file.exists()) {
                    file.delete()
                }
                file.createNewFile();
                Writer writer = file.newWriter("UTF-8");
                writer.write('' + tvWapPage.content);
                writer.close();
                //todo 日志


                pagelog.template = tvWapPage
                pagelog.userName = authenticateService.userDomain()?.username
                pagelog.updateTime = new Date()
                pagelog.save(flush: true)

                flash.message = "TvWapPage ${params.id} updated"
                redirect(controller: "tvWapTopic", action: "show", id: tvWapPage.topic.id)
            }
            else {
                render(view: 'edit', model: [tvWapPage: tvWapPage])
            }
        }
        else {
            flash.message = "TvWapPage not found with id ${params.id}"
            redirect(action: edit, id: params.id)
        }
    }

    def create = {
        def tvWapPage = new TvWapPage()

        if (params.tvWapTopicId) {
            def topic = TvWapTopic.get(params.tvWapTopicId)
            tvWapPage.topic = topic
        }

        tvWapPage.properties = params
        def tagTips = GameCmsTagTips.list()

        return ['tvWapPage': tvWapPage, tagTips: tagTips]
    }

    def save = {
        def tvWapPage = new TvWapPage(params)

        if (TvWapPage.findByTopicAndName(tvWapPage.topic, tvWapPage.name)) {
            flash.message = "Template name have used."
            render(view: 'create', model: [tvWapPage: tvWapPage])
        } else if (!tvWapPage.hasErrors() && tvWapPage.save()) {
            flash.message = "TvWapPage ${tvWapPage.id} created"

            def todir = grailsApplication.config.video.pageDir
            String envName = Environment.getCurrent().getName();
//      if (envName == 'development') {
//        String realpath = request.getRealPath("")
//        todir = realpath.substring(0, realpath.lastIndexOf("web-app") - 1) + '\\grails-app\\views\\page\\'
//      }
            def pathname = todir + "${tvWapPage.topic.name}"
            def path = new File(pathname)
            if (!path.exists()) {
                path.mkdirs()
            }
            def file = new File(pathname + "/_${tvWapPage.name}.gsp")
            if (file.exists()) {
                file.delete()
            }
            file.createNewFile()
            Writer writer = file.newWriter("UTF-8");
            writer.write('' + tvWapPage.content);
            writer.close();

            redirect(controller: "tvWapTopic", action: "show", id: tvWapPage.topic.id)
        }
        else {
            render(view: 'create', model: [tvWapPage: tvWapPage])
        }
    }

    //插入标签提醒
    def insertTag = {

        def tagtip = GameCmsTagTips.get(params.id)


        return [tagtip: tagtip]

    }

    /**
     * 显示历史记录详情
     */
    def showLog = {
        def tvWapPageLog = TvWapPageLog.get(params.id)

        return [tvWapPageLog: tvWapPageLog]
    }

    /**
     * 恢复历史修改记录
     */
    def recoverLog = {
        def tvWapPageLog = TvWapPageLog.get(params.id)
        if (tvWapPageLog) {
            flash.message = "成功恢复模板$tvWapPageLog.name 到版本：$tvWapPageLog.updateTime ,id:$tvWapPageLog.id"
            def tvWapPage = TvWapPage.get(tvWapPageLog.template.id)
            tvWapPage.properties = tvWapPageLog.properties

            if (!tvWapPage.hasErrors() && tvWapPage.save(flush: true)) {
                this.saveTemplate(tvWapPage)
            } else {
                flash.message = "恢复失败"
            }
            redirect(controller: "tvWapTopic", action: "show", id: tvWapPageLog.template.topic.id)
        } else {
            flash.message = "修改记录不存在"
            redirect(controller: "tvWapTopic", action: "list")
        }
    }

    /**
     * 保存文件
     * @param tvWapPage 需要保存的模板
     *
     */
    private void saveTemplate(def tvWapPage) {
        def todir = grailsApplication.config.video.pageDir
        String envName = Environment.getCurrent().getName();
//    if (envName == 'development') {
//      String realpath = request.getRealPath("")
//      todir = realpath.substring(0, realpath.lastIndexOf("web-app") - 1) + '\\grails-app\\views\\page\\'
//    }
        def pathname = todir + "${tvWapPage.topic.name}"
        def path = new File(pathname)
        if (!path.exists()) {
            path.mkdirs()
        }
        def file = new File(pathname + "/_${tvWapPage.name}.gsp")
        if (file.exists()) {
            file.delete()
        }
        file.createNewFile()
        Writer writer = file.newWriter("UTF-8");
        writer.write(tvWapPage.content);
        writer.close();
    }

    /**
     * 批量生成模板文件
     *
     * @author:RongWei
     * @date:2011-01-24
     */
    def createPages = {
        //传递参数非空判断
        if (StringUtil.isEmpty(params.pageValues)) {
            flash.msgType = "errors";
            flash.templateMessage = "页面传递参数错误，请联系管理员！";
            redirect(controller: "tvWapTopic", action: "show", id: params.topicId);
            return;
        }

        List<Long> idList = [];
        String[] pageIds = StringUtil.getString(params.pageValues).split(',', -1);
        //循环
        pageIds.each {
            idList.add(Long.valueOf(it));
        }

        //数据库查询选定模板对象信息
        List<TvWapPage> pagesList = TvWapPage.createCriteria().list() {
            inList('id', idList)
        }
        //判断
        if (!pagesList) {
            flash.msgType = "errors";
            flash.templateMessage = "页面传递参数错误，可能输入非法的请求URL，请联系管理员！";
            redirect(controller: "tvWapTopic", action: "show", id: params.topicId);
            return;
        }

        int sRecords = 0;//成功的条数
        def todir = grailsApplication.config.video.pageDir;//模板文件存放路径
        String envName = Environment.getCurrent().getName();//应用运行的环境
        //循环生成模板文件
        for (int i = 0; i < pagesList.size(); i++) {
            TvWapPage template = (TvWapPage) pagesList[i];

//      if (envName == 'development') {
//        String realPath = request.getRealPath("")
//        todir = realPath.substring(0, realPath.lastIndexOf("web-app") - 1) + '\\grails-app\\views\\page\\'
//      }

            try {
                def pathName = todir + "${template.topic.name}";
                def path = new File(pathName);
                if (!path.exists()) {
                    path.mkdirs();
                }
                def file = new File(pathName + File.separator + "_${template.name}.gsp");
                if (file.exists()) {
                    file.delete();
                }

                file.createNewFile();
                Writer writer = file.newWriter("UTF-8");
                writer.write(template.content);
                writer.close();

                sRecords++;

            } catch (Exception e) {
                log.info("[TvWapPageController.createPages()]:重新创建模板文件失败，可能出错原因：" + e.getMessage());
            }
        }

        if (sRecords == pagesList.size()) {
            flash.templateMessage = "重新创建模板文件成功。共计创建${sRecords}个模板文件！";
            redirect(controller: "tvWapTopic", action: "show", id: params.topicId);
        } else {
            flash.msgType = "errors";
            flash.templateMessage = "重新创建模板文件失败，请稍候再试！";
            redirect(controller: "tvWapTopic", action: "show", id: params.topicId);
        }
    }
    /**
     * 更新模板文件至数据库
     */
    def pagesIntoDb = {
        if (StringUtil.isEmpty(params.pageValues)) {
            flash.msgType = "errors";
            flash.templateMessage = "页面传递参数错误，请联系管理员！";
            redirect(controller: "tvWapTopic", action: "show", id: params.topicId);
            return;
        }
        List<Long> idList = [];
        String[] pageIds = StringUtil.getString(params.pageValues).split(',', -1);
        //循环
        pageIds.each {
            idList.add(Long.valueOf(it));
        }
        int sRecords = 0;//成功的条数
        def todir = grailsApplication.config.video.pageDir;//模板文件存放路径
        //循环读取模板文件至数据库
        for (int i = 0; i < pageIds.size(); i++) {
            TvWapPage tvWapPage = TvWapPage.get(pageIds[i])
            if (!tvWapPage) {
                flash.msgType = "errors";
                flash.templateMessage = "pageId=" + pageIds[i] + ",模板不存在！";
                redirect(controller: "tvWapTopic", action: "show", id: params.topicId);
                return;
            }
            try {
                def pathName = todir + "${tvWapPage.topic.name}";
                def path = new File(pathName);
                if (!path.exists()) {
                    path.mkdirs();
                }
                String fileName = pathName + "/_${tvWapPage.name}.gsp"     //文件名
                // 英文会出现乱码，由于用ue或记事本打开要修改文件时，文件里没有中文的话，会用别的字符集打开，所以需要手动保存为utf-8
                String content = FileUtils.readFileToString(new File(fileName), 'UTF-8')    //读取文件内容
                tvWapPage.content = content
                tvWapPage.save()
                sRecords++
            } catch (Exception e) {
                log.info("[TvWapPageController.pagesIntoDb()]:导入数据库失败，可能出错原因：" + e.getMessage());
            }
        }

        if (sRecords == pageIds.size()) {
            flash.templateMessage = "更新模板文件至数据库成功。共计导入${sRecords}个模板文件！";
            redirect(controller: "tvWapTopic", action: "show", id: params.topicId);
        } else {
            flash.msgType = "errors";
            flash.templateMessage = "更新模板文件至数据库失败，请稍候再试！";
            redirect(controller: "tvWapTopic", action: "show", id: params.topicId);
        }
    }
    def areas = {
        return [provinceList: Province.findAll()]
    }

}
