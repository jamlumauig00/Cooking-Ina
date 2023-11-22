package com.jam.cookingina

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jam.cookingina.Adapter.AdapterOnClick
import com.jam.cookingina.Adapter.KinginaAdapter
import com.jam.cookingina.Database.AppDatabase
import com.jam.cookingina.Database.CookingModel
import com.jam.cookingina.Database.CookingRepository
import com.jam.cookingina.Database.CookingViewModel
import com.jam.cookingina.Database.CookingViewModelFactory
import com.jam.cookingina.databinding.FragmentFirstBinding

class FirstFragment : Fragment(), AdapterOnClick {

    private lateinit var cookingViewModel: CookingViewModel

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private lateinit var rview: RecyclerView
    lateinit var arrayAdapter: KinginaAdapter
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        //val receivedValue = requireArguments().getString("data")
        val data = arguments?.getString("data")
        Log.e("dataargs", data.toString())

        Log.e( "data" , data.toString())

        binding.navbar.text = data
        rview = binding.cookingRview
        rview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val repository =
            CookingRepository(AppDatabase.getDatabase(requireContext()).cookingInaDao())
        cookingViewModel = ViewModelProvider(
            this,
            CookingViewModelFactory(repository)
        )[CookingViewModel::class.java]

        cookingViewModel.allData.observe(viewLifecycleOwner) { datas ->
            // Handle the data here
            Log.e("cookingViewModel", datas.toString())
            val filteredItems = ArrayList<CookingModel>()

            for (value in datas) {
                if (value.Category == data) {
                    Log.e("VALUE", value.Category.toString())
                    filteredItems.add(value)
                }
            }
            arrayAdapter = KinginaAdapter(filteredItems, this)
            rview.adapter = arrayAdapter
            arrayAdapter.notifyDataSetChanged()
        }

        //findNavController().popBackStack(R.id.mainactivity, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onclick(position: Int) {
        val bundle = Bundle()
        bundle.putInt("position", position)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)

    }

}