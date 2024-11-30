package com.mallcco.andy.sustitutorio.network

import com.mallcco.andy.sustitutorio.model.Post
import retrofit2.http.GET

/**
 * Interface defining API endpoints for Retrofit.
 */
public interface ApiService { // Cambiado a public
    /**
     * Fetches a list of posts from the API.
     *
     * @return A list of [Post] objects.
     */
    @GET("/posts")
    suspend fun getPosts(): List<Post>
}
