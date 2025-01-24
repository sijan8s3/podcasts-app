package com.sijan.podcastsapp.core.domain.utils

import com.sijan.podcastsapp.core.domain.utils.Error

enum class NetworkError: Error {
    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN,
    NO_INTERNET,
    UNAUTHORIZED
}
