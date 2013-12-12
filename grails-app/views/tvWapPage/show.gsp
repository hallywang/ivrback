<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <title>模板详情</title>
</head>
<body>
<div class="nav">
  <span class="menuButton"><g:link class="list" action="list">模板列表</g:link></span>
  <span class="menuButton"><g:link class="create" action="create">新建模板</g:link></span>
</div>
<div class="body">
  <h1>模板详情</h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <div class="dialog">
    <table>
      <tbody>

      <tr class="prop">
        <td valign="top" class="name">编号:</td>

        <td valign="top" class="value">${fieldValue(bean: tvWapPage, field: 'id')}</td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name">名称:</td>

        <td valign="top" class="value">${fieldValue(bean: tvWapPage, field: 'name')}</td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name">标题:</td>

        <td valign="top" class="value">${fieldValue(bean: tvWapPage, field: 'description')}</td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name">描述:</td>

        <td valign="top" class="value">${fieldValue(bean: tvWapPage, field: 'title')}</td>

      </tr>

       <tr class="prop">
        <td valign="top" class="name">是否发布:</td>

        <td valign="top" class="value">
          <g:if test="${tvWapPage.published == '1'}">
             已发布
          </g:if>
          <g:else>
             未发布
          </g:else>
        </td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name">链接:</td>

        <td valign="top" class="value">${fieldValue(bean: tvWapPage, field: 'link')}</td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name">内容:</td>

        <td valign="top" class="value">
          <g:textArea style="height:300;width :600" id="content" name="content" value="${content}" rows="10" cols="80"/>

        </td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name">内容类型:</td>

        <td valign="top" class="value">${fieldValue(bean: tvWapPage, field: 'contentType')}</td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name">题头:</td>

        <td valign="top" class="value">
          <g:textArea style="height:300;width :600" id="head" name="head" value="${tvWapPage.head}" rows="10" cols="80"/>

        </td>

      </tr>

      <tr class="prop">
        <td valign="top" class="name">专题:</td>

        <td valign="top" class="value"><g:link controller="tvWapTopic" action="show" id="${tvWapPage?.topic?.id}">${tvWapPage?.topic?.encodeAsHTML()}</g:link></td>

      </tr>

      </tbody>
    </table>
  </div>

  <div class="buttons">
    <g:form>
      <input type="hidden" name="id" value="${tvWapPage?.id}"/>
      <span class="button"><g:actionSubmit class="edit" value="编辑" action="edit"/></span>
      <span class="button"><g:actionSubmit class="delete" onclick="return confirm('确定要删除模板吗?');" value="删除" action="Delete"/></span>
    </g:form>
  </div>
</div>
</body>
</html>
