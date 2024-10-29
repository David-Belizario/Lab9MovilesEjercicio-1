package com.androidcourse.servicioapilab9ejercicio.Interface

import com.androidcourse.servicioapilab9ejercicio.ViewModel.ProductModel
import com.androidcourse.servicioapilab9ejercicio.ViewModel.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApiService {

    @GET("products")
    suspend fun getAllProducts(): ProductResponse

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductModel

}
