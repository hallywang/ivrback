<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>

    </title>
    <g:javascript library="jquery"/>
    <script type="text/javascript">
        $().ready(function() {
            //全选
            $("#checkall").click(
                    function() {
                        $("input[name='provId']").each(
                                function() {
                                    this.checked = document.getElementById('checkall').checked;
                                }
                                )
                    }
                    );
            $("#cancleButton").click(function() { //取消按钮，关闭对话框
                window.parent.close();
            });

            $("#insertButton").click(function() {  //插入标签到模板编辑框
                var provArray = document.getElementsByName("provId");
                var provIds = "";
                for (var i = 0; i < provArray.length; i++) {
                    if (provArray[i].checked == true) {
                        provIds += (provArray[i].value + ",");
                    }
                }
                provIds = provIds.substring(0, (provIds.length - 1));

                window.parent.returnValue = provIds;
                window.parent.close();
            });


        });

    </script>
    <base target="_self">
</head>
<body>

<div class="body">
    <div class="list">
        <table border="0" cellspacing="0" cellpadding="0">
            <thead>
            <tr>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <g:each in="${provinceList}" var="province" status="i">
                <g:if test="${i % 5 == 0}"><tr></g:if>
                <td>
                    <input type="checkbox" name="provId" value="${province.provId}"/>${province.provName}
                </td>

                <g:if test="${i == (provinceList.size()-1)}">
                    <%
                        for (int j = 0; j < 5 - provinceList.size() % 5; j++) {
                            println "<td>&nbsp;</td>"
                        }
                    %>
                </g:if>
                <g:if test="${(i+1) % 5 == 0}"></tr></g:if>
            </g:each>
        </table>

        <div class="buttons">
            <input id="checkall" type="checkbox" value=""/>全选 &nbsp;
            <span class="button"><input id="insertButton" class="save" type="button" value="确认"/></span>
            <span class="button"><input id="cancleButton" class="delete" type="button" value="取消"/></span>
        </div>
    </div>
</div>

</body>

</html>