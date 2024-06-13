package com.example.braingym.backend;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("login")
    Call<AuthResponse> login(@Body LoginRequest loginRequest);

    @POST("register")
    Call<AuthResponse> register(@Body RegisterRequest registerRequest);
}
