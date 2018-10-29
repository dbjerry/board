<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		<li class="active"><a href="/main.jsp">Main<span class="sr-only">(current)</span></a></li>
		
		<c:if test="${S_USER.userId != null}">
			<li class="active"><a href="/board/addBoard.jsp">게시판 생성 및 수정</a></li>
		</c:if>

		<c:forEach items="${boardList }" var="leftBoardList">
			<c:choose>
				<c:when test="${leftBoardList.board_yn == 'Y'}">
					<li id="board" class="active">
						<a href=
						"/postsPageList?page=1&pageSize=10&board_id=${leftBoardList.board_id }&board_name=${leftBoardList.board_name }">
						${leftBoardList.board_name }</a>
					</li>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
		</c:forEach>
		
	</ul>
</div>

