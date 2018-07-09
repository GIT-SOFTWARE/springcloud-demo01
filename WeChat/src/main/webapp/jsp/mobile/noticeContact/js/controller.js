/*******************************************************************************
 * @jsname:controller.js
 * @author:HUANGMINQUAN
 * @date:2016-8-11
 * @use:经销商公告平台.js
 ******************************************************************************/
var huadonglunbo = document.getElementById('huadonglunbo');
window.mySwipe = Swipe(
huadonglunbo,
			{
                startSlide:0,		//表示轮播开始的图片编号
                auto: 3000,         //表示自动轮播的间隔
                continuous: true,   //表示是否再来一轮
                disableScroll: true,  //表示是否在上面阻止默认的滚轮事件，不用改
                stopPropagation: true,   //表示是否阻止冒泡，不用改。
                callback: function(index, element) {
                //写小圆点的业务
                $(".xiaoyuandian ul li").eq(index).addClass("cur").siblings().removeClass("cur");
                }
                }
);
//小圆点的点击业务
$(".xiaoyuandian ul li").click(
function(){
    mySwipe.slide($(this).index(),1000);
    }
);
/**
 * 跳转页面
 */
function openNewPage(id, type){
    window.location.href = "newPage.html?id="+id+"&type="+type;
}
/**
 * 默认显示首页
 */
var tab = document.getElementsByClassName('tab');
for(var i=0;i<tab.length;i++){
    if(i==0){
        tab[i].style.display = "block";
    }else{
        tab[i].style.display = "none";
    }
}

/**
 * tab切换
 */
var li= document.getElementById('notice').getElementsByTagName("li");
for(var j=0;j<li.length;j++){
    li[j].index = j;
    li[j].onclick = function(){
       for(var k=0;k<li.length;k++){
           li[k].className = "";
           if(tab[k] != undefined){
               tab[k].style.display = "none";
           }
       }
       if(tab[this.index]!=undefined){
            tab[this.index].style.display = "block";
       }
       //var type= $(this).find("a").attr("title");
       $("#loadpage1").load("rollPage.html");
       this.className = "hover";
    }
}
//自动获取UL的宽度
var ul = document.getElementById("notice");
var len =0;
for(var c=0;c<li.length;c++){
    len = len+li[c].offsetWidth;
}
ul.style.width = len+"px";
/**
 * 向右移动
 */
var start,timer;
function rightStart(){
    clearInterval(timer);
    $(function(){
        start = $(".ul_wrap")[0].scrollLeft;
        timer = setInterval(function(){
            start= start+20;
            $('.ul_wrap').animate({'scrollLeft':start},100);
        },100);
    })
}

function rightEnd(){
      clearInterval(timer);
}
