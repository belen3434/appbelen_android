package com.example.appbelen_android.data.repository

import com.example.appbelen_android.data.model.Post
import com.example.appbelen_android.data.remote.RetrofitInstance

// Este repositorio se encarga de acceder a los datos usando Retrofit
class PostRepository {

    // Ya estaba: función que obtiene todos los posts desde la API (GET)
    suspend fun getPosts(): List<Post> {
        return RetrofitInstance.api.getPosts()
    }

    // Se agregó esta función para el METODO POST
    // Crea un nuevo post en la API (simulada)
    suspend fun createPost(post: Post): Post {
        return RetrofitInstance.api.createPost(post)
    }

    // Se agregó esta función para el METODO PUT
    // Actualiza completamente un post existente
    suspend fun updatePost(id: Int, post: Post): Post {
        return RetrofitInstance.api.updatePost(id, post)
    }

    // Se agregó esta función para el METODO PATCH
    // Modifica parcialmente un post (solo algunos campos)
    suspend fun patchPost(id: Int, cambios: Map<String, Any>): Post {
        return RetrofitInstance.api.patchPost(id, cambios)
    }

    // Se agregó esta función para el METODO DELETE
    // Elimina un post por su ID
    suspend fun deletePost(id: Int) {
        RetrofitInstance.api.deletePost(id)
    }
}
