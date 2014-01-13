class OneDayJob {
  static triggers = {
    simple name: 'OneDayJobTrigger', startDelay: 1000 * 60 * 60 * 24, repeatInterval: (1000 * 60 * 60 * 24)
    //应用启动后24小时运行一次，之后没24小时运行一次
  }

  private static final String JOB_TYPE = 'oneDay'
  def runJobService
  def initUaService

  def execute() {
    long a = System.currentTimeMillis()
    log.info "One day job start to run ..."
    //initUaService.initUaInfo()
    runJobService.run(JOB_TYPE);
    log.info "One day job end to run, use ${(System.currentTimeMillis() - a) / 1000} s"
  }
}
