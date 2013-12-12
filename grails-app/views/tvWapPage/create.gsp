<%@ page import="com.emag.gamecms.domain.template.TvWapTopic" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>新建模板</title>

    <g:javascript library="jquery"/>
    <jq:plugin name="dialog"/>
    <jq:plugin name="fieldselection"/>
    <script type="text/javascript" src="${request.contextPath}/js/wappage.js"></script>
    <script type="text/javascript">
        $().ready(function () {
            // 默认不使用fckedit
            $("#contentHtml").html($("#html1").html());

            $('#contentTypeSelect').change(
                    function () {
                        if (this.value == 'text/plain' || this.value == '自定义html') {
                            $("#contentHtml").html($("#html2").html());
                        } else {
                            $("#contentHtml").html($("#html1").html());
                        }
                    }
            );
        });
    </script>
</head>

<body>
<div id='tagDiv'></div>

<div class="nav">
    <span class="menuButton"><g:link class="list" action="list">模板列表</g:link></span>
</div>

<div class="body">
    <h1>新建模板</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${tvWapPage}">
        <div class="errors">
            <g:renderErrors bean="${tvWapPage}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="save" method="post">
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="name">名称:</label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: tvWapPage, field: 'name', 'errors')}">
                        <input type="text" maxlength="50" id="name" name="name"
                               value="${fieldValue(bean: tvWapPage, field: 'name')}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="contentType">内容类型:</label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: tvWapPage, field: 'contentType', 'errors')}">
                        <g:select id="contentTypeSelect"
                                  from="['auto','自定义html','text/plain','text/xml','wml/card','text/vnd.wap.wml','text/html','application/vnd.wap.xhtml+xml','application/asp_cs','noneContentType']"
                                  name="contentType"
                                  value="${tvWapPage.contentType}"/>
                        切换为其他会导致已经输入内容丢失
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="content">内容:</label>
                    </td>
                    <td valign="top" id="contentHtml"
                        class="value ${hasErrors(bean: tvWapPage, field: 'content', 'errors')}">
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="head">题头:</label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: tvWapPage, field: 'head', 'errors')}">

                        <g:textArea style="height:300;width :600" id="head" name="head"
                                    value="${fieldValue(bean: tvWapPage, field: 'head')}" rows="10" cols="80"/>

                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="description">标题:</label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: tvWapPage, field: 'description', 'errors')}">
                        <input type="text" id="description" name="description"
                               value="${fieldValue(bean: tvWapPage, field: 'description')}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="title">描述:</label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: tvWapPage, field: 'title', 'errors')}">
                        <input type="text" id="title" name="title"
                               value="${fieldValue(bean: tvWapPage, field: 'title')}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="link">链接:</label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: tvWapPage, field: 'link', 'errors')}">
                        <input type="text" id="link" name="link" value="${fieldValue(bean: tvWapPage, field: 'link')}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="topic.id">专题:</label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: tvWapPage, field: 'topic', 'errors')}">
                        <g:select optionKey="id" from="${TvWapTopic.listOrderByName()}" name="topic.id"
                                  value="${tvWapPage?.topic?.id}"></g:select>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="isPublish">是否发布:</label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: tvWapPage, field: 'published', 'errors')}">
                        <g:select name="isPublish" from="${[[okey:'1',ovalue:'发布'],[okey:'0',ovalue:'不发布']]}"
                                  optionKey="okey" optionValue="ovalue"></g:select>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>

        <div class="buttons">
            <span class="button"><input class="save" type="submit" value="完成并提交"/></span>
        </div>
    </g:form>

    <div id="html1" style="display: none">
        <table style="width:600">
            <g:each in="${tagTips}" var="tag" status="tagIndex">
                <g:if test="${tagIndex==0 || tagIndex%10==0}">
                    <tr>
                </g:if>
                <td>
                    <input id="tag" type="button"
                           style="width:70px;height:20px; border: #7f9db9 1px solid; vertical-align:middle;color: #054ea8;background: #d9eefd;margin: 0; padding: 0;"
                           onclick="insertTag(${tag.id})" value="${tag.tagName}"/>

                </td>
                <g:if test="${tagIndex==tagTips.size()-1 || (tagIndex+1)%10==0}">
                    </tr>
                </g:if>
            </g:each>
        </table>
        <g:textArea style="height:300;width :600" id="content" name="content"
                    value="${fieldValue(bean: tvWapPage, field: 'content')}" rows="10" cols="150"/>
    </div>

    <div id="html2" style="display: none">
        <fckeditor:editor id="content"
                          name="content"
                          width="500"
                          height="400"
                          fileBrowser="default">
            ${tvWapPage.content}
        </fckeditor:editor>
    </div>
</div>
</body>
</html>
