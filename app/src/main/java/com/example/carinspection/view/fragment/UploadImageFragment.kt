package com.example.carinspection.view.fragment

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.content.PermissionChecker.checkPermission
import com.example.carinspection.BuildConfig
import com.example.carinspection.R
import com.example.carinspection.databinding.FragmentUploadImageBinding
import com.example.carinspection.databinding.LoginLayoutBinding
import com.example.carinspection.model.InspectionData
import java.io.File
import java.io.IOException
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "documentType"
private const val ARG_PARAM2 = "id"
private const val ARG_PARAM3 = "idType"
private const val ARG_PARAM4 = "screenNumber"
private const val ARG_PARAM5 = "objectType"

/**
 * A simple [Fragment] subclass.
 * Use the [UploadImageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UploadImageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null
    private var param4: Int? = null
    private var param5: String? = null
    var inspectionData: InspectionData? = null
    var fragmentInterfacer: UploadImageFragmentInterface? = null
    private var fragmentUploadImageBinding: FragmentUploadImageBinding? = null
    var PERMISSIONS =
        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA)
    val PERMISSION_ALL = 1
    val REQUEST_CODE_TAKE_PICTURE = 0x2
    var mCurrentPhotoPath: String? = null
    private var mFileTemp: File? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)
            param4 = it.getInt(ARG_PARAM4)
            param5 = it.getString(ARG_PARAM5)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentUploadImageBinding = FragmentUploadImageBinding.inflate(inflater, container, false)
        return fragmentUploadImageBinding?.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentInterfacer = context as UploadImageFragmentInterface

    }

    private fun init() {
        fragmentUploadImageBinding?.run {
            title.text="Upload picture $param1"
            llUploadCamra?.setOnClickListener(View.OnClickListener {
                if (!hasPermissions(context, *PERMISSIONS)
                ) {
                    requestPermissionLauncher.launch(PERMISSIONS)
                } else {
                    dispatchTakePictureIntent()
                }
            })
        }
    }

    private fun setView() {

    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Ensure that there's a camera activity to handle the intent
        if (activity?.packageManager?.let {
                takePictureIntent.resolveActivity(it) } != null) {
            // Create the File where the photo should go
            var photoFile: File? = null
            try {
                photoFile = createImageFile()
            } catch (ex: IOException) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                val photoURI = FileProvider.getUriForFile(
                    requireContext(),
                    BuildConfig.APPLICATION_ID.toString() + ".fileprovider",
                    photoFile
                )
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                try {
                    startActivityForResult(
                        takePictureIntent,
                        REQUEST_CODE_TAKE_PICTURE
                    )
                } catch (e: Exception) {
                    Toast.makeText(
                        context,
                        "Please provide Permission",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File? {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
            imageFileName,  /* prefix */
            ".jpg",  /* suffix */
            storageDir /* directory */
        )

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.absolutePath
        mFileTemp = image
        return image
    }


    fun hasPermissions(context: Context?, vararg permissions: String?): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (permission in permissions) {
                if (permission?.let {
                        ContextCompat.checkSelfPermission(
                            context,
                            it
                        )
                    } != PackageManager.PERMISSION_GRANTED
                ) {
                    return false
                }
            }
        }
        return true
    }
    interface UploadImageFragmentInterface {

        fun sendDataToActivity(inspectionData: InspectionData)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UploadImageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(documentType: String, id: String?,idType: String?,screenNumber:Int,objectType:String) =
            UploadImageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, documentType)
                    id?.let {
                        putString(ARG_PARAM2, id)
                        putString(ARG_PARAM3, idType)
                    }
                    putInt(ARG_PARAM4,screenNumber)
                    putString(ARG_PARAM5,objectType)

                }
            }
    }
    fun getData() {
        if (checkValidationManadatory()) {
            inspectionData?.let { fragmentInterfacer?.sendDataToActivity(it) }
        }
    }

    private fun checkValidationManadatory(): Boolean {
        inspectionData =
            111?.let { InspectionData("ajhjha",param4 , "jksjk", true, it) }
         return true
    }

    val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            // Handle Permission granted/rejected
            permissions.entries.forEach {
                val permissionName = it.key
                val isGranted = it.value
                if (isGranted) {
                    // Permission is granted
                    dispatchTakePictureIntent()
                } else {
                    // Permission is denied
                }
            }
        }
        }

/* override  fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>?, grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_ALL -> {


                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                 *//*   if (isCutout) {
                        showCutImageDialog()
                    } else if (isReuploaded) {
                        selectImage()
                    } else if (isGallerySelected) {
                        openGallery()
                    } else {*//*
                        dispatchTakePictureIntent()
                   // }
                } else {
                    Toast.makeText(
                        context,
                        "Please allow to external storage ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }*/

