/*
 * 二级联动
 * @param firstSelectId，一级下拉列表的id
 * @param secondSelectId，二级下拉列表的id
 * @param defaultSecondValue，根据该值，可默认选中二级菜单中的对应选项
 * @param dataArray，存放一级列表与二级列表对应关系的数组，该数据一般由后台java程序直接输出在页面中
 * array[0]：一级下拉框中option的value
 * array[1]：二次下拉框中option的text
 * array[2]：二级下拉框中option的value
 * 格式如下：
 * dataArray[0] = new Array('四川', '雅安','YA');
 * dataArray[1] = new Array('四川', '遂宁','SN');
 * dataArray[2] = new Array('四川', '南充','NC');
 * dataArray[3] = new Array('四川', '内江','NJ');
 * dataArray[4] = new Array('四川', '宜宾','YB');
 * dataArray[5] = new Array('四川', '乐山','LS');
 */
function changeSecondSelect(firstSelectId, secondSelectId, defaultSecondValue, dataArray) {
    //获取一级下拉列表对象
    var firstSelect = document.getElementById(firstSelectId);

    //获取二次下拉列表对象
    var secondSelect = document.getElementById(secondSelectId);

    //初始化二级下拉列表 清空下拉数据
    secondSelect.length = 0;

    //根据一级菜单所选值，往二级菜单中加入正确的option
    for (var i = 0; i < dataArray.length; i++) {
        if (dataArray[i][0] == firstSelect.value) {
            var ops = new Option(dataArray[i][1], dataArray[i][2]);
            secondSelect.options[secondSelect.length] = ops;
        }
    }

    //如果二级菜单中没有任何选项，则在二级菜单中默认加入 “--- 请选择 ---”
    if (secondSelect.options.length == 0) {
        secondSelect.options[0] = new Option('--- 请选择 ---', '');
    }

    if (defaultSecondValue != '' && defaultSecondValue != 'null') {
        secondSelect.value = defaultSecondValue;
    }
}

function changeSecondSelectSpe(firstSelectId, secondSelectId, defaultSecondValue, dataArray, needDefaultSecondSelectOptionFlag) {
    //获取一级下拉列表对象
    var firstSelect = document.getElementById(firstSelectId);

    //获取二次下拉列表对象
    var secondSelect = document.getElementById(secondSelectId);

    //初始化二级下拉列表 清空下拉数据
    secondSelect.length = 0;

    //如果二级菜单中没有任何选项，则在二级菜单中默认加入 “--- 请选择 ---”
    if (needDefaultSecondSelectOptionFlag == "1") {
        secondSelect.options[0] = new Option('--- 请选择 ---', '');
    }

    //根据一级菜单所选值，往二级菜单中加入正确的option
    for (var i = 0; i < dataArray.length; i++) {
        if (dataArray[i][0] == firstSelect.value) {
            var ops = new Option(dataArray[i][1], dataArray[i][2]);
            secondSelect.options[secondSelect.length] = ops;
        }
    }

    if (defaultSecondValue != '' && defaultSecondValue != 'null') {
        secondSelect.value = defaultSecondValue;
    }
}

/**
 * 把scr下拉框中的选项移动到dest下拉框中
 * @param srcSelectId 源下拉框的id
 * @param destSelectId 目标下拉框的id
 * @param needRemoveSrc 移动后是否需要移除源下拉框中的选项，true表示需要移除
 * @param checkDestExists 将选项移入目标下拉框前是否需要校验源下拉框中是否已经存在该选项，true表示需要校验
 */
function moveSelectAction(srcSelectId, destSelectId, needRemoveSrc, checkDestExists) {
    var srcSelect = document.getElementById(srcSelectId);
    var destSelect = document.getElementById(destSelectId);

    for (var i = 0; i < srcSelect.options.length; i++) {
        var tmpOption = srcSelect.options[i];
        var ifExists = false; //默认dest下拉框中不存在待加入的option
        if (tmpOption.selected) {
            //查看dest下拉框中是否已经存在待加入的option
            if (checkDestExists) {
                for (var j = 0; j < destSelect.options.length; j++) {
                    if (tmpOption.value == destSelect.options[j].value) {
                        ifExists = true;
                        break;
                    }
                }
            }

            if (!ifExists) {
                //将选中的option加入到dest下拉框中
                destSelect.options[destSelect.options.length] = new Option(tmpOption.text, tmpOption.value);

                //将src下拉框中相应的option移除
                if (needRemoveSrc) {
                    srcSelect.remove(i);
                    i--;
                }
            }
        }
    }
}
