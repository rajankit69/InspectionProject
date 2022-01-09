package com.example.carinspection.view.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carinspection.R
import com.example.carinspection.databinding.FragmentFirstBinding
import com.example.carinspection.databinding.FragmentVideoBinding
import androidx.core.app.ActivityCompat.startActivityForResult

import android.provider.MediaStore

import android.content.Intent
import android.net.Uri
import androidx.core.app.ActivityCompat.startActivityForResult
import com.example.carinspection.view.activity.RecordVideoPostsActivity
import io.github.memfis19.annca.Annca

import io.github.memfis19.annca.internal.configuration.AnncaConfiguration





// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var databinding : FragmentVideoBinding? = null

/**
 * A simple [Fragment] subclass.
 * Use the [VideoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VideoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        // Inflate the layout for this fragment
        databinding = FragmentVideoBinding.inflate(inflater, container, false)
        return databinding?.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListner()
    }

    @SuppressLint("MissingPermission")
    private fun setListner() {
        requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE), 109)
        databinding?.run {
            ll.setOnClickListener{
           /*     Intent(MediaStore.ACTION_VIDEO_CAPTURE).also { takeVideoIntent ->
                    context?.packageManager?.let { it1 ->
                        takeVideoIntent.resolveActivity(it1)?.also {
                            takeVideoIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0)
                            takeVideoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10)
                            startActivityForResult(takeVideoIntent, 101)
                        }
                    }
                }*/
                /*startActivityForResult(
                    Intent(
                        context,
                        RecordVideoPostsActivity::class.java
                    ),
                 101
                )*/
                val videoLimited = AnncaConfiguration.Builder(requireActivity(), 101)
                videoLimited.setMediaAction(AnncaConfiguration.MEDIA_ACTION_VIDEO)
                videoLimited.setMediaQuality(AnncaConfiguration.MEDIA_QUALITY_AUTO)
                videoLimited.setVideoFileSize((5 * 1024 * 1024).toLong())
                videoLimited.setMinimumVideoDuration((.2* 60 * 1000).toInt())
                Annca(videoLimited.build()).launchCamera()
             /*   val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)

                intent.putExtra("android.intent.extra.durationLimit", 5);
                intent.putExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION, true);
                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5);
                intent.putExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION, true);

                startActivityForResult(intent, 101)*/
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment VideoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VideoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, itent: Intent?) {
        super.onActivityResult(requestCode, resultCode, itent)
        if (requestCode == 101 && resultCode == resultCode) {
            val videoUri: String? = itent?.getStringExtra(AnncaConfiguration.Arguments.FILE_PATH)
            databinding?.run {
                videoPlayer.visibility= View.VISIBLE
               videoPlayer.setVideoPath(videoUri)
                videoPlayer.start()
            }
        }
    }

}