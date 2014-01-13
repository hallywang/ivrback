class EightOclockJob {
  static triggers = {
    // 秒、分、时、日、月、周
    cron name: 'eightOclockJobTrigger', cronExpression: "0 0 8 * * ?"
  }

  private static final String JOB_TYPE = 'eightOclock'
  def runJobService

  def execute() {
    log.info "eight o'clock job start to run ..."
    long a = System.currentTimeMillis()

    runJobService.run(JOB_TYPE);

    log.info "eight o'clock job end to run, use ${(System.currentTimeMillis() - a) / 1000} s"
  }
}
