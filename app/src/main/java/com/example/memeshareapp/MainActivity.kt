package com.example.memeshareapp

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadMeme()
    }

    private fun loadMeme() {
        val textView = findViewById<TextView>(R.id.text_view)
        val imageView = findViewById<ImageView>(R.id.image_view)
        val url = "https://meme-api.herokuapp.com/gimme"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,null,
            { response->
                val imageUrl = response.getString("url")
                Glide.with(this).load(imageUrl).into(imageView)
            },
            { textView.text = "That didn't work!"
            })

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
    fun shareMeme(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"CHECKOUT THIS MEME :)")
        val chooser = Intent.createChooser(intent,"SHARE THIS MEME USING...")
        startActivity(chooser)
    }
    fun nextMeme(view: View) {
        loadMeme()
    }
}