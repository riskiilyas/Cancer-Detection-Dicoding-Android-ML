package com.dicoding.asclepius

import retrofit2.Response

class ArticleRepository(private val apiService: ApiService) {
    suspend fun getArticles(): List<Article>? {
        return apiService.getTopHeadlines().articles
    }
}
