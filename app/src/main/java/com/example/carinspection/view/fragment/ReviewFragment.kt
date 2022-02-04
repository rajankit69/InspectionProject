package com.example.carinspection.view.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carinspection.R
import com.example.carinspection.database.InspectionDatabase
import com.example.carinspection.databinding.FragmentFirstBinding
import com.example.carinspection.databinding.FragmentReviewBinding
import com.example.carinspection.model.InspectionData
import com.example.carinspection.view.adapter.AdapterRadioQuestion
import com.example.carinspection.view.adapter.ReviewAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReviewFragment : BaseFragment(),ReviewAdapter.ClickListnerOnItem {
    private var inspectionDataList: List<InspectionData>? = null
    private var reviewAdapter: ReviewAdapter?=null
    private var databinding: FragmentReviewBinding? = null
    private var param1: String? = null
    private var param2: String? = null
    private var mcontext : Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        databinding = FragmentReviewBinding.inflate(inflater, container, false)
        return databinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
    }
    private fun setView() {
        launch {
            inspectionDataList = mcontext?.let { InspectionDatabase.getInstance(it).inspectionDataDao.getAll() }
            CoroutineScope(Dispatchers.Main).launch {
                updateUi()
            }

        }

    }

    private fun updateUi() {
        reviewAdapter =
            inspectionDataList?.let {
                ReviewAdapter(
                    mcontext,
                    it,
                    this@ReviewFragment
                )
            }

        // attach adapter to the recycler view
        databinding?.reviewRecyclerView?.layoutManager =
            LinearLayoutManager(
                mcontext,
                LinearLayoutManager.VERTICAL,
                false
            )
        databinding?.reviewRecyclerView?.adapter = reviewAdapter

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ReviewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            ReviewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mcontext = context

    }

    override fun reviewEdit(position: Int) {

    }
}