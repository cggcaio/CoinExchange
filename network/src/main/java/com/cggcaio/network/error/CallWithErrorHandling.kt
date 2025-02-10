package com.cggcaio.network.error

import com.cggcaio.network.error.constants.ErrorMessages.BAD_REQUEST
import com.cggcaio.network.error.constants.ErrorMessages.FORBIDDEN
import com.cggcaio.network.error.constants.ErrorMessages.GENERIC_ERROR
import com.cggcaio.network.error.constants.ErrorMessages.NOT_FOUND
import com.cggcaio.network.error.constants.ErrorMessages.NO_DATA
import com.cggcaio.network.error.constants.ErrorMessages.NO_INTERNET
import com.cggcaio.network.error.constants.ErrorMessages.TOO_MANY_REQUESTS
import com.cggcaio.network.error.constants.ErrorMessages.UNAUTHORIZED
import com.cggcaio.network.error.model.CustomNetworkException
import com.cggcaio.network.error.model.HttpError
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class CallWithErrorHandling(
    private val _delegate: Call<Any>,
) : Call<Any> by _delegate {
    override fun enqueue(callback: Callback<Any>) {
        _delegate.enqueue(
            object : Callback<Any> {
                override fun onResponse(
                    call: Call<Any>,
                    response: Response<Any>,
                ) {
                    if (response.isSuccessful) {
                        callback.onResponse(call, response)
                    } else {
                        try {
                            val adapter = Moshi.Builder().build().adapter(HttpError::class.java)
                            val body = response.errorBody()?.string() ?: ""
                            if (body.isNotEmpty() && response.errorBody()?.contentType()?.subtype == "json") {
                                val error = adapter.fromJson(body)
                                if (error != null) {
                                    callback.onFailure(
                                        call,
                                        CustomNetworkException(
                                            code = response.code(),
                                            message = error.toString(),
                                            friendlyMessage = getFriendlyMessage(response.code()),
                                        ),
                                    )
                                } else {
                                    callback.onFailure(
                                        call,
                                        CustomNetworkException(
                                            code = response.code(),
                                            message = response.message(),
                                            friendlyMessage = getFriendlyMessage(response.code()),
                                        ),
                                    )
                                }
                            } else {
                                callback.onFailure(
                                    call,
                                    CustomNetworkException(
                                        code = response.code(),
                                        message = response.message(),
                                        friendlyMessage = getFriendlyMessage(response.code()),
                                    ),
                                )
                            }
                        } catch (exception: Exception) {
                            exception.cause
                                ?.let { callback.onFailure(call, it) }
                                ?.run { callback.onFailure(call, Throwable(exception.message)) }
                        }
                    }
                }

                override fun onFailure(
                    call: Call<Any>,
                    t: Throwable,
                ) {
                    val customException =
                        when (t) {
                            is UnknownHostException, is SocketTimeoutException -> {
                                CustomNetworkException(
                                    code = 0,
                                    message = "No internet connection",
                                    friendlyMessage = getFriendlyMessage(code = 0),
                                )
                            }
                            else -> t
                        }

                    callback.onFailure(call, customException)
                }
            },
        )
    }

    private fun getFriendlyMessage(code: Int): String =
        when (code) {
            400 -> BAD_REQUEST
            401 -> UNAUTHORIZED
            403 -> FORBIDDEN
            404 -> NOT_FOUND
            429 -> TOO_MANY_REQUESTS
            550 -> NO_DATA
            0 -> NO_INTERNET
            else -> GENERIC_ERROR
        }

    override fun clone(): Call<Any> = CallWithErrorHandling(_delegate.clone())
}
