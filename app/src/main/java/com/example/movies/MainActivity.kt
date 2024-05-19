package com.example.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.compose.rememberNavController
import com.example.movies.domain.navigation.BottomNavGraph
import com.example.movies.ui.theme.MoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        BottomNavGraph(navHostController = navController)
    }
}

@Composable
fun Constr() {

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (botom) = createRefs()

    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {

}
//@Composable
//fun FilmsContent(films: ResultDTO) {
//
//    Column(modifier = Modifier.clip(shape = RoundedCornerShape(15.dp))) {
//        AsyncImage(
//            model = ImageRequest.Builder(LocalContext.current)
//                .data(MovieApi.IMAGE_URL + films.poster_path)
//                .crossfade(true)
//                .build(),
//            contentDescription = stringResource(R.string.cont_desc_movie_post),
//            placeholder = painterResource(id = R.drawable.placeholder),
//            error = painterResource(id = R.drawable.image_error),
//            modifier = Modifier.fillMaxSize()
//        )
//
//        Text(
//            text = films.title,
//            modifier = Modifier.fillMaxWidth(),
//            fontSize = 16.sp,
//            fontFamily = FontFamily.Serif,
//            textAlign = TextAlign.Center
//        )
//    }
//}
