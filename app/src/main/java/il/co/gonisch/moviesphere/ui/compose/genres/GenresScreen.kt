package il.co.gonisch.moviesphere.ui.compose.genres

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import il.co.gonisch.moviesphere.data.Genre
import il.co.gonisch.moviesphere.data.Movie
import il.co.gonisch.moviesphere.ui.theme.MovieSphereTheme
import il.co.gonisch.moviesphere.viewmodels.GenresScreenViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GenresScreen(
    viewModel: GenresScreenViewModel = hiltViewModel()
) {
    val genres by viewModel.genres.collectAsState()

    GenresScreen(
        genres = genres,
        moviesProvider = { genreId -> viewModel.fetchMoviesByGenreId(genreId) },
        onPageSwap = { id -> viewModel.fetchMoviesByGenreId(id) }
    )
}

@Composable
fun GenresScreen(
    genres: List<Genre>,
    moviesProvider: (Int) -> Flow<PagingData<Movie>> = { flowOf() },
    onPageSwap: (Int) -> Unit = {}
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState(pageCount = { genres.size })
    val coroutineScope = rememberCoroutineScope() // for updating the pager

    if (pagerState.pageCount == 0) return

    LaunchedEffect(pagerState.currentPage) {
        selectedTab = pagerState.currentPage
        onPageSwap(genres[pagerState.currentPage].id)
    }

    ScrollableTabRow(
        selectedTabIndex = selectedTab,
        edgePadding = 0.dp
    ) {
        genres.forEachIndexed { index, genre ->
            Tab(
                selected = selectedTab == index,
                onClick = {
                    selectedTab = index
                    coroutineScope.launch {
                        pagerState.scrollToPage(index)
                    }
                },
                text = { Text(text = genre.name) }
            )
        }
    }
    HorizontalPager(
        state = pagerState,
        verticalAlignment = Alignment.Top
    ) { index ->
        val genreId = genres[index].id
        val moviesFlow = remember(genreId) { moviesProvider(genreId) }
        val pagingMovies = moviesFlow.collectAsLazyPagingItems()
        MoviesGridView(movies = pagingMovies)
    }
}

@Composable
@Preview
private fun GenresScreenPreview() {
    MovieSphereTheme {
        //GenresScreen(genres = (1..10).toList().map { "genre $it" })
    }
}

