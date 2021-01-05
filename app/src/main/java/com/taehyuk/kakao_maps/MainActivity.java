package com.taehyuk.kakao_maps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.taehyuk.kakao_maps.R;

import net.daum.mf.map.api.MapView;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapView mapView = new MapView(this);
        ViewGroup mapViewContainer = findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

    }


    //밑의 메소드는 우리가 통신 해서 서버로 부터 받아올 메소드
    private void getNameHobby(String name, String hobby)
    {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);   // ApiInterface 객체에 레트로핏 객체를 매핑시키는 부분이다.
        Call<Person> call = apiInterface.getNameHobby(name, hobby);
        call.enqueue(new Callback<Person>()
        {
            @Override
            public void onResponse(@NonNull Call<Person> call, @NonNull Response<Person> response)
            {
                if (response.isSuccessful() && response.body() != null)
                {
                    String getted_name = response.body().getName();
                    String getted_hobby = response.body().getHobby();
                    Log.e("getNameHobby()", "서버에서 이름 : " + getted_name + ", 서버에서 받아온 취미 : " + getted_hobby);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Person> call, @NonNull Throwable t)
            {
                Log.e("getNameHobby()", "에러 : " + t.getMessage());
            }
        });
    }

}




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