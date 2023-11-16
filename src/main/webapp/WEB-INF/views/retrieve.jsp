<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>모두 모여라, MoMo</title>
<link rel="stylesheet" href="css/top.css" />
<link rel="stylesheet" href="css/retrieve.css" />
<link rel="stylesheet" href="css/comment.css" />
</head>
<body>
	<jsp:include page="common/top.jsp" flush="true" /><br>
	<jsp:include page="momo/retrieve.jsp"/>
	<jsp:include page="momo/comment.jsp" flush="true" /><br>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script type ="text/javascript" src="js/retrieve.js"></script>
<script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=91jfq25mme&submodules=geocoder"></script>
<script type="text/javascript"> // 네이버지도 API
	var map = new naver.maps.Map("map", {
		center : new naver.maps.LatLng(37.3595316, 127.1052133),
		zoom : 15,
		mapTypeControl : true

	});

	map.setOptions({
		scaleControl : true,
		logoControl : true,
	});
	var infoWindow = new naver.maps.InfoWindow({
		anchorSkew : true
	});

	map.setCursor('pointer');
	function searchAddressToCoordinate(address) {
		naver.maps.Service
				.geocode(
						{
							query : address
						},
						function(status, response) {
							if (status === naver.maps.Service.Status.ERROR) {
								return alert('Something Wrong!');
							}

							if (response.v2.meta.totalCount === 0) {
								searchAddressToCoordinate('${ldto.loc_path}');
								return alert('상세지역을 찾을 수 없어 해당 시/구청의 위치를 표시합니다.');
							}

							var htmlAddresses = [], item = response.v2.addresses[0], point = new naver.maps.Point(
									item.x, item.y);

							if (item.roadAddress) {
								htmlAddresses.push('[도로명 주소] ' + item.roadAddress);
							}

							if (item.jibunAddress) {
								htmlAddresses.push('[지번 주소] ' + item.jibunAddress);
							}

							infoWindow
									.setContent([
											'<div style="padding:10px;min-width:200px;line-height:150%;">',
											'<h4 style="margin-top:5px;">검색 주소 : '
													+ address + '</h4><br />',
											htmlAddresses.join('<br />'), '</div>' ]
											.join('\n'));

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
				searchAddressToCoordinate('${mainboardDTO.momoLoc}');
			}
		});

		$('#submit').on('click', function(e) {
			e.preventDefault();

			searchAddressToCoordinate($('#address').val());
		});

		searchAddressToCoordinate('${mainboardDTO.momoLoc}');
	}
	function makeAddress(item) {
		if (!item) {
			return;
		}

		var name = item.name, region = item.region, land = item.land, isRoadAddress = name === 'roadaddr';

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

		return [ sido, sigugun, dongmyun, ri, rest ].join(' ');
	}

	function hasArea(area) {
		return !!(area && area.name && area.name !== '');
	}

	function hasData(data) {
		return !!(data && data !== '');
	}

	function checkLastString(word, lastString) {
		return new RegExp(lastString + '$').test(word);
	}

	function hasAddition(addition) {
		return !!(addition && addition.value);
	}

	naver.maps.onJSContentLoaded = initGeocoder;
</script>
</html>