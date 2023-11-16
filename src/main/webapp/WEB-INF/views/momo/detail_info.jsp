<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% pageContext.setAttribute("replaceChar", "\n"); %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=91jfq25mme&submodules=geocoder"></script>
<meta charset="UTF-8">
<script>
$(document).ready(function() {
	$("#fix_button").on("click",function(){
		var num = $(this).attr("data-num");
		location.href="momoUpdateForm?momo_id="+num;
	}); 
	$("#delete_button").on("click", function() {
		var momo_id = $(this).attr("data-num");	
		if(confirm("정말로 모임을 삭제하시겠습니까?")){
		$.ajax({
			type : "get",
			url : "deleteMomo",
			data : {
				momo_id : momo_id
			},
			dataType : "text", //응답받을 데이터 타입
			success : function(data, status, xhr) {
				location.href="momoManage"
			},
			error : function(xhr, status, error) {
				console.log("에러발생")
			}
		})}
		else{
			
		}

	});
});
</script>
</head>
<body>
<div class="select">
	<img src="https://kr.object.ncloudstorage.com/team6-image/momo_img/${mdto.momo_img}">
	<ul class="clearfix">
		<li>정보</li> 
           <li><a href="momoDetail_manage?momo_id=${momo_id}">신청관리</a></li>
       </ul>
<hr>
</div>
<div class="info">
        <P>기본 정보</P><br>
            <table>
                <tr>
                    <td width="200px">분류</td>
                    <td width="800px">${cdto.cate_path} </td>
                   </tr>   
                   <tr>
                    <td>모모 이름</td>
                    <td>${mdto.momoName}</td>
                   </tr>   
                   <tr>
                    <td>한줄소개</td>
                    <td >${mdto.momoIntro}</td>
                   </tr>   
                   <tr>
                    <td>상세소개</td>
                    <td>${fn:replace(mdto.momoDetail, replaceChar, "<br/>")}</td>
                   </tr>   
                   <tr>
                   <td colspan="2">
                    <div id="map" style="width:800px;height:400px;"></div>
                   </td>
                   </tr> 
                   <tr>
                    <td>장소</td>
                    <td>${ldto.loc_path} ${mdto.momoLoc}</td>
                   </tr>
                   <tr>
                    <td>날짜</td>
                    <td >${mdto.momoDate}</td>
                   </tr> 
                   <tr>
                    <td>시간</td>
                    <td >${mdto.momoTime}</td>
                   </tr>    
                   <tr>
                    <td>참가비용</td>
                    <td >${mdto.fee} </td>
                   </tr>   
                   <tr>
                    <td>인원</td>
                    <td >${mdto.headcount} / ${mdto.maxHeadcount}</td>
                   </tr>   
                   <tr>
                    <td>준비물</td>
                    <td >${mdto.materials}</td>
                   </tr>
            </table>
                   <div class="button">
             <input type="button" value="수정하기" id="fix_button" data-num="${momo_id}">
             <input type="button" value="삭제하기" id="delete_button" data-num="${momo_id}">
        </div>
        </div>
</body>
<script>

var map = new naver.maps.Map("map", {
    center: new naver.maps.LatLng(37.3595316, 127.1052133),
    zoom: 15,
    mapTypeControl: true
    
});

map.setOptions({
    scaleControl: true,
    logoControl: true,
});
var infoWindow = new naver.maps.InfoWindow({
    anchorSkew: true
});

map.setCursor('pointer');
function searchAddressToCoordinate(address) {
    naver.maps.Service.geocode({
        query: address
    }, function(status, response) {
        if (status === naver.maps.Service.Status.ERROR) {
            return alert('Something Wrong!');
        }

        if (response.v2.meta.totalCount === 0) {
            searchAddressToCoordinate('${ldto.loc_path}');
        	return alert('상세지역을 찾을 수 없어 해당 시/구청의 위치를 표시합니다.');
        }

        var htmlAddresses = [],
            item = response.v2.addresses[0],
            point = new naver.maps.Point(item.x, item.y);

        if (item.roadAddress) {
            htmlAddresses.push('[도로명 주소] ' + item.roadAddress);
        }

        if (item.jibunAddress) {
            htmlAddresses.push('[지번 주소] ' + item.jibunAddress);
        }

        infoWindow.setContent([
            '<div style="padding:10px;min-width:200px;line-height:150%;">',
            '<h4 style="margin-top:5px;">검색 주소 : '+ address +'</h4><br />',
            htmlAddresses.join('<br />'),
            '</div>'
        ].join('\n'));

        map.setCenter(point);
        infoWindow.open(map, point);
    });
}
function initGeocoder() {
    map.addListener('click', function(e) {
        searchCoordinateToAddress(e.coord);
    });

    $('#address').on('keydown', function(e) {
        var keyCode = e.which;

        if (keyCode === 13) { // Enter Key
            searchAddressToCoordinate('${mdto.momoLoc}');
        }
    });

    $('#submit').on('click', function(e) {
        e.preventDefault();

        searchAddressToCoordinate($('#address').val());
    });

    searchAddressToCoordinate('${mdto.momoLoc}');
}
function makeAddress(item) {
    if (!item) {
        return;
    }

    var name = item.name,
        region = item.region,
        land = item.land,
        isRoadAddress = name === 'roadaddr';

    var sido = '', sigugun = '', dongmyun = '', ri = '', rest = '';

    if (hasArea(region.area1)) {
        sido = region.area1.name;
    }

    if (hasArea(region.area2)) {
        sigugun = region.area2.name;
    }

    if (hasArea(region.area3)) {
        dongmyun = region.area3.name;
    }

    if (hasArea(region.area4)) {
        ri = region.area4.name;
    }

    if (land) {
        if (hasData(land.number1)) {
            if (hasData(land.type) && land.type === '2') {
                rest += '산';
            }

            rest += land.number1;

            if (hasData(land.number2)) {
                rest += ('-' + land.number2);
            }
        }

        if (isRoadAddress === true) {
            if (checkLastString(dongmyun, '면')) {
                ri = land.name;
            } else {
                dongmyun = land.name;
                ri = '';
            }

            if (hasAddition(land.addition0)) {
                rest += ' ' + land.addition0.value;
            }
        }
    }

    return [sido, sigugun, dongmyun, ri, rest].join(' ');
}

function hasArea(area) {
    return !!(area && area.name && area.name !== '');
}

function hasData(data) {
    return !!(data && data !== '');
}

function checkLastString (word, lastString) {
    return new RegExp(lastString + '$').test(word);
}

function hasAddition (addition) {
    return !!(addition && addition.value);
}

naver.maps.onJSContentLoaded = initGeocoder;
</script>
</html>