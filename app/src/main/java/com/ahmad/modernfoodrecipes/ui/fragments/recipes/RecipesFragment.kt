package com.ahmad.modernfoodrecipes.ui.fragments.recipes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmad.modernfoodrecipes.viewmodels.MainViewModel
import com.ahmad.modernfoodrecipes.adapters.RecipesAdapter
import com.ahmad.modernfoodrecipes.databinding.FragmentRecipesBinding
import com.ahmad.modernfoodrecipes.util.Constants
import com.ahmad.modernfoodrecipes.util.Constants.Companion.API_KEY
import com.ahmad.modernfoodrecipes.util.NetworkResult
import com.ahmad.modernfoodrecipes.viewmodels.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class RecipesFragment : Fragment() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var recipesViewModel: RecipesViewModel
    private val mAdapter by lazy { RecipesAdapter() }
    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        recipesViewModel = ViewModelProvider(requireActivity())[RecipesViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)



        try {
            setUpRecyclerView()
            requestApiData()
        }catch (e:Exception){
            Log.e("TAGA", "onCreateView: "+e.localizedMessage.toString() )
        }

        return binding.root

    }

    private fun showShimmerEffect(){
        binding.shimmerFrameLayout.startShimmer()

    }


    private fun hideShimmerEffect() {
        binding.shimmerFrameLayout.hideShimmer()
        binding.shimmerFrameLayout.visibility = View.GONE

    }

    private fun requestApiData(){

        mainViewModel.getRecipes(recipesViewModel.applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    Toast.makeText(
                        requireContext(),
                        response.data.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading->{
                    showShimmerEffect()
                }
            }
        }
    }
    private fun setUpRecyclerView(){
        binding.recyclerView.adapter = mAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

}