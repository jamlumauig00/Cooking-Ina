package com.jam.cookingina

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jam.cookingina.Database.AppDatabase
import com.jam.cookingina.Database.CookingRepository
import com.jam.cookingina.Database.CookingViewModel
import com.jam.cookingina.Database.CookingViewModelFactory
import com.jam.cookingina.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private lateinit var cookingViewModel: CookingViewModel
    private lateinit var data: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        val position = arguments?.getInt("position")

        val repository = CookingRepository(AppDatabase.getDatabase(requireContext()).cookingInaDao())
        cookingViewModel = ViewModelProvider(
            this,
            CookingViewModelFactory(repository)
        )[CookingViewModel::class.java]

        // Observe LiveData to get data
        cookingViewModel.allData.observe(viewLifecycleOwner) { datas ->
            // Handle the data here
            Log.e("position", position.toString())
            data = datas[position!!].Category.toString()
            binding.title.text = datas[position].Cuisine
            binding.cookingdesc.text = datas[position].Description
            binding.ingDesc.text = datas[position].Ingredients
            binding.howToDesc.text = datas[position].How

        }

          val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
               // Handle the back press here
               val args = Bundle()
               args.putString("data", data)
               findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment, args)
               // Consume the back press
               isEnabled = false
           }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
