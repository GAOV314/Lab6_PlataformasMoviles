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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(recipes: List<Recipe>) {
    var selectedRecipeIndex by remember { mutableStateOf(0) } // Guarda el índice de la receta seleccionada

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfff8f9f9))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            // Barra superior (Top App Bar)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_action_name),
                    contentDescription = "Botón de menú",
                    tint = Color(0xFFF08080)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "POPULAR RECIPES",
                    fontSize = 20.sp,
                    color = Color(0xFFF08080),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.ic_action_search),
                    contentDescription = "Botón de búsqueda",
                    tint = Color(0xFFF08080)
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            // LazyRow para mostrar las recetas
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(recipes) { index, recipe ->
                    // Imagen de cada receta
                    Image(
                        painter = painterResource(id = recipe.imageRes), // Carga de imagen local
                        contentDescription = recipe.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(200.dp)
                            .clickable {
                                // Actualiza el índice de la receta seleccionada cuando se hace clic
                                selectedRecipeIndex = index
                            }
                            .border(
                                if (index == selectedRecipeIndex) BorderStroke(2.dp, Color.Red)
                                else BorderStroke(0.dp, Color.Transparent)
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Mostrar el resumen de la receta seleccionada
            Text(
                text = recipes[selectedRecipeIndex].summary,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

// Función de ejemplo para obtener recetas de muestra
fun getSampleRecipes(): List<Recipe> {
    return listOf(
        Recipe(
            name = "Prime Rib Roast",
            imageRes = R.drawable.prime_rib, // Sustituir por la imagen local
            summary = "The Prime Rib Roast is a classic and tender cut of beef taken from the rib primal cut...",
            time = "5HR",
            likes = 685,
            comments = 107
        ),
        Recipe(
            name = "Lasagna",
            imageRes = R.drawable.lasagna, // Sustituir por la imagen local
            summary = "This lasagna recipe is a family favorite, loaded with cheese and delicious layers of sauce...",
            time = "2HR",
            likes = 540,
            comments = 75
        ),
        Recipe(
            name = "Hamburger",
            imageRes = R.drawable.hamburger_image1, // Sustituir por la imagen local
            summary = "A delicious homemade burger with fresh ingredients...",
            time = "1HR",
            likes = 850,
            comments = 150
        ),
        Recipe(
            name = "Spaghetti Bolognese",
            imageRes = R.drawable.spaguetti_bolognese,   // Sustituir por la imagen local
            summary = "A classic Italian pasta dish with rich Bolognese sauce...",
            time = "1HR 30MIN",
            likes = 920,
            comments = 90
        ),
        Recipe(
            name = "Chicken Curry",
            imageRes = R.drawable.chicken_curry, // Sustituir por la imagen local
            summary = "A spicy and flavorful chicken curry with a rich sauce...",
            time = "1HR 20MIN",
            likes = 780,
            comments = 130
        ),
        Recipe(
            name = "Chocolate Cake",
            imageRes = R.drawable.chocolate_cake1, // Sustituir por la imagen local
            summary = "A moist and rich chocolate cake, perfect for dessert...",
            time = "3HR",
            likes = 1100,
            comments = 200
        )
    )
}
@Preview
@Composable
fun PreviewSplash(){
    Lab6RecetasMovilesTheme {
        ScreenSplash()
    }
}

@Preview
@Composable
fun PreviewHome() {
    Lab6RecetasMovilesTheme {
        Home(getSampleRecipes()) // Vista previa con recetas de ejemplo
    }
}

