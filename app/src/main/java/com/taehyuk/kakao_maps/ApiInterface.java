package com.taehyuk.kakao_maps;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


//우선 밑에는 예시
public interface ApiInterface
{
    @GET("example_select.php")   //우리가 만든 서버 url
    Call<Person> getNameHobby(      //person클래스(메서드의 리턴형)이고 name,hobby를 요청해서 getNameHobby(우리가 만든거)에 담을거다 그리고 getNameHobby는 내가 만들어야될 메서드
            @Query("name") String name,
            @Query("hobby") String hobby
    );
}