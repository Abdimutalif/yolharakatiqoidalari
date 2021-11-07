package com.uz.yolharakatiqoidalari.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.uz.yolharakatiqoidalari.R
import com.uz.yolharakatiqoidalari.adapters.RvAdapterLeek
import com.uz.yolharakatiqoidalari.databinding.FragmentLeekBinding
import com.uz.yolharakatiqoidalari.db.MyDbHalper
import com.uz.yolharakatiqoidalari.models.Znak
import com.uz.yolharakatiqoidalari.models.ZnakLeek
import com.google.android.material.bottomnavigation.BottomNavigationView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LeekFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LeekFragment : Fragment() {
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
    lateinit var fragmentLeekBinding: FragmentLeekBinding
    lateinit var root:View
    lateinit var myDbHalper: MyDbHalper
    lateinit var rvAdapterLeek: RvAdapterLeek
    lateinit var listLeek:ArrayList<Znak>
    lateinit var listLeek1:ArrayList<Znak>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentLeekBinding = FragmentLeekBinding.inflate(inflater,container,false)
        root = fragmentLeekBinding.root
        myDbHalper = MyDbHalper(root.context)
        listLeek1 = myDbHalper.getAllZnak()
        var list = ArrayList<Znak>()
        for (i in listLeek1) {
            if (i.znak_leek_backGraund==R.drawable.ic_heart_1){
                list.add(i)
            }
        }
            rvAdapterLeek = RvAdapterLeek(list, object : RvAdapterLeek.OnItemClick {
            override fun onItemClickLeek(znakLeek: Znak, position: Int) {

            }

            override fun onItemClicDelete(znakLeek: Znak, position: Int) {
                list.remove(znakLeek)
                rvAdapterLeek.notifyItemRemoved(position)
                rvAdapterLeek.notifyItemRangeChanged(position,list.size)
                znakLeek.znak_leek_backGraund = R.drawable.ic_heart__1__1
                myDbHalper.editeZnak(znakLeek)
            }

            override fun onItemEditeZnak(znakLeek: Znak, position: Int) {
                var bundle = Bundle()
                bundle.putInt("position", position)
                bundle.putSerializable("znak",znakLeek)
                var navOptions = NavOptions.Builder()
                navOptions.setEnterAnim(R.anim.enter)
                navOptions.setExitAnim(R.anim.exite)
                navOptions.setPopEnterAnim(R.anim.pop_enter)
                navOptions.setPopExitAnim(R.anim.pop_exite)
                findNavController().navigate(
                    R.id.editeLeekFragment,
                    bundle,
                    navOptions.build()
                )
            }

            override fun onItemClickOp(znakLeek: Znak, position: Int) {
                var bundle = Bundle()
                bundle.putInt("position", position)
                bundle.putSerializable("znak",znakLeek)
                var navOptions = NavOptions.Builder()
                navOptions.setEnterAnim(R.anim.enter)
                navOptions.setExitAnim(R.anim.exite)
                navOptions.setPopEnterAnim(R.anim.pop_enter)
                navOptions.setPopExitAnim(R.anim.pop_exite)
                findNavController().navigate(
                    R.id.infoZnakragment,
                    bundle,
                    navOptions.build()
                )
            }

        })
        fragmentLeekBinding.rvLeek.adapter = rvAdapterLeek

        var calback = object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.home1
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,calback)

        return root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottom_navigation).visibility = View.VISIBLE

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LeekFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LeekFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}