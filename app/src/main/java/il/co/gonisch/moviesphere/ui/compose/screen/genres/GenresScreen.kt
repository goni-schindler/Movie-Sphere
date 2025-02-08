package il.co.gonisch.moviesphere.ui.compose.screen.genres

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import il.co.gonisch.moviesphere.data.Genre
import il.co.gonisch.moviesphere.data.Movie
import il.co.gonisch.moviesphere.data.UiState
import il.co.gonisch.moviesphere.ui.theme.MovieSphereTheme
import il.co.gonisch.moviesphere.viewmodels.GenresScreenViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch


@Composable
fun GenresScreen(
    viewModel: GenresScreenViewModel = hiltViewModel()
) {
    val genres by viewModel.genres.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() } // State for Snackbar

    when (genres) {
        is UiState.Loading ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

        is UiState.Success -> GenresScreen(
            genres = (genres as UiState.Success<List<Genre>>).data,
            moviesProvider = { genreId -> viewModel.fetchMoviesByGenreId(genreId) },
            onPageSwap = { id -> viewModel.fetchMoviesByGenreId(id) }
        )

        is UiState.Error -> {
            LaunchedEffect(snackBarHostState) {
                snackBarHostState.showSnackbar(
                    message = "Error getting movies, please re-launch the app",
                )
            }
            SnackbarHost(hostState = snackBarHostState)
        }
    }

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
    Column {
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
            Column(modifier = Modifier.fillMaxSize()) {
                MoviesGridView(movies = pagingMovies)
                // Show CircularProgressIndicator when loading new data
                if (pagingMovies.loadState.refresh is LoadState.Loading ||
                    pagingMovies.loadState.append is LoadState.Loading
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun GenresScreenPreview() {
    MovieSphereTheme {
        GenresScreen(genres = (1..10).toList().map { Genre(it, "testGenre") })
    }
}

