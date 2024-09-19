package edu.uvg.lab6recetasmoviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.uvg.lab6recetasmoviles.ui.theme.Lab6RecetasMovilesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab6RecetasMovilesTheme {
                Home(getSampleRecipes()) // Llama a la pantalla principal con recetas de ejemplo
            }
        }
    }
}

data class Recipe(
    val name: String,
    val imageRes: Int, // Imagen local (recurso de imagen)
    val summary: String,
    val time: String,
    val likes: Int,
    val comments: Int
)

@Composable
fun ScreenSplash(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(Color(0xFFF08080)),
        contentAlignment = Alignment.Center
    )
    {
        Image(
            modifier = Modifier
                .graphicsLayer(alpha = 0.2f)
                .size(1200.dp),
            painter = painterResource(id = R.drawable.lasagna),
            contentDescription = "Fondo de lazagna",
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(modifier = Modifier,
                painter = painterResource(id = R.drawable.hat_chef),
                contentDescription = "Figura de sombrero chef"
            )
            Text(text = "Chef\nRecipes", maxLines = 2, fontSize = 40.sp)
        }
    }
}




@Preview
@Composable
fun PreviewSplash(){
    Lab6RecetasMovilesTheme {
        ScreenSplash()
    }
}

