package com.kurly.logickurly.presentation.recommendRecipe.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.kurly.logickurly.R
import com.kurly.logickurly.databinding.DialogRecipeNoticeBinding

class RecipeNoticeDialog : DialogFragment() {
    private var _binding: DialogRecipeNoticeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialogRecipeNoticeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.confirmBtn.setOnClickListener{
            dismiss()
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        // full Screen code
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        // 레이아웃 배경설정
        dialog?.window?.setBackgroundDrawableResource(R.color.dim)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}