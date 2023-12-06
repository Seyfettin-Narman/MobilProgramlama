package com.example.mobil_uygulama1;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuoteService {
    @GET("/posts/1")
    Call<Quote> getQuote();
}
