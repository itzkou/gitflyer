package com.kou.gitflyer.presentation.auth

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.kou.gitflyer.R
import com.kou.gitflyer.databinding.ActivityLoginBinding
import com.kou.gitflyer.presentation.users.UsersActivity
import com.kou.gitflyer.utils.Resource
import com.kou.gitflyer.utils.coordinateBtnAndInputs
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        login()
        observe()
    }


    private fun login() {
        coordinateBtnAndInputs(binding.btnContinue, binding.edToken)
        binding.btnContinue.setOnClickListener {
            viewModel.login("Bearer " + binding.edToken.text.toString())
        }
    }

    private fun observe() {
        viewModel.loginResponse.observe(this) { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> resource?.let {
                    Toast.makeText(this,"Token is valid!",Toast.LENGTH_SHORT).show()
                    binding.btnContinue.setBackgroundColor(Color.GREEN)
                    startActivity(Intent(this,UsersActivity::class.java))

                }
                Resource.Status.ERROR -> resource?.let {
                    Toast.makeText(this,"Token is invalid!",Toast.LENGTH_SHORT).show()
                    binding.btnContinue.setBackgroundColor(Color.RED)


                }

            }

        }
    }
}