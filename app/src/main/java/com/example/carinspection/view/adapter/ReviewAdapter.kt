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
                    val inspectionDatas:  List<InspectionData>,
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
            inspectionDatas?.get(position)?.let {  inspectionData ->
                if(inspectionData.objectType?.equals(Constants.STRING_DATA) == true && inspectionData.editable ) {
                    binding.titleTextView.text = inspectionData.question
                    binding.contentTextView.text =  inspectionData?.answer
                    binding.optionalTextView.visibility = View.GONE
                }else if(inspectionData.objectType?.equals(Constants.LIST_DATA) == true && inspectionData.editable){
                  var listNameValue : ListNameValue? =  inspectionData.answer?.let { it1 -> AppHelper.convertStringToListNameDataObject(it1) }
                    binding.titleTextView.text = listNameValue?.LIST_NAME_VALUE
                    binding.contentTextView.text =  inspectionData?.answer
                    if(listNameValue?.DEFAULT_REMARK_IF_SELECTED!=null)
                    {
                        binding.optionalTextView.text = listNameValue?.DEFAULT_REMARK_IF_SELECTED
                        binding.optionalTextView.visibility = View.VISIBLE
                    }else{
                        binding.optionalTextView.visibility = View.GONE
                    }

                }


            binding.editImageView.setOnClickListener {
                onclickItemClickListener.reviewEdit( inspectionDatas?.get(adapterPosition))
            }
            }

        }
    }


    interface ClickListnerOnItem {

        fun reviewEdit(inspectionData: InspectionData)
    }

    override fun getItemCount(): Int {
        return inspectionDatas.size
    }

    class CustomViewHolder(val binding: ReviewRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

}
