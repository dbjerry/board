<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Insert title here</title>
<%@include file="/common/basicLib.jsp"%>
<%-- <% UserVo user = (UserVo) request.getAttribute("userVo"); %> --%>
<script>
	$(document).ready(function(){
		console.log("document.ready");
		
		//tr에 select (class="userClick")
		$(".setId").on("click", function(){
			
			var postsno = $("#posts_no").text(); //$(this).children()[1].innerText;
			
			$("#posts_no").val(postsno);
			$("#frm").submit();
		});
		
		// 댓글 삭제
		$(".updateCommBtn").on("click", function(){
			
			var comm_id = $(this).closest("tr").children()[0].innerText;
			var comm_use = $(this).closest("tr").children()[1].innerText;
			var board_id = '${postsvo.board_id}';
			var posts_no = '${postsvo.posts_no}';
			
			console.log(comm_use);
			
			if(comm_use == "N"){
				alert("삭제된 댓글입니다.");
				history.go(0);
			}else if(comm_use == "Y"){
				if(comm_id != null){
					alert("삭제하시겠습니까?");
					location.href = '/updateComm?board_id='+board_id+'&posts_no='+posts_no+'&comm_id='+comm_id;
				}
			}
		});
	});
</script>
<style type="text/css">
	.updateCommBtn{width:10px;height:13px;font-size:12px;background:gray;color:pink;border:1px solid #000;}
	.updateCommBtn > span{display:flex;justify-content:center;line-height:10px;}
</style>
</head>
<body>
<%-- header --%>
<%@include file="/common/header.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<%-- left --%>
			<%@include file="/common/left.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
					
				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label">글 제목</label>
					<div class="col-sm-10">
						${postsvo.posts_title}
					</div>
				</div><br>
				
				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label">글 내용</label>
					<div class="col-sm-10">
						${postsvo.posts_cnt}
					</div>
				</div>
				
				<c:if test="${S_USER.userId != null}">
					<c:if test="${S_USER.userId == postsvo.userid}">
					
						<form id="frm" action="/postsUpdateForm" method="get" class="form-horizontal" role="form">
							<input type="hidden" id="posts_no" name="posts_no" value="${postsvo.posts_no}"/>
							<input type="submit" class="btn btn=default" value="수정">
						</form>
						
						<form id="frm" action="/postsUpdateUse" method="post" class="form-horizontal" role="form">
							<input type="hidden" id="posts_no" name="posts_no" value="${postsvo.posts_no}"/>
							<input type="hidden" id="board_id" name="board_id" value="${postsvo.board_id}"/>
							<input type="submit" class="btn btn=default" value="삭제">
						</form>
				
					</c:if>
				</c:if>
				
				<form id="frm" action="/posts/addReplyPostsForm.jsp" 
					method="get" class="form-horizontal" role="form">
					<input type="hidden" id="posts_no" name="posts_no" value="${postsvo.posts_no}"/>
					<input type="hidden" id="board_id" name="board_id" value="${postsvo.board_id}"/>
					<input type="submit" class="btn btn=default" value="답글">
				</form>
				
				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label">첨부파일</label>
					<div class="col-sm-10">
						<c:forEach items="${attaList}" var="atta">
							${atta.atta_file}<br>
						</c:forEach>
					</div>
				</div><br>
				
				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label">댓글</label>
					<div class="col-sm-10">
						<form id="frm" action="/addComm" method="post" class="form-horizontal" role="form">
							<input type="hidden" id="board_id" name="board_id" value="${postsvo.board_id}"/>
							<input type="hidden" id="posts_no" name="posts_no" value="${postsvo.posts_no}"/>
							<input type="hidden" id="userid" name="userid" value="${S_USER.userId}"/>
							<input id="comm_cnt" name="comm_cnt" type="text" class="form-control" style="width: 300px;">
							<input type="submit" class="btn btn=default" value="댓글저장">
						</form>
					</div>
				</div><br>
				
				<div class="form-group">
					<div class="col-sm-10">
						<table border="1">
							<tr>
								<th colspan="3">댓글</th>
							</tr>
							<tr>
								<td style="display: none;">번호</td>
								<td style="display: none;">구분</td>
								<td>내용</td>
								<td>작성자</td>
								<td>작성일시</td>
							</tr>
						
							<c:forEach items="${commList}" var="comm">
								<tr class="tr_comm">
									<td style="display: none;">${comm.comm_id}</td>
									<td style="display: none;">${comm.comm_use}</td>
									<td>${comm.comm_cnt}
										<c:if test="${S_USER.userId != null}">
											<c:if test="${S_USER.userId == comm.userid}">
												<button class="updateCommBtn"><span>x</span></button>
											</c:if>
										</c:if>
									</td>
									<td>${comm.userid}</td>
									<td>${comm.comm_date}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

