<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../koreanFoods/foodSearch.css">
<title>Insert title here</title>
</head>
<body>
	
	<div class="map-wrap">
		<div id="map" style="width:100%;height:100%;position:relative; overflow:hidden;"></div>
		
		<div id="menu_wrap" class="bg_white">
			<div class="option">
				<div>
					<form onsubmit="searchPlaces(); return false;">
					음식점 : <input type="text" value=" " id = "keyword" size="15">
					<button type="submit">검색하기</button>
					</form>
				</div>
			</div>
			<hr>
			<ul id="placesList"></ul>
			<div id="pagination"></div>
		
		</div>
	</div>
	
	
	
	
	<div id="map" style="width:300px; height:500px;"></div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=abc2d2f1dffc906d0c488a1f1ba5953c"></script>
	
	
	
	<script>
		
		var markers = [];
		
		var infowindow = new kakao.maps.InfoWindow({zIndex:1});
		var container = document.getElementById('map');
		        var options = {
		            center: new kakao.maps.LatLng(33.450701, 126.570667),
		            level: 3
		        };
				// 지도 생성 
		        var map = new kakao.maps.Map(container, options);
				
				// 장소 검색 객체 생성
				var ps = new kakao.maps.services.Places(map);
				
				// 카테고리로 음식점 검색
				ps.categorySearch('FD6', placesSearchCB, {useMapBounds:true});
				
				// 키워드 검색 완료시 호출되는 콜백함수
				function placesSearchCB (data, status, pagination){
					if (status === kakao.maps.services.Status.OK){
						for(var i = 0; i < data.length; i++){
							displayMarker(data[i]);
						}
					}
				}
				
				// 지도에 마커표시하는 함수
				function displayMarker(place){
					// 마커를 생성하고 지도에 표시
					var marker = new kakao.maps.Marker({
						map: map,
						position: new kakao.maps.LatLng(place.y, place.x)
					});
					
					// 마커에 클릭이벤트 등록
					kakao.maps.event.addListener(marker, 'click', function(){
						// 마커를 클릭하면 장소명이 인포윈도우에 표출
						infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
						infowindow.open(map, marker);
					});
				}
		        
		        
		
    </script>

</body>
</html>