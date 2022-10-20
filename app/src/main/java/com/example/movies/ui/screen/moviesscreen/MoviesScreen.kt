package com.example.movies.ui.screen.moviesscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.movies.ui.common.ShimmerAnimation
import com.example.movies.ui.screen.moviesscreen.composable.Tabs
import com.example.movies.ui.screen.moviesscreen.composable.TabsContent
import com.example.movies.ui.theme.montserratFonts
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalUnitApi::class)
@ExperimentalPagerApi
@Composable
fun MoviesScreen(
    nvaController: NavHostController,
    viewModel: MoviesViewModel = hiltViewModel()
) {


    val resource = viewModel.series.value

    if (resource.isLoading) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            repeat(5) {
                item {
                    ShimmerAnimation()
                }
            }
        }
    }

    if (resource.error.isNotBlank()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = resource.error.toString(),
                fontFamily = montserratFonts,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    if (!resource.data.isNullOrEmpty()) {
        val pagerState = rememberPagerState(
            pageCount = resource.data?.map { it.season ?: 0 }?.distinct()?.size
                ?: 0
        )//save page state
        val selectedPageIndex = remember { mutableStateOf(1) }//save page index
        Column(
            modifier = Modifier.background(Color.DarkGray)
        ) {

            TopAppBar(backgroundColor = Color.DarkGray, elevation = 0.dp) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "Game of Throne",
                        modifier = Modifier.padding(all = Dp(5F)),
                        textAlign = TextAlign.Center,
                        style = TextStyle(color = Color.White, fontSize = 20.sp),
                        fontFamily = montserratFonts, fontWeight = FontWeight.SemiBold//custom font
                    )
                }
            }
            Tabs(
                movies = resource.data?.map { it.season ?: 0 }?.distinct() ?: emptyList(),
                pagerState = pagerState
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Season : ${selectedPageIndex.value}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = Dp(5F)),
                textAlign = TextAlign.Center,
                style = TextStyle(color = Color.White, fontSize = 20.sp),
                fontFamily = montserratFonts, fontWeight = FontWeight.SemiBold//custom font)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color.White)
            )
            Spacer(modifier = Modifier.height(15.dp))
            TabsContent(
                list = resource.data ?: emptyList(), pagerState = pagerState
            ) {
                selectedPageIndex.value = it + 1
            }
        }
    }/* else {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "No data",
                fontFamily = montserratFonts,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }*/
}
