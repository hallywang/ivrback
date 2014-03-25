package com.emag.constants

/**

 * function description.

 * <p><h2>Change History</h2>

 * 14-3-11 | hallywang | created

 * </p>

 * @author hallywang

 * @version 1.0.0

 */
class IvrConstants {

  // 操作类型,即高阳接口传来的操作类型
  public static final LinkedHashMap operateIds=new LinkedHashMap()

   //专家类型,分科室
  public static final LinkedHashMap EXPERT_TYPES=new LinkedHashMap()


  static{
    operateIds.put('00002','专家号')
    operateIds.put('0601','挂机')

    EXPERT_TYPES.put("1","内科")
    EXPERT_TYPES.put("2","外科")
    EXPERT_TYPES.put("3","妇科")
    EXPERT_TYPES.put("4","男科")
    EXPERT_TYPES.put("5","老年科")
    EXPERT_TYPES.put("6","儿科")
    EXPERT_TYPES.put("7","五官科")




  }
}
