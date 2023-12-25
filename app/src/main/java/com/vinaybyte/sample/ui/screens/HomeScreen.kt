package com.vinaybyte.sample.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vinaybyte.sample.data.model.MovieItem
import com.vinaybyte.sample.ui.components.ListItemsView

/**
 * @Author: Vinay
 * @Date: 25-12-2023
 */
@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    movies: List<MovieItem>,
    onItemClick: (MovieItem) -> Unit = {},
) {
    val listState = rememberLazyListState()
    Column(modifier.background(MaterialTheme.colorScheme.background)) {
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(4.dp)
        ) {
            items(
                items = movies,
                key = { it.id },
                itemContent = { movie ->
                    ListItemsView(
                        item = movie,
                        onItemClick = onItemClick
                    )
                }
            )
        }
    }
}