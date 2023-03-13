package com.kou.gitflyer.presentation.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import coil.load
import com.kou.gitflyer.R
import com.kou.gitflyer.databinding.ActivityProfileBinding
import com.kou.gitflyer.presentation.auth.LoginViewModel
import com.kou.gitflyer.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getUser()
    }


    fun getUser(){
        viewModel.getUser()
        viewModel.user.observe(this) { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    resource?.let {
                        binding.imageView.load(resource.data
                            ?.avatar_url)
                    }
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(this,"Network Faillure!",Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}