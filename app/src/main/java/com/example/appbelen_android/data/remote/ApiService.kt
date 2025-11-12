package com.example.appbelen_android.data.remote

import com.example.appbelen_android.data.model.Post
import retrofit2.http.GET
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.PATCH
import retrofit2.http.DELETE
import retrofit2.http.Path

// Esta interfaz define los endpoints HTTP de la API REST
interface ApiService {

    // Ya estaba: metodo GET para obtener todos los posts
    @GET("posts")
    suspend fun getPosts(): List<Post>

    // Se agregó esta función para el METODO POST
    // Crea un nuevo post en la API
    @POST("posts")
    suspend fun createPost(@Body post: Post): Post

    // Se agregó esta función para el METODO PUT
    // Actualiza completamente un post existente
    @PUT("posts/{id}")
    suspend fun updatePost(
        @Path("id") id: Int,
        @Body post: Post
    ): Post

    // Se agregó esta función para el METODO PATCH
    // Modifica parcialmente un post (por ejemplo, solo el título)
    @PATCH("posts/{id}")
    suspend fun patchPost(
        @Path("id") id: Int,
        @Body post: Map<String, Any>
    ): Post

    // Se agregó esta función para el METODO DELETE
    // Elimina un post por su ID
    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id: Int)
}
