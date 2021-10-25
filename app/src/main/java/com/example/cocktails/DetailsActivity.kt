package com.example.cocktails

import android.app.Activity
import android.os.Bundle
import com.example.cocktails.databinding.ActivityDetailsBinding

class DetailsActivity : Activity() {
    lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
    }

}