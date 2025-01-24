package com.sijan.podcastsapp.core.presentation.utils

import android.content.Context
import com.sijan.podcastsapp.R
import com.sijan.podcastsapp.core.domain.utils.NetworkError

fun NetworkError.toString(context: Context):String{

    val resId = when(this){
        NetworkError.REQUEST_TIMEOUT -> R.string.error_request_timeout
        NetworkError.TOO_MANY_REQUESTS -> R.string.error_too_many_requests
        NetworkError.SERVER_ERROR -> R.string.error_server
        NetworkError.SERIALIZATION -> R.string.error_serialization
        NetworkError.UNKNOWN -> R.string.error_unknown
        NetworkError.NO_INTERNET -> R.string.error_no_internet
        NetworkError.UNAUTHORIZED -> R.string.error_unauthorized
    }

    return context.getString(resId)

}