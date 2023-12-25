package com.vinaybyte.sample.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vinaybyte.sample.R

/**
 * @Author: Vinay
 * @Date: 25-12-2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AppBar(title: String = stringResource(R.string.app_name)) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(title)
        }
    )
}