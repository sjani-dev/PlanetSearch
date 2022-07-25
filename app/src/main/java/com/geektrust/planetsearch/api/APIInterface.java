package com.geektrust.planetsearch.api;

import com.geektrust.planetsearch.model.PlanetDetails;
import com.geektrust.planetsearch.model.PlanetListResposne;
import com.geektrust.planetsearch.model.VehicleDetails;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("planets")
    Observable<List<PlanetDetails>> getPlanetList();

    @GET("vehicles")
    Observable<List<VehicleDetails>> getVehicleList();
/*
    @POST("find")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun findFalcone(@Body findFalconeRequestTO: FindFalconeRequestTO): Call<FindFalconeResponseTO>

    @POST("token")
    @Headers("Accept:application/json")
    fun getToken(): Call<TokenResponseTO>*/
}
