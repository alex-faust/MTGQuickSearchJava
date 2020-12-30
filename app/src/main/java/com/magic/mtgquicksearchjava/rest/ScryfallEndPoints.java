package com.magic.mtgquicksearchjava.rest;

import com.magic.mtgquicksearchjava.model.Card;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ScryfallEndPoints
{
    @GET("cards/named")
    Call<Card> getCard(@Query(value=("fuzzy"))String name);

    /*@GET("cards/named?fuzzy={name}")
    Call<Card> getCardMaybe(@Path("name") String name);*/
}
