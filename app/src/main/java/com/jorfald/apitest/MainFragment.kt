package com.jorfald.apitest

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley


class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    lateinit var queue: RequestQueue

    private lateinit var quoteText: TextView
    private lateinit var kanyeView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        queue = Volley.newRequestQueue(activity)

        quoteText = view.findViewById(R.id.quote_text)
        kanyeView = view.findViewById(R.id.kanye_image)

        val mp = MediaPlayer.create(context, R.raw.kanye_sound)

        kanyeView.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
            }

            //getKanyeQuote()
        }
    }

    private fun getKanyeQuote() {
        viewModel.getKanyeQuote(queue) { quoteObject ->
            if (quoteObject == null) {
                Toast.makeText(activity, "Teknisk feil", Toast.LENGTH_LONG).show()
            } else {
                quoteText.text = quoteObject.quote
            }
        }
    }
}