package com.emag.gamecms.controllers

import com.emag.gamecms.domain.system.MagSysRequestmap
import com.emag.gamecms.domain.system.MagSysUser2

class MainController {
    def authenticateService
    def menuService
    private static List<MagSysRequestmap> SOURCE_LIST = new ArrayList<MagSysRequestmap>()

    private static List<MagSysRequestmap> TARGET_LIST = new ArrayList<MagSysRequestmap>()

    def index = {
        if (!authenticateService.isLoggedIn()) {
            redirect controller: 'login', action: 'auth', params: params
        }

        MagSysUser2 user = (MagSysUser2) authenticateService.userDomain()
        session.loginUser = user
        def roles = user?.authorities

        //几个根目录
        def rooturls = MagSysRequestmap.createCriteria().list() {
            and {
                eq("status", 1)
                isNull("father")
            }
            order("orders", 'asc')
        }

        StringBuffer userRoles = new StringBuffer()
        def myUrlList = MagSysRequestmap.createCriteria().list() {
            and {
                or {
                    roles.each {
                        userRoles.append("," + it.authority)
                        like("configAttribute", "%" + it.authority + "%")
                    }
                }
                ne("realUrl", "1")
                eq('status', 1)
            }
        }

        //这个用户有的权限
        def myRootUrlList = MagSysRequestmap.createCriteria().list() {
            and {
                or {
                    roles.each {
                        userRoles.append("," + it.authority)
                        like("configAttribute", "%" + it.authority + "%")
                    }
                }
                ne("realUrl", "1")
                eq('status', 1)
                isNull("father")
            }
            order("orders", 'asc')
        }
        session["roles"] = userRoles.length() > 0 ? userRoles.substring(1) : "";

        [rooturls: rooturls, myUrlList: myUrlList, myRootUrlList: myRootUrlList, menuService: menuService, authenticateService: authenticateService]
    }

    def leftMenu = {
        MagSysUser2 user = (MagSysUser2) authenticateService.userDomain()
        /*
        * 因无法加载SP厂家对象的信息而修改
        *
        * @editor:RongWei
        * @editDate:2010-12-07
        */
        MagSysUser2 suser = MagSysUser2.get(user.id)
        session.loginUser = suser

        def roles = user?.authorities

        //几个根目录
        def rooturls = MagSysRequestmap.createCriteria().list() {
            and {

                eq("status", 1)
                isNull("father")
            }

            order("orders", 'asc')
        }

        /*
        * 存放用户角色标识的变量,并单独存放在Session中
        *
        * @editor:RongWei
        * @editDate:2010-12-07
        */
        StringBuffer userRoles = new StringBuffer()

        //这个用户有的权限
        def myUrlList = MagSysRequestmap.createCriteria().list() {
            and {
                or {
                    roles.each {
                        userRoles.append("," + it.authority)
                        like("configAttribute", "%" + it.authority + "%")
                    }
                }
                ne("realUrl", "1")
                eq('status', 1)
            }
        }
        def myRooturls = MagSysRequestmap.createCriteria().list() {
            and {
                or {
                    roles.each {
                        userRoles.append("," + it.authority)
                        like("configAttribute", "%" + it.authority + "%")
                    }
                }
                eq("status", 1)
                isNull("father")
            }

            order("orders", 'asc')
        }
        session["roles"] = userRoles.length() > 0 ? userRoles.substring(1) : "";

        return [rooturls: rooturls, myRooturls: myRooturls, myUrlList: myUrlList, menuService: menuService]
    }

}
