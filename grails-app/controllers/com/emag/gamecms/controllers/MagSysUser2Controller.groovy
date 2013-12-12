package com.emag.gamecms.controllers

import com.emag.gamecms.domain.system.MagSysDept
import com.emag.gamecms.domain.system.MagSysRole
import com.emag.gamecms.domain.system.MagSysUser2

/**
 * User controller.
 */
class MagSysUser2Controller {


  def authenticateService

  // the delete, save and update actions only accept POST requests
  def static allowedMethods = [delete: 'POST', save: 'POST', update: 'POST']

  def index = {
    redirect(action: list, params: params)
  }

  def list = {
    if (!params.max) {
      params.max = 10
    }
    [personList: MagSysUser2.list(params)]
  }

  def show = {
    [person: MagSysUser2.get(params.id)]
  }

  /**
   * Person delete action. Before removing an existing person,
   * he should be removed from those authorities which he is involved.
   */
  def delete = {

    def person = MagSysUser2.get(params.id)
    if (person) {
      def authPrincipal = authenticateService.principal()
      //avoid self-delete if the logged-in user is an admin
      if (!(authPrincipal instanceof String) && authPrincipal.username == person.username) {
        flash.message = "You can not delete yourself, please login as another admin and try again"
      }
      else {
        //first, delete this person from People_Authorities table.
        MagSysRole.findAll().each { it.removeFromPeople(person) }
        person.delete()
        flash.message = "MagSysUser2 ${params.id} deleted."
      }
    }
    else {
      flash.message = "MagSysUser2 not found with id ${params.id}"
    }

    redirect(action: list)
  }

  def edit = {

    def person = MagSysUser2.get(params.id)
    if (!person) {
      flash.message = "MagSysUser2 not found with id ${params.id}"
      redirect(action: list)
      return
    }
    MagSysUser2 loginUser = MagSysUser2.get(((MagSysUser2) session.loginUser).id);
    def roles = []
    if (isSysAdminRole(loginUser)) {
      roles = MagSysRole.list();
    } else {   // 非 admin sysuser
      roles = MagSysRole.findAll("from MagSysRole as m where  m.dept.id=:dept", [dept: loginUser.getDept().id])
    }
    [person: person, deptList: getDeptList(loginUser), authorityList: roles]
  }

  /**
   * Person update action.
   */
  def update = {

    def person = MagSysUser2.get(params.id)
    if (!person) {
      flash.message = "MagSysUser2 not found with id ${params.id}"
      redirect(action: edit, id: params.id)
      return
    }

    def oldPassword = person.passwd
    person.properties = params
    if (!params.passwd.equals(oldPassword)) {
      person.passwd = authenticateService.encodePassword(params.passwd)
    }
    if (person.save()) {
      MagSysRole.findAll().each { it.removeFromPeople(person) }
      addRoles(person)
      redirect(action: show, id: person.id)
    }
    else {
      render(view: 'edit', model: [person: person, authorityList: MagSysRole.list()])
    }
  }

  def create = {
    def person = new MagSysUser2()
    person.properties = params
    MagSysUser2 loginUser = MagSysUser2.get(((MagSysUser2) session.loginUser).id);
    def roles = []
    if (isSysAdminRole(loginUser)) {
      roles = MagSysRole.list();
    } else {   // 非 admin sysuser
      roles = MagSysRole.findAll("from MagSysRole as m where  m.dept.id=:dept", [dept: loginUser.getDept().id])
    }
    [person: person, deptList: getDeptList(loginUser), authorityList: roles]
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
    for (MagSysRole role: loginUser.authorities) {
      if (role.authority == "ROLE_ADMIN" || role.authority == "ROLE_SYSUSER") {
        flag = true;
        break;
      }
    }
    return flag;
  }

  //判断是否是系统管理员角色
  def isSysAdminRole(MagSysUser2 user) {
    boolean flag = false;

    user?.authorities?.each {

      if (it.authority == 'ROLE_ADMIN' || it.authority == 'ROLE_SYSUSER') {
        flag = true
      }
    }

    return flag

  }

  /**
   * Person save action.
   */
  def save = {

    def person = new MagSysUser2()
    person.properties = params
    person.passwd = authenticateService.encodePassword(params.passwd)
    if (person.save()) {
      addRoles(person)
      redirect(action: show, id: person.id)
    }
    else {
      render(view: 'create', model: [authorityList: MagSysRole.list(), person: person])
    }
  }

  private void addRoles(person) {
    for (String key in params.keySet()) {
      if (key.contains('ROLE') && 'on' == params.get(key)) {
        MagSysRole.findByAuthority(key).addToPeople(person)
      }
    }
  }

  /**
   * 进入修改密码页面
   */
  def editPass = {
    [userInfo: session.loginUser]
  }

  def updatePass = {
    def oldUser = MagSysUser2.get(params.id)
    if (oldUser) {
      if (authenticateService.encodePassword(params.passwd).equals(oldUser.getPasswd())) {
        if (params.pass?.equals(params.pass1)) {
          oldUser.passwd = authenticateService.encodePassword(params.pass)
          oldUser.save(flush: true)
          flash.message = "密码修改成功！"
        } else {
          //两次输入的密码不正确
          flash.message = "两次输入的密码不一致！"
        }
      } else {
        // 输入的原始密码不正确
        flash.message = "输入的原始密码不正确！"
      }

    } else {

      flash.message = "MagSysUser2 with id ${params.id} is not existing "
    }

    redirect(action: editPass)
  }
}
