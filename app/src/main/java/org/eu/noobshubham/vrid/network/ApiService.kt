package org.eu.noobshubham.vrid.network

import org.eu.noobshubham.vrid.model.Blog
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("posts")
    fun getBlogList(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<List<Blog>>
}
