package com.example.appbelen_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appbelen_android.ui.screens.PostScreen
import com.example.appbelen_android.ui.theme.AppBelen_AndroidTheme
import com.example.appbelen_android.viewmodel.PostViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Permite que la app dibuje contenido debajo de las barras del sistema
        // Esto es opcional, solo mejora la visualización "edge-to-edge"
        WindowCompat.setDecorFitsSystemWindows(window, false)

        //  Aquí inicia Jetpack Compose
        setContent {

            //  Aplicamos el tema general de la app (Material 3)
            AppBelen_AndroidTheme {

                //  Inyectamos correctamente el ViewModel
                // En lugar de crearlo manualmente con PostViewModel(),
                // usamos la función viewModel() de Compose, que se encarga de mantener su ciclo de vida.
                val postViewModel: PostViewModel = viewModel()

                //  Llamamos a la pantalla principal y le pasamos el ViewModel
                PostScreen(viewModel = postViewModel)
            }
        }
    }
}