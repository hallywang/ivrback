package com.emag.wabao

import com.vivame.util.TimeUtil

/**
 * 返回龙币文本的文件名，文件名格式：103_10303_20111129_001.txt：
 * 103_10303 固定，
 * 20111129 是“今日”日期，注意 20111129 文件里面存放的是 28 号的数据
 * 001 为“今日”第一个文件，每个文件里记录条数最多5w行
 * User: linguangfa
 * Date: 2011-11-30
 * Time: 15:57:07
 */
class FileNameTool {
  /** 记录总行数      */
  private int totalLines

  /** 根据记录总行数和每个文本可容纳记录数计算出来的文本数量      */
  private int fileCnt

  /** 保存文件名列表      */
  private List<String> fileNameList = new ArrayList<String>()

  /** 每个文本可容纳记录数      */
  private static final int MAX_LINES = 200000

  /**
   * 构造函数
   * @param totalines 总记录数
   */
  public FileNameTool(int totalLines) {
    this.totalLines = totalLines

    //fileNameList.clear() // 文件列表为静态，每次new操作时必须清空，否则该列表中的内容会越来越大
    fileCnt = (totalLines - 1) / MAX_LINES + 1
    for (int i = 0; i < fileCnt; i++) {
      StringBuilder buff = new StringBuilder()
      buff <<'/JOYBEAN_PRESENT_'
      buff << TimeUtil.getDate(new Date() - 1,"yyyyMMdd") << '_'  //日期
      buff << (i + 1) // 文件序号
      buff << '.txt.writing' // 文件后缀

      fileNameList.add(buff.toString())
    }
  }

/**
 * 获取当前记录的文件名
 * 第 1 行记录 至 第 50000 行记录放入 001 文本
 * 第 50001 行记录 至 100000 行记录放入 002 文本
 * 依次类推
 * @lineNum 当前记录的行号
 */
  public String getCurrentFileName(int currentLineNo) {
    // 根据记录的行号来计算出该条记录应该写入的文件名
    int fileNameIndex = ((currentLineNo - 1) / MAX_LINES + 1) - 1

    return fileNameList[fileNameIndex]
  }

/**
 * 获取上一条记录的文件名
 * 比如当前记录是第 50000 行，则上一条记录是49999行，上一条记录的文件序号是 001，当前记录的文件序号也是是001
 * 比如当前记录是第 50001 行，则上一条记录是 50000行，上一条记录的文件序号是 001，当前记录的文件序号则是002
 */
  public String getPreFileName(int currentLineNo) {
    if (currentLineNo == 1) {
      return null
    }
    return getCurrentFileName(currentLineNo - 1)
  }

  public List<String> getFileNameList() {
    return fileNameList
  }

  public static void main(String[] aa) {

  }

}
