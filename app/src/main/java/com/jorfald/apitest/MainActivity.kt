package com.jorfald.apitest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Klaxon

class MainActivity : AppCompatActivity() {

    lateinit var factTextView: TextView
    lateinit var newFactButton: Button
    lateinit var cryptoInput: EditText

    lateinit var queue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        queue = Volley.newRequestQueue(this)

        factTextView = findViewById(R.id.fact_text_view)
        newFactButton = findViewById(R.id.new_fact_button)
        cryptoInput = findViewById(R.id.crypto_input)

        newFactButton.setOnClickListener {
            var input = cryptoInput.text.toString()
            if (input.isEmpty()) {
                input = "bitcoin"
            }
            getCrypto(input)

            //getFact(1)
            getCrypto(input)
        }
    }

    fun getCrypto(input: String) {
        val url = "https://api.coincap.io/v2/assets?search=$input"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { json ->
                // Dette gikk bra!

                // Parse JSON
                val cryptoData = Klaxon().parse<CryptoData>(json)
                val data = cryptoData?.data
                val first = data?.firstOrNull()

                if (first != null) {
                    factTextView.text =
                        "Symbol: ${first?.symbol}\nName: ${first?.name}\nPrice: ${first?.priceUsd}\nChange last 24: ${first?.changePercent24Hr}%"
                } else {
                    factTextView.text = "No cryptocurrency found"
                }
            },
            { error ->
                // Vise en feilmelding

                print(error)
                Toast.makeText(this, error.message.toString(), Toast.LENGTH_LONG).show()
            }
        )

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    fun getFact(number: Int) {
        // Instantiate the RequestQueue.
        val url = "https://dog-facts-api.herokuapp.com/api/v1/resources/dogs?number=$number"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { json ->
                // Dette gikk bra!

                // Parse JSON
                val factList = Klaxon().parseArray<DogFact>(json)

                // Show fact
                for (factObject in factList ?: listOf()) {
                    factTextView.text = "" + factObject.fact + "\n\n"
                }
            },
            { error ->
                // Vise en feilmelding

                print(error)
                Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
            }
        )

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}