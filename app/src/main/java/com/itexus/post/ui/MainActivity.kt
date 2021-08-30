package com.itexus.post.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.itexus.post.ui.screens.Screen
import com.itexus.post.ui.screens.Screen.PostDetails.postId
import com.itexus.post.ui.screens.post_detail.PostDetailsScreen
import com.itexus.post.ui.screens.posts.PostsScreen
import com.itexus.post.ui.theme.PostTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PostTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Posts.route
    ) {
        composable(Screen.Posts.route) {
            PostsScreen(showPostDetails = {
                navController.navigate(Screen.PostDetails.createRoute(it.id))
            })
        }
        composable(Screen.PostDetails.route) {
            val postId = it.arguments?.postId
            requireNotNull(postId) { "postId parameter wasn't found" }
            PostDetailsScreen(postId = postId)
        }
    }
}