class OneWeekJob {
  static triggers = {
    //秒 分 时 天 月 周  每周二晚上4点开始执行
    cron name: 'OneWeekJobCronTrigger', cronExpression: '0 0 4 ? * 3 '
  }

  private static final String JOB_TYPE = 'oneWeek'
  def runJobService

  def execute() {
    log.info "One week job start to run ..."
    long a = System.currentTimeMillis()

    runJobService.run(JOB_TYPE);

    /*try {
      abService.AbLunxun()
    } catch (Exception e) {
      log.error e
    }

    try {
      initStatisticService.loadWeekUser()
    } catch (Exception e) {
      log.error e
    }*/

    log.info "One week job end to run, use ${(System.currentTimeMillis() - a) / 1000} s"
  }
}
