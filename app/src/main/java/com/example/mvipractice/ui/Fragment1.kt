package com.example.mvipractice.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvipractice.R
import com.example.mvipractice.data.model.PhotosItem
import com.example.mvipractice.data.network.ApiHelper
import com.example.mvipractice.data.network.ApiHelperImpl
import com.example.mvipractice.data.network.RetrofitBuilder
import com.example.mvipractice.databinding.Fragment1Binding
import com.example.mvipractice.ui.vm.MainAdapter
import com.example.mvipractice.ui.vm.MainViewModel
import com.example.mvipractice.ui.vm.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class Fragment1 : Fragment() {
    private lateinit var binding: Fragment1Binding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var recyclerViewAdapter: MainAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_1, container, false)
        setUpUi()
        setUpViewModel()
        observeViewModel()
        setUpClicks()
        return binding.root
    }

    private fun setUpClicks() {
        binding.btPress.setOnClickListener{
            lifecycleScope.launch {
                mainViewModel.userIntent.send(MainIntent.FetchUser)
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            mainViewModel.state.collect() {
                when(it) {
                    is MainState.Idle -> {}
                    is MainState.Loading -> {
                        binding.btPress.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is MainState.Photos -> {
                        binding.btPress.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        renderList(it.photos)
                    }
                    is MainState.Error -> {
                        binding.btPress.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                        Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun renderList(photos: List<PhotosItem>) {
        binding.recyclerView.visibility = View.VISIBLE
        photos.let { list ->
            list.let {
                recyclerViewAdapter.addData(it)
//                it.forEach {
//                    Log.i("result", "renderList: \n +${it.id}, ${it.title}\n ${it.url}\n${it.thumbnailUrl}")
//                }
            }
        }
    }

    private fun setUpViewModel() {
        mainViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(
                ApiHelperImpl(
                    RetrofitBuilder.apiService)))[MainViewModel::class.java]
    }

    private fun setUpUi() {
        recyclerViewAdapter = MainAdapter(requireContext(), arrayListOf(), fragment = this)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            run {
                addItemDecoration(
                    DividerItemDecoration(
                        context,
                        (layoutManager as LinearLayoutManager).orientation
                    )
                )
            }
            adapter = recyclerViewAdapter
        }
    }
}