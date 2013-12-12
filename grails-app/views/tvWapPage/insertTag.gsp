<g:form action="insertTagSave" method="post">
    <div class="body">
   <div class="list">
    ${tagtip.tagName}

    <table border="0" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <input type="hidden" id="tagcontent" name="tagcontent" value="${fieldValue(bean: tagtip, field: 'tagContent')}"/>
        <input type="hidden" id="tagCode" name="tagCode" value="${fieldValue(bean: tagtip, field: 'tagCode')}"/>

        <g:each in="${tagtip.tagParams}" var="tagParam" status="paramIndex">
            <tr>
                <td><g:if test="${!tagParam.nullAble}">
                    <span style="color:red">*</span>
                </g:if>${tagParam.paramName}</td>
                <td>
                    <input style="background-color:#fbfcff;border:1px solid #ccc;font:12px verdana, arial, helvetica, sans-serif;margin:2px 0;padding:2px 4px;" type="text" id="paramValue${paramIndex}" name="paramValue${paramIndex}" value="${tagParam.defaultValue}"/>
                    <input type="hidden" id="paramCode${paramIndex}" name="paramCode${paramIndex}" value="${tagParam.paramCode}"/>
                    <input type="hidden" id="nullAble${paramIndex}" name="nullAble${paramIndex}" value="${tagParam.nullAble}"/>

                </td>
                <td style="white-space:normal; width:300px;">
                    <g:if test="${tagParam.paramType == 1}">
                        <input id="tag" type="button"
                                style="width:60px;height:20px; border:1px solid black;vertical-align:middle"
                                onclick="show('paramValue${paramIndex}')" value="${tagParam.paramDesc}"/>
                    </g:if>
                    <g:else>
                        ${tagParam.paramDesc}
                    </g:else>
                </td>
            </tr>
        </g:each>

    </table>

    <div class="buttons">

        <span class="button"><input id="insertButton" class="save" type="button" value="插入"/></span>

        <span class="button"><input class="delete" type="button" id="cancleButton" value="取消"/></span>
    </div>
</g:form>
</div>
</div>

<script type="text/javascript">$().ready(function() {

    var paramcodes = new Array(); //参数代码
    var paramvalues = new Array(); //参数值
    var nullAbles = new Array(); // 参数是否允许空

    var paramcount = ${tagtip.tagParams.size()};

    $("#cancleButton").click(function() { //取消按钮，关闭对话框


        $('#tagDiv').dialog('close');
        $('#tagDiv').dialog('destroy');
    });

    $("#insertButton").click(function() {  //插入标签到模板编辑框
        var pagecontent = $("#content");

        var content = $("#tagcontent").val();
        var tagcode = $("#tagCode").val();

        for (var i = 0; i < paramcount; i++) {
            paramcodes[i] = $('#paramCode' + i).val();
            paramvalues[i] = $('#paramValue' + i).val();
            nullAbles[i] = $('#nullAble' + i).val();
        }

        for (var j = 0; j < paramcount; j++) {

            // alert(nullAbles[j]);
            if (nullAbles[j] == 'false' && paramvalues[j] == '') {
                alert("带有红星标记的必填");
                return false;
            }

        }

        // pagecontent.replaceSelection("\r\n"+dealTagParam(tagcode, paramcodes, paramvalues)+"\r\n"+content+"\r\n"+dealTagEnd(tagcode));

        pagecontent.val(pagecontent.val().replace(String.fromCharCode(1), dealTagParam(tagcode, paramcodes, paramvalues) + "\r\n" + content + "\r\n" + dealTagEnd(tagcode)))


        $('#tagDiv').dialog('close');
        $('#tagDiv').dialog('destroy');
    });


});

function show(id) {

    var w = 750;
    var h = 400;
    var result;
    result = showModalDialog("${request.getContextPath()}/tvWapPage/areas", window, "status:no;location:no;titlebar:no;dialogHeight: " + h + "px; dialogWidth: " + w + "px;status:no;scroll:yes;center:yes;help:no");
    //alert(result);
    if (result != null) {
        $("#"+id).val(result);
    }
}

</script>