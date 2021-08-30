package com.itexus.post.screen.posts

import com.itexus.post.core.ScreenTest
import com.itexus.post.data.local.dao.PostDao
import com.itexus.post.modules.testModules
import com.itexus.post.ui.screens.posts.BasePostsViewModel
import com.itexus.post.ui.screens.posts.PostsViewModel
import com.itexus.post.utils.enqueueResponse
import com.itexus.post.utils.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.test.get
import org.koin.test.inject

@ExperimentalCoroutinesApi
class PostScreenTest : ScreenTest() {

    override val modules = testModules

    private val mockWebServer: MockWebServer by inject()
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        mockWebServer.start(8081)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
        mockWebServer.shutdown()
    }

    @Test
    fun `check posts request`() = testDispatcher.runBlockingTest {

        // Arrange
        mockWebServer.enqueueResponse("users.json", 200)
        mockWebServer.enqueueResponse("posts.json", 200)
        mockWebServer.enqueueResponse("comments.json", 200)
        val viewModel: BasePostsViewModel = PostsViewModel(get(), testDispatcher)
        val postDao = get<PostDao>()

        // Act
        val posts = viewModel.posts.getOrAwaitValue()
        val dbPosts = postDao.getPosts().getOrAwaitValue()

        // Assert
        assertEquals(100, posts.size)
        assertEquals(500, posts.map { it.comments }.flatten().size)
        assertEquals(10, posts.map { it.user }.distinct().size)

        assertEquals(100, dbPosts.size)
        assertEquals(500, dbPosts.map { it.comments }.flatten().size)
        assertEquals(10, dbPosts.map { it.user }.distinct().size)
    }
}