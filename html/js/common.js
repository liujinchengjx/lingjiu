//图片地址

//主体.顶级域名,方便于跨子域访问
var stUrlReg=/[^\.]*?\.com\.cn|[^\.]*?\.net\.cn|[^\.]*?\.org\.cn|[^\.]*?\.edu\.cn|[^\.]*?\.com|[^\.]*?\.cn|[^\.]*?\.net|[^\.]*?\.org|[^\.]*?\.edu|[^\.]*?\.tk|[^\.]*?\.lc/i;
//alert(window.location.host);
var sBaseAreaName=window.location.host.toLowerCase().match(stUrlReg)[0];
/************************************************************* 公共函数集合 开始 *******************************************************/
function toPenny(num,len){
	len=len||2;
	if(isNaN(parseFloat(num)))return num;
	var tempNum = num/100;
	var isFloat = /^\d+\.\d+$/;
	if(isFloat.test(tempNum)){
		return tempNum.toFixed(len);
	}else return tempNum;
}
function toFloat(num,len){
	len=len||2;
	if(isNaN(parseFloat(num)))return num;
	else return num.toFixed(len);
}
function trim(sStr){
	if(typeof(sStr)!='string')return sStr;
	return sStr.replace(/(^\s*)|(\s*$)/g,"");
}
function escapeString(sText){
	return sText.replace(/(['"\\])/g,"\\$1");	
}
//获取url参数
function getUrlParam(sName)
{	
	if(sName)
	{
		var sValue='';
		var re= new RegExp(sName+"=([^&=]+)");
		var st=null;
		st=window.location.search.match(re);
		if(st&&st.length==2)
		{	
			st[1]=st[1].replace(/^\s*|\s*$/g,'');
			sValue=st[1];
		}
		return sValue
	}
	else
	{
		alert("参数不能为空");
		return false;
	}
}
function locationHref(url,target){
	//可以支持多种跳转方式，而且document.referrer做记录
	target=target?target:'_self';
	var a=document.createElement("a");
	a.style.display='none';
	a.href=url;
	a.target=target;
	a.innerHTML='&nbsp;';
	document.body.appendChild(a);
	a.click();
	if(target!='_self')document.body.removeChild(a);
}
function showTips(str){
	if(str == ''){
		return;
	}
	var html = '<div class="infoTips" id="infoTips"><p>'+str+'</p></div>';
	$(html).prependTo('body').show();
	window.setTimeout(function(){
		$("#infoTips").hide().remove();
	},1000);
}
//判断用户是否登录
function isUserLogin(){
	var _usergd=getCookie('gscm_user');
	if(_usergd==null)_usergd='';
	_usergd=$.trim(_usergd);
	if(_usergd=='')return false;
	else return true;	
}
function loginHide(){
	if(isUserLogin()){
		$("#footer_login").hide();
	}else{
		$("#footer_login").show();
	}
}
function getCookie(name){
	var start=document.cookie.indexOf(name+"=");
	if(start==-1) return null;
	var len=start + name.length + 1;
	var end = document.cookie.indexOf(';',len);
	if (end==-1) end=document.cookie.length;
	return unescape(document.cookie.substring(len, end));
}


function deleteCookie(name){
	//sBaseAreaName来自common.js
	document.cookie = name+'=;path=/;domain='+sBaseAreaName+';expires=Thu, 01-Jan-1970 00:00:01 GMT'; 
}

function getUrlParam2(param)
{
var url = window.location.href;
var re = new RegExp("(\\\?|&)" + param + "=([^&]+)(&|$)", "i");
var m = url.match(re);
if (m)
return m[2];
else
return '';
}

function exitCommonLogin(){
    var ticket = getCookie("SSOID");
    if(ticket==null||ticket==""){
       exitCommonLocalLogin();
    }else{
	    var url=TERMINAL_URL+"/jsonrest/mobile/user/UserLogin/0/doLogout?cookieName="+ticket;
		$.ajax({
			type: "get",
			dataType: "json",
			url:url,
			async:false,//非必须.默认true
			cache:false,//非必须.默认false
			success: function(data){
				if(data.msgCode == 1){
				   deleteCookie("SSOID");
				   deleteCookie("successLogin");
				   exitCommonLocalLogin();
				}else{
					alert(data.msg);
				}
			}
		});
	}
}

function exitCommonLocalLogin(){
    var accessToken = getCookie("accessToken");
    var url;
    if(accessToken!=null&&accessToken!=""){
       url=TERMINAL_URL+"/jsonrest/mobile/user/UserLogin/0/exitLogin";
	   	$.ajax({
			type: "post",
			data: "accessToken=" + accessToken,
			dataType: "json",
			url:url,
			async:false,//非必须.默认true
			cache:false,//非必须.默认false
			success: function(data){
				if(data.msgCode == 1){
					  deleteCookie("100jia_user");
					  deleteCookie("100jia_ln");
					  deleteCookie("login_type");
					  deleteCookie("pro_cust");
					  deleteCookie("accessToken");
				      var href = window.location.href;
				      if(href.indexOf('gotoUrl')==-1){
				    	 return;
					  }else{
					     window.location=href;
					  }
				}else{
					alert(data.msg)
				}
			}
		});
    }else{
       url=TERMINAL_URL+"/jsonrest/mobile/user/UserLogin/0/exitLogin";
	   	$.ajax({
			type: "post",
			dataType: "json",
			url:url,
			async:false,//非必须.默认true
			cache:false,//非必须.默认false
			success: function(data){
				if(data.msgCode == 1){
				      var href = window.location.href;
				      if(href.indexOf('gotoUrl')==-1){
				    	 return;
					  }else{
					     window.location=href;
					  }
				}else{
					alert(data.msg)
				}
			}
		});
    }
}







function getResource(){
	var t = (function() {
		var u = navigator.userAgent.toLowerCase();
		return {
			isWeixin: function() {
				return (RegExp("micromessenger").test(u))
			}
		};
	})();
	window.util = t;
	if (!t.isWeixin()) {
		return 4;
	}else{
		return 3;
	}
}

function getUrlParam(sName){	
	if(sName){
		var sValue='';
		var re= new RegExp(sName+"=([^&=]+)");
		var st=null;
		st=window.location.search.match(re);
		if(st&&st.length==2){	
			st[1]=st[1].replace(/^\s*|\s*$/g,'');
			sValue=st[1];
		}
		return sValue;
	}
	else{
		alert("参数不能为空");
		return false;
	}
}
