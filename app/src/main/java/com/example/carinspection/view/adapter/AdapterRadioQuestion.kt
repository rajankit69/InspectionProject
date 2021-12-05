package com.example.carinspection.view.adapter

import android.app.Activity
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carinspection.databinding.QuestionRadioLayoutBinding
import com.example.carinspection.model.ListNameValue

class AdapterRadioQuestion (  val activity: Activity?,
val list_name_value:  List<ListNameValue>,
val onclickItemClickListener: AdapterRadioQuestion.ClickListnerOnItem
) : RecyclerView.Adapter<AdapterRadioQuestion.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = QuestionRadioLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            holder.binding.radQuestionOption.text = list_name_value.get(position).LIST_NAME_VALUE
            holder.binding.radQuestionOption.setOnClickListener {
                holder.binding.radQuestionOption.text = "kjskk"
                list_name_value.get(position)?.DEFAULT_REMARK_IF_SELECTED?.let {
                    if (!it.isEmpty ()) {
                        holder.binding.defaultRemarkTextView.text =
                            list_name_value.get(position).DEFAULT_REMARK_IF_SELECTED
                    }
                }
              list_name_value.get(position).isSelected = true
               onclickItemClickListener.onClickItem(position)
            }
    }





    interface ClickListnerOnItem {

        fun onClickItem(position: Int)
    }

    override fun getItemCount(): Int {
        return list_name_value.size
    }

    class CustomViewHolder(val binding: QuestionRadioLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

}
