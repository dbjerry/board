<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/login.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/js/jquery-3.3.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
<title>login.jsp</title>
<script type="text/javascript">
	$(document).ready(function(){
		if(getCookie("remember") == "Y"){
			$("#remember-me").attr("checked", true);
					
			$("#userId").val(getCookie("userId"));
		} else {
			$("#remember-me").attr("checked", false);
		}
	});
	
	function getCookie(cookieName){
		
		var result = "";
		var cookieString = document.cookie;
		var cookies = cookieString.split("; ");
		
		for (var i = 0; i < cookies.length; i++){
			var str = cookies[i];
			if(str.indexOf(cookieName + "=") >= 0){
				result = str.substring(str.lastIndexOf("=")+1);
			}
		}
		return result;
	}
</script>
</head>
<body>
<div class="container">
	<div class="div_out">
		<div class="div_in">
			<div class="centered">
				<form action="/login" method="post">
					<h1>Login :)</h1>
					<input type="text" name="userId" id="userId" required autofocus placeholder="Give me your ID"><br>
					<input type="password" name="pass" id="pass" placeholder="Your secret password" required><br>
					<label>
						<input id="remember-me" type="checkbox" value="remember-me" name="remember-me">I will remember you
					</label><br><br>
					<button id="login_btn" type="submit">Login</button>
				</form>
			</div>
		</div>
	</div>
</div>

</body>

</html>

