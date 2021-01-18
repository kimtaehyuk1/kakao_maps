package com.taehyuk.kakao_maps;

import com.taehyuk.kakao_maps.model.address_search.AddressSearch;
import com.taehyuk.kakao_maps.model.category_search.CategoryResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;


//우선 밑에는 예시
public interface ApiInterface
{
    //장소이름으로 검색
    @GET("v2/local/search/keyword.json")
    Call<CategoryResult> getSearchLocation(
            @Header("Authorization") String token,
            @Query("query") String query,
            @Query("size") int size
    );

    //카테고리로 검색
    @Headers("Authorization: KakaoAK 854f125c22a59fae5ca1ac050c814371")
    @GET("v2/local/search/category.json")
    Call<CategoryResult> getSearchCategory(
//            @Header("Authorization") String token,
            @Query("category_group_code") String category_group_code,
            @Query("x") String x,
            @Query("y") String y,
            @Query("radius") int radius
    );

    //장소이름으로 특정위치기준으로 검색
    @GET("v2/local/search/keyword.json")
    Call<CategoryResult> getSearchLocationDetail(
            @Header("Authorization") String token,
            @Query("query") String query,
            @Query("x") String x,
            @Query("y") String y,
            @Query("size") int size
    );

    @Headers("Authorization: KakaoAK 854f125c22a59fae5ca1ac050c814371")
    @GET("v2/local/search/address.json")
    Call<AddressSearch> getSearchAddress(
//            @Header("Authorization") String token,
            @Query("query") String query,
            @Query("size") int size
    );
}