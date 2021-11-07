package com.uz.yolharakatiqoidalari.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainer
import androidx.navigation.fragment.findNavController
import com.uz.yolharakatiqoidalari.R
import com.uz.yolharakatiqoidalari.databinding.FragmentAddZnakBinding
import com.uz.yolharakatiqoidalari.databinding.FragmentInfoZnakragmentBinding
import com.uz.yolharakatiqoidalari.db.MyDbHalper
import com.uz.yolharakatiqoidalari.models.Znak
import com.google.android.material.bottomnavigation.BottomNavigationView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InfoZnakragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InfoZnakragment : Fragment() {
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
    lateinit var fragmentInfoZnakBinding: FragmentInfoZnakragmentBinding
    lateinit var root:View
    lateinit var myDbHalper: MyDbHalper
    lateinit var listZnak:ArrayList<Znak>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentInfoZnakBinding = FragmentInfoZnakragmentBinding.inflate(inflater,container,false)
        root = fragmentInfoZnakBinding.root
        (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottom_navigation).visibility = View.GONE
        myDbHalper = MyDbHalper(root.context)
        var znak = arguments?.getSerializable("znak") as Znak
//        var position = arguments?.getInt("position",0)
//        var znak = myDbHalper.getAllZnak()[position!!]

        fragmentInfoZnakBinding.imageZnak.setImageURI(Uri.parse(znak.znak_image))
        fragmentInfoZnakBinding.nameTv.text = znak.znak_name
        fragmentInfoZnakBinding.nameZnak.text = znak.znak_name
        fragmentInfoZnakBinding.info.text = znak.znak_info
        fragmentInfoZnakBinding.closeBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InfoZnakragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InfoZnakragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}