package com.kou.gitflyer.presentation.users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kou.gitflyer.databinding.ActivityUsersBinding
import com.kou.gitflyer.presentation.auth.LoginViewModel
import com.kou.gitflyer.presentation.profile.ProfileActivity
import com.kou.gitflyer.utils.Resource
import com.kou.gitflyer.utils.coordinateBtnAndInputs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UsersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUsersBinding
    private val viewModel: UsersViewModel by viewModels()

    @Inject
    lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUi()
        loadInitialData()
        refreshData()

    }


    private fun setupUi() {

        binding.rvUsers.apply {
            layoutManager =
                LinearLayoutManager(this@UsersActivity, LinearLayoutManager.VERTICAL, false)
            usersAdapter.setUserClickListener {
                viewModel.setId(it)
                startActivity(Intent(this@UsersActivity,ProfileActivity::class.java))
            }
            adapter = usersAdapter
        }

    }

    private fun loadInitialData() {
        viewModel.getUsers(1,30)
        viewModel.users.observe(this) { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS ->  {
                    resource.data?.let { usersResponse ->
                        if (usersResponse.isNotEmpty()) {
                            usersAdapter.updateUsers(usersResponse)
                        }

                    }
                }

                Resource.Status.ERROR -> Toast.makeText(this,"Network Faillure",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun refreshData() {
        coordinateBtnAndInputs(binding.btnRefresh,binding.edPage,binding.edSince)
        binding.btnRefresh.setOnClickListener {
            viewModel.getUsers(binding.edSince.text.toString().toInt(),binding.edPage.text.toString().toInt())
        }
    }
    private fun searchUser() {
        binding.searchView.setOnClickListener {
            //TODO search
        }
    }


}