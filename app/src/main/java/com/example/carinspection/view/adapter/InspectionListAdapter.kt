package com.example.carinspection.view.adapter

import android.app.Activity
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carinspection.R
import com.example.carinspection.databinding.InceptionRowLayoutBinding
import com.example.carinspection.model.LeadData

class InspectionListAdapter(
    val activity: Activity?,
    val leadDatas:  List<LeadData>,
    val onclickItemClickListener: InspectionListAdapter.ClickListnerOnItem
) : RecyclerView.Adapter<InspectionListAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(activity)
        val binding =
            InceptionRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        with(holder) {
            leadDatas?.get(position)?.let {
                binding.cardView?.setOnClickListener {
                    onclickItemClickListener.onClickItem(position)
                }
                binding?.run {
                    CustomerNametxtView.text = "Name: ".plus(it.cUSTOMER_NAME)
                    customerEmailTv.text = "Email: ".plus(it.eMAIL_ID)
                    customerPhoneTv.text = "Phone: ".plus(it.pHONE_NUMBER)
                    customerWhatsappTv.text = "Whtsapp Number: ".plus(it.wHATSAPP_NUMBER)
                    customerAddressTv.text = "Address: ".plus(it.aDDRESS)
                    customerLandMarkTv.text = "Landmark: ".plus(it.lANDMARK)
                    customerCityTv.text = "City: ".plus(it.cITY_NAME)
                    customerPinTv.text = "Pincode: ".plus(it.pIN_CODE.toString())
                    customerDistrictTv.text = "District: ".plus(it.dISTRICT_NAME)
                    CustomerStatetxtView.text = "State: ".plus(it.sTATE_NAME)
                    customerCountryTv.text = "Country: ".plus(it.cOUNTRY_NAME)
                    CustomerMaketxtView.text = "Make Name: ".plus(it.mAKE_NAME)
                    customerModelTv.text = "Mode: ".plus(it.mODEL)
                    customerRegTv.text = "Registration: ".plus(it.rEGISTRATION_ID)
                }
                binding.exapndMore.setOnClickListener {
                    if (binding.hiddenView.getVisibility() == View.GONE) {

                        TransitionManager.beginDelayedTransition(binding.cardView, AutoTransition())
                        binding.hiddenView.setVisibility(View.VISIBLE);
                        binding.expandLess.setVisibility(View.VISIBLE)
                        binding.exapndMore.setVisibility(View.GONE);                    }
                }
                binding.expandLess.setOnClickListener {
                    if (binding.hiddenView.getVisibility() == View.VISIBLE) {
                        TransitionManager.beginDelayedTransition(binding.cardView, AutoTransition())
                        binding.hiddenView.setVisibility(View.GONE)
                        binding.expandLess.setVisibility(View.GONE)
                        binding.exapndMore.setVisibility(View.VISIBLE)
                    }
                }

            }
        }
    }


    interface ClickListnerOnItem {

        fun onClickItem(position: Int)
    }

    override fun getItemCount(): Int {
        return leadDatas.size
    }

    class CustomViewHolder(val binding: InceptionRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

}
