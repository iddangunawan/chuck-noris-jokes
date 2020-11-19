package com.mylektop.chucknorisjokes.api

import com.mylektop.chucknorisjokes.api.dao.CategoriesDataDao
import com.mylektop.chucknorisjokes.api.dao.JokesDataDao
import com.mylektop.chucknorisjokes.util.Constant
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by iddangunawan on 11/18/20
 */
interface ApiService {
    @GET("jokes/categories")
    fun getJokesCategories(): Observable<List<CategoriesDataDao>>

    @GET("jokes/random")
    fun getJokesRandom(@Query("category") category: String?): Observable<JokesDataDao>

    @GET("jokes/random")
    fun getJokesRandomByCategory(@Query("category") category: String?): Observable<JokesDataDao>

    @GET("jokes/search")
    fun getJokesSearch(@Query("query") query: String): Observable<JokesDataDao>

    companion object Factory {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl(Constant.BASE_URL)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}