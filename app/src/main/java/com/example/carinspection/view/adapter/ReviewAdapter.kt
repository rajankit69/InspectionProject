package com.example.carinspection.view.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carinspection.databinding.ReviewRowLayoutBinding
import com.example.carinspection.model.InspectionData
import com.example.carinspection.model.ListNameValue
import com.example.carinspection.util.AppHelper
import com.example.carinspection.util.Constants

class ReviewAdapter(val activity: Context?,
                    val inspectionData:  List<InspectionData>,
                    val onclickItemClickListener: ClickListnerOnItem
) : RecyclerView.Adapter<ReviewAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(activity)
        val binding =
            ReviewRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        with(holder) {
            inspectionData?.get(position)?.let {
                if(it.objectType?.equals(Constants.STRING_DATA) == true && it.editable ) {
                    binding.titleTextView.text = it.question
                    binding.contentTextView.text =  it?.answer
                }else if(it.objectType?.equals(Constants.LIST_DATA) == true && it.editable){
                  var listNameValue : ListNameValue? =  it.answer?.let { it1 -> AppHelper.convertStringToListNameDataObject(it1) }
                    binding.titleTextView.text = listNameValue?.LIST_NAME_VALUE
                    binding.contentTextView.text =  it?.answer
                    if(listNameValue?.DEFAULT_REMARK_IF_SELECTED!=null)
                    {
                        binding.optionalTextView.text = listNameValue?.DEFAULT_REMARK_IF_SELECTED
                        binding.optionalTextView.visibility = View.VISIBLE
                    }

                }

            }
            binding.cardView?.setOnClickListener {
                onclickItemClickListener.reviewEdit(position)
            }
            binding?.run {

            }
            binding.editImageView.setOnClickListener {

            }

        }
    }


    interface ClickListnerOnItem {

        fun reviewEdit(position: Int)
    }

    override fun getItemCount(): Int {
        return 22
    }

    class CustomViewHolder(val binding: ReviewRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

}
