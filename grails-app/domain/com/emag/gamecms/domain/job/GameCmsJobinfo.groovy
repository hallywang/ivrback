package com.emag.gamecms.domain.job

class GameCmsJobinfo {
    String jobName;
    String jobType;
    Integer orders = 0; //排序
    String jobService;
    String jobMethod;

    Date updateTime = new Date();

    static hasMany = [jobRuninfo: GameCmsJobRuninfo]

    static constraints = {

        jobName(blank: false);
        jobType(blank: false);
        jobService(blank: false);
        jobMethod(blank: false);
        updateTime(nullable: false);
        orders(nullable: false);
        orders(unique: 'jobType');
    }

    static mapping = {
        table 'game_cms_jobinfo'
        version false
    }
}
