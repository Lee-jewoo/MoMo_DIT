<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<meta charset="UTF-8">

    <script>
    $(document).ready(function() {

    	$(".banish_button").on("click",function(){
    		var guest_id = $(this).attr("data-guest_id");
    		var momo_id = $(this).attr("data-momo_id");
    		if(confirm("정말로 모임에서 추방하시겠습니까?")){
    			$.ajax({
        			
                    type:"get",
                    url:"banishGuest",
                    data:{
                    	guest_id:guest_id,momo_id:momo_id
                    	},
                    dataType:"text",  //응답받을 데이터 타입
                    success:function(data,status,xhr){
                    	location.reload();
                    },
                    error:function(xhr,status,error){
                        console.log("에러발생")
                    }
                })
        		
    		}else{
    		}
    	});
    	
    	$(".agree_button").on("click",function(){
    		var applicant_id = $(this).attr("data-applicant_id");
    		var momo_id=$(this).attr("data-momo_id");
    		var nickname=$(this).attr("data-nickname");
    		var headcount=$(this).attr("data-headcount");
    		var maxHeadcount=$(this).attr("data-maxHeadcount");
    		if(maxHeadcount<=headcount){
    			alert("모집인원이 가득찼습니다.");
    		}
    		else{
    		$.ajax({
    			
                type:"get",
                url:"agreeApplicant",
                data:{
                	applicant_id:applicant_id,
                	momo_id:momo_id,
                	nickname:nickname
                	},
                dataType:"text",  //응답받을 데이터 타입
                success:function(data,status,xhr){
                	location.reload();
                },
                error:function(xhr,status,error){
                    console.log("에러발생")
                }
            })}
    	});    	
    	
    	$(".disagree_button").on("click",function(){
    		var applicant_id = $(this).attr("data-applicant_id");
    		if(confirm("정말로 신청을 거부하시겠습니까?")){
    		$.ajax({
    			
                type:"get",
                url:"disagreeApplicant",
                data:{
                	applicant_id:applicant_id
                	},
                dataType:"text",  //응답받을 데이터 타입
                success:function(data,status,xhr){
                	location.reload();
                },
                error:function(xhr,status,error){
                    console.log("에러발생")
                }
            })}
    		else{
    			
    		}
    	});   	
    	
    	$(".cancel_button").on("click",function(){
    		var cancel_id = $(this).attr("data-cancel_id");
    		$.ajax({
    			
                type:"get",
                url:"cancelGuest",
                data:{
                	cancel_id:cancel_id
                	},
                dataType:"text",  //응답받을 데이터 타입
                success:function(data,status,xhr){
                	location.reload();
                },
                error:function(xhr,status,error){
                    console.log("에러발생")
                }
            })
    	});
    });
    </script>
</head>
<body>
 <div class="select">
        <img src="https://kr.object.ncloudstorage.com/team6-image/momo_img/${dto.momo_img}">
        <ul class="clearfix">
            <li><a href="momoDetail_info?momo_id=${momo_id}">정보</a></li> 
            <li>신청관리</li>
        </ul>
        <hr>
    </div>
	<div class="manage">
		<p>회원 신청 관리</p>
		<br>
		<!-- 참여자 목록 -->
		<p>모모 참여자</p>
		<br>
		<div class="join">
			<table>
				<tr>
					<c:choose>
					<c:when test="${!empty gMap_list}">
					<c:forEach var="gMap" items="${gMap_list}" varStatus="status">
						<td>
							<table id="bgcolor">
								<tr>
									<td rowspan="2"><img src="https://kr.object.ncloudstorage.com/team6-image/profile_img/${gMap.profile_img}"></td>
									<td class="td_nickname">${gMap.nickname}</td>
									<td rowspan="2"><input type="button" value="추방"
										class="banish_button" data-guest_id="${gMap.guest_id}" data-momo_id="${gMap.momo_id}"></td>
								</tr>
								<tr>
									<c:choose>
									<c:when test="${gMap.gender == 'm'}">
										<td class="td_gender">남자</td>
									</c:when>
									<c:otherwise>
										<td class="td_gender">여자</td>
									</c:otherwise>
									</c:choose>
								</tr>
							</table>
						</td>
						<c:if test="${status.count%4==0}">
							<tr>
								<td height="10"></td>
							</tr>
						</c:if>
					</c:forEach>
					</c:when>
					<c:otherwise>
						<td>
							<table id="bgcolor">
								<tr>
									<td>참여자가 없습니다.</td>
								</tr>
							</table>
						</td>
					</c:otherwise>
					</c:choose>
				</tr>
			</table>
		</div>
		<!-- 신청자 목록 -->
		<p>신청자</p>
		<br>
		<div class="guest">
			<table>
				<c:choose>
				<c:when test="${!empty aMap_list}">
				<c:forEach var="aMap" items="${aMap_list}" varStatus="status">
					<tr>
					<td>
						<table id="bgcolor2">
							<tr>
								<td rowspan="2" width="100px" height="100px"><img
									src="https://kr.object.ncloudstorage.com/team6-image/profile_img/${aMap.profile_img}"></td>
								<td class="td_nickname">${aMap.nickname}</td>
								<td width="550px">${aMap.reason1}</td>
								<td><input type="button" value="승인" class="agree_button"
									data-applicant_id="${aMap.applicant_id}" data-nickname="${aMap.nickname}" data-momo_id="${momo_id}"
									data-headcount="${aMap.headcount}" data-maxHeadcount="${aMap.maxHeadcount}" ></td>
							</tr>
							<tr>
								<c:choose>
									<c:when test="${aMap.gender == 'm'}">
										<td class="td_gender">남자</td>
									</c:when>
									<c:otherwise>
										<td class="td_gender">여자</td>
									</c:otherwise>
								</c:choose>
								<td width="550px">${aMap.reason2}</td>
								<td><input type="button" value="거부" class="disagree_button" data-applicant_id="${aMap.applicant_id}"></td>
							</tr>
						</table>
						</td>
					</tr>
				</c:forEach>
				</c:when>
				<c:otherwise>
					<td>
						<table id="bgcolor2">
							<tr>
								<td>신청자가 없습니다.</td>
							</tr>
						</table>
					</td>
				</c:otherwise>
				</c:choose>
			</table>
		</div>
		<!-- 신청 취소 사유 목록 -->
		<p>신청 취소 사유</p>
		<br>
		<div class="cancel">
			<table>
				<c:choose>
				<c:when test="${!empty cMap_list}">
				<c:forEach var="cMap" items="${cMap_list}">
					<tr>
					<td>
						<table id="bgcolor2">
							<tr>
								<td width="800px">취소 사유1 : ${cMap.reason1}</td>
								<td rowspan="2"><input type="button" value="확인"
									class="cancel_button" data-cancel_id="${cMap.cancel_id}"></td>
							</tr>
							<tr>
								<td>취소 사유2 : ${cMap.reason2}</td>
							</tr>
						</table>
						</td>
					</tr>
				</c:forEach>
				</c:when>
				<c:otherwise>
					<td>
						<table id="bgcolor2">
							<tr>
								<td>취소한 사람이 없습니다.</td>
							</tr>
						</table>
					</td>
				</c:otherwise>
				</c:choose>
			</table>
		</div>
	</div>
</body>
</html>