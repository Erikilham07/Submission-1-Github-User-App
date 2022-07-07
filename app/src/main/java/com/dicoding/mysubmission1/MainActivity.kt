package com.dicoding.mysubmission1

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mysubmission1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUserGithub.setHasFixedSize(true)

        list.addAll(listUser)
        showRecyclerList()
    }

    private val listUser: ArrayList<User>
        get() {
            val UserName = resources.getStringArray(R.array.username)
            val Name = resources.getStringArray(R.array.name)
            val Avatar = resources.obtainTypedArray(R.array.avatar)
            val Location = resources.getStringArray(R.array.location)
            val Company = resources.getStringArray(R.array.company)
            val Followers = resources.getStringArray(R.array.followers)
            val Following = resources.getStringArray(R.array.following)
            val Repository = resources.getStringArray(R.array.repository)
            val listUser = ArrayList<User>()
            for (i in Name.indices) {
                val user = User(UserName[i],Name[i], Location[i], Avatar.getResourceId(i, -1), Company[i], Followers[i], Following[i], Repository[i])
                listUser.add(user)
            }
            return listUser
        }

    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvUserGithub.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvUserGithub.layoutManager = LinearLayoutManager(this)
        }
        val listUserAdapter = ListUserAdapter(list)
        binding.rvUserGithub.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                val intent = Intent(this@MainActivity, DetailUser::class.java)
                intent.putExtra(DetailUser.EXTRA_DETAIL, data)
                startActivity(intent)
            }
        })
    }
}