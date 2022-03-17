package com.jorfald.apitest

import android.util.Log
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.beust.klaxon.Klaxon

class MainViewModel : ViewModel() {

    var currentQuote = ""

    fun getKanyeQuote(requestQueue: RequestQueue, callback: (KanyeQuote?) -> Unit) {
        val url = "https://api.kanye.rest"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { json ->
                val quote: KanyeQuote? = Klaxon().parse<KanyeQuote>(json)
                callback(quote)
                currentQuote = quote?.quote ?: ""
            },
            { error ->
                Log.e("Error:", error.message ?: "")
                callback(null)
            }
        )

        requestQueue.add(stringRequest)
    }
}