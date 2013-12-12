function insertTag(id) {
    var now = new Date();
    var number = now.getSeconds();
    var pagecontent = $("#content");
    pagecontent.replaceSelection(String.fromCharCode(1));
    $('#tagDiv').dialog({
        autoOpen: false,
        width: 700 ,
        position: 'top',
        modal: true,
        close: function(event, ui) {
            pagecontent.val(pagecontent.val().replace(String.fromCharCode(1), ""));
        }


    });
    $('#tagDiv').load("/gamecms/tvWapPage/insertTag/" + id + "?t=" + number);
    $('#tagDiv').dialog('open');
}

function viewPage(page) {
    var now = new Date();
    var number = now.getSeconds();
    //var pagecontent = $("#content");
    //pagecontent.replaceSelection(String.fromCharCode(1));
    $('#tagDiv').dialog({
        autoOpen: false,
        width: 330 ,
        height: 420,
        position: 'top',
        modal: true,
        close: function(event, ui) {
            //pagecontent.val(pagecontent.val().replace(String.fromCharCode(1), ""));
        }


    });

    $('#tagDiv').load("/gamecms/spWapPage/view?page=" + page + "&t=" + number);
    $('#tagDiv').dialog('open');

}

/**
 * 处理tagcode，加入参数，如 <cms:entries> --> <cms:entries channel=''>
 * @param tagCode
 */
function dealTagParam(tagcode, paramcodes, paramvalues) {
    var fulltag = tagcode.substring(0, tagcode.lastIndexOf(">"));

    for (i = 0; i < paramcodes.length; i++)
    {
        fulltag += " " + paramcodes[i] + "=" + "\"" + paramvalues[i] + "\"";
    }

    return fulltag + " >";

}

/**
 * 生成tagcode结尾，如 </cms:entries>
 * @param tagCode
 */
function dealTagEnd(tagCode) {
    var endcode = tagCode.substring(1, tagCode.length);
    return    "</" + endcode;

}


function setSelectionRange(input, selectionStart, selectionEnd)

{
    if (input.setSelectionRange) {
        input.focus();
        input.setSelectionRange(selectionStart, selectionStart);
    }
    else if (input.createTextRange) {
        var range = input.createTextRange();
        range.collapse(true);
        range.moveEnd('character', selectionEnd);
        range.moveStart('character', selectionStart);
        range.select();
    }
}