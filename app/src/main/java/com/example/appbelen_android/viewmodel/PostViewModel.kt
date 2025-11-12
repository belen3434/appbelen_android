package com.example.appbelen_android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appbelen_android.data.model.Post
import com.example.appbelen_android.data.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// ViewModel que mantiene el estado de los datos obtenidos desde la API
class PostViewModel : ViewModel() {

    // Instancia del repositorio (se encarga de llamar a Retrofit)
    private val repository = PostRepository()

    // Flujo mutable (donde guardaremos la lista de posts)
    private val _postList = MutableStateFlow<List<Post>>(emptyList())

    // Flujo público de solo lectura que observa la UI
    val postList: StateFlow<List<Post>> = _postList

    //  Se ejecuta automáticamente al iniciar la app
    init {
        println(" ViewModel inicializado, llamando a fetchPosts()")
        fetchPosts()
        // Si quieres probar otros métodos, puedes descomentar temporalmente:
        // createNewPost()
        // updatePostCompleto()
        // patchPostParcial()
        // deletePostPorId()
    }

    // --------------------------------------------------------------
    //  FUNCIÓN: Obtiene los datos desde la API (GET)
    // --------------------------------------------------------------
    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                println(" Llamando a la API...")
                _postList.value = repository.getPosts()
                println("Se obtuvieron ${_postList.value.size} posts.")
            } catch (e: Exception) {
                println(" Error al obtener datos: ${e.localizedMessage}")
            }
        }
    }

    // --------------------------------------------------------------
    // FUNCIÓN: Crea un nuevo post (POST)
    // --------------------------------------------------------------
    private fun createNewPost() {
        viewModelScope.launch {
            try {
                val nuevoPost = Post(
                    userId = 1,
                    id = 0, // se ignora en jsonplaceholder
                    title = "Título nuevo de prueba",
                    body = "Contenido del post creado desde la app"
                )
                val response = repository.createPost(nuevoPost)
                println("Post creado: $response")
            } catch (e: Exception) {
                println(" Error al crear post: ${e.localizedMessage}")
            }
        }
    }

    // --------------------------------------------------------------
    // FUNCIÓN: Actualiza completamente un post existente (PUT)
    // --------------------------------------------------------------
    private fun updatePostCompleto() {
        viewModelScope.launch {
            try {
                val postActualizado = Post(
                    userId = 1,
                    id = 1,
                    title = "Título actualizado desde la app",
                    body = "Contenido completamente modificado desde la app"
                )
                val response = repository.updatePost(1, postActualizado)
                println("Post actualizado (PUT): $response")
            } catch (e: Exception) {
                println(" Error al actualizar post: ${e.localizedMessage}")
            }
        }
    }

    // --------------------------------------------------------------
    // FUNCIÓN: Modifica parcialmente un post (PATCH)
    // --------------------------------------------------------------
    private fun patchPostParcial() {
        viewModelScope.launch {
            try {
                val cambios = mapOf("title" to "Nuevo título parcial desde la app")
                val response = repository.patchPost(1, cambios)
                println("✅ Post modificado parcialmente (PATCH): $response")
            } catch (e: Exception) {
                println("Error al modificar post: ${e.localizedMessage}")
            }
        }
    }

    // --------------------------------------------------------------
    //  FUNCIÓN: Elimina un post por su ID (DELETE)
    // --------------------------------------------------------------
    private fun deletePostPorId() {
        viewModelScope.launch {
            try {
                repository.deletePost(1)
                println("🗑Post eliminado correctamente (DELETE)")
            } catch (e: Exception) {
                println("Error al eliminar post: ${e.localizedMessage}")
            }
        }
    }
}