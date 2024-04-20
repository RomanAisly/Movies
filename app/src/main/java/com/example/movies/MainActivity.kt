package com.example.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.movies.ui.screens.MainScreen
import com.example.movies.ui.theme.MoviesTheme


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
