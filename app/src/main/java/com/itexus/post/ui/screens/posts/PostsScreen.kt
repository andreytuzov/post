package com.itexus.post.ui.screens.posts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.itexus.post.R
import com.itexus.post.domain.model.Post
import com.itexus.post.ui.screens.widgets.ProgressBar
import com.itexus.post.ui.screens.widgets.TryLater
import com.itexus.post.ui.utils.brief
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun PostsScreen(showPostDetails: (Post) -> Unit) {
    val viewModel: BasePostsViewModel = getViewModel()
    val posts: List<Post> by viewModel.posts.observeAsState(initial = emptyList())
    val isLoading: Boolean by viewModel.isLoading.observeAsState(false)
    val error: BasePostsViewModel.Error? by viewModel.error.observeAsState(initial = null)
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(scaffoldState = scaffoldState) {
        when {
            posts.isEmpty() && error != null -> {
                TryLater(viewModel::refresh)
            }
            isLoading -> ProgressBar()
            else -> {
                PostsContent(
                    modifier = Modifier.padding(10.dp),
                    posts = posts,
                    onPostClicked = showPostDetails
                )
                error?.let {
                    val errorMsg = stringResource(id = it.screenError)
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(errorMsg)
                        viewModel.error.value = null
                    }
                }
            }
        }
    }
}

@Composable
fun PostsContent(
    modifier: Modifier = Modifier,
    posts: List<Post>,
    onPostClicked: (Post) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(posts) {
            PostItem(
                modifier = Modifier.clickable { onPostClicked(it) },
                post = it
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun PostItem(
    modifier: Modifier = Modifier,
    post: Post
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = post.title, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = post.body.brief(), style = MaterialTheme.typography.body2)
            Spacer(modifier = Modifier.height(10.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.align(Alignment.CenterStart),
                    text = stringResource(id = R.string.author, post.user.name),
                    style = MaterialTheme.typography.overline
                )
                Text(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    text = stringResource(id = R.string.comments_count, post.comments.size),
                    style = MaterialTheme.typography.overline
                )
            }
        }
    }
}

