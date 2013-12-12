// JavaScript Document

// JavaScript Document


// 选项卡效果
//arg0：鼠标点击元素
//arg1：显示区域元素
//arg2：鼠标点击选项卡显示区域的样式
var optionShow = function(arg0,arg1,arg2){
			$(arg0).removeClass(arg2);
			$(arg0+':eq(0)').addClass(arg2);
			$(arg1).hide();
			$(arg1+':eq(0)').show();
					
			$(arg0).each(function(index){
				$(this).mouseover(function(){
					$(arg0).removeClass(arg2);
					$(this).addClass(arg2);
					$(arg1).hide();
					$(arg1+':eq('+index+')').show();
				});
			});
		}



//头部导航下拉功能初始化
function topNav(){
	var FNode = $('#headMain .downNav').parent();
	FNode.children().not('a').hide();

	
}

//搜索信息验证
function validateSearch(){
	$('.topSearchTxt');
}


//购买道具弹出框效果


//热门投票二级菜单

var NavSec = function(){
	$('.mainContentTop span div').hide();
	$('.mainContentTop span').mouseover(function(){
		$(this).children('div').show();
		$(this).addClass('nav');
	}).mouseout(function(){
		$(this).children('div').hide();
		$(this).removeClass('nav');
	});
}


//道具购买显示

var magicShow = function(){
	$('.showPropBlockNav a').click(function(){
		$('.showPropBlock').hide();
	});
	
	
	var args = arguments;
	
	
	$(this).scroll(function() {
		var bodyTop = 0;  
		if (typeof window.pageYOffset != 'undefined') {  
			bodyTop = window.pageYOffset;  
		} else if (typeof document.compatMode != 'undefined' && document.compatMode != 'BackCompat') {  
			bodyTop = document.documentElement.scrollTop;  
		} else if (typeof document.body != 'undefined') {  
			bodyTop = document.body.scrollTop;  
		}  
		
		for(var i = 0;i < args.length;i++){
			$(args[i]).css("top", bodyTop);
		}	
		
	}); 
	
}

var hideMenu = function(){
	$(arguments[0]).hide();
}

var tag_areaShow = function(){
	var tag_areaBg = new Array('#e75555','#d65152','#c44e4e','#b24a4b','#9f4746','#8e4445','#7b4140','#767676','#848484','#919191','#9f9f9f','#acacac','#bbbbbb','#c8c8c8','#c8c8c8');
	
	$('.tag_area a').each(function(index){
		if(index <= 14 ){			   
			$(this).css({'background':tag_areaBg[index],'border-color':tag_areaBg[index],'color':'#ffffff'}).mouseover(function(){
				$(this).css({'background':'#ffffff','border-color':tag_areaBg[index],'color':tag_areaBg[index]});
			}).mouseout(function(){
				$(this).css({'background':tag_areaBg[index],'border-color':tag_areaBg[index],'color':'#ffffff'});
			});
		}
	});
}


//回复表情

var showFace = function(){
	$('.face_select').click(function(){
		$('.face_content').toggle();
	});
	
	$('.face_content li').click(function(){
		$('.face_content').hide();
	});
		
}


var ajaxmenu = function(){
	$(arguments[0]).toggle();
		
}


//图片展示功能
var runWay ;		//当值为1时，向下移动，当值为-1时，向上移动。
var runNum = 0;			//移动高度
var topValue ;		//距离顶部的位移
var heightValue ;	//UL高度
var maxHeight = 90;
var timer;
var timeValue = 5;
var numValue = 0;	//被选中的ID
var runT = true ;	//是否可以滚动


//滚动图片功能初始化
var loadRunMap = function(){
	
	clickMapList();		//点击图片列表响应效果
	$('.runMapListCon ul li').eq(0).addClass("mouseOn");
	
}

//图片列表滚动
var runMapList = function(){
	
	maxHeight = 450;
	
	if(arguments[0] == -1 ){
		runWay = 10;
	}else if(arguments[0] == 1){
		runWay = -10;
	}
	
	if(runT){
		runT = false;
		timer = setInterval(runMapNum,5);
	}
}

//图片按钮点击滚动
var runMapBtn = function(){
	
	maxHeight = 90;
	
	if(arguments[0] == -1 ){
		runWay = 10;
		numValue--;
		
	}else if(arguments[0] == 1){
		runWay = -10;
		numValue++;
	}
	
	if(numValue <= 0  ){
		numValue = 0;
		
	}else if(numValue > iconList.length - 1){
		numValue= 0;
		$('.runMapListCon ul').css("top", "0px");
		runWay = 0;
	}
	
	if(runT){
		runT = false;
		putImgUrl(numValue);	//图片变换
		chooseImgListBg(numValue);
	
		timer = setInterval(runMapNum,5);
	}
}


//图片滚动一个标准位置
var runMapNum = function(){	
	
	heightValue = -1 * parseInt($('.runMapListCon ul').css("height")) + 450;
	topValue =  parseInt($('.runMapListCon ul').css("top"));
	
	if( runNum < maxHeight  && runNum > -1 * maxHeight && topValue <= 0 && topValue >=  heightValue){
		
		runNum = runNum + runWay;

		topValue = topValue + runWay;
		$('.runMapListCon ul').css("top", topValue);	
		
	}else{
		clearInterval(timer);
		runNum = 0;
		
		if(topValue > 0){
			$('.runMapListCon ul').css("top", "0px");
		}else if(topValue < heightValue){
			$('.runMapListCon ul').css("top", heightValue);
		}
		runT = true;
		return false;
	}	
	
}

//单击图片列表，显示图片
var clickMapList = function(){
	$('.runMapListCon ul li').each(function(index){
		$(this).click(function(){
			putImgUrl(index);	//图片变换
			chooseImgListBg(index);		//被选择图片背景变换
			putImgHeight();
			numValue = index;
			//获取评论
			var picid = $(this).attr('picid');
			getComment(picid);
		});
	});
}

//图片列表背景
var chooseImgListBg = function(index){
	$('.runMapListCon ul li').eq(index).addClass("mouseOn").siblings().removeClass("mouseOn");
}

var getMessage = function(index){
	$('.comments').eq(index).show().siblings(".comments").hide();
}

//给展示区大图付图片地址，给查看原图付图片地址。
var putImgUrl = function(index){
	$('.runMapCon img')	.attr("src",function(){return imgSrc+mapList[index]});
	$('.runMapConText a').attr("href", function() {return imgSrc+mapList[index]});
	//getMessage(index);
}

var putImgHeight = function(){
	$('.contLeftBtn').css("height", function(){return $('.runMapCon img').css("height");});
	$('.contRightBtn').css("height", function(){return $('.runMapCon img').css("height");});
}


//图片展示功能END

/**
 *绑定分页
 */
function pages(){
	$(".page a").each(function(){
				$(this).attr('url',$(this).attr('href'));
				$(this).attr('href','javascript:void(0);');
				$(this).click(function(){
						var url=$(this).attr('url');
						//取出里面的数字
					  var url= url.replace(/[^(\d|&)]*/g,"");
						var arr = url.split("&");
					  var picid= arr[1];
						var page = arr[2];
						getComment(picid,page);
					});
		});
}

//根据图片ID，获取相关评论
function getComment(picid,page){
  var op='getComment';
  var picid = parseInt(picid);
  var page=parseInt(page);
  $.ajax({
		type: "get",
		url: "space.php?do=album&op="+op,
		cache: false,
		dataType:"html",
		data: {'picid':picid,'page':page},
		beforeSend:function(XMLHttpRequest)
		{
			if(picid == 0){
				return false;
			}
		},
		success: function(result){
				$("#commentlist").empty();
				$("#commentlist").html(result);
				showFace();
				pages();
		},
   		error: function(){
        	alert('系统错误');
    	}
	});
}

//图片展示功能END


