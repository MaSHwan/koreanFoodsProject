<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
</head>
<body>
	
	
		
	
	<div id="map" style="width:300px; height:500px;"></div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=abc2d2f1dffc906d0c488a1f1ba5953c"></script>
	
	
	
	<script>
		
		
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng( 37.572756722019356, 126.9758715167294), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption);

// 마커가 표시될 위치입니다 
var markerPosition  = new kakao.maps.LatLng(37.572756722019356, 126.9758715167294); 

// 마커를 생성합니다
var marker = new kakao.maps.Marker({
    position: markerPosition
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);

var iwContent = '<div style="padding:5px;">설가온 <br><a href="https://www.google.com/maps/uv?pb=!1s0x357ca2eb52cf6963%3A0x87b70ab00f6b5b56!15sCgIYIQ&viewerState=lb&sa=X&ved=2ahUKEwjqtcKc2Mj8AhWLh1YBHYkbDGIQzeMEegQIChAA&imagekey=!1e10!2sAF1QipOgrvZ5HbMNd5F9phPTD4GXKTkpEY3DO0EQ0rXP,37.572756722019356,126.9758715167294" style="color:blue" target="_blank">메뉴보기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    iwPosition = new kakao.maps.LatLng(37.572756722019356, 126.9758715167294); //인포윈도우 표시 위치입니다

// 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({
    position : iwPosition, 
    content : iwContent 
});
  
// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
infowindow.open(map, marker); 
				
		        
		        
		
    </script>

</body>
</html>