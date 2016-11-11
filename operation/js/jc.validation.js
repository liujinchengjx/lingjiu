/**
通用表单校验 
Author:liujc
Date:2012-11-13
**/
validateForm = function (id){
	var flag = true;
	var hasFocus=false;
	$.each($("#"+id+" input,#"+id+" textarea,#"+id+" select"),function(i,field){
		 //alert($(field).attr("name")+"===="+$(field).attr("isValidate"));
		if($(field).attr("validate")!=null && $(field).attr("validate")!="undefined" && $(field).attr("isValidate")!='false'){
			 if(validateField($(field)) == false){
			 	flag = false;
			 	if(hasFocus==false){//出错是到页面的最上端
			 		$(field).focus();
			 		hasFocus=true;
			 	}
			 } 
		 }
	});
	
	return flag;
};
	
initValidateForm = function (id){
	$.each($("#"+id+" select,#"+id+" textarea,#"+id+" input"),function(i,field){
	 	if($(field).attr("validate")!=null && $(field).attr("validate")!="undefined" && $(field).attr("isValidate")!='false'){
			$(field).bind("blur",function(){
	    		validateField($(this));
	   		});
	  	}
	 });
};
	
validateField = function (field){
	var sValidate = field.attr("validate");
	var validate  = eval("({"+sValidate+"})");
	
	var rules    = validate.rules;
	var custom   = validate.custom;
	var ajax     = validate.ajax;
	var msgContainer = validate.msgContainer;
	var promptText = "";
	if(rules!=null && rules!="undefined"){
		$.each(rules,function(i,rule){
			var errorMsg = validateFieldRule(field,rule);
			if(errorMsg!=null) promptText +=""+ errorMsg +"";
		});
	}
	if(custom!=null && custom!="undefined"){
		var subPromptText = validateFieldCustom(field,custom);
		if(promptText.length>0){
			promptText=promptText+subPromptText;
		}else{
			promptText = subPromptText;
		}
	}
	var flag = false;
	if(promptText.length>0){
		  showValidateMsg(field,promptText,msgContainer);//提示
		  flag = false;
	 }else{
	  hideValidateMsg(field,msgContainer);//隐藏
	  flag = true;
	 }
	
	 return flag;
};
	
	
validateFieldRule=function (field,rule){
	var errorMsg;
	switch(rule){
		case "required"     : errorMsg = _validateRequired(field);break;
		case "integer"      : errorMsg = _validateInteger(field);break;
		case "mobile"        : errorMsg = _validateMobile(field);break;
		case "email"        : errorMsg = _validateEmail(field);break;
		case "url"            : errorMsg = _validateUrl(field);break;
		case "upperCaseChar"  : errorMsg = _validateUrl(field);break;
		case "postcode"    :errorMsg = _validatePostcode(field);break;
		case "password"    :errorMsg = _validatePassword(field);break;
	}
	return errorMsg;
};


validateFieldCustom=function (filed,fn){
	return fn.call(this);
};

validateFieldRegx=function (filed,regx){
	 
};

validateFieldAjax = function (filed,ajax){

};

_validatePassword=function(filed){
	if(filed.val()=='') return '';
	var regx =	/^\w{6,16}$/i;
	if(!regx.test(filed.val())) return "请输入6-16位数字或字母字符";
}
_validatePostcode=function(filed){
	if(filed.val()=='') return '';
	var regx =	/^\d{6}$/i;
	if(!regx.test(filed.val())) return "格式不对";
};

_validateEmail=function(filed){
	if(filed.val()=='') return '';
	var regx = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/i; 
	if(!regx.test(filed.val())) return "格式不对";
};

_validateMobile=function(filed){
	if(filed.val()=='') return '';
	var regx =	/^1[3|5|8|4][0-9]{9}$/i
	if(!regx.test(filed.val())) return "格式不对";
};

_validateRequired=function (filed){
	var st=$(filed).attr('showtext');
	if (st){
		if (! $(filed).attr('_trueValue')){
		   return "请选择列表！";	
		}
	}else if($(filed).val() == null || $(filed).val() ==""){
		return "不能为空";
	}
};

_validateInteger=function (filed){
	if(filed.val()=='') return '';
	var reg = /^[0-9]+$/;
	if(!reg.test($(filed).val())){
		return "请输入整数";
	}
};

function _validateMin(filed){
	if(filed.val()=='') return '';
	var reg = /^[0-9]+$/;
	 if(!reg.test($(filed).val())){
	  return "请输入大于0的整数";
	 }
};


showValidateMsg=function (field,msg,msgContainer){//显示消息
	var $this=$(field);
	var fieldid = $this.attr("id");
	var isEditTable=$this.parents(".edit-tables")
	if(isEditTable.length>0){
		hideValidateMsg(field,msgContainer);//现在之前先把原有的消息隐藏
		var msgId= "for_"+fieldid;//默认的消息容器是当前输入域ID对应的for_id
		if(msgContainer!=null && msgContainer!="undefined"){//如果有指定的消息容器则使用指定的
			msgId=msgContainer
		}
		var title = $("#"+msgId).attr("title");
		if(title!=null && title!="undefined"){
			msg=title + msg
		}
		$("#"+fieldid).closest("td").prev().
		prepend(" <label id='for_"+fieldid+"' style='display: inline;' class='error'>"+msg+"</label>");			
	}else{
		 $this.css('border','red solid 2px');
	}
}

showValidateMsgAtMsgDiv=function (field,msg){
	$("#msgdiv").html(msg);
};

hideValidateMsg=function (field,msgContainer){
	var $this=$(field);
	var isEditTable=$this.parents(".edit-tables")
	if(isEditTable.length>0){
		var fieldid = $this.attr("id");
		var msgId= "for_"+fieldid;//默认的消息容器是当前输入域ID对应的for_id
		if(msgContainer!=null && msgContainer!="undefined"){//如果有指定的消息容器则使用指定的
			msgId=msgContainer
		}
		$("#"+msgId).remove();
	}else{
		 $this.css('border','');
	}
};

hideValidateMsgAtMsgDiv=function (field){
	 $("#msgdiv").hide();
};

