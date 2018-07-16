<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8" >
    <title>验证码</title>
    <h2>验证码</h2>
    <img border=0 src="http://localhost:8080/vcode/get" id="imageMask" onclick="myReload()" style="cursor: pointer">
    <br/>
    <input type="text" id="signcode"/>
    <button value="提交" onclick="check()"  style="height: 20px;width: 60px"/>提交
</head>
<body>
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/mui.min.js"></script>
<script type="text/javascript">


    //用于刷新验证码
    function myReload() {
        document.getElementById("imageMask").src = document.getElementById("imageMask").src + "?nocache=" + new Date().getTime();
    }

    function check() {
        var vcode = $('#signcode').val();
        console.log("vcode==>"+vcode)
        $.ajax({
            url: "http://localhost:8080/vcode/login",
            type: 'GET',
                data: {
                signcode: vcode,
                account: "account",
                password: "password"
            },
            dataType:"json",
            xhrFields: {
                withCredentials: true
            },
            success: function (result) {
                console.log(result)
                if(result.code==200){
                    window.location.href="jsp/success.jsp";
                }else{
                    window.location.href="jsp/error.jsp";
                }
            }
        })
    }
</script>
</body>
</html>