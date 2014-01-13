/**
 * 每天凌晨两点和早晨八点各运行一次
 */
class TwoOclockJob {
  static triggers = {
    // 秒、分、时、日、月、周
    cron name: 'twoOclockJobTrigger', cronExpression: "0 0 2 * * ?"
  }

  private static final String JOB_TYPE = 'twoOclock'
  def runJobService

  def execute() {
    log.info "two o'clock job start to run ..."
    long a = System.currentTimeMillis()

    runJobService.run(JOB_TYPE);

    log.info "two o'clock job end to run, use ${(System.currentTimeMillis() - a) / 1000} s"
  }
}
