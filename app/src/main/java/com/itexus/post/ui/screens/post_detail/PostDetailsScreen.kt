package com.itexus.post.ui.screens.post_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.itexus.post.R
import com.itexus.post.domain.model.Comment
import com.itexus.post.domain.model.Post
import com.itexus.post.domain.model.User
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun PostDetailsScreen(postId: Long) {
    val viewModel: BasePostViewModel = getViewModel {
        parametersOf(postId)
    }
    val post: Post? by viewModel.post.observeAsState()
    post?.let {
        PostDetailsContent(it)
    }
}

@Composable
fun PostDetailsContent(post: Post) {
    LazyColumn(modifier = Modifier.padding(20.dp)) {
        item {
            Title(title = post.title)
            Spacer(modifier = Modifier.height(10.dp))
            Body(body = post.body)
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.comments),
                style = MaterialTheme.typography.h6
            )
        }
        itemsIndexed(post.comments) { index, item ->
            CommentItem(item)
            if (index != post.comments.lastIndex) {
                Divider()
            }
        }
    }
}

@Composable
fun Title(title: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(align = Alignment.Center),
        text = title,
        style = MaterialTheme.typography.h4.copy(fontSize = 25.sp),
        textAlign = TextAlign.Center
    )
}

@Composable
fun Body(body: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = body,
        style = MaterialTheme.typography.body1
    )
}

@Composable
fun CommentItem(comment: Comment) {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(
            text = stringResource(id = R.string.author, comment.name),
            style = MaterialTheme.typography.caption
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = comment.body, style = MaterialTheme.typography.body2)
    }
}