//页面公用js库，包括增删改查等等公用函数
var __parent = parent ;
var baseUrl="/jsonrest/";
var STATICS_FILESERVER_DOMAIN="http://upload.qinziyou.com";

Array.prototype.S=String.fromCharCode(2); //拓展 数组方法
Array.prototype.in_array=function(e){ //arr.in_array('test');//判断 test 字符串是否存在于 arr 数组中，存在返回true 否则false
	 if(typeof(this.S)=="undefined"){ 
	    return false;
	 }
	var r=new RegExp(this.S+e+this.S);
	return (r.test(this.S+this.join(this.S)+this.S));
};
//拓展数组 ，是数组拥有remove方法
Array.prototype.remove=function(dx){
    if(isNaN(dx)||dx>this.length){return false;} 
    for(var i=0,n=0;i<this.length;i++){
        if(this[i]!=this[dx]){
            this[n++]=this[i] 
        } 
    } 
    this.length-=1 
} 
function accMul(arg1,arg2){ //乘法
    var m=0,s1=arg1.toString(),s2=arg2.toString();  
    try{m+=s1.split(".")[1].length}catch(e){}  
    try{m+=s2.split(".")[1].length}catch(e){}  
    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);  
}
Number.prototype.mul = function (arg){ 
    return accMul(arg, this); 
}
String.prototype.mul = function (arg){ 
    return accMul(arg, this); 
}
function accDiv(arg1,arg2){  //返回值：arg1除以arg2的精确结果 
	var t1=0,t2=0,r1,r2;  
	try{t1=arg1.toString().split(".")[1].length}catch(e){}  
	try{t2=arg2.toString().split(".")[1].length}catch(e){}  
	with(Math){  
		r1=Number(arg1.toString().replace(".",""));  
		r2=Number(arg2.toString().replace(".",""));  
		return (r1/r2)*pow(10,t2-t1);  
	}  
} 
Number.prototype.div = function (arg){ 
    return accDiv(this, arg); 
} 
String.prototype.div = function (arg){ 
    return accDiv(this, arg); 
}
//返回值：arg1加上arg2的精确结果 
function accAdd(arg1,arg2){ 
    var r1,r2,m; 
    try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0} 
    try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0} 
    m=Math.pow(10,Math.max(r1,r2)) 
    return (arg1*m+arg2*m)/m 
} 
Number.prototype.add = function (arg){ 
    return accAdd(arg,this); 
}
String.prototype.add = function (arg){ 
    return accAdd(arg,this); 
}
String.prototype.trim=function(){
　　return this.replace(/^\s+/g,"").replace(/\s+$/g,"");
}

function getCurrenyDateTime(){ //获取当前时间 yyyy-mm-dd hh:mm:ss
   var nowDate = new Date();
   var dtetime = nowDate.getFullYear() + '-' + lPad(nowDate.getMonth() + 1) +
   '-' + lPad(nowDate.getDate())+' '+lPad(nowDate.getHours())+":"+lPad(nowDate.getMinutes())+":"
   +lPad(nowDate.getSeconds());
   return dtetime;
}
function getCurrenyDate(){ //获取当前日期 yyyy-mm-dd
   var nowDate = new Date();
   var dte = nowDate.getFullYear() + '-' + lPad(nowDate.getMonth() + 1) +
   '-' + lPad(nowDate.getDate());
   return dte;
}
function lPad(s) {
  return s < 10 ? '0' + s:s ;
}
function toThousands(num) { //此方法有丢失精度问题，请慎用，
	num=num+"";
	var index = num.indexOf("."); 
	if (index==-1) {//无小数点 
	   return (num || 0).toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,');
	}else{
	   var intPart = num.substring(0, index); 
	   var pointPart = num.substring(index + 1, num.length); 
	   intPart=(intPart || 0).toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,');
	   return intPart +"."+ pointPart; 
	}
}
function delThousands(num){//此方法有丢失精度问题，请慎用
	var x = (num+"").split(); 
	return parseFloat(x.join("")); 
} 
function getBodyWidth() {
    var bodyWidth = 0;
    if (self.innerWidth) {
    	bodyWidth = self.innerWidth;
    }
    else if (document.documentElement) {
		bodyWidth = document.documentElement.clientWidth;
		if (bodyWidth == 0) bodyWidth = document.body.clientWidth;
	}
	else {
		bodyWidth = document.body.clientWidth;
	}
	return bodyWidth;
}

function getBodyHeight() {
    var bodyHeight = 0;
    if (self.innerHeight) {
    	bodyHeight = self.innerHeight;
    }
    else if (document.documentElement) {
		bodyHeight = document.documentElement.clientHeight;
		if (bodyHeight == 0) bodyHeight = document.body.clientHeight;
	}
	else {
		bodyHeight = document.body.clientHeight;
	}
	return bodyHeight;
}
function myFocus(obj,color){
	 var text=obj.getAttribute("tip");		 //判断文本框中的内容是否是默认内容
	 if(obj.value==text){
	   obj.value="";
	 }
	 obj.style.backgroundColor=color;	 //设置文本框获取焦点时候背景颜色变换
}
function myblur(obj,color){
	obj.style.background=color; //当鼠标离开时候改变文本框背景颜色
	var text=obj.getAttribute("tip");
	if (obj.value==""){
		obj.value=text;
	}
}
function closePopWindow() {
	try {
		__parent.window.closeGerentWindow();
	} catch (ex) {alert("无法获得引用（0x10004）！" + ex.message);}
}
//获取index页的urlParas全局变变量对象属性为id的值
function getIframeParm(id){
	try {
		var activatedTabId = __parent.tabElement.omTabs('getActivated');
		if (activatedTabId){
			 activatedTabId=activatedTabId.replace("tab_","");
			 var tb=__parent.document.getElementById(activatedTabId);
			 url=tb.src;
			 var paraString = url.substring(url.indexOf("?")+1,url.length).split("&"); 
			 var paraObj = {} 
			 for (i=0; j=paraString[i]; i++){ 
				paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=")+1,j.length); 
			 } 
			 var returnValue = paraObj[id.toLowerCase()]; 
			 if(typeof(returnValue)=="undefined"){ 
			   return ""; 
			 }else{ 
			   return decodeURI(returnValue); 
			} 
		}else{
			return "";
		}
	} catch (ex) {alert("无法获得引用（0x10004）！" + ex.message);return ''; }
}
//获取当前模块名称
function getCurrModules(){ //如果想在edit页面设置权限，请在tabId后面加上"_001",这样就不会跟其他的重复
   	try {
		var activatedTabId = __parent.tabElement.omTabs('getActivated');
		if (activatedTabId){
			 activatedTabId=activatedTabId.replace("tab_","").replace("_001","");
			 var tb=__parent.document.getElementById(activatedTabId);
			 var modulesId=tb.id;
			 if (modulesId){
			 	return modulesId;
			 }else{
				 return "";
			 }
		}else{
			return "";
		}
	} catch (ex) {alert("无法获得引用（0x10004）！" + ex.message);return ''; }
}
function showPopWindow(title, url, width, height, buttons,rowdatas) {
	try {
		if (url.indexOf("?") > 0) {
			url += "&popup=1&rnd=" + Math.random();
		}
		else {
			url += "?popup=1&rnd=" + Math.random();
		}
		__parent.window.showGerentPopWindow(title, url, width, height, buttons,rowdatas);
	} catch (ex) {alert("无法获得引用（0x10003）！" + ex.message);}
}

function getLoginUserId(){
	try {
		var user=__parent.document.getElementById("userId");
		return user.value;
	} catch (ex) {alert("无法获得引用（0x10003）！" + ex.message);}
}

// 样式 style="ime-mode:disabled" 禁止中文输入     
function noPermitInput(e){       
	   var evt = window.event || e ;     
		if(isIE()){ 			   	    
			evt.returnValue=false; //ie 禁止键盘输入     
		}else{ 	    		    
			evt.preventDefault(); //fire fox 禁止键盘输入     
		}  
}     
function isIE() { 
	if (window.navigator.userAgent.toLowerCase().indexOf("msie") >= 1){     
		return true; 
	}else{
		return false;     
	}
}  
function getFieldSelection(select_field){
  var word='';
	if (document.selection) {
		var sel = document.selection.createRange();
		if (sel.text.length > 0) {
			word = sel.text;
		}
	}else if (select_field.selectionStart || select_field.selectionStart == '0') {
		var startP = select_field.selectionStart;
		var endP = select_field.selectionEnd;
		if (startP != endP) {
			word = select_field.value.substring(startP, endP);
		}
	}
	return word;
} 

//检测语法字符串公式是否合法
function checkFormula(fstr){
	 var fm=fstr;
	 var fm1=fm.substring(fm.length-1,fm.length);
	 fm2 = fm.substring(0,1);
	 if (fm==''){
		 $.omMessageTip.show({content : '公式设置不能为空！',timeout : 2000,type : 'error'}); 
	     return false;
	 }else if((fm1=='.') || (fm1=='+') || (fm1=='-') || (fm1=='/') || (fm1=='*')){
		 $.omMessageTip.show({content : '字符串公式最后的字符不能是操作符！',timeout : 2000,type : 'error'});
		 return false;
	 }else if((fm2=='.') || (fm2=='+') || (fm2=='-') || (fm2=='/') || (fm2=='*')){
		 $.omMessageTip.show({content : '字符串公式最左边的字符不能是操作符！',timeout : 2000,type : 'error'});
		 return false;
	 }else if(fm.indexOf(")(") >=0){
		 $.omMessageTip.show({content : "公式设置存在')(' 字符！",timeout : 2000,type : 'error'}); 
		 return false;
	 }else if(chkHalf(fm)){
		 $.omMessageTip.show({content : "公式设置存在全角字符！",timeout : 2000,type : 'error'}); 
		 return false;
	 }
	 return true;
}
// 判断是否有全角字符
function chkHalf(str){
      for(var i=0;i<str.length;i++){ 
         strCode=str.charCodeAt(i);   
         if((strCode>65248)||(strCode==12288)){   
            return true;                      
         } 
       } 
	   return false;
}
//字符串是否是纯字母或下划线
function isEngOnline(str){	
	var regu ="^[a-zA-Z\_]+$";  /*匹配6-16个字符，只能是数字或字母，不包括下划线的正则表达式^[0-9a-zA-Z\_]+$*/
	var re=new RegExp(regu);
	if (re.test(str)) {
		return true;
	}else {
		return false;
	}
}
//根据时间生成唯一的字符串
function generateByDate(){
	var date = new Date(); //日期对象
	var nowstr = "";
	nowstr = date.getFullYear(); //读英文就行了
	nowstr = nowstr + (date.getMonth()+1); //取月的时候取的是当前月-1如果想取当前月+1就可以了
	nowstr = nowstr + date.getDate();
	nowstr = nowstr + date.getHours();
	nowstr = nowstr + date.getMinutes();
	nowstr = nowstr + date.getSeconds();
	return nowstr;
}

//在center-panel取添加一个新的Tabs
function addNewTab(title, url,paras,callback){
	try {
		for(var p in paras){
		   var name=p;//属性名称
		   var value=paras[p];//属性对应的值   
		   if(value!=undefined){ 
			   if(url.indexOf("?") > 0){
				  url += "&"+name+"="+ value;
			   }else{
				  url += "?"+name+"="+ value;
			   }
		   }
		}
		__parent.window.addNewTab(title, url,paras,callback);
	} catch (ex) {alert("无法获得引用（0x10003）！" + ex.message);}
}
 //关闭当前活动业	
function closeCurrActivateTabs(len){
	__parent.window.closeCurrActivateTabs();  
}
//弹出一个网页窗口
function showFullScreen(url) {
	try {
		 window.open(encodeURI(encodeURI(url)),'_blank',' left=0,top=0,width='+ (screen.availWidth - 10) +',height='+ (screen.availHeight-60) +',scrollbars=yes,resizable=yes,toolbar=no,location=no,status=no,toolbar=no,menubar=no,titlebar=no');
	} catch (ex) {alert("无法获得引用（0x10002）！" + ex.message);}
}

function getQueryString(name) { 
	try {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		var r = window.location.search.substr(1).match(reg);
		if (r != null) {
			var paramName=unescape(r[2]);
		    paramName=decodeURI(paramName);
			return paramName;
		}else{
		    return "";
		}
	} catch (ex) {}
}
function stringSplit(str){
	try {
     var strsArray= new Array(); //定义一数组
	 var strs="1,2,3";
	 strs=str;
     strsArray=strs.split(","); //字符分割
	 return strsArray;
   } catch (ex) {alert("无法获得引用（0x10002）！" + ex.message);}
}

function locationHref(url,target){
	//可以支持多种跳转方式，而且document.referrer做记录
	var fun=function(){
		target=target?target:'_self';
		var a=document.createElement("a");
		a.style.display='none';
		a.href=url;
		a.target=target;
		a.innerHTML='&nbsp;';
		document.body.appendChild(a);
		a.click();
		if(target!='_self')document.body.removeChild(a);
	};
	//解决IE6超链接javascript:void(0)不跳转
	if(isIE()&&isIE()<7.0)setTimeout(fun,1);
	else fun();
}
function trim(sStr){
	if(typeof(sStr)!='string')return sStr;
	return sStr.replace(/(^\s*)|(\s*$)/g,"");
}
function checkJsonRight(data){
	//如果登录拦截器发现登录问题会返回 msgCode=3，再看failureCode
	//failureCode= 1 --没有登录
	//failureCode= 2 -- 登录信息异常，如登录COOKIE在浏览器端被认为篡改
	//failureCode= 3-- 登录超时
	var length=arguments.length;
	var href='/login/login.html';
	var target='_top';
	if(!data||!data.msgCode){
		return;			
	}else if(data.msgCode!=3){
		return;	
	}else if(data.msgCode==3){
		alert(data.msg);
		locationHref(href,target);
	}
}
function convertParamToUrl(vo){
	 var param=[];
	 for(name in vo){
		 param.push(name+"="+ vo[name]);  
	 }
	 return param.join("&");
}
function chkSingleNodes(nodeData){ //单选 增值服务费用选项用
   var parentNode=addValueTree.omTree('getParent',nodeData);
   if (nodeData.pid==""){
	  addValueTree.omTree('uncheck',nodeData);
	  parentNode = nodeData;
   }
   var childrens=addValueTree.omTree('getChildren',parentNode);
   var chkCount=0;
   var ischk=false;
   $.each(childrens, function(key, val) {
	  ischk=addValueTree.omTree('isCheck',val); 
	  if(ischk){
		chkCount=chkCount+1;
		if(chkCount>1){
			//addValueTree.omTree('uncheck',nodeData);
			//$.omMessageTip.show({content : "对不起！增值服务费用项必须是单选",timeout : 2000,type : 'error'});
			//return false;    
		}
	  }
   });				
}

/**
 * 数字之间加逗号
 * @param   {String}    参数
 * @return  {String}    返回结果
 */
function formatNum(str){
    str = str.toString();
    if (/[^0-9\.]/.test(str)){return str;}
    var strFloor = '';
    if(RegExp('\\.').test(str)){
        strArr = str.split('.');
        str = strArr[0];
        strFloor = '.' + strArr[1];
    }
    var n = str.length % 3;
    if(n){
        return str.slice(0,n) +  str.slice(n).replace(/(\d{3})/g,',$1') + strFloor;
    }else{
        return str.replace(/(\d{3})/g,',$1').slice(1) + strFloor;
    }
};

//获取url参数
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

function toPenny(num,len){
	len=len||2;
	if(isNaN(parseFloat(num)))return num;
	var tempNum = num/100;
	var isFloat = /^\d+\.\d+$/;
	if(isFloat.test(tempNum)){
		return tempNum.toFixed(len);
	}else return tempNum;
}
