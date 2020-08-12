
$("#account").blur (function(){
	
	var nickname = $("#account").val();

	if(nickname == '' ){
        
    
		$(this).next().html("帐号不能为空");
		
		$(this).next().show();
    }
});

$("#password").blur (function(){
    check_pwd(); 
});


$("#password").focus(function(){
	$(this).next().hide();
});

$("#account").focus(function(){
	$(this).next().hide();
})



function check_pwd(){
	
	var pwd = $("#password").val();
	
	var reg = /^[\w@!#$%^&*~]{0,150}$/;
	
	if(!reg.test(pwd)){
		$("#password").next().html("密码不符合规范");
		$("#password").next().show();
	}
}

$("#cor_password").blur(function(){
	check_cpwd();	
});
$("#cor_password").focus(function(){
	$(this).next().hide();
});

	
function check_cpwd(){

	var pwd = $("#password").val();
	
	var cpwd = $("#cor_password").val();
	
	
	if(cpwd == ''){
		$("#cor_password").next().html("确认密码不能为空");
		$("#cor_password").next().show();
	}
	if(cpwd != pwd){
		$("#cor_password").next().html("输入的密码不一致");
		$("#cor_password").next().show();
	}
}