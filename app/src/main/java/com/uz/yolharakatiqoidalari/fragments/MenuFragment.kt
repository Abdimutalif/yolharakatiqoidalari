package com.uz.yolharakatiqoidalari.fragments

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentContainer
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.uz.yolharakatiqoidalari.R
import com.uz.yolharakatiqoidalari.adapters.ViewPagerAdapter
import com.uz.yolharakatiqoidalari.databinding.FragmentMenuBinding
import com.uz.yolharakatiqoidalari.databinding.TabItemBinding
import com.uz.yolharakatiqoidalari.models.Category
import com.github.florent37.runtimepermission.kotlin.askPermission
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.karumi.dexter.BuildConfig
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.jvm.Throws

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    lateinit var fragmentMenuBinding: FragmentMenuBinding
    lateinit var root:View
    lateinit var listCategory:ArrayList<Category>
    lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var photoURI: Uri
    var bool:Boolean=false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMenuBinding = FragmentMenuBinding.inflate(inflater,container,false)
        root = fragmentMenuBinding.root
        (activity)!!.findViewById<BottomNavigationView>(R.id.bottom_navigation).visibility = View.VISIBLE
        (activity as AppCompatActivity).setSupportActionBar(fragmentMenuBinding.toolbar)
        loadCategory()
        viewPagerAdapter = ViewPagerAdapter(listCategory,requireActivity())
        fragmentMenuBinding.viewPager2.adapter = viewPagerAdapter
        TabLayoutMediator(fragmentMenuBinding.tabLayout,fragmentMenuBinding.viewPager2){ tab,position->
            tab.text = listCategory[position].category_name
        }.attach()
        statTab()
        var calback = object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                (activity as AppCompatActivity).finish()
            }
        }

        fragmentMenuBinding.tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val customView = tab!!.customView
                var tabItemBinding = TabItemBinding.bind(customView!!)
                tabItemBinding.cons2.setBackgroundColor(Color.WHITE)
                tabItemBinding.nameCategory.setTextColor(Color.parseColor("#005CA1"))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val customView = tab!!.customView
                var tabItemBinding = TabItemBinding.bind(customView!!)
                tabItemBinding.cons2.setBackgroundColor(Color.parseColor("#005CA1"))
                tabItemBinding.nameCategory.setTextColor(Color.WHITE)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        return root
    }

    override fun onStop() {
        super.onStop()
        viewPagerAdapter = ViewPagerAdapter(listCategory,requireActivity())
        fragmentMenuBinding.viewPager2.adapter = viewPagerAdapter
    }

    override fun onResume() {
        super.onResume()
        if (bool){
            viewPagerAdapter = ViewPagerAdapter(listCategory,requireActivity())
            fragmentMenuBinding.viewPager2.adapter = viewPagerAdapter
        }else if (ContextCompat.checkSelfPermission(root.context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(root.context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
        }
    }
    private fun statTab() {
        val tabCount = fragmentMenuBinding.tabLayout.tabCount
        for (i in 0 until tabCount){
            var tabItemBinding = TabItemBinding.inflate(LayoutInflater.from(root.context),null,false)
            val tabAt = fragmentMenuBinding.tabLayout.getTabAt(i)
            tabAt!!.customView = tabItemBinding.root
            tabItemBinding.nameCategory.text = listCategory[i].category_name
            if (i==0){
                tabItemBinding.cons2.setBackgroundColor(Color.WHITE)
                tabItemBinding.nameCategory.setTextColor(Color.parseColor("#005CA1"))
            }else{
                tabItemBinding.cons2.setBackgroundColor(Color.parseColor("#005CA1"))
                tabItemBinding.nameCategory.setTextColor(Color.WHITE)

            }
        }
    }

    private fun loadCategory() {
        listCategory = ArrayList()
        listCategory.add(Category("Ogohlantiruvchi",0))
        listCategory.add(Category("Imtiyozli",1))
        listCategory.add(Category("Ta'qiqlovchi",2))
        listCategory.add(Category("Buyuruvchi",3))
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        when(itemId){
            R.id.add->{
                var bundle = Bundle()
                bool=true
                var navOptions = NavOptions.Builder()
                navOptions.setEnterAnim(R.anim.enter)
                navOptions.setExitAnim(R.anim.exite)
                navOptions.setPopEnterAnim(R.anim.pop_enter)
                navOptions.setPopExitAnim(R.anim.pop_exite)
                findNavController().navigate(R.id.addZnakFragment,bundle,navOptions.build())
            }
        }
        return super.onOptionsItemSelected(item)
    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MenuFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}