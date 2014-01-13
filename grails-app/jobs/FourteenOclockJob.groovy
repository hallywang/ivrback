/**
 * 14点运行一次的job
 */
class FourteenOclockJob {
  static triggers = {
    // 秒、分、时、日、月、周
    cron name: 'fourteenOclockJobTrigger', cronExpression: "0 0 14 * * ?"
  }

  def runJobService
  private static final String JOB_TYPE = 'fourteenOclock'

  def execute() {
    log.info "fourteen o'clock job start to run ..."
    long a = System.currentTimeMillis()

    runJobService.run(JOB_TYPE)

    log.info "fourteen o'clock job end to run, use ${(System.currentTimeMillis() - a) / 1000} s"
  }
}
