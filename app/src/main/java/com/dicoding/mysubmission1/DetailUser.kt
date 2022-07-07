package com.dicoding.mysubmission1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dicoding.mysubmission1.databinding.ActivityDetailUserBinding

class DetailUser : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detail = intent.getParcelableExtra<User>(EXTRA_DETAIL) as User
        Glide.with(binding.detailphoto.context)
            .load(detail.avatar)
            .circleCrop()
            .into(binding.detailphoto)
        binding.tvName.text = detail.name
        binding.tvUsername.text = detail.username
        binding.tvFollower.text = "Followers \n ${detail.followers}"
        binding.tvFollowing.text = "Following \n ${detail.following}"
        binding.tvCompany.text = "Company : ${detail.company}"
        binding.tvRepository.text = "Repository \n ${detail.repository}"
        binding.tvLocation.text = detail.location

        binding.button.setOnClickListener{
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Github/${binding.tvName.text.toString()}")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }

    companion object{
        const val EXTRA_DETAIL = "extra_detail"
    }
}