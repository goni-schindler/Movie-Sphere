package il.co.gonisch.moviesphere.ui.compose.genres

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState

import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf

import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import il.co.gonisch.moviesphere.ui.theme.MovieSphereTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GenresScreen(
    modifier: Modifier = Modifier,
    genres: List<String>
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState(pageCount = { genres.size })

    ScrollableTabRow(selectedTabIndex = selectedTab) {
        genres.forEachIndexed { index, genre ->
            Tab(
                selected = selectedTab == index,
                onClick = { selectedTab = index },
                text = { Text(text = genre) }
            )
        }
    }
    HorizontalPager(
        state = pagerState,
        verticalAlignment = Alignment.Top
    ) { index ->
        //MoviesGridView()
    }
}

@Composable
@Preview
private fun GenresScreenPreview() {
    MovieSphereTheme {
        GenresScreen(genres = (1..10).toList().map { "genre $it" })
    }
}

