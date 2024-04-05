package com.dicoding.asclepius.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dicoding.asclepius.NewsAdapter
import com.dicoding.asclepius.NewsViewModel
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ActivityNewsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsActivity : AppCompatActivity() {
    private val viewModel: NewsViewModel by viewModel()
    private lateinit var binding: ActivityNewsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = NewsAdapter(emptyList()) {
            val newsUrl = it.url
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(newsUrl))
            startActivity(browserIntent)
        }

        binding.rvNews.adapter = adapter

        viewModel.articlesLiveData.observe(this) {
            adapter.changeList(it)
        }

        viewModel.errorLiveData.observe(this) {
            Toast.makeText(this, "Something Went Wrong!", Toast.LENGTH_SHORT).show()
        }


        viewModel.getArticles()
    }
}