package com.cggcaio.coinexchange.network.error

import com.cggcaio.coinexchange.network.error.constants.ErrorMessages.BAD_REQUEST
import com.cggcaio.coinexchange.network.error.constants.ErrorMessages.FORBIDDEN
import com.cggcaio.coinexchange.network.error.constants.ErrorMessages.GENERIC_ERROR
import com.cggcaio.coinexchange.network.error.constants.ErrorMessages.NO_DATA
import com.cggcaio.coinexchange.network.error.constants.ErrorMessages.TOO_MANY_REQUESTS
import com.cggcaio.coinexchange.network.error.constants.ErrorMessages.UNAUTHORIZED
import com.cggcaio.coinexchange.network.error.model.CustomApiException
import com.cggcaio.coinexchange.network.error.model.HttpError
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

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

                            if (body.isNotEmpty() && response.errorBody()?.contentType()?.subtype() == "json") {
                                val error = adapter.fromJson(body)
                                if (error != null) {
                                    callback.onFailure(
                                        call,
                                        CustomApiException(
                                            code = response.code(),
                                            message = error.toString(),
                                            friendlyMessage = getFriendlyMessage(response.code()),
                                        ),
                                    )
                                } else {
                                    callback.onFailure(
                                        call,
                                        HttpException(response),
                                    )
                                }
                            } else {
                                callback.onFailure(call, HttpException(response))
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
                    callback.onFailure(call, t)
                }
            },
        )
    }

    private fun getFriendlyMessage(code: Int): String =
        when (code) {
            400 -> BAD_REQUEST
            401 -> UNAUTHORIZED
            403 -> FORBIDDEN
            429 -> TOO_MANY_REQUESTS
            550 -> NO_DATA
            else -> GENERIC_ERROR
        }

    override fun clone(): Call<Any> = CallWithErrorHandling(_delegate.clone())
}
