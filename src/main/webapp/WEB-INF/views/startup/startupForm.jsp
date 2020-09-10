<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<!-- 우편주소 받는거 -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="/resources/js/addressapi.js"></script>
<script>
function execPostCode() {
    new daum.Postcode({
        oncomplete: function(data) {
           // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

           // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
           // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
           var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
           var extraRoadAddr = ''; // 도로명 조합형 주소 변수

           // 법정동명이 있을 경우 추가한다. (법정리는 제외)
           // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
           if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
               extraRoadAddr += data.bname;
           }
           // 건물명이 있고, 공동주택일 경우 추가한다.
           if(data.buildingName !== '' && data.apartment === 'Y'){
              extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
           }
           // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
           if(extraRoadAddr !== ''){
               extraRoadAddr = ' (' + extraRoadAddr + ')';
           }
           // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
           if(fullRoadAddr !== ''){
               fullRoadAddr += extraRoadAddr;
           }

           // 우편번호와 주소 정보를 해당 필드에 넣는다.
           console.log(data.zonecode);
           console.log(fullRoadAddr);
           
           
           
           document.getElementById('compAddr').value = fullRoadAddr;
           
       }
    }).open();
}

</script>
<head>
<meta charset="UTF-8">
<title>스타트업 회원가입 폼</title>
</head>
<body>
<div class="container">
<br> 
<hr>


<header class="card-header">
	<h4 class="card-title mt-2">스타트업 회원가입</h4>
</header>

<article class="card-body">
<form method="post" action="${contextPath}/startup/join_startup.do" enctype="multipart/form-data">

		<div class="col form-group">
			<label>아이디 </label>   
		  	<input type="text" class="form-control" name="id">
		</div> 
		 
		<div class="col form-group">
			<label>비밀번호</label>
		  	<input type="text" class="form-control" name="pwd" value="password">
		</div>
		
		<div class="col form-group">
			<label>회사명</label>
		  	<input type="text" class="form-control" name="compName" value="compName">
		</div>
		
		<div class="col form-group">
			<label>대표 이름</label>
		  	<input type="text" class="form-control" name="compCeoName" value="compCeoName">
		</div>
		
		<div class="col form-group">
			<label>이메일</label>
		  	<input type="email" class="form-control" name="compEmail" value="comp@test.com">
			<small class="form-text text-muted">정확한 이메일 형식을 지켜주세요.</small>
		</div>
		
		<div class="col form-group">
			<label>사업자 번호</label>
		  	<input type="text" class="form-control" name="compBizNo" value="123-345-6756">
		</div>
		<!-- 주소입력란  -->
		<div class="form-group">   
			<label>회사 주소</label>   
			<button type="button" class="btn btn-default" onclick="execPostCode();"><i class="fa fa-search"></i> 주소검색</button>                   
			<input class="form-control"  placeholder="주소 검색을 이용해 주세요" name="compAddr" id="compAddr" type="text" readonly="readonly" ><br>
    		<input class="form-control"  placeholder="상세주소" name="compDetailAddr" id="compDetailAddr" type="text" value="상세주소"/>
		</div>
		<!-- 주소입력 끝 -->
		
		<div class="col form-group">
			<label>전화번호</label>
		  	<input type="text" class="form-control" name="compTel" value="010-2345-9487">
		</div> 
	
		<div class="col form-group">
			<label>업력</label>
		  	<input type="text" class="form-control" name="compStart" value="1개월">
		</div> 
		
		<div class="col form-group">
			<label>세부사항</label>
		  	<textarea class="form-control" name="compDetail">세부사항이다</textarea>
		</div> 
		
		<div class="col form-group">
			<label>회사 이미지</label>
			<input type="file" class="form-control" name="compImg">
		</div> 
		
	
		<div class="form-group">
		  <label>업종</label>
			  <select id="compBizType" class="form-control" name="compBizType">
			      <option>가전 / 디지털</option>
			      <option>패션의류 / 잡화</option>
			      <option>홈인테리어</option>
			      <option>도서 / 음반</option>
			      <option>식품</option>
				  <option selected="">기타</option>
			  </select>
		</div>
		
	
    <div class="form-group">
		<button type="submit" class="btn btn-primary btn-block">
			가입 완료
		</button>
	</div> <!-- form-group// -->      
                                          
</form>
</article> <!-- card-body end .// -->



</div> 


</body>
</html>