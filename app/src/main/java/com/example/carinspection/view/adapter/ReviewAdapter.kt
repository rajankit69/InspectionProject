package com.example.carinspection.view.adapter

import android.app.Activity
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carinspection.databinding.ReviewRowLayoutBinding
import com.example.carinspection.model.InspectionData
import com.example.carinspection.model.LeadData

class ReviewAdapter(val activity: Activity?,
                    val inspectionData:  List<InspectionData>,
                    val onclickItemClickListener: InspectionListAdapter.ClickListnerOnItem
) : RecyclerView.Adapter<ReviewAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(activity)
        val binding =
            ReviewRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        with(holder) {
            leadDatas?.get(position)?.let {
                binding.cardView?.setOnClickListener {
                    onclickItemClickListener.onClickItem(position)
                }
                binding?.run {

                }
                binding.editImageView.setOnClickListener {
                }

            }
        }
    }


    interface ClickListnerOnItem {

        fun reviewEdit(position: Int)
    }

    override fun getItemCount(): Int {
        return 34
    }

    class CustomViewHolder(val binding: ReviewRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

}
