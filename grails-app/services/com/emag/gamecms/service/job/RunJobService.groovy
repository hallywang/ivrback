package com.emag.gamecms.service.job

import com.emag.gamecms.domain.job.GameCmsJobinfo
import com.emag.gamecms.domain.job.GameCmsJobRuninfo
import org.codehaus.groovy.grails.commons.ApplicationHolder

/**
 * Created by IntelliJ IDEA.
 * User: cyg
 * Date: 11-8-30
 * Time: 上午11:03
 * To change this template use File | Settings | File Templates.
 */
class RunJobService {
    boolean transactional = true
    def grailsAttributes;

    def run(String jobType) {

        def start = new Date();
        long a = System.currentTimeMillis()
        GameCmsJobinfo job;

        //List<GameCmsJobinfo> jobList = GameCmsJobinfo.findAllByJobType(jobType);
        List<GameCmsJobinfo> jobList = GameCmsJobinfo.createCriteria().list() {
            eq('jobType', jobType)
            order('orders', 'asc')
        }
        ListIterator iterator = jobList.listIterator();
        while (iterator.hasNext()) {
            job = iterator.next()
            def jobservice = job.getJobService();
            def jobmethod = job.getJobMethod();
            start = new Date();
            a = System.currentTimeMillis()

            try {

                def service = ApplicationHolder.getApplication().getMainContext().getBean(jobservice);
                service.invokeMethod(jobmethod, null);

                def info = "运行成功";
                def end = new Date();
                long b = System.currentTimeMillis()
                String runtime = String.valueOf((b - a) / 1000);

                addRuninfo(job, start, end, info, runtime)
            } catch (Exception e) {
                def info = "运行失败" + ":" + e.toString();
                def end = new Date();
                long b = System.currentTimeMillis()
                String runtime = String.valueOf((b - a) / 1000);

                addRuninfo(job, start, end, info, runtime)
                e.printStackTrace();
            }

        }

    }

    def startJob(Long jobId, Long runInfoId) {

        def start = new Date();
        long a = System.currentTimeMillis()
        //GameCmsJobinfo job = GameCmsJobinfo.get(jobId);
        def job = GameCmsJobinfo.get(jobId)
        def runinfo = GameCmsJobRuninfo.get(runInfoId);
        try {
            if (job != null) {
                def jobservice = job.getJobService();
                def jobmethod = job.getJobMethod();

                def service = ApplicationHolder.getApplication().getMainContext().getBean(jobservice);
                service.invokeMethod(jobmethod, null);

                def info = "运行成功"
                def end = new Date();
                long b = System.currentTimeMillis()
                String runtime = String.valueOf((b - a) / 1000);

                updateRuninfo(runinfo, job, start, end, info, runtime)
            }
        } catch (Exception e) {
            def info = "运行失败" + ":" + e.toString();
            def end = new Date();
            long b = System.currentTimeMillis()
            String runtime = String.valueOf((b - a) / 1000);

            updateRuninfo(runinfo, job, start, end, info, runtime)
            e.printStackTrace();
        }
    }

    def addRuninfo(def job, def start, def end, def info, def runtime) {
        GameCmsJobRuninfo runinfo = new GameCmsJobRuninfo();
        runinfo.setStartTime(start);
        runinfo.setEndTime(end);
        runinfo.setJobinfo(job);
        runinfo.setRunInfo(info);
        runinfo.setRunTime(runtime);
        runinfo.save(flush: true)
    }

    def updateRuninfo(def runinfo, def job, def start, def end, def info, def runtime) {
        if (runinfo != null) {
            runinfo.setStartTime(start);
            runinfo.setEndTime(end);
            runinfo.setJobinfo(job);
            runinfo.setRunInfo(info);
            runinfo.setRunTime(runtime);
            runinfo.save(flush: true)
        }
    }


}
