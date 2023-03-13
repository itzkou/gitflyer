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
                    resource?.let { user->
                        binding.imageView.load(user.data
                            ?.avatar_url)
                        binding.txusrname.text = user.data?.name
                        binding.txfowooing.text = user.data?.following.toString()
                        binding.txfolo.text = user.data?.following.toString()
                        binding.txpubgist.text = user.data?.public_gists.toString()
                    }
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(this,"Network Faillure!",Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}