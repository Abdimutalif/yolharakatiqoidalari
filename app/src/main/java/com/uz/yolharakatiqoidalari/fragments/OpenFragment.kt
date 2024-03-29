package com.uz.yolharakatiqoidalari.fragments

import android.Manifest
import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.uz.yolharakatiqoidalari.R
import com.uz.yolharakatiqoidalari.databinding.FragmentOpenBinding
import com.github.florent37.runtimepermission.kotlin.askPermission
import com.google.android.material.bottomnavigation.BottomNavigationView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OpenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OpenFragment : Fragment() {
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
    lateinit var fragmentOpenBinding: FragmentOpenBinding
    lateinit var root:View
    lateinit var handler: Handler
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentOpenBinding = FragmentOpenBinding.inflate(inflater,container,false)
        root = fragmentOpenBinding.root

        handler = Handler()
        handler.postDelayed({
            var bundle = Bundle()
            var navOptions = NavOptions.Builder()
            navOptions.setEnterAnim(R.anim.enter)
            navOptions.setExitAnim(R.anim.exite)
            navOptions.setPopEnterAnim(R.anim.pop_enter)
            navOptions.setPopExitAnim(R.anim.pop_exite)
            findNavController().navigate(R.id.menuFragment,bundle,navOptions.build())
        },1000)
        return root
    }

    override fun onResume() {
        super.onResume()
        (activity)!!.findViewById<BottomNavigationView>(R.id.bottom_navigation).visibility = View.GONE
        findNavController().popBackStack()
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OpenFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OpenFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}