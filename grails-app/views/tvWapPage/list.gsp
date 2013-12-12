<%@ page import="com.emag.gamecms.domain.template.TvWapTopic; com.emag.gamecms.domain.template.TvWapPage" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>模板列表</title>
    <g:javascript library="jquery"/>
    <jq:plugin name="dialog"/>
    <jq:plugin name="fieldselection"/>
    <script type="text/javascript" src="${request.contextPath}/js/wappage.js"></script>

</head>
<body>
<div id='tagDiv'></div>
<div class="nav">
    <span class="menuButton"><g:link class="create" action="create">新建模板</g:link></span>
</div>
<div class="body">
    <h1>模板列表</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

     %{--添加查询条件--}%
    <g:form name="myForm" action="list">
        <div class="list">
            <tr>
              <td>
                &nbsp;关键字<input type="text" name="description" value="${params['description']}" maxlength="11" style="width:100px;">
                &nbsp;专题 <g:select name="topicId" from="${TvWapTopic.listOrderByName()}" optionKey="id" value="${params['topicId']}" noSelection="${['' : '-- 请选择 --']}"></g:select>
                <span class="button"><input class="save" type="submit" value="查询" /></span>
              </td>
            </tr>
        </div>
      </g:form>
  
    <div class="list">
        <table>
            <thead>
            <tr>

                <g:sortableColumn property="id" title="编号" width="5%"/>

                <g:sortableColumn property="name" title="名称" width="10%"/>
                <th>访问地址</th>
                <g:sortableColumn property="description" title="描述"/>

                <g:sortableColumn property="title" title="标题" width="8%"/>

                <g:sortableColumn property="contentType" title="内容类型"/>

                <g:sortableColumn property="topic" title="专题" width="6%"/>

                <g:sortableColumn property="published" title="是否发布"/>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${tvWapPageList}" status="i" var="tvWapPage">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="show" id="${tvWapPage.id}">${fieldValue(bean: tvWapPage, field: 'id')}</g:link></td>

                    <td>${fieldValue(bean: tvWapPage, field: 'name')}</td>
                    <td>wap/${tvWapPage.topic.name}/${tvWapPage.name}</td>
                    <td>${fieldValue(bean: tvWapPage, field: 'description')}</td>

                    <td>${fieldValue(bean: tvWapPage, field: 'title')}</td>

                    <td>${fieldValue(bean: tvWapPage, field: 'contentType')}</td>

                    <td>${tvWapPage.topic}</td>

                    <td>
                      <g:if test="${tvWapPage.published == '1'}">
                        已发布
                      </g:if>
                      <g:else>
                        未发布
                      </g:else>
                    </td>

                    <td>
                        <g:link action="show" id="${tvWapPage.id}">查看</g:link>
                        <g:link action="edit" id="${tvWapPage.id}">编辑</g:link>
                        <g:link action="delete" onclick="return confirm('确定删除吗?');" id="${tvWapPage.id}">删除</g:link>
                        <input id="tag" type="button" style="width:30px;height:20px; border: #7f9db9 1px solid; vertical-align:middle;color: #054ea8;background: #d9eefd;margin: 0; padding: 0;"
                           onclick="viewPage('wap_${tvWapPage.topic.name}_${tvWapPage.name}');" value="预览"/>

                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
    <div class="paginateButtons">
        <g:paginate total="${tvWapPageTotalCount}" params="${params}"/>
    </div>
</div>
</body>
</html>
