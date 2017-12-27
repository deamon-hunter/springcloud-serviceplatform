// 以下为官方示例
$().ready(function() {
	validateRule();
    loadUser();
	// $("#signupForm").validate();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
/**
 * 填充表单
 */
function loadUser(){
    var deptId= $("#deptId").val();
    var userId=$('#userId').val();
    //只有单个角色
    var roleId=$('#roleIds').val();
    //通过部门id 查询次用户能够选择的角色
    $.ajax({
        type : "post",
        data: {depetId:deptId},
        dataType: 'json',
        url : "/sys/role/depetId",
        success : function(roles) {
            addRole(roles.data,roleId);
            //回显角色

        }
    });
}
function update() {
	$("#roleIds").val(getCheckedRoles());
	$.ajax({
		cache : true,
		type : "POST",
		url : "/sys/user/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg(data.msg);
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.msg(data.msg);
			}

		}
	});

}
function getCheckedRoles() {
    //适合获取单选的值
    var adIds = $("input[name='role']:checked").val();
    // $("input:checkbox[name=role]:checked").each(function(i) {
    // 	if (0 == i) {
    // 		adIds = $(this).val();
    // 	} else {
    // 		adIds += ("," + $(this).val());
    // 	}
    // });
    return adIds;
}
function setCheckedRoles() {
	var roleIds = $("#roleIds").val();

	var adIds = "";
	$("input:checkbox[name=role]:checked").each(function(i) {
		if (0 == i) {
			adIds = $(this).val();
		} else {
			adIds += ("," + $(this).val());
		}
	});
	return adIds;
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			},
			username : {
				required : true,
				minlength : 2,
                remote : {
                    url : "/sys/user/exit", // 后台处理程序
                    type : "post", // 数据发送方式
                    dataType : "json", // 接受数据格式
                    data : { // 要传递的数据
                        username : function() {
                            return $("#username").val();
                        }
                    }
                }
			},
			password : {
				required : true,
				minlength : 6
			},
			confirm_password : {
				required : true,
				minlength : 6,
				equalTo : "#password"
			},
			email : {
				required : true,
				email : true
			},
			topic : {
				required : "#newsletter:checked",
				minlength : 2
			},
			agree : "required"
		},
		messages : {

			name : {
				required : icon + "请输入姓名"
			},
			username : {
				required : icon + "请输入您的用户名",
				minlength : icon + "用户名必须两个字符以上",
                remote : icon + "用户名已经存在"
			},
			password : {
				required : icon + "请输入您的密码",
				minlength : icon + "密码必须6个字符以上"
			},
			confirm_password : {
				required : icon + "请再次输入密码",
				minlength : icon + "密码必须6个字符以上",
				equalTo : icon + "两次输入的密码不一致"
			},
			email : icon + "请输入您的E-mail",
		}
	})
}
var openDept = function(){
	layer.open({
		type:2,
		title:"选择部门",
		area : [ '300px', '450px' ],
		content:"/system/sysDept/treeView"
	})
}
function loadDept( deptId,deptName){
	$("#deptId").val(deptId);
	$("#deptName").val(deptName);
}
/**
 * 通过传入角色数组 生成对应的iput框
 * @param roles
 */
function addRole(roles) {
    $("#rolesLabel").empty();
    for(i=0;i<roles.length;i++){
        var role=roles[i];
        var createobj = $(" <label class='checkbox-inline'> <input name='role'  type='radio' value='"+role.roleId+"' text='"+role.roleName+"'>"+role.roleName +"</label>"); //把div定义为变量createobj
        $("#rolesLabel").append(createobj); //把createobj这个变量追加到html中的'body'里
    }
}
function addRole(roles,roleId) {
    $("#rolesLabel").empty();
    for(i=0;i<roles.length;i++){
        var role=roles[i];
        var createobj;
        if(roleId==role.roleId){
            createobj= $(" <label class='checkbox-inline'> <input name='role'  type='radio' checked='checked' value='"+role.roleId+"' text='"+role.roleName+"'>"+role.roleName +"</label>"); //把div定义为变量createobj
        }else{
            createobj= $(" <label class='checkbox-inline'> <input name='role'  type='radio' value='"+role.roleId+"' text='"+role.roleName+"'>"+role.roleName +"</label>"); //把div定义为变量createobj
        }
        $("#rolesLabel").append(createobj); //把createobj这个变量追加到html中的'body'里
    }
}