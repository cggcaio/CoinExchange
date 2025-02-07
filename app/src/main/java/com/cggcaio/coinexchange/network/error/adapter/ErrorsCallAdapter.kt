package com.cggcaio.coinexchange.network.error.adapter

import com.cggcaio.coinexchange.network.error.CallWithErrorHandling
import retrofit2.Call
import retrofit2.CallAdapter

class ErrorsCallAdapter(
    private val delegateAdapter: CallAdapter<Any, Call<*>>,
) : CallAdapter<Any, Call<*>> by delegateAdapter {
    override fun adapt(call: Call<Any>): Call<*> = delegateAdapter.adapt(CallWithErrorHandling(call))
}
