package com.example.carinspection.view.fragment

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.example.carinspection.BuildConfig
import com.example.carinspection.R
import com.example.carinspection.database.InspectionDatabase
import com.example.carinspection.databinding.FragmentUploadImageBinding
import com.example.carinspection.model.InspectionData
import com.example.carinspection.model.UploadImageData
import com.example.carinspection.util.AppHelper
import com.example.carinspection.util.Constants
import com.google.gson.Gson
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val documentType = "documentType"
private const val id = "id"
private const val idType = "idType"
private const val screenNumber = "screenNumber"
private const val objectType = "objectType"

/**
 * A simple [Fragment] subclass.
 * Use the [UploadImageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UploadImageFragment : BaseFragment() {
    private  var uploadImageData: UploadImageData?=null
    // TODO: Rename and change types of parameters
    private var documentType: String? = null
    private var id: String? = null
    private var idType: String? = null
    private var screenNumber: Int? = null
    private var objectType: String? = null
    var inspectionData: InspectionData? = null
    var fragmentInterfacer: UploadImageFragmentInterface? = null
    private var fragmentUploadImageBinding: FragmentUploadImageBinding? = null
    var PERMISSIONS =
        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA)
    val PERMISSION_ALL = 1
    val REQUEST_CODE_TAKE_PICTURE = 0x2
    var mCurrentPhotoPath: String? = null
    private var mFileTemp: File? = null
    var path : String?= ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            documentType = it.getString(com.example.carinspection.view.fragment.documentType)
            id = it.getString(id)
            idType = it.getString(com.example.carinspection.view.fragment.idType)
            screenNumber = it.getInt(com.example.carinspection.view.fragment.screenNumber)
            objectType = it.getString(com.example.carinspection.view.fragment.objectType)

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
            title.text="Upload picture $documentType"
            imgCamera?.setOnClickListener(View.OnClickListener {
                if (!hasPermissions(context, *PERMISSIONS)
                ) {
                    requestPermissions(PERMISSIONS, PERMISSION_ALL)
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
                    putString(com.example.carinspection.view.fragment.documentType, documentType)
                    id?.let {
                        putString(com.example.carinspection.view.fragment.id, id)
                        putString(com.example.carinspection.view.fragment.idType, idType)
                    }
                    putInt(com.example.carinspection.view.fragment.screenNumber,screenNumber)
                    putString(com.example.carinspection.view.fragment.objectType,objectType)

                }
            }
    }
    fun getData() {
        if (checkValidationManadatory()) {
            inspectionData?.let { fragmentInterfacer?.sendDataToActivity(it) }
        }
    }

    private fun checkValidationManadatory(): Boolean {
        inspectionData = InspectionData(AppHelper.convertToString(uploadImageData), screenNumber, Constants.UPLOAD_IMAGE, false)
         return true
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
           PERMISSION_ALL -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    dispatchTakePictureIntent()

                } else {
                    Toast.makeText(
                        context,
                        getString(R.string.please_denied_to_read_your_external_storage),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
          REQUEST_CODE_TAKE_PICTURE -> if (resultCode == Activity.RESULT_OK) {
                try {
                    croptImage()
                } catch (e: Exception) {
                }
            }
            CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> {
                val result = CropImage.getActivityResult(data)
                var resultUri: Uri? = null
                if (resultCode == Activity.RESULT_OK) {
                    resultUri = result.uri
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    val error = result.error
                }
                try {
                    path = resultUri?.path
                    if (path == null) {
                        return
                    }
                    fragmentUploadImageBinding?.imageView?.let {
                        Glide.with(this)
                            .load(File(path))
                            .centerCrop()
                            .into(it)
                    }
                    fragmentUploadImageBinding?.imageView?.visibility = View.VISIBLE

                    uploadImageData = UploadImageData(path,documentType,documentType,Calendar.getInstance().getTime().toString())

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    }

    private fun croptImage() {
        context?.let {
            CropImage.activity(Uri.fromFile(mFileTemp))
                .setInitialCropWindowPaddingRatio(0f).start(it, this)
        }

    }

}


