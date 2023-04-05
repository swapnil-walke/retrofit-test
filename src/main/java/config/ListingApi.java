package config;

import dto.Response;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ListingApi {

    @GET("/ouch")
    Call<Response> callExternalService();
}

