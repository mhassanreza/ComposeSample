package com.bigint.birthdayreminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bigint.birthdayreminder.data.model.test.Entry
import com.bigint.birthdayreminder.data.model.test.SeedsTestDto
import com.bigint.birthdayreminder.data.remote.Resource
import com.bigint.birthdayreminder.ui.shared.LoadingContent
import com.bigint.birthdayreminder.ui.theme.BirthdayReminderTheme
import com.bigint.birthdayreminder.utils.logging
import com.bigint.birthdayreminder.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BirthdayReminderTheme {
                MainScreenContent(viewModel)
            }
        }
    }
}

@Composable
fun ShowServicesCategories(data: List<Entry>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(data) { entry ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val url = "http:${entry.picture.url}"
                    logging(url)
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current).data(url).crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = stringResource(R.string.app_name),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(90.dp)
                    )
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        Text(
                            text = entry.title, style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = entry.url, style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun MainScreenContent(viewModel: MainViewModel) {
    var showLoadingDialog by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        viewModel.callServicesDataApi()
    }

    val data by viewModel.servicesData.observeAsState()
    Box(modifier = Modifier) {
        when (data) {
            is Resource.Loading -> {
                showLoadingDialog = true
            }

            is Resource.Success -> {
                ShowServicesCategories((data as Resource.Success<SeedsTestDto>).data.entries)
                showLoadingDialog = false
            }

            is Resource.Error -> {
                logging("error")
                showLoadingDialog = false
            }

            null -> logging("data is null????")
        }
        if (showLoadingDialog) {
            LoadingContent()
        }
    }


}