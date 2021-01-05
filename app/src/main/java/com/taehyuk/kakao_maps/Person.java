package com.taehyuk.kakao_maps;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



// 여긴 모델클래스, 받아올 정보같은 느낌(밑에껀 일단 예시)
// name, hobby 2가지 값을 받아올 것이니 String으로 변수 2개를 생성한 다음 게터세터를 만들어주면 된다.
public class Person
{
    @Expose
    @SerializedName("name") private String name;

    @Expose
    @SerializedName("hobby") private String hobby;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getHobby()
    {
        return hobby;
    }

    public void setHobby(String hobby)
    {
        this.hobby = hobby;
    }
}