package com.frontend.gato_goku.proyectofrontendfinal.retrofit;
import android.os.StrictMode;

import com.frontend.gato_goku.proyectofrontendfinal.models.Delegacion;

import java.io.IOException;
import java.util.List;
import okhttp3.OkHttpClient;
import retrofit2.Call;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DelegacionManager {
	private String url;

	private DelegacionApiClient delegacionApiClient; 
	
	    private static OkHttpClient.Builder httpClient;
	 
	public DelegacionManager (String url) {
		this.url = url;
		Retrofit retrofit = new Retrofit.Builder().baseUrl(this.url)
			    .addConverterFactory(GsonConverterFactory.create()).build();
		delegacionApiClient = retrofit.create(DelegacionApiClient.class);
		  httpClient =  new OkHttpClient.Builder();
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
	}
	
	public List<Delegacion> getDelegacions() {
		
		Call<List<Delegacion>> delegacionsApiCall = delegacionApiClient.showDelegaciones();

		try {return delegacionsApiCall.execute().body();
			} catch (IOException e) {
			System.err.println("Error calling delegacions API");
			e.printStackTrace();
		}catch (Exception e) {
			System.err.println("Error " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public Delegacion getDelegacion(Long id) {
		Call<Delegacion> delegacionApiCall = delegacionApiClient.delegacion(id);
		Delegacion delegacion = null;
		
		try {
			 delegacion = delegacionApiCall.execute().body();
		} catch (IOException e) {
			System.err.println("Error calling delegacion API");
			e.printStackTrace();
		} 
		
		return delegacion;
	}

	public boolean createDelegacion(Delegacion delegacion) {
		Call<Void> delegacionApiCall = delegacionApiClient.create(delegacion);
		boolean result = false;
		
		try {
	result =delegacionApiCall.execute().isSuccessful();
		} catch (IOException e) {
			System.err.println("Error calling delegacion API");
			e.printStackTrace();
		} 
		
		return result;
	}

	public boolean updateDelegacion(Delegacion delegacion) {
		//Call<Void> delegacionApiCall = DelegacionApiClient.update(delegacion);
		/*boolean result = false;
		
		try {
			//result = delegacionApiCall.execute().isSuccessful();
		} catch (IOException e) {
			System.err.println("Error calling delegacion API");
			e.printStackTrace();
		} 
		/*/
		return false;
	
	}

	public boolean deleteDelegacion(Long id) {
		Call<Void> delegacionApiCall = delegacionApiClient.delete(id);
		boolean result = false;
		
		try {
			result = delegacionApiCall.execute().isSuccessful();
		} catch (IOException e) {
			System.err.println("Error calling delegacion API");
			e.printStackTrace();
		} 
		
		return result;
	}
}
