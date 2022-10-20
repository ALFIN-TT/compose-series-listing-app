package com.example.movies.ui.screen.moviesscreen.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import coil.compose.rememberAsyncImagePainter
import com.example.movies.common.formatDate
import com.example.movies.data.model.Movie
import com.example.movies.ui.theme.montserratFonts
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

// on below line we are creating a tab content method
// in which we will be displaying the individual page of our tab .
@ExperimentalPagerApi
@Composable
fun TabsContent(list: List<Movie>, pagerState: PagerState, onPageChange: (Int) -> Unit) {

    // horizontal pager for tab layout.
    HorizontalPager(state = pagerState) { page ->
        // specifying the different pages.
        if (pagerState.currentPage == page) onPageChange.invoke(page)//page selection callback
        SessionList(list = list.filter { it.season == (page + 1) })
    }
}

// creating a Tab Content
@Composable
fun SessionList(list: List<Movie>) {
    // on below line we are creating a column
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        itemsIndexed(list) { index, season ->

            val painter = rememberAsyncImagePainter(season.originalImage)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = if (index == list.lastIndex) 80.dp else 0.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,

                ) {
                Text(
                    text = "${season.number} - ${season.name}",
                    style = TextStyle(color = Color.White, fontSize = 18.sp),
                    fontFamily = montserratFonts,
                    fontWeight = FontWeight.SemiBold//custom font
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Aired Date - ${
                        formatDate(
                            fromFormat = "yyyy-MM-dd",
                            toFormat = "dd MMM yyyy",
                            dateToFormat = season.airDate ?: ""
                        )
                    }",
                    style = TextStyle(color = Color.White, fontSize = 14.sp),
                    fontFamily = montserratFonts,
                    fontWeight = FontWeight.Medium//custom font
                )

                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    painter = painter,
                    contentDescription = season.name
                )

                Text(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = HtmlCompat.fromHtml(
                        season.summary ?: "",
                        HtmlCompat.FROM_HTML_MODE_LEGACY
                    ).toString(),
                    style = TextStyle(color = Color.White, fontSize = 18.sp),
                    fontFamily = montserratFonts,
                    fontWeight = FontWeight.Light//custom font)
                )
            }
        }
    }
}