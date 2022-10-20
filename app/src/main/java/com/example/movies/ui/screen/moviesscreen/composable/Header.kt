package com.example.movies.ui.screen.moviesscreen.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movies.ui.theme.montserratFonts
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch


@ExperimentalPagerApi
@Composable
fun Tabs(movies: List<Int>, pagerState: PagerState) {

    // creating a variable for the scope.
    val scope = rememberCoroutineScope()

    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.Transparent,
        contentColor = Color.Transparent,
        divider = {},
        indicator = {}
    ) {

        movies.forEachIndexed { index, _ ->
            Tab(
                icon = {
                    var backgroundColor =
                        if (pagerState.currentPage == index) Color(0xFFCA7900) else Color(0xFF9A9C9C).copy(
                            alpha = 0.3f
                        )
                    Box(
                        modifier = Modifier
                            .size(width = 45.dp, height = 45.dp)
                            .background(color = backgroundColor, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        PageNumber(int = index + 1)
                    }
                },
                selected = pagerState.currentPage == index,
                // on click for the tab which is selected.
                onClick = {
                    //specifying the scope.
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}


@Composable
fun PageNumber(int: Int) {
    androidx.compose.material.Text(
        text = int.toString(),
        style = TextStyle(color = Color.White, fontSize = 18.sp),
        fontFamily = montserratFonts, fontWeight = FontWeight.Medium//custom font
    )
}

