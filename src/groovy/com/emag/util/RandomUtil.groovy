package com.emag.util

/**
 * emag softeware All Rights Reserved@2010-2012
 * 作者: 王海利
 * 日期: 2010-8-5
 * 时间: 15:16:37
 * 说明: 该类实现。。。。
 * 修改记录:
 * 1.创建文件
 */
class RandomUtil {
  /**
   *  得到指定个数的大于某个数的不重复随机数
   * @param num 随机数个数
   * @param max 随机数范围最大值
   * @param randomMin 随机数要大于的值
   * @return list
   */
  public static List getRandomInt(int num, int max, int randomMin) {
    List srcList = []
    for (int i = randomMin; i < max; i++) {
      srcList.add(i)
    }
    if(max==num){
      return srcList
    }
    if (srcList.size() > num) {
      for (int k = 0; k < (max - randomMin - num); k++) {
        srcList.remove(new Random().nextInt(srcList.size()))
      }
    } else {
      srcList = []
    }
    return srcList
  }

  public static void main(String[] arts) {
    getRandomInt(100, 100,90).each {
      print it
      print ","
    }
  }
  /**
   *
   * @param num 随机数个数
   * @param max 随机数范围最大值
   * @param randomMin 随机数要大于的值
   * @return list
   */
  public static List getRandomIntCommon(int num, int max, int randomMin) {
    def l = []
    while (l.size() != num) {
      int r = new Random().nextInt(max)
      if (r > randomMin) {
        l.add(r)
      }
    }
    return l
  }

}
