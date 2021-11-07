package com.uz.yolharakatiqoidalari.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.uz.yolharakatiqoidalari.R
import com.uz.yolharakatiqoidalari.adapters.RvAdapter
import com.uz.yolharakatiqoidalari.databinding.FragmentZnakBinding
import com.uz.yolharakatiqoidalari.db.MyDbHalper
import com.uz.yolharakatiqoidalari.models.Znak
import com.uz.yolharakatiqoidalari.models.ZnakLeek

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ZnakFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ZnakFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    lateinit var fragmentZnakBinding: FragmentZnakBinding
    lateinit var root:View
    lateinit var myDbHalper: MyDbHalper
    lateinit var listZnak:ArrayList<Znak>
    lateinit var rvAdapter: RvAdapter
    var color:Int=R.drawable.ic_heart_1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentZnakBinding = FragmentZnakBinding.inflate(inflater,container,false)
        root = fragmentZnakBinding.root
        myDbHalper = MyDbHalper(root.context)
        listZnak = myDbHalper.getAllZnak()
        var listZnaks = ArrayList<Znak>()
        for (i in listZnak) {
            if (i.znak_category_position==param1 && i.znak_category.equals(param2, ignoreCase = true)){
                listZnaks.add(i)
            }
        }
        return root
    }


    override fun onResume() {
        super.onResume()
        listZnak = myDbHalper.getAllZnak()
        var listZnaks = ArrayList<Znak>()
        for (i in listZnak) {
            if (i.znak_category_position==param1 && i.znak_category.equals(param2, ignoreCase = true)){
                listZnaks.add(i)
            }
        }

        when(param1){
            0 -> {
                rvAdapter = RvAdapter(root.context, listZnaks, object : RvAdapter.OnItemClick {
                    override fun onItemClickLeek(znak: Znak, position: Int) {
                        if (znak.znak_leek_backGraund == R.drawable.ic_heart__1__1) {
                            znak.znak_leek_backGraund = R.drawable.ic_heart_1
                            myDbHalper.editeZnak(znak)
                            rvAdapter.notifyDataSetChanged()
                        } else if (znak.znak_leek_backGraund == R.drawable.ic_heart_1) {
                            znak.znak_leek_backGraund = R.drawable.ic_heart__1__1
                            myDbHalper.editeZnak(znak)

                            // myDbHalper.deleteZnakLeek(znak)
                            rvAdapter.notifyDataSetChanged()
                        }
                    }

                    override fun onItemClicDelete(znak: Znak, position: Int) {
                        myDbHalper.deleteZnak(znak)
                        listZnaks.remove(znak)
                        rvAdapter.notifyItemRemoved(position)
                        rvAdapter.notifyItemRangeChanged(position, listZnaks.size)
                        // clearImages()
                    }


                    override fun onItemEditeZnak(znak: Znak, position: Int) {
                        var bundle = Bundle()
                        bundle.putInt("position", position)
                        bundle.putInt("category", param1!!)
                        bundle.putSerializable("znak1",znak)
                        var navOptions = NavOptions.Builder()
                        navOptions.setEnterAnim(R.anim.enter)
                        navOptions.setExitAnim(R.anim.exite)
                        navOptions.setPopEnterAnim(R.anim.pop_enter)
                        navOptions.setPopExitAnim(R.anim.pop_exite)
                        findNavController().navigate(R.id.editeFragment, bundle, navOptions.build())
                    }

                    override fun onItemClickOp(znak: Znak, position: Int) {
                        var bundle = Bundle()
                        bundle.putInt("position", position)
                        bundle.putSerializable("znak",znak)
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
                }, color)
                fragmentZnakBinding.rv.adapter = rvAdapter
            }
            1->{
                rvAdapter = RvAdapter(root.context,listZnaks,object:RvAdapter.OnItemClick{
                    override fun onItemClickLeek(znak: Znak, position: Int) {
                        if (znak.znak_leek_backGraund == R.drawable.ic_heart__1__1) {
                            znak.znak_leek_backGraund = R.drawable.ic_heart_1
                            myDbHalper.editeZnak(znak)
                            rvAdapter.notifyDataSetChanged()
                        } else if (znak.znak_leek_backGraund == R.drawable.ic_heart_1) {
                            znak.znak_leek_backGraund = R.drawable.ic_heart__1__1
                            myDbHalper.editeZnak(znak)

                            // myDbHalper.deleteZnakLeek(znak)
                            rvAdapter.notifyDataSetChanged()
                        }
                    }

                    override fun onItemClicDelete(znak: Znak, position: Int) {
                        myDbHalper.deleteZnak(znak)
                        listZnaks.remove(znak)
                        rvAdapter.notifyItemRemoved(position)
                        rvAdapter.notifyItemRangeChanged(position,listZnaks.size)
                        //  clearImages()
                    }

                    override fun onItemEditeZnak(znak: Znak, position: Int) {
                        var bundle = Bundle()
                        bundle.putInt("position", position)
                        bundle.putInt("category", param1!!)
                        bundle.putSerializable("znak1",znak)
                        var navOptions = NavOptions.Builder()
                        navOptions.setEnterAnim(R.anim.enter)
                        navOptions.setExitAnim(R.anim.exite)
                        navOptions.setPopEnterAnim(R.anim.pop_enter)
                        navOptions.setPopExitAnim(R.anim.pop_exite)
                        findNavController().navigate(R.id.editeFragment, bundle, navOptions.build())
                    }

                    override fun onItemClickOp(znak: Znak, position: Int) {
                        var bundle = Bundle()
                        bundle.putInt("position", position)
                        bundle.putSerializable("znak",znak)
                        var navOptions = NavOptions.Builder()
                        navOptions.setEnterAnim(R.anim.enter)
                        navOptions.setExitAnim(R.anim.exite)
                        navOptions.setPopEnterAnim(R.anim.pop_enter)
                        navOptions.setPopExitAnim(R.anim.pop_exite)
                        findNavController().navigate(R.id.infoZnakragment, bundle, navOptions.build())
                    }
                },color)
                fragmentZnakBinding.rv.adapter = rvAdapter
            }
            2->{
                rvAdapter = RvAdapter(root.context,listZnaks,object:RvAdapter.OnItemClick{
                    override fun onItemClickLeek(znak: Znak, position: Int) {
                        if (znak.znak_leek_backGraund == R.drawable.ic_heart__1__1) {
                            znak.znak_leek_backGraund = R.drawable.ic_heart_1
                            myDbHalper.editeZnak(znak)
                            rvAdapter.notifyDataSetChanged()
                        } else if (znak.znak_leek_backGraund == R.drawable.ic_heart_1) {
                            znak.znak_leek_backGraund = R.drawable.ic_heart__1__1
                            myDbHalper.editeZnak(znak)

                            // myDbHalper.deleteZnakLeek(znak)
                            rvAdapter.notifyDataSetChanged()
                        }
                    }

                    override fun onItemClicDelete(znak: Znak, position: Int) {
                        myDbHalper.deleteZnak(znak)
                        listZnaks.remove(znak)
                        rvAdapter.notifyItemRemoved(position)
                        rvAdapter.notifyItemRangeChanged(position,listZnaks.size)
                        //clearImages()
                    }

                    override fun onItemEditeZnak(znak: Znak, position: Int) {
                        var bundle = Bundle()
                        bundle.putInt("position", position)
                        bundle.putInt("category", param1!!)
                        bundle.putSerializable("znak1",znak)
                        var navOptions = NavOptions.Builder()
                        navOptions.setEnterAnim(R.anim.enter)
                        navOptions.setExitAnim(R.anim.exite)
                        navOptions.setPopEnterAnim(R.anim.pop_enter)
                        navOptions.setPopExitAnim(R.anim.pop_exite)
                        findNavController().navigate(R.id.editeFragment, bundle, navOptions.build())
                    }

                    override fun onItemClickOp(znak: Znak, position: Int) {
                        var bundle = Bundle()
                        bundle.putInt("position", position)
                        bundle.putSerializable("znak",znak)
                        var navOptions = NavOptions.Builder()
                        navOptions.setEnterAnim(R.anim.enter)
                        navOptions.setExitAnim(R.anim.exite)
                        navOptions.setPopEnterAnim(R.anim.pop_enter)
                        navOptions.setPopExitAnim(R.anim.pop_exite)
                        findNavController().navigate(R.id.infoZnakragment, bundle, navOptions.build())
                    }
                },color)
                fragmentZnakBinding.rv.adapter = rvAdapter
            }
            3->{
                rvAdapter = RvAdapter(root.context,listZnaks,object:RvAdapter.OnItemClick{
                    override fun onItemClickLeek(znak: Znak, position: Int) {
                        if (znak.znak_leek_backGraund == R.drawable.ic_heart__1__1) {
                            znak.znak_leek_backGraund = R.drawable.ic_heart_1
                            myDbHalper.editeZnak(znak)
                            rvAdapter.notifyDataSetChanged()
                        } else if (znak.znak_leek_backGraund == R.drawable.ic_heart_1) {
                            znak.znak_leek_backGraund = R.drawable.ic_heart__1__1
                            myDbHalper.editeZnak(znak)

                            // myDbHalper.deleteZnakLeek(znak)
                            rvAdapter.notifyDataSetChanged()
                        }
                    }

                    override fun onItemClicDelete(znak: Znak, position: Int) {
                        myDbHalper.deleteZnak(znak)
                        listZnaks.remove(znak)
                        rvAdapter.notifyItemRemoved(position)
                        rvAdapter.notifyItemRangeChanged(position,listZnaks.size)
                        //clearImages()
                    }

                    override fun onItemEditeZnak(znak: Znak, position: Int) {
                        var bundle = Bundle()
                        bundle.putInt("position", position)
                        bundle.putInt("category", param1!!)
                        bundle.putSerializable("znak1",znak)
                        var navOptions = NavOptions.Builder()
                        navOptions.setEnterAnim(R.anim.enter)
                        navOptions.setExitAnim(R.anim.exite)
                        navOptions.setPopEnterAnim(R.anim.pop_enter)
                        navOptions.setPopExitAnim(R.anim.pop_exite)
                        findNavController().navigate(R.id.editeFragment, bundle, navOptions.build())
                    }

                    override fun onItemClickOp(znak: Znak, position: Int) {
                        var bundle = Bundle()
                        bundle.putInt("position", position)
                        bundle.putSerializable("znak",znak)
                        var navOptions = NavOptions.Builder()
                        navOptions.setEnterAnim(R.anim.enter)
                        navOptions.setExitAnim(R.anim.exite)
                        navOptions.setPopEnterAnim(R.anim.pop_enter)
                        navOptions.setPopExitAnim(R.anim.pop_exite)
                        findNavController().navigate(R.id.infoZnakragment, bundle, navOptions.build())
                    }
                },color)
                fragmentZnakBinding.rv.adapter = rvAdapter
            }
        }
    }


    fun notify(b:Boolean){
        var listZnaks = ArrayList<Znak>()
        for (i in listZnak) {
            if (i.znak_category_position==param1 && i.znak_category.equals(param2, ignoreCase = true)){
                listZnaks.add(i)
            }
        }
        rvAdapter.list = listZnaks
        rvAdapter.notifyDataSetChanged()
    }

    private fun clearImages() {
        val filesDir = activity?.filesDir
        if (filesDir!!.isDirectory){
            var listFiles = filesDir.listFiles()
            for (i in listFiles) {
                i.delete()
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
         * @return A new instance of fragment ZnakFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1:Int, param2: String) =
            ZnakFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}