package com.jorfald.apitest

import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley


class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    lateinit var queue: RequestQueue

    private lateinit var quoteText: TextView
    private lateinit var kanyeView: ImageView

    private var imageList: List<Drawable?> = listOf()
    private lateinit var mp: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        queue = Volley.newRequestQueue(activity)
        mp = MediaPlayer.create(context, R.raw.kanye_sound)

        imageList = listOf(
            ContextCompat.getDrawable(requireContext(), R.drawable.kanye),
            ContextCompat.getDrawable(requireContext(), R.drawable.kanye2),
            ContextCompat.getDrawable(requireContext(), R.drawable.kanye3),
            ContextCompat.getDrawable(requireContext(), R.drawable.kanye4),
            ContextCompat.getDrawable(requireContext(), R.drawable.kanye5),
            ContextCompat.getDrawable(requireContext(), R.drawable.kanye6),
            ContextCompat.getDrawable(requireContext(), R.drawable.kanye7),
            ContextCompat.getDrawable(requireContext(), R.drawable.kanye8),
            ContextCompat.getDrawable(requireContext(), R.drawable.kanye9)
        )

        quoteText = view.findViewById(R.id.quote_text)
        kanyeView = view.findViewById(R.id.kanye_image)

        bindButtons()
    }

    private fun bindButtons() {
        kanyeView.setOnClickListener {
            if (!mp.isPlaying) {
                mp.start()
            }

            getKanyeQuote()
        }

        quoteText.setOnClickListener {
            val directions = MainFragmentDirections.actionMainFragmentToQuoteFragmentFragment(
                viewModel.currentQuote
            )
            findNavController().navigate(directions)
        }
    }

    override fun onResume() {
        super.onResume()

        if (viewModel.currentQuote.isNotEmpty()) {
            quoteText.text = viewModel.currentQuote
        }
    }

    private fun getKanyeQuote() {
        viewModel.getKanyeQuote(queue) { quoteObject ->
            if (quoteObject == null) {
                Toast.makeText(activity, "Teknisk feil", Toast.LENGTH_SHORT).show()
            } else {
                val random = imageList.random()
                kanyeView.setImageDrawable(random)
                quoteText.text = quoteObject.quote
            }
        }
    }
}
