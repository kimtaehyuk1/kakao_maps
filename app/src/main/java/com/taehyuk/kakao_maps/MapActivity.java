package com.taehyuk.kakao_maps;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.taehyuk.kakao_maps.R;

import net.daum.android.map.coord.MapCoordLatLng;
import net.daum.mf.map.api.MapCircle;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static net.daum.mf.map.api.MapPoint.mapPointWithGeoCoord;


public class MapActivity extends AppCompatActivity {

//    MapView mMapView;
    final static String TAG = "MapTAG";
    private static final MapPoint MARKER_POINT = null;
        ViewGroup mMapViewContainer;
    ArrayList<Document> bigMartList = new ArrayList<>(); //대형마트 MT1
    ArrayList<Document> documentArrayList = new ArrayList<>(); //지역명 검색 결과 리스트
    MapPOIItem searchMarker = new MapPOIItem();
    MapPoint currentMapPoint;
    private double mCurrentLng; //Long = X, Lat = Yㅌ
    private double mCurrentLat;
    private double mSearchLng = -1;
    private double mSearchLat = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_map);




//        MapView mapView = new MapView(this);
//        mapView.setDaumMapApiKey("5d0ff8b0a626cd2ff7ba148462f35a79");
//        ViewGroup mapViewContainer = findViewById(R.id.map_view);
//        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(37.5514579595, 126.951949155);
//        mapView.setMapCenterPoint(mapPoint, true);
        //true면 앱 실행 시 애니메이션 효과가 나오고 false면 애니메이션이 나오지않음.
//        mapViewContainer.addView(mapView);

//        MapPOIItem marker = new MapPOIItem();
//        marker.setItemName("한세사이버보안고등학교");
//        marker.setTag(0);
//        marker.setMapPoint(mapPoint);
//        // 기본으로 제공하는 BluePin 마커 모양.
//        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
//        // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
//        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
//        mapView.addPOIItem(marker);


//        MapPOIItem marker = new MapPOIItem();
//        marker.setItemName("Default Marker");
//        marker.setTag(10);
//        marker.setMapPoint(MARKER_POINT);
////        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
//        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
//
//        mapView.addPOIItem(marker);

//        MapCircle circle1 = new MapCircle(
//                MapPoint.mapPointWithGeoCoord(37.537094, 127.005470), // center
//                500, // radius
//                Color.argb(128, 255, 0, 0), // strokeColor
//                Color.argb(128, 0, 255, 0) // fillColor
//        );
//        circle1.setTag(1234);
//        mapView.addCircle(circle1);

        mCurrentLat= 126.951949155;
        mCurrentLng= 37.5514579595;
//////            mMapView.removeAllCircles();
        requestSearchLocal(mCurrentLng, mCurrentLat);
//////            mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
//////            mMapView.removeAllPOIItems();
////


    }





    //밑의 메소드는 우리가 통신 해서 서버로 부터 받아올 메소드
    private void requestSearchLocal(double x, double y) {


        MapView mapView = new MapView(this);
        ViewGroup mapViewContainer = findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);



//        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(mCurrentLng, mCurrentLat);
//        mapView.setMapCenterPoint(mapPoint, true);
//
//        MapPOIItem marker = new MapPOIItem();
//        marker.setItemName("한세고");
//        marker.setTag(0);
//        marker.setMapPoint(mapPoint);
//        // 기본으로 제공하는 BluePin 마커 모양.
//        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
//        mapView.addPOIItem(marker);
//        mMapView = new MapView(this);
//        mMapViewContainer = findViewById(R.id.map_view);
//        mMapViewContainer.addView(mMapView);




//        맵 리스너
//        mapView.setMapViewEventListener(this); // this에 MapView.MapViewEventListener 구현.
//        mapView.setPOIItemEventListener(this);
//        mapView.setOpenAPIKeyAuthenticationResultListener(this);


        bigMartList.clear();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);   // ApiInterface 객체에 레트로핏 객체를 매핑시키는 부분이다.
        apiInterface.getSearchCategory("KaKaoAK 5d0ff8b0a626cd2ff7ba148462f35a79", "MT1", x + "", y + "", 1000).enqueue(new Callback<CategoryResult>() {
            @Override
            public void onResponse(@NonNull Call<CategoryResult> call, @NonNull Response<CategoryResult> response) {
                if (response.code() == 200) {
                    assert response.body() != null;
                    if (response.body().getDocuments() != null) {
                        Log.d(TAG, "bigMartList Success");
                    bigMartList.addAll(response.body().getDocuments());
                    }

//                    모두 통신 성공 시 circle 생성
                    MapCircle circle1 = new MapCircle(
                            mapPointWithGeoCoord(y, x), // center
                            1000, // radius
                            Color.argb(128, 255, 0, 0), // strokeColor
                            Color.argb(128, 0, 255, 0) // fillColor
                    );
                    circle1.setTag(1234);
                    mapView.addCircle(circle1);
                    Log.d("SIZE1", bigMartList.size() + "");

                    int tagNum = 0;
                    for (Document document : bigMartList) {
                        MapPOIItem marker = new MapPOIItem();
                        marker.setItemName(document.getPlaceName());
                        marker.setTag(tagNum++);
                        double x = Double.parseDouble(document.getY());
                        double y = Double.parseDouble(document.getX());
                        //카카오맵은 참고로 new MapPoint()로  생성못함. 좌표기준이 여러개라 이렇게 메소드로 생성해야함
                        MapPoint mapPoint = mapPointWithGeoCoord(x, y);
                        marker.setMapPoint(mapPoint);
                        marker.setMarkerType(MapPOIItem.MarkerType.CustomImage); // 마커타입을 커스텀 마커로 지정.
                        marker.setCustomImageResourceId(R.drawable.mart); // 마커 이미지.
                        marker.setCustomImageAutoscale(false); // hdpi, xhdpi 등 안드로이드 플랫폼의 스케일을 사용할 경우 지도 라이브러리의 스케일 기능을 꺼줌.
                        marker.setCustomImageAnchor(0.5f, 1.0f); // 마커 이미지중 기준이 되는 위치(앵커포인트) 지정 - 마커 이미지 좌측 상단 기준 x(0.0f ~ 1.0f), y(0.0f ~ 1.0f) 값.
                        mapView.addPOIItem(marker);
                    }

                }
            }

            @Override
            public void onFailure(Call<CategoryResult> call, Throwable t) {
//                Log.d(TAG, "FAIL");
            }
        });




    }





}

























