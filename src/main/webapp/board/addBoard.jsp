<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/login.css">
<link rel="stylesheet" href="/css/font.css">
<title>addBoard.jsp</title>
<%@include file="/common/basicLib.jsp" %>

</head>
<body>
<%@include file="/common/header.jsp" %>
<%@include file="/common/left.jsp" %>
<div class="container" style="margin-bottom: 30px;">
	<div class="div_out">
		<div class="div_in">
			<div class="centered">
				<h2 style="font-family: Consolas; font-size: 27pt; font-weight: bold;">Create Board</h2><br>
					<form action="/boardInsert" method="post">
						<label>Board Name</label>
						<input type="text" id="board_name" name="board_name">
						<select name="board_yn">
							<option selected="selected">--선택--</option>
							<option value="Y">USE</option>
							<option value="N">UNUSED</option>
						</select>
						<input type="submit" value="create"><br>
					</form>
			</div>
		</div>
	</div>
</div>
<div class="container" style="margin-top: 30px;">
	<div class="div_out">
		<div class="div_in">
			<div class="centered">
			<h2 style="font-family: Consolas; font-size: 27pt; font-weight: bold;">Update Board</h2><br>
				<c:forEach items="${boardList }" var="addBoardList">
					<form action="/boardUpdate" method="post">
						<input type="hidden" id="board_id" name="board_id" value="${addBoardList.board_id }">
						<label>Board Name</label>
						<input type="text" id="board_name" name="board_name" value="${addBoardList.board_name }">
						<select name="board_yn" id="board_yn">
							<!--				
							<option value="Y" 
								${addBoardList.board_yn == "Y" ? "selected" : ""}>${addBoardList.board_yn }</option>
							<option value="N">${addBoardList.board_yn == "Y" ? "N" : "Y"}</option>
							-->
							<option value="Y">USE</option>
							<option value="N" ${addBoardList.board_yn == "N" ? 'selected' : ""}>UNUSED</option>
						</select>
						<input type="submit" class="setId" value="update"><br>
					</form>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
</body>
</html>