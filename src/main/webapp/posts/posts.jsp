
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<title>Insert title here</title>
<%@include file="/common/basicLib.jsp"%>
<style>
	.userClick {
		cursor : pointer;
	}
	
	#disabled{
		pointer-events: none;
		cursor: default;
	}
</style>
<script>
	$(document).ready(function(){
		console.log("document.ready");
		
		//tr에 select (class="userClick")
		$(".postsClick").on("click", function(){
			
			var posts_use = $(this).children()[4].innerText;
			var posts_no = $(this).children()[0].innerText;
			console.log(posts_no);
			/* $("#posts_no").val(posts_no);
			$("#frm").submit(); */
			
			if(posts_use == "N"){
				alert("삭제된 게시글입니다.");
				history.go(0);
			}else if(posts_use == "Y"){
				location.href = '/postsDetail?posts_no='+posts_no;
			}
		});
	});
</script>
</head>
<form id="frm" action="/postsDetail" method="get">
	<input type="hidden" id="posts_no" name="posts_no"/>
</form>
<body>
<%@include file="/common/header.jsp"%>
<div class="container-fluid">
	<div class="row">

		<%@include file="/common/left.jsp"%>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="row">
				<div class="col-sm-8 blog-main">
					<h2 class="sub-header">${boardvo.board_name}</h2>
					<div class="table-responsive">
						<table class="table table-striped table-hover">

							<tr>
								<th>게시글번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일시</th>
								<th style="display:none;">사용여부</th>
							</tr>
							
							<c:forEach items="${postsList}" var="posts">
								<tr class="postsClick">
									<td>${posts.posts_no}</td>
									<td>${posts.posts_title}</td>
									<td>${posts.userid}</td>
									<td>${posts.posts_date}</td>
									<td style="display:none;">${posts.posts_use}</td>
								</tr>
							</c:forEach>
							
						</table>
					</div>
					<a class="btn btn-default pull-right"
							href="/posts/addPostsForm.jsp?board_id=${boardvo.board_id}">게시글 등록</a>
					
					<div class="text-center">
						<ul class="pagination">
							<li>
								<c:choose>
									<c:when test="${page eq 1}">
										<a href="/postsPageList?page=1&pageSize=10&board_id=${boardvo.board_id}"
											aria-label="Previous" id="disabled"><span aria-hidden="true">&laquo;</span></a>
									</c:when>
									<c:otherwise>
										<a href="/postsPageList?page=1&pageSize=10&board_id=${boardvo.board_id}"
											aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
									</c:otherwise>
								</c:choose>
								
							</li>
							
							<li>
								<c:choose>
									<c:when test="${page > 1}">
										<a href="/postsPageList?page=${page-1}&pageSize=10&board_id=${boardvo.board_id}"
											aria-label="Previous"><span aria-hidden="true">&lt;</span></a>
									</c:when>
									<c:otherwise>
										<a href="/postsPageList?page=1&pageSize=10&board_id=${boardvo.board_id}"
											aria-label="Previous" id="disabled"><span aria-hidden="true">&lt;</span></a>
									</c:otherwise>
								</c:choose>
							</li>
							
							<c:forEach begin="1" end="${postsCnt}" var="i">
								<li><a href="/postsPageList?page=${i}&pageSize=10&board_id=${boardvo.board_id}">${i}</a></li>
							</c:forEach>
							
							<li>
								<c:choose>
									<c:when test="${page eq postsCnt}">
										<a href="/postsPageList?page=${postsCnt}&pageSize=10&board_id=${boardvo.board_id}"
											aria-label="Next" id="disabled"><span aria-hidden="true">&gt;</span></a>
									</c:when>
									<c:otherwise>
										<a href="/postsPageList?page=${page+1}&pageSize=10&board_id=${boardvo.board_id}"
											aria-label="Next"><span aria-hidden="true">&gt;</span></a>
									</c:otherwise>
								</c:choose>
							</li>
							
							<li>
								<c:choose>
									<c:when test="${page eq postsCnt}">
										<a href="/postsPageList?page=${postsCnt}&pageSize=10&board_id=${boardvo.board_id}"
											aria-label="Next" id="disabled" ><span aria-hidden="true">&raquo;</span></a>
									</c:when>
									<c:otherwise>
										<a href="/postsPageList?page=${postsCnt}&pageSize=10&board_id=${boardvo.board_id}"
											aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
									</c:otherwise>
								</c:choose>
							</li>
						</ul>
					</div>
				</div>	
			</div>
		</div>
	</div>
</div>
</body>
</html>