package com.example.professor_allocation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DepartamentoService {

    @POST("departments")
    Call<DepartamentoResponse> createRequest(@Body DepartamentoPost departamentoPost);

    @GET("departments")
    Call <List<DepartamentoResponse>> getAllCourses();

    @PUT("departments/{departments_id}")
    Call<DepartamentoResponse> putRequest(@Body DepartamentoPost departamentoPut,@Path("departments_id") int id);

    @DELETE("departments/{departments_id}")
    Call<Object> deleteRequest(@Path("departments_id") int id);
}
