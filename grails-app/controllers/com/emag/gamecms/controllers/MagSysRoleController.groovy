package com.emag.gamecms.controllers

import com.emag.gamecms.domain.system.MagSysDept
import com.emag.gamecms.domain.system.MagSysRequestmap
import com.emag.gamecms.domain.system.MagSysRole
import com.emag.gamecms.domain.system.MagSysUser2

/**
 * Authority Controller.
 */
class MagSysRoleController {

  // the delete, save and update actions only accept POST requests
  def static allowedMethods = [save: 'POST', update: 'POST']
  def authenticateService
  def menuService
  def index = {
    redirect(action: list, params: params)
  }

  def list = {
    if (!params.max) {
      params.max = 10
    }
    MagSysUser2 loginUser = MagSysUser2.get(((MagSysUser2) session.loginUser).id);

    def authorities = MagSysRole.createCriteria().list(params) {
      //添加查询条件
      if (params.authority) {
        like('authority', "%${params.authority}%")
      }
      if (params.deptId) {
        eq('dept.id', Long.parseLong(params.deptId))
      }
      if (params.description) {
        like('description', "%${params.description}%")
      }
      if(!isAdmin(loginUser)){
        ne('authority',"ROLE_ADMIN")
      }

    }
    [deptList: getDeptList(loginUser), authorityList: authorities, authoritiesTotalCount: authorities?.totalCount]
  }

  private List<MagSysDept> getDeptList(loginUser) {
    boolean isAdmin = isAdmin(loginUser);
    List<MagSysDept> deptList = new ArrayList<MagSysDept>();
    if (isAdmin) {
      deptList = MagSysDept.findAll("from MagSysDept d");
    } else {
      Long deptId = 0;
      if (loginUser.dept) {
        deptId = loginUser.dept.id;
      }
      deptList = MagSysDept.findAll("from MagSysDept d where d.id = :aa", ["aa": deptId]);
    }
    return deptList;
  }

  private boolean isAdmin(loginUser) {
    boolean flag = false;
    for (MagSysRole role : loginUser.authorities) {
      if (role.authority == "ROLE_ADMIN") {
        flag = true;
        break;
      }
    }
    return flag;
  }

  def show = {
    def authority = MagSysRole.get(params.id)
    def rms = MagSysRequestmap.findAllByConfigAttributeLike('%' + authority.authority + '%')


    [authority: authority, rms: rms]
  }

  def delete = {

    def authority = MagSysRole.get(params.id)
    if (!authority) {
      flash.message = "MagSysRole not found with id ${params.id}"
      redirect(action: list)
      return
    }

    String oldRole = authority.authority
    def rms = MagSysRequestmap.findAllByConfigAttributeLike('%' + oldRole + '%')
    rms.each {
      if (it.configAttribute.indexOf(',') >= 0) {
        List parts = it.configAttribute.split(',') as List
        parts.remove oldRole
        it.configAttribute = parts.join(',')
      } else if (it.configAttribute.equals(oldRole)) {
        it.configAttribute = ''
      }
    }
    authority.delete()
    authenticateService.clearCachedRequestmaps()


    flash.message = "MagSysRole ${params.id} deleted."

    redirect(action: list)
  }

  def edit = {
    def authority = MagSysRole.get(params.id)

    if (!authority) {
      flash.message = "MagSysRole not found with id ${params.id}"
      redirect(action: list)
      return
    }
    def rms = MagSysRequestmap.findAllByConfigAttributeLike('%' + authority.authority + '%')

    def user = authenticateService.userDomain()
    def roles = user?.authorities

    def urls = MagSysRequestmap.createCriteria().list() {
      and {
        or {

          roles.each {
            like("configAttribute", "%" + it.authority + "%")
          }

        }

        isNull("father")
      }


    }

    //这个用户有的权限
    def myUrlList = MagSysRequestmap.createCriteria().list() {
      and {
        or {
          roles.each {
            like("configAttribute", "%" + it.authority + "%")
          }
        }
        ne("realUrl", "1")
        //eq('status', 1)
      }
    }
    [authority: authority, rms: rms, urls: urls, myUrlList: myUrlList, menuService: menuService]
  }

  /**
   * Authority update action. When updating an existing authority instance, the requestmaps which contain
   * them should also be updated.
   */
  def update = {

    def authority = MagSysRole.get(params.id)
    if (!authority) {
      flash.message = "MagSysRole not found with id ${params.id}"
      redirect(action: edit, id: params.id)
      return
    }

    String oldRole = authority.authority
    authority.properties = params
    String role = params.authority
    authority.authority = 'ROLE_' + role.toUpperCase()

    def rms = MagSysRequestmap.findAllByConfigAttributeLike('%' + oldRole + '%')
    rms.each {
      if (it.configAttribute.indexOf(',') >= 0) {
        List parts = it.configAttribute.split(',') as List
        parts.remove oldRole
        it.configAttribute = parts.join(',')
      } else if (it.configAttribute.equals(oldRole)) {
        it.configAttribute = ''
      }
    }

    def urls = request.getParameterValues("url")
    if (authority.save()) {
      urls.each {
        def r = MagSysRequestmap.get(it)
        List parts = []
        if (r.configAttribute) { //防止为空
          parts = r.configAttribute.split(',') as List
        }

        parts.add authority.authority


        r.configAttribute = parts.join(',')
        r.save()
      }
      authenticateService.clearCachedRequestmaps()
      redirect(action: show, id: authority.id)
    } else {
      render(view: 'edit', model: [authority: authority])
    }
  }

  def create = {
    def authority = new MagSysRole()
    authority.authority = ''
    authority.properties = params

    def user = authenticateService.userDomain()
    def roles = user?.authorities

    //获取一级菜单
    def urls = MagSysRequestmap.createCriteria().list() {
      and {
        or {


          roles.each {
            like("configAttribute", "%" + it.authority + "%")
          }
        }
        isNull("father")  //父节点为空，表示该菜单为一级菜单
      }
    }

    //这个用户有的权限
    def myUrlList = MagSysRequestmap.createCriteria().list() {
      and {


        or {
          roles.each {
            like("configAttribute", "%" + it.authority + "%")
          }
        }

        ne("realUrl", "1")
      }
    }

    [authority: authority, urls: urls, myUrlList: myUrlList, menuService: menuService]
  }

  /**
   * Authority save action.
   */
  def save = {

    def authority = new MagSysRole()
    String au = params.authority
    authority.properties = params
    //here translate user's input to the required format
    authority.authority = 'ROLE_' + au.toUpperCase()
    if (authority.save()) {

      def urls = request.getParameterValues("url")

      urls.each {
        def r = MagSysRequestmap.get(it)
        List parts = r.configAttribute.split(',') as List
        parts.add authority.authority
        r.configAttribute = parts.join(',')
        r.save()
      }


      authenticateService.clearCachedRequestmaps()
      redirect(action: show, id: authority.id)
    } else {
      render(view: 'create', model: [authority: authority])
    }
  }
}
