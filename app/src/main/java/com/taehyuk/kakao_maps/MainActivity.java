package com.taehyuk.kakao_maps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.taehyuk.kakao_maps.R;

import net.daum.mf.map.api.MapCircle;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//밑의 코드는  HttpURLConnection의 구현 코드이다.
//public class MainActivity extends AppCompatActivity {
//
//    private TextView tv_outPut;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // 위젯에 대한 참조.
//        tv_outPut = (TextView) findViewById(R.id.tv_outPut);
//
//        // URL 설정.
//        String url = "https://hosting.cafe24.com/";
//
//        // AsyncTask를 통해 HttpURLConnection 수행.
//        NetworkTask networkTask = new NetworkTask(url, null);
//        networkTask.execute();
//    }
//
//    public class NetworkTask extends AsyncTask<Void, Void, String> {
//
//        private String url;
//        private ContentValues values;
//
//        public NetworkTask(String url, ContentValues values) {
//
//            this.url = url;
//            this.values = values;
//        }
//
//        @Override //AsyncTask를 이용하고 excute시키면 이 함수 시행되는데(여기서 네트웍되는데 우린 클래스 따로 해서 기능은 다 구형된 상태 결과만씀
//        protected String doInBackground(Void... params) {
//
//            String result; // 요청 결과를 저장할 변수.
//            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
//            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.
//
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//
//            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
//            tv_outPut.setText(s);
//        }
//    }
//}




    public class MainActivity extends AppCompatActivity {

//        MapView mMapView;
//        final static String TAG = "MapTAG";
//        ViewGroup mMapViewContainer;
//        ArrayList<Document> bigMartList = new ArrayList<>(); //대형마트 MT1
//        MapPOIItem searchMarker = new MapPOIItem();
//        MapPoint currentMapPoint;
//        private double mCurrentLng; //Long = X, Lat = Yㅌ
//        private double mCurrentLat;
//        private double mSearchLng = -1;
//        private double mSearchLat = -1;

        Button search_btn;

        


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            setContentView(R.layout.activity_main);

            search_btn = (Button) findViewById(R.id.search_btn);

            search_btn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {

                    Intent intent = new Intent(MainActivity.this, MapActivity.class);
                    startActivity(intent);
                }
            });

//            MapView mapView = new MapView(this);
//            ViewGroup mapViewContainer = findViewById(R.id.map_view);
//            mapViewContainer.addView(mapView);


        }

    }



//        //밑의 메소드는 우리가 통신 해서 서버로 부터 받아올 메소드
//        private void requestSearchLocal(double x, double y) {
//
//
//             bigMartList.clear();
//
//            ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);   // ApiInterface 객체에 레트로핏 객체를 매핑시키는 부분이다.
//            Call<CategoryResult> call = apiInterface.getSearchCategory("KaKaoAK 5d0ff8b0a626cd2ff7ba148462f35a79", "MT1", x + "", y + "", 1000);
//            call.enqueue(new Callback<CategoryResult>() {
//                @Override
//                public void onResponse(@NonNull Call<CategoryResult> call, @NonNull Response<CategoryResult> response) {
//                    if (response.isSuccessful()) {
//                        assert response.body() != null;
//                        if (response.body().getDocuments() != null) {
//                            Log.d(TAG, "bigMartList Success");
//                            bigMartList.addAll(response.body().getDocuments());
//                        }
//
//                        //모두 통신 성공 시 circle 생성
//                        MapCircle circle1 = new MapCircle(
//                                MapPoint.mapPointWithGeoCoord(y, x), // center
//                                1000, // radius
//                                Color.argb(128, 255, 0, 0), // strokeColor
//                                Color.argb(128, 0, 255, 0) // fillColor
//                        );
//                        circle1.setTag(5678);
//                        mMapView.addCircle(circle1);
//                        Log.d("SIZE1", bigMartList.size() + "");
//
//                        int tagNum = 10;
//                        for (Document document : bigMartList) {
//                            MapPOIItem marker = new MapPOIItem();
//                            marker.setItemName(document.getPlaceName());
//                            marker.setTag(tagNum++);
//                            double x = Double.parseDouble(document.getY());
//                            double y = Double.parseDouble(document.getX());
//                            //카카오맵은 참고로 new MapPoint()로  생성못함. 좌표기준이 여러개라 이렇게 메소드로 생성해야함
//                            MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(x, y);
//                            marker.setMapPoint(mapPoint);
//                            marker.setMarkerType(MapPOIItem.MarkerType.CustomImage); // 마커타입을 커스텀 마커로 지정.
//                            marker.setCustomImageResourceId(R.drawable.mart); // 마커 이미지.
//                            marker.setCustomImageAutoscale(false); // hdpi, xhdpi 등 안드로이드 플랫폼의 스케일을 사용할 경우 지도 라이브러리의 스케일 기능을 꺼줌.
//                            marker.setCustomImageAnchor(0.5f, 1.0f); // 마커 이미지중 기준이 되는 위치(앵커포인트) 지정 - 마커 이미지 좌측 상단 기준 x(0.0f ~ 1.0f), y(0.0f ~ 1.0f) 값.
//                            mMapView.addPOIItem(marker);
//                        }
//
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<CategoryResult> call, Throwable t) {
//                    Log.d(TAG, "FAIL");
//                }
//            });



//밑의 메소드는 우리가 통신 해서 서버로 부터 받아올 메소드
//    private void getNameHobby(String name, String hobby)
//    {
//        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);   // ApiInterface 객체에 레트로핏 객체를 매핑시키는 부분이다.
//        Call<Person> call = apiInterface.getNameHobby(name, hobby);  //그 후 Call 객체를 모델 클래스(Person)에 맞춰 생성한 뒤, ApiInterface 객체를 참조해서 추상 메서드인 getNameHobby()를 호출한다. 그리고 메서드 선언부에서 String name, hobby를 인자로 넣을 것을 정의했다.
//        call.enqueue(new Callback<Person>()
//        {
//            @Override   //안의 onResponse()를 보면, if문으로 response의 상태를 확인한 다음 게터로 name, hobby 각각의 값들을 받아와 getted_name, getted_hobby 변수에 저장한다.
//            public void onResponse(@NonNull Call<Person> call, @NonNull Response<Person> response)
//            {
//                if (response.isSuccessful() && response.body() != null)
//                {
//                    String getted_name = response.body().getName();
//                    String getted_hobby = response.body().getHobby();
//                    Log.e("getNameHobby()", "서버에서 이름 : " + getted_name + ", 서버에서 받아온 취미 : " + getted_hobby);
//                }
//            }
//
//        @Override
//        public void onFailure(@NonNull Call<Person> call, @NonNull Throwable t)
//        {
//            Log.e("getNameHobby()", "에러 : " + t.getMessage());
//        }
//    });
//        }




//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo( "com.example.kakao_maps", PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//    }  이 코드는 해쉬키 받는 코드