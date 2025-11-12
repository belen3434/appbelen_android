package com.example.appbelen_android.ui.screens

import android.widget.Toast
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.appbelen_android.viewmodel.PostViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(viewModel: PostViewModel) {
    val posts = viewModel.postList.collectAsState().value
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Listado de Posts") }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            //  BOTONES DE PRUEBA PARA LOS MÉTODOS HTTP
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        viewModelTest(viewModel, "post", context)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("POST")
                }

                Button(
                    onClick = {
                        viewModelTest(viewModel, "put", context)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text("PUT")
                }

                Button(
                    onClick = {
                        viewModelTest(viewModel, "patch", context)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
                ) {
                    Text("PATCH")
                }

                Button(
                    onClick = {
                        viewModelTest(viewModel, "delete", context)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("DELETE")
                }
            }

            // LISTA DE PUBLICACIONES (GET)
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(posts) { post ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(text = "Título: ${post.title}", style = MaterialTheme.typography.titleMedium)
                            Text(text = post.body, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}

/**
 *  Función auxiliar para probar los métodos HTTP
 * (usa reflexión para llamar a las funciones privadas del ViewModel)
 */
private fun viewModelTest(viewModel: PostViewModel, action: String, context: android.content.Context) {
    when (action) {
        "post" -> {
            Log.d("AppBelen", " Probando método POST desde botón")
            Toast.makeText(context, "Ejecutando método POST", Toast.LENGTH_SHORT).show()
            val method = viewModel.javaClass.getDeclaredMethod("createNewPost")
            method.isAccessible = true
            method.invoke(viewModel)
        }
        "put" -> {
            Log.d("AppBelen", "🟡Probando método PUT desde botón")
            Toast.makeText(context, "Ejecutando método PUT", Toast.LENGTH_SHORT).show()
            val method = viewModel.javaClass.getDeclaredMethod("updatePostCompleto")
            method.isAccessible = true
            method.invoke(viewModel)
        }
        "patch" -> {
            Log.d("AppBelen", " Probando método PATCH desde botón")
            Toast.makeText(context, "Ejecutando método PATCH", Toast.LENGTH_SHORT).show()
            val method = viewModel.javaClass.getDeclaredMethod("patchPostParcial")
            method.isAccessible = true
            method.invoke(viewModel)
        }
        "delete" -> {
            Log.d("AppBelen", " Probando método DELETE desde botón")
            Toast.makeText(context, "Ejecutando método DELETE", Toast.LENGTH_SHORT).show()
            val method = viewModel.javaClass.getDeclaredMethod("deletePostPorId")
            method.isAccessible = true
            method.invoke(viewModel)
        }
    }
}