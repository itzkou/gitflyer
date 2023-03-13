package com.kou.gitflyer.presentation.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kou.gitflyer.R
import com.kou.gitflyer.databinding.ActivityProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}