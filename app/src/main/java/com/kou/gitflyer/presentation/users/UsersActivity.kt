package com.kou.gitflyer.presentation.users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kou.gitflyer.databinding.ActivityUsersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUsersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}