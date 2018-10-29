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
<link rel="shortcut icon" href="favicon.ico" />
<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>


<script src="/SE2/js/HuskyEZCreator.js"></script>
<script type="text/javascript">
	var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.

	$(document)
			.ready(
					function() {
						// Editor Setting
						nhn.husky.EZCreator.createInIFrame({
							oAppRef : oEditors, // 전역변수 명과 동일해야 함.
							elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
							sSkinURI : "/SE2/SmartEditor2Skin.html", // Editor HTML
							fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
							htParams : {
								// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
								bUseToolbar : true,
								// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
								bUseVerticalResizer : true,
								// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
								bUseModeChanger : true,
							}
						});

						// 전송버튼 클릭이벤트
						$("#savebutton").click(
								function() {
									if (confirm("저장하시겠습니까?")) {
										// id가 smarteditor인 textarea에 에디터에서 대입
										oEditors.getById["smarteditor"].exec(
												"UPDATE_CONTENTS_FIELD", []);

										// 이부분에 에디터 validation 검증
										if (validation()) {
											$("#frm").submit();
										}
									}
								})

						attachFile = {
							idx : $("#attaListSize").val(),
							add : function() { // 파일필드 추가
								var o = this;
								var idx = o.idx;
								if(o.idx < 4){
									var div = document.createElement('div');
									div.style.marginTop = '3px';
									div.id = 'file' + o.idx;
	
									var file = document.all ? document
											.createElement('<input name="files">')
											: document.createElement('input');
									file.type = 'file';
									file.name = 'files' + o.idx;
									file.size = '40';
									file.id = 'fileField' + o.idx;
	
									var btn = document.createElement('input');
									btn.type = 'button';
									btn.value = '삭제';
									btn.onclick = function() {
										o.del(idx)
									}
									btn.style.marginLeft = '5px';
	
									div.appendChild(file);
									div.appendChild(btn);
									document.getElementById('attachFileDiv')
											.appendChild(div);
									
										o.idx++;
								}
							},
							del : function(idx) { // 파일필드 삭제
								
								if (document.getElementById('fileField' + idx).value != ''
										&& !confirm('삭제 하시겠습니까?')) {
									return;

								}
								document.getElementById('attachFileDiv')
										.removeChild(
												document.getElementById('file'
														+ idx));
								this.idx--;
							}
						}

						
						$(".deleteAtta").on("click", function(){
							
							var attafile = $("#attafile").val();
							console.log(attafile);
							
							$("#attaFileName").val(attafile);
							$("#hiddenFrm").submit();
							
							$(this).parent().remove();
							attachFile['idx']--;
							
						});
					});

	// 필수값 Check
	function validation() {
		var contents = $.trim(oEditors[0].getContents());
		if (contents === '<p>&nbsp;</p>' || contents === '') { // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음. 
			alert("내용을 입력하세요.");
			oEditors.getById['smarteditor'].exec('FOCUS');
			return false;
		}

		return true;
	}
</script>
<%@ include file="/common/basicLib.jsp"%>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<form id="hiddenFrm" action="/updateAttachFile" method="post">
	<input type="hidden" id="attaFileName" name="attaFileName">
</form>
<body>
<%@ include file="/common/header.jsp"%>
<div class="container-fluid">
	<div class="row">
		
		<%@ include file="/common/left.jsp"%>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main tests">
		
			<form action="/postsUpdateForm" method="post" id="frm"
				class="form-horizontal" role="form" enctype="multipart/form-data">
				<!-- "/addPostsForm" ==> PostsServlet.java -->	
				<input type="hidden" name="board_id" value="${r_postsvo.board_id }">
				<input type="hidden" name="posts_no" value="${r_postsvo.posts_no }">
				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label">제목</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" style="width: 700px;" id="posts_title" 
							name="posts_title" readonly value="${r_postsvo.posts_title }">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-10">
						<label for="userNm" class="col-sm-2 control-label">내용</label>
						<textarea name="smarteditor" id="smarteditor" rows="10" cols="100" style="width:766px; height:412px;">
							${r_postsvo.posts_cnt }
						</textarea> 
						<!-- <input type="button" id="savebutton" value="서버전송" /> -->
					</div>
				</div>
				
				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label">첨부파일</label>
					<div class="col-sm-10">
						<c:choose>
							<c:when test="${update_attaList != null }">
								<c:forEach items="${update_attaList}" var="updateAtta">
									<div>
										<input type="hidden" id="attafile" name="attafile" value="${updateAtta.atta_no }">
										<input type="text" readonly value="${updateAtta.atta_file}">
										<input type="button" class="deleteAtta" value="삭제">
									</div>
								</c:forEach>
							</c:when>
						</c:choose>
						<div id="attachFileDiv">
							<input type="hidden" id="attaListSize" value="${update_attaList.size() }">
							<input type="file" name="files4" value="" size="40">
							<input type="button" value="추가" onclick="attachFile.add()" style="margin-left:5px">
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button id="savebutton" type="button" class="btn btn-default">수정</button>
					</div>
				</div>
				
			</form>
		</div>
	</div>
</div>	
</body>
</html>