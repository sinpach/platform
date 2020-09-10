<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>헤더</title>
</head>
<body>
	<div class="container-fluid px-md-5  pt-4 pt-md-5">
		<div class="row justify-content-between">
			<div class="col-md-8 order-md-last">
				<div class="row">
					<div class="col-md-6 text-center">
						<a class="navbar-brand" href="/plt/">StartUp <span>Company</span> <small>Book Publishing Company</small></a>
					</div>
					<div class="col-md-6 d-md-flex justify-content-end mb-md-0 mb-3">
						<nav class="navbar navbar-expand-sm">
							<ul class="navbar-nav ml-auto">
								<li class="nav-item dropdown">
				                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
				                        	쪽지
				                    </a>
				                    <div class="dropdown-menu">
				                        <a class="dropdown-item" href="#">
				                        	<button class="btn btn-default text-muted" data-target="#mesPop" data-toggle="modal">쪽지 보내기</button>
				                        </a>
				                        <a class="dropdown-item text-muted text-center" href="${contextPath}/message/messageList.do?id=${member.id}">쪽지 함</a>
				                    </div>
			             		</li>
			             		<li class="nav-item dropdown">
			                    	 <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
				                        	마이페이지
				                    </a>
				                    <div class="dropdown-menu">
				                        <a class="dropdown-item text-muted text-center" href="#">내 정보 관리</a>
				                        <a class="dropdown-item text-muted text-center" href="#">스크랩 리스트</a>
				                        <a class="dropdown-item text-muted text-center" href="#">현황 관리</a>
				                        <a class="dropdown-item text-muted text-center" href="#">내역 관리</a>
				                        <a class="dropdown-item text-muted text-center" href="#">로그아웃</a>
				                    </div>
			                	</li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
			<div class="col-md-4 d-flex">
				<div class="social-media">
		    		<p class="mb-0 d-flex">
		    			<a href="#" class="d-flex align-items-center justify-content-center"><span class="fa fa-facebook"><i class="sr-only">Facebook</i></span></a>
		    			<a href="#" class="d-flex align-items-center justify-content-center"><span class="fa fa-twitter"><i class="sr-only">Twitter</i></span></a>
		    			<a href="#" class="d-flex align-items-center justify-content-center"><span class="fa fa-instagram"><i class="sr-only">Instagram</i></span></a>
		    		</p>
        		</div>
			</div>
		</div>
	</div>
	<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
	    <div class="container-fluid">
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="fa fa-bars"></span> Menu
	      </button>
	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav m-auto">
	        	<li class="nav-item"><a href="#" class="nav-link">사이트 소개</a></li>
	        	<li class="nav-item"><a href="#" class="nav-link">제조사 찾기</a></li>
	        	<li class="nav-item"><a href="#" class="nav-link">전문가 찾기</a></li>
	        	<li class="nav-item"><a href="#" class="nav-link">공지사항</a></li>
	        	<li class="nav-item"><a href="#" class="nav-link">Q&A</a></li>
	        </ul>
	      </div>
	    </div>
  	</nav>
    <!-- END nav -->
    <!-- message Modal -->
     <div class="modal fade" id="mesPop">
        <div class="modal-dialog">
            <div class="modal-content" align="center">
            	<!-- Modal Header -->
		        <div class="modal-header">
		        	<h4 class="modal-title">쪽지 보내기</h4>
		        	<button type="button" class="close" data-dismiss="modal">&times;</button>
		      	</div>
		      	<div class="modal-body">
	                <form class="py-4" method="post" action="${contextPath}/message/sendMessage.do">
	                	<input type="hidden" name="sendId" value="${member.id}"> 
	                    <div class="form-group col-sm-10">
	                        <label for="inputReceiveId">수신자</label>
	                        <input type="text" name="receiveId" class="form-control" id="inputReceiveId" placeholder="받을 사람">
	                    </div>
	                    <div class="form-group col-sm-10">
	                        <label for="InputContent">내용</label>
	                        <textarea class="form-control" name="content" id="InputContent" rows="5" cols="10" placeholder="내용을 입력하세요."></textarea>
	                    </div>
	                    <button type="submit" class="btn btn-primary">전송</button>
	                    <button type="reset" class="btn btn-warning">다시입력</button>
	                    <button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
	                </form>
		      	</div>
            </div>
        </div>
    </div>
</body>
</html>