package com.vmodev.baomoivmo.news.hot_news

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vmodev.baomoivmo.R
import com.vmodev.baomoivmo.common.MyViewModelFactory
import com.vmodev.baomoivmo.databinding.FragmentHotNewsBinding
import com.vmodev.baomoivmo.news.NewsViewModel
import com.vmodev.baomoivmo.news.data.remote.NewsServices
import com.vmodev.baomoivmo.news.repository.NewsRepository
import com.vmodev.baomoivmo.news.view.NewsAdapter


class HotNewsFragment : Fragment() {
    lateinit var viewModel: NewsViewModel

    private val retrofitService = NewsServices.getInstance()
    val adapter = NewsAdapter()

    private lateinit var binding: FragmentHotNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHotNewsBinding.inflate(inflater, container, false)
//        binding.viewmodel = viewModel
//        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, MyViewModelFactory(NewsRepository(retrofitService))).get(NewsViewModel::class.java)

        binding.rcvHotnews.adapter = adapter

        viewModel.getNews()
    }


}