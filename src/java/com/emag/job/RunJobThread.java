package com.emag.job;

import com.emag.gamecms.service.job.RunJobService;
import org.codehaus.groovy.grails.commons.ApplicationHolder;

/**
 * Created by IntelliJ IDEA.
 * User: cyg
 * Date: 11-10-26
 * Time: 下午2:29
 * To change this template use File | Settings | File Templates.
 */
public class RunJobThread extends Thread {
    RunJobService runJobService = new RunJobService();
    Long jobId ;
    Long runInfoId ;

    public RunJobThread(Long jobId , Long runInfoId) {
        this.jobId = jobId;
        this.runInfoId = runInfoId;
    }

    public void run() {
        RunJobService service = (RunJobService) ApplicationHolder.getApplication().getMainContext().getBean("runJobService");
        service.startJob(this.jobId ,this.runInfoId);
    }
}
