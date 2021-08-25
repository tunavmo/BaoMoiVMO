package com.vmodev.baomoivmo.video

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vmodev.baomoivmo.ExoPlayerActivity
import com.vmodev.baomoivmo.MainActivity
import com.vmodev.baomoivmo.R
import com.vmodev.baomoivmo.video.utils.Resource
import com.vmodev.baomoivmo.video.view.VideoAdapter
import kotlinx.android.synthetic.main.fragment_video.*


class VideoFragment : Fragment(R.layout.fragment_video) {
    lateinit var viewModel:VideoViewModel
    lateinit var videoAdapter: VideoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).videoViewModel
        setupRecyclerView()

        videoAdapter.setOnItemClickListener {
            val intent = Intent(context, ExoPlayerActivity::class.java).apply {
                putExtra("URL", it.url)
            }
            startActivity(intent)
            Log.d("VIDEO_FRAGMENT", "Clicked item")
        }

        viewModel.videos.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { videoResponse ->
                        videoAdapter.diff.submitList(videoResponse.videos)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e("VideoFragment", "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

    }
    private fun hideProgressBar() {
        paginationProgressBarVideo.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBarVideo.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        videoAdapter = VideoAdapter()
        rcvVideo.apply {
            adapter = videoAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

}