package com.ahmad.modernfoodrecipes.ui.fragments.recipes

import android.os.Bundle
import android.provider.SyncStateContract.Constants
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.ahmad.modernfoodrecipes.databinding.RecipesBottomSheetBinding
import com.ahmad.modernfoodrecipes.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.ahmad.modernfoodrecipes.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.ahmad.modernfoodrecipes.viewmodels.RecipesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup


class RecipesBottomSheet : BottomSheetDialogFragment() {

    private lateinit var recipesViewModel: RecipesViewModel

    private var mealTypeChip = DEFAULT_MEAL_TYPE
    private var mealTypeChipId =0
    private var dietTypeChip = DEFAULT_DIET_TYPE
    private var dietTypeChipId =0

    private lateinit var _binding:RecipesBottomSheetBinding
    private val binding get() = _binding
    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipesViewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = RecipesBottomSheetBinding.inflate(layoutInflater, container, false)


        recipesViewModel.readMealAndDietType.asLiveData().observe(viewLifecycleOwner) { value ->
            mealTypeChip = value.selectedMealType
            dietTypeChip = value.selectedDietType
            updateChip(value.selectedMealTypeId, binding.mealTypeChipGroup)
            updateChip(value.selectedDietTypeId, binding.dietTypeChipGroup)
        }



        binding.mealTypeChipGroup.setOnCheckedStateChangeListener(){group, checkedIds->
            val chip = group.findViewById<Chip>(checkedIds[0])
            val selectedMealType = chip.text.toString().lowercase()
            mealTypeChip = selectedMealType
            mealTypeChipId = checkedIds[0]

        }
        binding.dietTypeChipGroup.setOnCheckedStateChangeListener(){group, checkedIds->
            val chip = group.findViewById<Chip>(checkedIds[0])
            val selectedDietType = chip.text.toString().lowercase()
            dietTypeChip = selectedDietType
            dietTypeChipId = checkedIds[0]

        }
        binding.applyBtn.setOnClickListener{
            recipesViewModel.saveMealAndDietType(mealTypeChip, mealTypeChipId, dietTypeChip, dietTypeChipId)
            val action: RecipesBottomSheetDirections.ActionRecipesBottomSheetToRecipesFragment()

        }
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun updateChip(chipId: Int, chipGroup: ChipGroup) {
        if(chipId!=0){
            try {
                chipGroup.findViewById<Chip>(chipId).isChecked = true
            }
            catch (e:Exception){
                Log.d("RecipesBottomSheet", "updateChip: "+e.message.toString())
            }
        }

    }


}