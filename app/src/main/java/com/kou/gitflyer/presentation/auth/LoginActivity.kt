package com.kou.gitflyer.presentation.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kou.gitflyer.R
import com.kou.gitflyer.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    fun setupUi(){
        binding.btnContinue.isEnabled = false
    }
}