package com.emag.gamecms.domain.job

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 11-8-8
 * Time: 下午3:53
 * To change this template use File | Settings | File Templates.
 */
class GameCmsJobRuninfo {

  Date startTime;  //job启动开始时间
  Date endTime;    //job启动结束时间
  String runInfo;  //job运行信息
  String runTime;  //job运行时间
  static belongsTo = [jobinfo: GameCmsJobinfo]

  static constraints = {

    startTime(nullable: true);
    endTime(nullable: true);
    runInfo(nullable: true);
    runTime(nullable: true);
    jobinfo();
  }

  static mapping = {
    table 'game_cms_job_runinfo'
    version false

    columns {
      runInfo type: 'text'
    }
  }


}
