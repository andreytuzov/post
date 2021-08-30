package com.itexus.post.data.remote.mapper

import com.itexus.post.data.remote.model.*
import com.itexus.post.domain.model.*

interface RemotePostMapper {

    fun map(
        users: List<UserResponse>,
        posts: List<PostResponse>,
        comments: List<CommentResponse>
    ): List<Post>

    class Impl : RemotePostMapper {

        override fun map(
            users: List<UserResponse>,
            posts: List<PostResponse>,
            comments: List<CommentResponse>
        ): List<Post> {
            return posts.map { post ->
                Post(
                    id = post.id.toLong(),
                    user = map(users.first { it.id == post.userId }),
                    title = post.title,
                    body = post.body,
                    comments = comments.filter { it.postId == post.id }.map(::map)
                )
            }
        }

        private fun map(response: UserResponse) = with(response) {
            User(
                id = id.toLong(),
                name = name,
                username = username,
                email = email,
                address = map(address),
                phone = phone,
                website = website,
                company = map(company)
            )
        }

        private fun map(response: AddressResponse) = with(response) {
            Address(
                street = street,
                suite = suite,
                city = city,
                zipcode = zipcode,
                location = Location(
                    latitude = location.latitude,
                    longitude = location.longitude
                )
            )
        }

        private fun map(response: CompanyResponse) = with(response) {
            Company(
                name = name,
                catchPhrase = catchPhrase,
                bs = bs
            )
        }

        private fun map(response: CommentResponse) = with(response) {
            Comment(
                id = id.toLong(),
                name = name,
                email = email,
                body = body
            )
        }
    }
}