package com.itexus.post.domain.model

sealed class RefreshResult {
    object Success : RefreshResult()
    object NetworkConnectionError : RefreshResult()
}