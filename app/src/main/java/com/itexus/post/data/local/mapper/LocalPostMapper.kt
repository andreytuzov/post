package com.itexus.post.data.local.mapper

import com.itexus.post.data.local.model.*
import com.itexus.post.domain.model.*

interface LocalPostMapper {

    fun map(post: Post): PostWithCommentAndUser
    fun map(post: PostWithCommentAndUser): Post

    class Impl : LocalPostMapper {

        override fun map(post: Post) = with(post) {
            PostWithCommentAndUser(
                post = PostEntity(
                    id = id,
                    userId = user.id,
                    title = title,
                    body = body
                ),
                user = map(user),
                comments = comments.map { map(id, it) }
            )
        }

        private fun map(user: User) = with(user) {
            UserEntity(
                id = id,
                name = name,
                username = username,
                email = email,
                address = map(address),
                phone = phone,
                website = website,
                company = map(company)
            )
        }

        private fun map(address: Address) = with(address) {
            AddressEntity(
                street = street,
                suite = suite,
                city = city,
                zipcode = zipcode,
                location = LocationEntity(
                    latitude = location.latitude,
                    longitude = location.longitude
                )
            )
        }

        private fun map(company: Company) = with(company) {
            CompanyEntity(
                name = name,
                catchPhrase = catchPhrase,
                bs = bs
            )
        }

        private fun map(postId: Long, comment: Comment) = with(comment) {
            CommentEntity(
                id = id,
                postId = postId,
                name = name,
                email = email,
                body = body
            )
        }

        override fun map(post: PostWithCommentAndUser) = with(post) {
            Post(
                id = post.post.id,
                user = map(user),
                title = post.post.title,
                body = post.post.body,
                comments = this@with.comments.map { map(it) }
            )
        }

        private fun map(userEntity: UserEntity) = with(userEntity) {
            User(
                id = id,
                name = name,
                username = username,
                email = email,
                address = map(address),
                phone = phone,
                website = website,
                company = map(company)
            )
        }

        private fun map(address: AddressEntity) = with(address) {
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

        private fun map(company: CompanyEntity) = with(company) {
            Company(
                name = name,
                catchPhrase = catchPhrase,
                bs = bs
            )
        }

        private fun map(comment: CommentEntity) = with(comment) {
            Comment(
                id = id,
                name = name,
                email = email,
                body = body
            )
        }
    }
}