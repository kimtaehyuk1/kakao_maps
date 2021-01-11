package com.taehyuk.kakao_maps;

import com.taehyuk.kakao_maps.CategoryResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;


//우선 밑에는 예시
public interface ApiInterface
{
    //카테고리로 검색

    @GET("v2/local/search/category.json")
    Call<CategoryResult> getSearchCategory(
            @Header("Authorization") String Authorization,
            @Query("category_group_code") String category_group_code,
            @Query("x") String x,
            @Query("y") String y,
            @Query("radius") int radius
    );

//    @GET("example_select.php")   //우리가 만든 서버 url
//    Call<Person> getNameHobby(      //모델클래스에 맞게 콜할것이고 그리고 getNameHobby는 최종 메인에서 쓰여질 메소드 (즉 밑의 요청할거 를 쿼리쓰면됨)
//            @Query("name") String name,
//            @Query("hobby") String hobby
//    );
}