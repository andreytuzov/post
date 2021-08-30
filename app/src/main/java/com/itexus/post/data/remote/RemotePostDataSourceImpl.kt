package com.itexus.post.data.remote

import com.itexus.post.data.RemotePostDataSource
import com.itexus.post.data.model.RemotePostResult
import com.itexus.post.data.remote.api.PostApi
import com.itexus.post.data.remote.api.UserApi
import com.itexus.post.data.remote.mapper.RemotePostMapper
import retrofit2.HttpException
import java.io.IOException

class RemotePostDataSourceImpl(
    private val userApi: UserApi,
    private val postApi: PostApi,
    private val mapper: RemotePostMapper
) : RemotePostDataSource {

    override suspend fun getPosts(): RemotePostResult {
        return try {
            val users = userApi.getUsers()
            val posts = postApi.getPosts()
            val comments = postApi.getComments()
            val result = mapper.map(users, posts, comments)
            RemotePostResult.Success(result)
        } catch (ex: IOException) {
            RemotePostResult.NetworkConnectionError
        } catch (ex: HttpException) {
            RemotePostResult.NotFoundException
        } catch (ex: Throwable) {
            RemotePostResult.NotFoundException
        }
    }
}

