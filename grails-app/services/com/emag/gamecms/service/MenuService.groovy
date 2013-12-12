package com.emag.gamecms.service

import com.emag.gamecms.domain.system.MagSysRequestmap

class MenuService {

  boolean transactional = false

  private StringBuilder buff = new StringBuilder()


  private List<MagSysRequestmap> rms = new ArrayList<MagSysRequestmap>()

  private List<MagSysRequestmap> myUrlList = new ArrayList<MagSysRequestmap>()

  def serviceMethod() {
  }

  public List<MagSysRequestmap> getRootMenuList() {
    List<MagSysRequestmap> rtnList = new ArrayList<MagSysRequestmap>()

    MagSysRequestmap.createCriteria().list {
      and {
        eq('status', 1)
        isNull("father")
      }
      order('orders', 'asc')
    }
  }

  /**
   * 生成菜单树
   */
  public String generateMenuList(String appName, MagSysRequestmap url, int kk, MagSysRequestmap rootUrl) {
    if (!myUrlList?.contains(url)) {
      return ""
    }
    if (myUrlList?.contains(url)) {
      if (url.equals(rootUrl)) {
        kk++
      }
    }

    buff.append("<li>")
    buff.append("  <span class='").append(url.leaf == 0 ? "folder" : "file").append("'").append(url.equals(rootUrl) ? "id='iddd" + kk + "'" : "").append(" >")
    buff.append(url.leaf == 0 ? url.name : "<a href=\"${appName}/${url.realUrl}\" target=\"rightFrame\">${url.name}</a>")
    buff.append("  </span>")

    if (url.leaf == 0) {
      buff.append("<ul ").append(url.equals(rootUrl) ? "id='id" + kk + "'" : "").append(">")
      url.childs.each {
        if (it.status == 1) {
          generateMenuList(appName, it, kk, url)
        }
      }
      buff.append("</ul>")
    }

    buff.append("</li>")

    return buff.toString()
  }

  /**
   *  新建修改角色时，菜单的生成
   */
  public String generateUrlList(MagSysRequestmap url) {


    if (!myUrlList?.contains(url)) {
      return ""
    }
    String checkStr = rms.contains(url) ? "checked" : ""
    buff.append("<li style='list-style-type: none; width:400px'>")
    buff.append("<span class='").append(url.leaf == 0 ? "folder" : "file").append("' >")
    buff.append("<input type=\"checkbox\" name=\"url\" value=\"${url.id}\" ${checkStr}/>")
    buff.append("$url.name")
    buff.append("  </span>")

    if (url.leaf == 0) {
      buff.append("<ul>")
      url.childs.each {
        generateUrlList(it)
      }
      buff.append("</ul>")
    }

    buff.append("</li>")

    return buff.toString()


  }

  public void initMenuBuff() {
    buff.setLength(0)

  }

  public void setMyUrl(List<MagSysRequestmap> myUrlList) {
    this.myUrlList = myUrlList
  }

  public void setRms(List<MagSysRequestmap> rms) {
    this.rms = rms
  }
}
