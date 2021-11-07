package com.uz.yolharakatiqoidalari.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.navigation.fragment.findNavController
import com.uz.yolharakatiqoidalari.R
import com.uz.yolharakatiqoidalari.adapters.SpinnerAdapter
import com.uz.yolharakatiqoidalari.databinding.FragmentAddZnakBinding
import com.uz.yolharakatiqoidalari.databinding.FragmentZnakBinding
import com.uz.yolharakatiqoidalari.databinding.ItemBottomsheetBinding
import com.uz.yolharakatiqoidalari.db.MyDbHalper
import com.uz.yolharakatiqoidalari.models.Category
import com.uz.yolharakatiqoidalari.models.Znak
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.jvm.Throws

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddZnakFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddZnakFragment : Fragment() {
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
    lateinit var fragmentAddZnakBinding: FragmentAddZnakBinding
    lateinit var root:View
    lateinit var listCategory:ArrayList<String>
    lateinit var filAbsolutePath:String
    lateinit var ImagePath:String
    lateinit var spinnerAdapter:SpinnerAdapter
    lateinit var photoURI:Uri
    lateinit var myDbHalper: MyDbHalper
    lateinit var currentImagePath:String
    lateinit var listZnak:ArrayList<Znak>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentAddZnakBinding = FragmentAddZnakBinding.inflate(inflater, container, false)
        root = fragmentAddZnakBinding.root
        (activity)!!.findViewById<BottomNavigationView>(R.id.bottom_navigation).visibility =
            View.GONE
        loadCategory()
        myDbHalper = MyDbHalper(root.context)
        fragmentAddZnakBinding.image.setOnClickListener {
            var bottomSheetDialog = BottomSheetDialog(root.context, R.style.BottomSheetDialogThem)
            var itemBottomSheetDialog = ItemBottomsheetBinding.inflate(LayoutInflater.from(root.context), null, false)

            itemBottomSheetDialog.camera.setOnClickListener {
                var imageFile = createImageFile()
                photoURI= FileProvider.getUriForFile(root.context,com.uz.yolharakatiqoidalari.BuildConfig.APPLICATION_ID,imageFile)
                getTakeImageContent.launch(photoURI)
                bottomSheetDialog.hide()
            }
            itemBottomSheetDialog.galereya.setOnClickListener {
                picImageForNewGallery()
                bottomSheetDialog.hide()
            }
            bottomSheetDialog.setContentView(itemBottomSheetDialog.root)
            bottomSheetDialog.show()
        }
        spinnerAdapter = SpinnerAdapter(listCategory)
        fragmentAddZnakBinding.spinnerCategory.adapter = spinnerAdapter
        fragmentAddZnakBinding.saveBtn.setOnClickListener {
            var name = fragmentAddZnakBinding.name.text.toString()
            var info = fragmentAddZnakBinding.info.text.toString()
            var position_spinner = fragmentAddZnakBinding.spinnerCategory.selectedItemPosition
            if (name.isNullOrBlank()){
                Toast.makeText(root.context, "Iltimos ismingizni kiriting", Toast.LENGTH_SHORT).show()
            }else if (info.isNullOrBlank()){
                Toast.makeText(root.context, "Iltimos infoni kiriting", Toast.LENGTH_SHORT).show()
            }else if (name.isNotBlank() && info.isNotBlank()){
                var znak = Znak(ImagePath,name,info,listCategory[position_spinner],position_spinner,R.drawable.ic_heart__1__1)
                myDbHalper.addZnak(znak)
                println(ImagePath)
                findNavController().popBackStack()
            }
        }
        fragmentAddZnakBinding.closeBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        return root
    }

    private val getTakeImageContent = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        if (it) {
            fragmentAddZnakBinding.image.setImageURI(photoURI)
            var openInputStream = activity?.contentResolver?.openInputStream(photoURI)
            var format = SimpleDateFormat("yyyyMMdd_HHmmss",Locale.getDefault()).format(Date())
            var file = File(activity?.filesDir, "$format.jpg")
            var fileoutputStream = FileOutputStream(file)
            openInputStream?.copyTo(fileoutputStream)
            openInputStream?.close()
            fileoutputStream.close()
            var filAbsolutePath = file.absolutePath
            ImagePath = filAbsolutePath
        }
    }


    @Throws(IOException::class)
    private fun createImageFile(): File {
        val date = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val externalFilesDir = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_$date",".jpg",externalFilesDir).apply {
            absolutePath
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (::currentImagePath.isInitialized){
//            fragmentAddZnakBinding.image.setImageURI(Uri.fromFile(File(currentImagePath)))
//        }
//    }

    // camera




    // gallerry
    private fun picImageForNewGallery() {
        getImageContent.launch("image/*")
    }

    private var getImageContent = registerForActivityResult(ActivityResultContracts.GetContent()){ uri->
        uri?:return@registerForActivityResult
        fragmentAddZnakBinding.image.setImageURI(uri)
        var openInputStream =(activity)?.contentResolver?.openInputStream(uri)
        var filesDir = (activity)?.filesDir
        var format = SimpleDateFormat("yyyyMMdd_HHmmss",Locale.getDefault()).format(Date())
        var file = File(filesDir,"$format.jpg")
        val fileOutputStream = FileOutputStream(file)
        openInputStream!!.copyTo(fileOutputStream)
        openInputStream.close()
        fileOutputStream.close()
        var filAbsolutePath = file.absolutePath
        ImagePath = filAbsolutePath
//        var fileInputStream = FileInputStream(file)
//        val readBytes = fileInputStream.readBytes()
    }

    private fun loadCategory() {
        listCategory = ArrayList()
        listCategory.add("Ogohlantiruvchi")
        listCategory.add("Imtiyozli")
        listCategory.add("Ta'qiqlovchi")
        listCategory.add("Buyuruvchi")
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddZnakFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddZnakFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

