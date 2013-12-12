package com.emag.gamecms.controllers.job

import com.emag.gamecms.domain.job.GameCmsJobinfo

import com.emag.gamecms.domain.job.GameCmsJobRuninfo
import com.emag.job.RunJobThread
import com.vivame.util.TimeUtil

class GameCmsJobinfoController {
    def grailsAttributes;
    def runJobService;
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        def jobs = GameCmsJobinfo.createCriteria().list(params) {
            if(params.sort == 'jobType' && params.order == 'desc')
                order('jobType','desc')
            else
                order('jobType','asc')
            order('orders', 'asc')
        }

        [gameCmsJobinfoInstanceList: jobs, gameCmsJobinfoInstanceTotal: GameCmsJobinfo.count()]
    }

    def create = {
        def gameCmsJobinfoInstance = new GameCmsJobinfo()
        gameCmsJobinfoInstance.properties = params
        return [gameCmsJobinfoInstance: gameCmsJobinfoInstance]
    }

    def save = {
        def gameCmsJobinfoInstance = new GameCmsJobinfo(params)
        if (gameCmsJobinfoInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'gameCmsJobinfo.label', default: 'GameCmsJobinfo'), gameCmsJobinfoInstance.id])}"
            redirect(action: "show", id: gameCmsJobinfoInstance.id)
        }
        else {
            render(view: "create", model: [gameCmsJobinfoInstance: gameCmsJobinfoInstance])
        }
    }

    def show = {
        def gameCmsJobinfoInstance = GameCmsJobinfo.get(params.id)


        if (!gameCmsJobinfoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsJobinfo.label', default: 'GameCmsJobinfo'), params.id])}"
            redirect(action: "list")
        }
        else {

            [gameCmsJobinfoInstance: gameCmsJobinfoInstance]
        }
    }

    def start = {
        def gameCmsJobinfoInstance = GameCmsJobinfo.get(params.id)
        if (!gameCmsJobinfoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsJobinfo.label', default: 'GameCmsJobinfo'), params.id])}"
            redirect(action: "list")
        }
        else {

            StringBuffer info = new StringBuffer(gameCmsJobinfoInstance.jobName);
            info.append(" is running");
            flash.message = info.toString();

            def jobRuninfos = GameCmsJobRuninfo.createCriteria().list(params) {
                eq('jobinfo', gameCmsJobinfoInstance)
                like('runInfo', '%running')
            }
            if (jobRuninfos) {
                info.append(" 正在启动，请稍候再试");
                flash.message = info.toString();
                return render(view: "show", model: [gameCmsJobinfoInstance: gameCmsJobinfoInstance])
            }

            def start = new Date();

            GameCmsJobRuninfo runinfo = new GameCmsJobRuninfo();
            runinfo.setStartTime(start);
            runinfo.setJobinfo(gameCmsJobinfoInstance);
            runinfo.setRunInfo(info.toString());
            runinfo.save(flush: true);

            RunJobThread runJob = new RunJobThread(gameCmsJobinfoInstance.id, runinfo.id);
            runJob.start();

            return render(view: "show", model: [gameCmsJobinfoInstance: gameCmsJobinfoInstance])
        }
    }

    def view = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def gameCmsJobinfoInstance = GameCmsJobinfo.get(params.id)
        if (!gameCmsJobinfoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsJobinfo.label', default: 'GameCmsJobinfo'), params.id])}"
            redirect(action: "list")
        }
        else {
            def jobRuninfos = GameCmsJobRuninfo.createCriteria().list(params) {
                eq('jobinfo', gameCmsJobinfoInstance)
                order('id', 'desc')
            }
            def count = jobRuninfos.totalCount
            [jobRuninfos: jobRuninfos, total: count]
        }
    }

    def edit = {
        def gameCmsJobinfoInstance = GameCmsJobinfo.get(params.id)
        if (!gameCmsJobinfoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsJobinfo.label', default: 'GameCmsJobinfo'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [gameCmsJobinfoInstance: gameCmsJobinfoInstance]
        }
    }

    def update = {
        def gameCmsJobinfoInstance = GameCmsJobinfo.get(params.id)
        if (gameCmsJobinfoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (gameCmsJobinfoInstance.version > version) {

                    gameCmsJobinfoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'gameCmsJobinfo.label', default: 'GameCmsJobinfo')] as Object[], "Another user has updated this GameCmsJobinfo while you were editing")
                    render(view: "edit", model: [gameCmsJobinfoInstance: gameCmsJobinfoInstance])
                    return
                }
            }
            gameCmsJobinfoInstance.properties = params
            if (!gameCmsJobinfoInstance.hasErrors() && gameCmsJobinfoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'gameCmsJobinfo.label', default: 'GameCmsJobinfo'), gameCmsJobinfoInstance.id])}"
                redirect(action: "show", id: gameCmsJobinfoInstance.id)
            }
            else {
                render(view: "edit", model: [gameCmsJobinfoInstance: gameCmsJobinfoInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsJobinfo.label', default: 'GameCmsJobinfo'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def gameCmsJobinfoInstance = GameCmsJobinfo.get(params.id)
        if (gameCmsJobinfoInstance) {
            try {
                gameCmsJobinfoInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'gameCmsJobinfo.label', default: 'GameCmsJobinfo'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'gameCmsJobinfo.label', default: 'GameCmsJobinfo'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsJobinfo.label', default: 'GameCmsJobinfo'), params.id])}"
            redirect(action: "list")
        }
    }

    def delRunInfo = {
        def gameCmsJobRuninfo = GameCmsJobRuninfo.get(params.id)
        def jobid = gameCmsJobRuninfo.jobinfo.id
        log.info "del job ${jobid} jobruninfo "
        if (gameCmsJobRuninfo) {
            try {
                gameCmsJobRuninfo.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'gameCmsJobRuninfo.label', default: 'GameCmsJobRuninfo'), params.id])}"
                redirect(action: "view", id: jobid)
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'gameCmsJobRuninfo.label', default: 'GameCmsJobRuninfo'), params.id])}"
                redirect(action: "view", id: jobid)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'gameCmsJobRuninfo.label', default: 'GameCmsJobRuninfo'), params.id])}"
            redirect(action: "view", id: jobid)
        }
    }
}
