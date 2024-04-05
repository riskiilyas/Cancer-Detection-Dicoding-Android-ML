package com.dicoding.asclepius

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: ArticleRepository) : ViewModel() {
    val articlesLiveData = MutableLiveData<List<Article>>()
    val errorLiveData = MutableLiveData<String>()

    fun getArticles() {
        viewModelScope.launch {
            try {
                val response = repository.getArticles()
                articlesLiveData.postValue(response!!)
            } catch (e: Exception) {
                errorLiveData.postValue("Error: ${e.message}")
            }
        }
    }
}