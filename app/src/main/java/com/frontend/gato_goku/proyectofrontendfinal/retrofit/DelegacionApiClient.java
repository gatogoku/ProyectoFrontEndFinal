package com.frontend.gato_goku.proyectofrontendfinal.retrofit;

import com.frontend.gato_goku.proyectofrontendfinal.models.Delegacion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DelegacionApiClient {
	
	@Headers("Accept: application/json")
	@GET("/springForms/web")
    Call<List<Delegacion>> showDelegaciones ();
	
	@Headers("Accept: application/json")
	@GET("/springForms/web/{id}")
    Call<Delegacion> delegacion (@Path("id") Long id);
	
	@Headers("Accept: application/json")
	@POST("/springForms/web/insert")
    Call<Void> create (@Body Delegacion delegacion);
	
	@Headers("Accept: application/json")
	@PUT("/springForms/web/update")
    Call<Void> update (Delegacion delegacion);
	
	@DELETE("/springForms/web/delete/{id}")
    Call<Void> delete (@Path("id") Long id);
	
}
