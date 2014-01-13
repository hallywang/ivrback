class TenMinJob {
  static triggers = {
    simple name: 'TenMinJobTrigger', startDelay: 1000 * 600, repeatInterval: 1000 * 600
    //应用启动后十分钟运行，十分钟一次
  }

  private static final String JOB_TYPE = 'tenMin'
  def runJobService

  def execute() {
    log.info "Ten minute job start to run ..."
    long a = System.currentTimeMillis()

    runJobService.run(JOB_TYPE);

    log.info "Ten minute job end to run, use ${(System.currentTimeMillis() - a) / 1000} s"
  }


}
