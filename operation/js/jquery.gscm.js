var GSCM = {
	// sbar: show sidebar	
	statusCode: {ok:200, error:300, timeout:301},
	loadLogin:function(){//登录超时调用
		
	},	
	ajaxError:function(xhr, ajaxOptions, thrownError){	
	   $.omMessageBox.waiting('close');
	}
};
//


(function ($) {
    var _editable_tags = [{name:"input", input:1},{name:"textarea", input:1},{name:"select", input:1},
							  {name:"span", input:0},{name:"div", input:0}];
	//getVo方法是用来获取一个元素里面的元素值的数组
    $.fn.getVo = function (settings) {
        var params = {           
        };
        $.extend(params, settings);
        var me = $(this);
		if (me.length<=0) return null;
		var jsonobj={};		
		getJsonObj();
        //arrayToJson(vo);
		//检查属性存在不存在
		function existProperty(obj, name) {
			if(typeof(obj.attr(name))=="undefined") return false;
			return true;
		};
		function getJsonObj(){
			var jsonobjtemp={};
			for (var i=0; i<_editable_tags.length; i++) {
				me.find(_editable_tags[i].name).each(function(index) {
					var $this = $(this);
					var name = $this.attr("name");
					if (name != undefined && existProperty($this, "name") == true) {
						if (_editable_tags[i].input === 1) { //输入域
							if (_editable_tags[i].name === "select") {
								 var m = $this.getAttribute("multiple") ? $this.getAttribute("multiple") : "";
								 if(m == "true" || m == true) {
									  if (m == "true" || m == true) {
											var values = $this.val(); 
											var strvalues="";
											for(var key in values){  
											  strvalues=strvalues+ values[key] + ",";  
											}											
											jsonobjtemp[name] = values;
										}
							     }else{	
									 jsonobjtemp[name] =$this.find("option:selected").text();
								 }
							}else {								
								var t = $this.attr("type") ? $this.attr("type").toLowerCase() : "";
								if (t == "checkbox") {
									var nocheck = $this.attr("nocheck") ? $this.attr("nocheck") : "0";
									if ($this.attr("checked")) {										
										jsonobjtemp[name] =  $this.val();
									}else{									
										jsonobjtemp[name] =  nocheck;
									}
								}else if (t == "radio") {								
									if ($this.attr("checked")){ jsonobjtemp[name] = $this.val();}
								}else{
									if (existProperty($this, "showtext")){
										jsonobjtemp[name]=typeof($this.attr("_trueValue"))=="undefined"?"":
										($this.val().trim()!=""?$this.attr("_trueValue"):"");
									}else{
								   		jsonobjtemp[name] = ($this.val()==$this.attr("tip"))? "": $this.val();
									}
								}
							}
						}
						else {							
							jsonobjtemp[name] =  $this.text();
						}							
					}
				});
			}
			jsonobj=jsonobjtemp;
		}		
		return  jsonobj;
    };

   //iniPage方法是用来初始化一个页面上元素的值
    $.fn.iniPage = function (settings) {
        var params = {
			vo: null,            
			pre:""
        };
        $.extend(params, settings);
        var me = $(this);
		if (params.vo instanceof Array) params.vo=params.vo[0];		
        iniElements();//初始化
		//检查属性存在不存在
		function existProperty(vo, name) {
			for (var prop in vo) {
				if (prop == name) return true;
			}
			return false;
		};
		
		Date.prototype.format = function (format) {           
            return format;
        };
        //转换时间格式
        function conertJsonTimeAndFormat(jsonTime, format) {
            return new Date(eval(jsonTime.replace(/\/Date\((\d+)\)\//gi, "new Date($1)"))).format(format);
        } 
		function isObj(str){
			if(str === null || typeof str === 'undefined'){
				return false;
			}
			return typeof str === 'object';
		}
		//初始化多个元素
		function iniElements(){
			var val="";
			for (var i=0; i<_editable_tags.length; i++) {
				me.find(_editable_tags[i].name).each(function(index) {
					var $this = $(this);
					var name = $this.attr("name"); 
					var showtext = $this.attr("showtext");
					var p = $this.attr("pattern") ? $this.attr("pattern").toLowerCase() : "";
					if (name != undefined) {
						val = getParamsVal(name,params.vo);
						if (_editable_tags[i].input === 1) { //输入域
							 if (p=="combo"){
								 $this.omCombo('value',val);							 
							 }else{
								if(showtext){
									if (val==undefined){
									    val="-1";	 
									}
								   $this.val(getParamsVal(showtext,params.vo)).attr('_trueValue', val);
								}else{
								   $this.val(val);
								}
							 }
						}else {
							$this.html(val);
						}							
					}
				});
			}
		};// end iniElement
		
		function getParamsVal(propName,params){ //下拉值的情况
		   var arrys = propName.split(".");
		   if(arrys.length==1){
			  return params[arrys[0]];
		   }else if (arrys.length==2){
			  return params[arrys[1]];
		   }else if (arrys.length==3){
			  if (params[arrys[1]]!=null){
				  if (params[arrys[1]][arrys[2]]!=null){
			         return params[arrys[1]][arrys[2]];
				  }
			  }
			  return "";
		   }
		}
	};
   //end iniPage	
	
  //ajax提交
  $.ajaxPost = function(settings) {	 
		var params = {           
		    dataType: "json",
			method: "POST",
			data:null,
			url:null,
			success:null,
            asynced: true,
			cache:false,
			msg:true  //true表示带回调用的地方显示msg
        };
        $.extend(params, settings);		
		$.ajax({
			type:params.method,
			dataType: params.dataType,
			url:baseUrl + params.url,
			data:params.data,
			async:params.asynced,
			cache:params.cache,
			msg:params.msg,
			beforeSend: function(XMLHttpRequest){
				$.omMessageBox.waiting({title:'请稍候',content:'服务器正在处理请求，请稍候....'});				
			},
			complete: function(XMLHttpRequest, textStatus){
				$.omMessageBox.waiting('close');				
			},
			success: function(json, textStatus) {
				$.omMessageBox.waiting('close');
				checkJsonRight(json);
				if(params.success){
				   if(params.msg){
					  params.success(json, textStatus);
				   }else if(json.msgCode==1){
				      params.success(json, textStatus);
				   }else if(json.msgCode==2){
					  $.omMessageTip.show({content : json.msg,timeout : 2000,type : 'error'}); 
				   }
				}
			},
			error: GSCM.ajaxError
		});
	};
  //end ajax 
  //初始化input
  $.fn.initializeInput = function(settings) {
	    var params = {           
		    vo: null,
            asynced: true,
			init:true,
			comb:null,
			onkeydown:null
        };
        $.extend(params, settings);		
		var b = (params.vo && params.vo != null && (params.vo instanceof Object)) ? true : false;
		var me = $(this);
		if (params.onkeydown){
			me.bind('keydown',function(){
				if(event.keyCode==13){
					params.onkeydown();
                }					   
			});
		}
		if (params.init){
			initializeInput(me, params.asynced);
		}
		if (b){ //用vo初始化 界面
		    me.iniPage(params);
		}
		function getOnValueChange(id){
			var fun=null;
			if (params.comb!=null){
				for(var i=0;i<params.comb.length;i++){
					 if (params.comb[i].id == id) {
						return params.comb[i].onValueChange;
					 }
				}
			}
			return fun;
		}
		function initializeInput(element,asynced) {			
				//element.attr("initialized",true);  //标记该元素已经初始化过
				for (var i=0; i<_editable_tags.length; i++) {					
					element.find(_editable_tags[i].name).each(function(index) {
						var $this = $(this);						
						if (_editable_tags[i].name === "input") {
						    var p = $this.attr("pattern") ? $this.attr("pattern").toLowerCase() : "";							
							var isedit = $this.attr("editable")=="true" ? true : false;
							var multi =$this.attr("multi")=="true" ? true : false;
							var initialized = $this.attr("initialized")=="true" ? true : false;
							var remote = $this.attr("remote") ? $this.attr("remote") : "";
							var width=  $this.attr("width") ? $this.attr("width").toLowerCase() : "100px";
							var defaultvalue=  $this.attr("value") ? $this.attr("value").toLowerCase() : "";
							var emptyText=$this.attr('emptyText') ? $this.attr("emptyText").toLowerCase() : ""
							var v= $this.attr("validate");
							if (v){
								var $td=$this.closest("td").prev('.label');
								var len=$td.find("span").length;
								if (len==0){
								   $td.prepend("<span class='color_red'>*</span>");
								}
							}
							if (p == "date" || p == "datetime") {
									$this.addClass((p == "date") ? "ui-datebox" : "ui-datetimebox");
									var defaultDate=$this.attr("defaultDate") ? $this.attr("defaultDate").toLowerCase() : ""
									if (p == "date"){$this.omCalendar({editable :isedit,width:width});
									   if (defaultDate=="now"){$this.val(getCurrenyDate());}
									};
									if (p == "datetime"){$this.omCalendar({dateFormat:"yy-mm-dd H:i:s",editable:isedit,width:width});
									    if (defaultDate=="now"){$this.val(getCurrenyDateTime());}
									};
							}else {																			
								if (p == "int" || p == "uint") {
									$this.omNumberField({allowDecimals:false});
								}
								if (p == "float" || p == "ufloat") {
									$this.omNumberField({allowDecimals:true,decimalPrecision:10});
								}
								if ((p=="combo") && (remote!='')){	 									   
								   if(!initialized){										   
										$this.attr("initialized",true);  //标记该元素已经初始化过
										var fn=getOnValueChange($this.attr("id"));
										$this.omCombo({			//交易状态
											dataSource : Dictionary[remote],width : width,editable : isedit,value : defaultvalue,
											optionField:'dictName',valueField : "dictCode",multi : multi,
											emptyText:emptyText,onValueChange:fn		
										});			
								   }else{
									   $this.omCombo('value',defaultvalue); //默认一下
								   }
								}else {
									$this.attr("type")!='checkbox'?$this.addClass("ui-textbox"):false;
									$this.val(defaultvalue);
								}
							}
						}else if (_editable_tags[i].name === "textarea") {		
							var v= $this.attr("validate");
							if(v){
								var $td=$this.closest("td").prev('.label');
								var len=$td.find("span").length;
								if (len==0){
								  $td.prepend("<span class='color_red'>*</span>");
								}
							}
							$this.addClass("ui-textbox");
							$this.val("");
						}
					}); // end find
				} // end for			  
		}
		
	}; // end initializeInput
	
  //在光标处插入文本
  $.insertAtCursor =function (myField, myValue) {
        //IE support
        if (document.selection) {
            myField.focus();
            sel            = document.selection.createRange();
            sel.text    = myValue;
            sel.select();
        } else if (myField.selectionStart || myField.selectionStart == '0') {  //MOZILLA/NETSCAPE support       
            var startPos    = myField.selectionStart;
            var endPos        = myField.selectionEnd;
            // save scrollTop before insert
            var restoreTop    = myField.scrollTop;
            myField.value    = myField.value.substring(0, startPos) + myValue + myField.value.substring(endPos, myField.value.length);
            if (restoreTop > 0) {
                // restore previous scrollTop
                myField.scrollTop = restoreTop;
            }
            myField.focus();
            myField.selectionStart    = startPos + myValue.length;
            myField.selectionEnd    = startPos + myValue.length;
        } else {
            myField.value += myValue;
            myField.focus();
        }
    };
   $.fn.initGridDropList = function(settings) { // 下拉grid
	    var params = {
			dataSource:"",
			text:"",
			title:"",
			width:420,
			height: 250,
			limit:20,
			onRowClick:null,
			onSuccess:null,
            colModel :null
        };
        $.extend(params, settings);		
		var me = $(this);
		var target=getTargetName($(this));
		var selectorId=getSelectorId($(this));
		var html="<div class='omcombo-ct' style='position: absolute; display: block; left: 0;'>";
		html=html+"<table id='"+target+"'></table></div>";
		me.after(html);
		var timer = "_"+selectorId+"_timer";
        var input = $("#"+selectorId).focus(function() {
					                     dropList.show();
					                 }).blur(function() {
					                     window[timer] = setTimeout(function() {
					                        dropList.hide();
					                     }, 450);
					                 });
       var inputOffset=input.offset();	   
	   var dropList = input.next().css({top:inputOffset.top+input.outerHeight()+2,left:inputOffset.left,
											"z-index":"1000"}).mousedown(function(e){
                                            e.stopPropagation();
                                            setTimeout(function() {
                                                clearTimeout(window[timer]);
                                            }, 25);
                                        });
       $(document.body).mousedown(function() {
            dropList.hide();
       }); 
	   me.bind('keypress',function(event){ //绑定回车事件-2014/12/12修复传递中文乱码问题
            if(event.keyCode == "13") {
				 var url=$("#"+target).omGrid("getUrl");
				 var lastIndex=url.lastIndexOf("=");
				 url=url.substring(0, lastIndex+1);
				 var v=me.val();
				 var value=encodeURI(encodeURI(v));
				 $("#"+target).omGrid('setData',url+value);
				 event.stopPropagation();
            }
       });
	   me.bind('blur',function(event){
		   if(me.val() == '' || me.val() == null || me.val() == '选择客户'){
			   $(this).val("").attr('_trueValue', "");
		   }
	   });
       initializeGrid();		
	   function initializeGrid() {
			$("#"+target).omGrid({
					dataSource :params.dataSource,
					method : 'POST',
					width :params.width,
					height : params.height,
					showIndex : true,
					limit:params.limit,
					title : params.title,
					autoFit : true,
					colModel :params.colModel,
					onRowClick : function(rowIndex,rowData,event) {
						input.val(rowData[params.text]).attr('_trueValue', rowData.id);
						input.focus();
						dropList.hide();
						if (params.onRowClick) params.onRowClick(rowData);
						
					},
					onSuccess:function(data,testStatus,XMLHttpRequest,event){
						 if (params.onSuccess) params.onSuccess(data);
					 }
				});
            input.next().hide();
        };
		function getTargetName(obj){			
			var selectorId=getSelectorId(obj);
			var target=selectorId+"_target_table";
			return target;
		};
		function getSelectorId(obj){			
			var selectorId=obj.selector;
			var ipos = selectorId.indexOf("#");
			selectorId=selectorId.substring(ipos+1,selectorId.length);//取后部分
			return selectorId;
		};
		function getData(obj){
			var target=getTargetName(obj);
			if (target){
				var store=$("#"+target).omGrid('getData');
				return store.rows;
			} 
			return null;
		}
		$.fn.setData = function(url) {
			if (url){
				var target=getTargetName($(this));
				if (target){
					params.dataSource=url;
					$("#"+target).omGrid('setData',url);
				}
			}
		};
		$.fn.getData = function () {
			var target=getTargetName($(this));
			if (target){
				var store=$("#"+target).omGrid('getData');
				return store.rows;
			}
			return null;
		};
		$.fn.getfieldById = function (id,field) {
			 var val="";
			 recData = getData($(this));
			 if (recData){
				for(var i=0;i<recData.length;i++){
					  if (id==recData[i].id){
						  return recData[i][field];
					  }
				}
			 }
			return val;
		};
		$.fn.getValue = function () {
		   var val=$(this).attr("_trueValue");
		   return val;
		};
		$.fn.clearValue = function () {
			$(this).val("").attr('_trueValue', "");
		}
  	}
    $.fn.initToolBar = function(inToolbar) { // 
	    var params = {
			toolBarSeq:1
        };
		if (inToolbar){
			params.toolBarSeq=inToolbar;
		}	
		var me = $(this); 
		initializeToolBar();
	    function initializeToolBar() {
		   var btns=[],sep={separtor:true};;	
		   var currModules=getCurrModules();
		   var url="systemmanager/FrameworkButtons/0/loadButtonsList?modules="+currModules+"&toolBarSeq="+params.toolBarSeq;
		   $.ajaxPost({url:url,msg:false,success:function(json,textStatus){
			      if (json.msgCode==1){
					  var len=json.rows.length;
					  for(var i=0;i<len;i++){
						  var funname=json.rows[i].hander;
						  if (funname){
							  funname=funname.replace("()",""); 
						  }
						  var fun = eval(funname); 
						  var btn={label:json.rows[i].text,id:getButtonId(i),onClick:fun,
						           icons:{left : "/images/icon/"+json.rows[i].icon+".png"}
						  };
						  btns.push(btn);
						 // btns.push(sep);
					  }
					  me.omButtonbar({
						 width : "100%",
						 btns : btns
					  });
				  }
			}
	   	   });
           
        };
        function getButtonId(btnIndex){ //获取按钮的id名称， 命名是根据 btn + toolBarSeq + i+1
		    var btnId="btn"+params.toolBarSeq;
			btnId=btnId+(btnIndex+1);
			return btnId;
		}
		function getData(obj){
		}
  	}	
	$.fn.iniGscmGrid = function(settings) { // 初始化插件内容
	    var params = {
			start:0,
			limit:100,
			callback:null,
			url:"",
			currIndex:1
        };
		$.extend(params, settings);	
		var me = $(this);
		if(params.url!=""){
			params.url=setQueStr(params.url,"start",params.start);
			params.url=setQueStr(params.url,"limit",params.limit);
		}
		iniPagingBar();
	    function iniPagingBar() {
           me.empty();
		   var html="<div class='om-grid om-widget om-widget-content' style='height: 20px;'>";
		   html=html+" <div class='pDiv om-state-default'>";
		   html=html+"  <div class='pDiv2'>";
		   html=html+"     <div class='pGroup'>";
		   html=html+"		 <div class='pFirst pButton om-icon'>";
		   html=html+"			<span class='om-icon-seek-start'></span>";
		   html=html+"		 </div>";
		   html=html+"		 <div class='pPrev pButton om-icon'>";
		   html=html+"			<span class='om-icon-seek-prev'></span>";
		   html=html+"		 </div>";
		   html=html+"	   </div>";
		   html=html+"     <div class='btnseparator'></div>";
		   html=html+"     <div class='pGroup'>";
		   html=html+"		  <span class='pControl'>第<input type='text' size='4' value='1'>页，共<span></span>页</span>";
		   html=html+"	   </div>";
		   html=html+"     <div class='btnseparator'></div>";
		   html=html+"     <div class='pGroup'>";
		   html=html+"		 <div class='pNext pButton om-icon'>";
		   html=html+"			<span class='om-icon-seek-next'></span>";
		   html=html+"		 </div>";
		   html=html+"		 <div class='pLast pButton om-icon'>";
		   html=html+"			<span class='om-icon-seek-end'></span>";
		   html=html+"		 </div>";
		   html=html+"	   </div>";
		   html=html+"     <div class='btnseparator'></div>";
		   html=html+"     <div class='pGroup'>";
		   html=html+"        <div class='pReload pButton om-icon'>";
		   html=html+"		     <span class='om-icon-refresh'></span>";
		   html=html+"        </div>";
		   html=html+"	   </div>";
		   html=html+"     <div class='btnseparator'></div>";
		   html=html+"     <div class='pGroup'>";
		   html=html+"			<span class='pPageStat'></span>";
		   html=html+"	   </div>";
 		   html=html+"   </div>";
		   html=html+"  </div>";
		   html=html+" </div>";
		   me.html(html);
		   if(params.callback){
			  if(params.url!=""){
				  var url=setQueStr(params.url,"start",0);
				  loadGridData(url,1,0);
			   }
		   }
		   me.find(".pFirst").click(function(){
				if(params.callback){
				    if(params.url!=""){
					   var url=setQueStr(params.url,"start",0);
					   loadGridData(url,1,0);
			        }
		        }
           });
		   me.find(".pPrev").click(function(){
				if(params.callback){
				   if(params.url!=""){
					  var currIndex=0,start=0;
					  var url="";
					  if(params.currIndex>1){
						 currIndex=params.currIndex-1;
						 start=params.start-params.limit;
						 url=setQueStr(params.url,"start",start);
					  }else{
						  currIndex=1;
						  start=params.currIndex-1;
						  url=setQueStr(params.url,"start",start);
					  }
			          loadGridData(url,currIndex,start);
			        }
		        }
           });
		   me.find(".pNext").click(function(){
				if(params.callback){
				   var currIndex=0,start=0;
				   var url="";
				   if(params.url!=""){
					  if(params.currIndex>0){
						 var pages= parseInt(me.find(".pDiv2 .pGroup .pControl span").html());
						 currIndex=params.currIndex+1;
						 if (currIndex<=pages){
							 start=params.start+params.limit;
							 url=setQueStr(params.url,"start",start);
							 loadGridData(url,currIndex,start);
						 }
					  }else{
						  currIndex=1;
						  start=params.limit;
						  url=setQueStr(params.url,"start",start);
						  loadGridData(url,currIndex,start);
					  }
			        }
		        }
           });
		   me.find(".pLast").click(function(){
				if(params.callback){
				   if(params.url!=""){
					  var total=parseInt(me.find(".pDiv2 .pGroup .pPageStat span").html());
					  var pLastPageIndex=parseInt(me.find(".pDiv2 .pGroup .pControl span").html());
					  var pages=Math.ceil(total/params.limit);
					  var start=((pages-1)*params.limit);
					  url=setQueStr(params.url,"start",start); //最后一页
					  loadGridData(url,pLastPageIndex,start);
			        }
		        }
           });
		   me.find(".pReload").click(function(){
				me.refreshData();
           });
		   
        };
		function loadGridData(url,currIndex,start){
			 $.ajaxPost({url:url,msg:false,success:function(json,textStatus){
					    if(params.callback){
							params.callback(json);
							me.find(".pDiv2").find("input").val(currIndex);
							params.currIndex=currIndex;
							params.start=start;
							params.url=url;
							var pageSize=0,pages=0;
							if(json.total<(params.start+params.limit)){
							  pageSize=json.total;
							}else{
							  pageSize=params.limit+params.start;
							}
							pages= Math.ceil(json.total/params.limit);
							me.find(".pDiv2 .pGroup .pPageStat").html("共<span>"+json.total+"</span>条数据，显示"+
																	  (params.start+1)+"-"+pageSize+"条");
							me.find(".pDiv2 .pGroup .pControl span").html(pages);
						}else{ $.omMessageTip.show({content : json.msg,timeout : 4000,type:'error'});}
			 }});
		}
        function setQueStr(url, ref, value) {//设置参数值
		    var paraString = url.substring(url.indexOf("?")+1,url.length).split("&"); 
			var paraObj = {} 
			for (i=0; j=paraString[i]; i++){ 
				paraObj[j.substring(0,j.indexOf("="))] = j.substring(j.indexOf("=")+1,j.length); 
			} 
			paraObj[ref]=value;
			if (url.indexOf("?")>-1){
				var lastIndex=url.indexOf("?");
				url=url.substring(0, lastIndex);
			}
			paramArr=[];
			for(var p in paraObj){
				var name=p;//属性名称   
				var value=paraObj[p];//属性对应的值   
				paramArr.push(name+"="+value);
			}  
			return url+"?"+paramArr.join("&");
		};
		$.fn.refreshData = function (){
			if(params.callback){
			   if(params.url!=""){
				  loadGridData(params.url,params.currIndex,params.start);
			   }
		     }
		}
  	}
	
	$.fn.drap = function(opts) { // 拖动
	     var _self = this, _this = $(this), posX = 0, posY = 0;
         opts = jQuery.extend({
            DrapMove: null,
            IsLimit: false,
            Callback: function () { }
         }, opts || {});
         _self.mousemove = function (e) {
            e.stopPropagation();
            if ($.browser.msie) { e = window.event; }
            var x = e.clientX - posX;
            var y = e.clientY - posY;
            if (opts.IsLimit) {
                if (x < 0) {
                    x = 0;
                }
                if (y < 0) {
                    y = 0;
                }
                if (x > ($(document).width() - _this.width() - 2)) {
                    x = ($(document).width() - _this.width() - 2);
                }
                if (y > ($(document).height() - _this.height() - 2)) {
                    y = ($(document).height() - _this.height() - 2);
                }
            }
            _this.css("left", x + "px");
            _this.css("top", y + "px");
            //_this.find(opts.DrapMove).text("top:" + y + ",left:" + (x + _this.width()))
        }
        _this.find(opts.DrapMove).mousedown(function (e) {
            e.stopPropagation();
            if ($.browser.msie) { e = window.event; }
            posX = e.clientX - parseInt(_this.offset().left);
            posY = e.clientY - parseInt(_this.offset().top);
            $(document).mousemove(function (ev) {
                _self.mousemove(ev);
            });
        });
        $(document).mouseup(function () {
            $(document).unbind("mousemove");
            opts.Callback();
        });
        _this.find(opts.DrapMove).css("cursor", "move");
  	}	
	
	
})(jQuery);


