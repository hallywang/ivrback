function formatContent() {
    var codes = $('#content1').html();
    //所有的链接全部换成单引号
    codes = codes.replace(/\"/g, "\'");
    codes = codes.replace(/<br>/g, "<br/>");
    codes = codes.replace(/<BR>/g, "<BR/>");
    codes = codes.replace(/amp;/g, "");
    codes = codes.replace(/&/g, "&amp;");
    $('#content').val(codes);
}

//上移
function upDIV(id) {
    $("#div" + id).insertBefore($("#div" + id).prev());
    formatDIV();
}

//下移
function downDIV(id) {
    $("#div" + id).insertAfter($("#div" + id).next());
    formatDIV();
}

//tr初始化
function formatDIV() {
    $("#content1").find("div[class=main]:first").find("input[class=up]").remove();
    $("#content1").find("div[class=main]:last").find("input[class=down]").remove();
    var issuccess = "true";
    //判断文字修改的是否有错
    $("#content1").find("input").each(
            function() {
                if ($(this).attr("type") == "text") {
                    issuccess = "false";
                    return false;   //退出each循环
                }
            }
            );
    if (issuccess == "false") {
        alert("还有未修改成功的内容，请确定");
        return false;
    }
    $("#content1").find("div[class=right]").remove();
    //$("#content1").find("div[class=main]:eq(0)").append(b);
    $("#content1").find("div[class=main]").each(
            function(i) {
                //alert(i + $(this).attr("id")) ;
                var id = $(this).attr("id");
                var divid = id.substring(3);
                var imagebutton = "<div class='right' style='float:right;padding-right:35px;'><input type='button' onclick='delDIV(" + divid + ")' value='删除'/><input type='button' onclick='insertDIV(" + divid + ")' value='插入'/><input type='button' onclick='upDIV(" + divid + ")' value='上移' class='up' /><input type='button' onclick='downDIV(" + divid + ")' value='下移' class='down' /></div>";
                var b = "<div class='right' style='float:right;padding-right:35px;'><input type='button' onclick='updateDIV(" + divid + ")' value='修改'/><input type='button' onclick='delDIV(" + divid + ")' value='删除'/><input type='button' onclick='insertDIV(" + divid + ")' value='插入'/><input type='button' onclick='upDIV(" + divid + ")' value='上移' class='up' /><input type='button' onclick='downDIV(" + divid + ")' value='下移' class='down'/></div>";
                var abutton = "<div class='right' style='float:right;padding-right:35px;'><input type='button' onclick='updateTitle(" + divid + ")' value='修改'/><input type='button' onclick='delDIV(" + divid + ")' value='删除'/><input type='button' onclick='insertDIV(" + divid + ")' value='插入'/><input type='button' onclick='upDIV(" + divid + ")' value='上移' class='up' /><input type='button' onclick='downDIV(" + divid + ")' value='下移' class='down'/></div>";

                if ($(this).children().children().eq(0).is("img")) {
                    $(this).find("br").before(imagebutton);
                } else if ($(this).children().children().eq(0).is("a")) {
                    $(this).find("br").before(abutton);
                } else {
                    $(this).find("br").before(b);
                }

            }
            );
    $("#content1").find("div[class=main]:first").find("input[class=up]").remove();
    $("#content1").find("div[class=main]:last").find("input[class=down]").remove();

}

//删除
function delDIV(did) {
    if (document.getElementById("div" + did)) {
        $("#div" + did).remove();
    }
    formatContent();
    formatDIV();
}

//文字编辑
function updateDIV(did) {
    var con = $("#div" + did).children().html().trim();
    var result = "<div style='float:left' class='left'>  <input type='text' id='c" + did + "' value='" + con + "' maxlength='15'  size='30' onkeyup=\"value = value.replace(/[^\a-zA-Z0-9\u4e00-\u9fa5]+$/g, '')\" onbeforepaste=\"clipboardData.setData('text', clipboardData.getData('text').replace(/[^\a-zA-Z0-9\u4e00-\u9fa5]+$/g, ''))\"  /> </div><div class='right' style='float:right;padding-right:35px;'> <input type='button' onclick='updateSaveDIV(" + did + ")' value='确定'/> </div> <br> ";
    document.getElementById("div" + did).innerHTML = result;
    //formatContent();
}

//游戏链接内容编辑
function updateTitle(did) {
    //alert($("#div" + did).find("a:eq(0)").attr("href"));
    var con = $("#div" + did).find("a:eq(0)").html().trim();
    var path = $("#div" + did).find("a:eq(0)").attr("href");
    var result = "<div style='float:left' class='left'>  <input type='text' id='c" + did + "' value='" + con + "' maxlength='20'  size='30'  onkeyup=\"value = value.replace(/[^\a-zA-Z0-9\u4e00-\u9fa5]+$/g, '')\"  onbeforepaste=\"clipboardData.setData('text', clipboardData.getData('text').replace(/[^\a-zA-Z0-9\u4e00-\u9fa5]+$/g, ''))\" /> <input type='hidden' id='h" + did + "' value='" + path + "' /> </div><div class='right' style='float:right;padding-right:35px;'> <input type='button' onclick='updateSaveTitle(" + did + ")' value='确定'/> </div> <br> ";
    document.getElementById("div" + did).innerHTML = result;
    //formatContent();
}

function updateSaveTitle(did) {
    var newc = document.getElementById("c" + did).value;
    var path = document.getElementById("h" + did).value;
    if (newc.trim().length == 0) {
        alert("游戏链接显示不能为空");
        return;
    }
    var result = "<div style='float:left' class='left'><a href='" + path + "'  > " + newc + " </a></div><div class='right' style='float:right;padding-right:35px;'><input type='button' onclick='updateTitle(" + did + ")' value='修改'/> <input type='button' onclick='delDIV(" + did + ")' value='删除'/> <input type='button' onclick='insertDIV(" + did + ")' value='插入'/> <input type='button' onclick='upDIV(" + did + ")' value='上移' class='up'/> <input type='button' onclick='downDIV(" + did + ")' value='下移' class='down'/> </div> <br>";
    document.getElementById("div" + did).innerHTML = result;
    formatContent();
    formatDIV();
}

//文字编辑修改
function updateSaveDIV(did) {
    var newc = document.getElementById("c" + did).value;
    if (newc.trim().length == 0) {
        alert("文字不能为空");
        return;
    }
    var result = "<div style='float:left' class='left'> " + newc + "  </div><div class='right' style='float:right;padding-right:35px;'> <input type='button' onclick='updateDIV(" + did + ")' value='修改'/> <input type='button' onclick='delDIV(" + did + ")' value='删除'/> <input type='button' onclick='insertDIV(" + did + ")' value='插入'/> <input type='button' onclick='upDIV(" + did + ")' value='上移' class='up'/> <input type='button' onclick='downDIV(" + did + ")' value='下移' class='down'/> </div> <br>  ";
    document.getElementById("div" + did).innerHTML = result;
    formatContent();
    formatDIV();
}

String.prototype.trim = function() {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}

//保存，更新之前处理div
function checkContent() {
    var issuccess = "true";
    //判断文字修改的是否有错
    $("#content1").find("input").each(
            function() {
                if ($(this).attr("type") == "text") {
                    issuccess = "false";
                    return false;   //退出each循环
                }
            }
            );
    if (issuccess == "false") {
        alert("模板内容没正确修改，请重试");
        return false;
    }
    var content = "";
    //必选项都填写后才处理div
    if ($("#name").val().trim().length > 0 && $("#title").val().trim().length > 0 && $("#content").val().trim().length > 0) {
        $("#content1").find("div[class=right]").remove();
        $("#content1").find("div[class=main]").each(
                function() {
                    var id = $(this).attr("id");
                    if ($(this).children().children().eq(0).is("img")) {
                        var src = $($(this)).find("img:eq(0)").attr("src");
                        content += "<div id='" + id + "' class='main' style='height:35px;'> <div style='float:left' class='left'><img src='" + src + "' height='35' width='160'> </img> </div><br></div>";
                    } else if ($(this).children().children().eq(0).is("a")) {
                        var href = $($(this)).find("a:eq(0)").attr("href");
                        var title = $(this).children().children().html();
                        content += "<div id='" + id + "' class='main' style='height:35px;'> <div style='float:left' class='left'><a href='" + href + "'>" + title + "</a> </div><br></div>";
                    } else {
                        content += "<div id='" + id + "' class='main' style='height:35px;'><div style='float:left' class='left'> " + $(this).children().html() + " </div><br></div>";
                    }
                    //}


                }
                )
        //替换单引为双引
        content = content.replace(/\"/g, "\'");
        //br加上闭包
        content = content.replace(/<br>/g, "<br/>");
        content = content.replace(/<BR>/g, "<BR/>");
        content = content.replace(/amp;/g, "");
        content = content.replace(/&/g, "&amp;");
        $('#content').val(content);
        //alert(content);
    }
    return true;
}