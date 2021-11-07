package com.uz.yolharakatiqoidalari

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.uz.yolharakatiqoidalari.databinding.ActivityMainBinding
import com.github.florent37.runtimepermission.kotlin.askPermission
import com.github.florent37.runtimepermission.kotlin.coroutines.experimental.askPermission
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class MainActivity : AppCompatActivity() {
    var bool: Boolean = false
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        var controller = findNavController(R.id.fragment)
        askPermission(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE) {
            bool = false
            activityMainBinding.bottomNavigation.setOnNavigationItemSelectedListener {
                var id = it.itemId
                when (id) {
                    R.id.home1 -> {
                        controller.navigate(R.id.menuFragment)
                    }
                    R.id.leek -> {
                        controller.navigate(R.id.leekFragment)
                    }
                    R.id.info -> {
                        controller.navigate(R.id.infoFragment)
                    }
                }
                true
            }
        }.onDeclined {
            if (it.hasDenied()) {
                AlertDialog.Builder(this).setMessage("Iltimos dostup bering yo`qsa sizga yordam bera olmayman")
                    .setCancelable(false)
                    .setPositiveButton("Ok") { dialog, which ->
                        it.askAgain()
                    }.setNegativeButton("No") { dialog, which ->
                        dialog.dismiss()
                    }.show()
            }
            if (it.hasForeverDenied()) {
                AlertDialog.Builder(this).setMessage("Iltimos dostup bering yo`qsa sizga yordam bera olmayman")
                    .setCancelable(false)
                    .setPositiveButton("Ok") { dialog, which ->
                        bool = true
                        it.goToSettings()
                    }.show()
            }

        }


//        Dexter.withContext(this)
//            .withPermissions(
//                Manifest.permission.CAMERA,
//                Manifest.permission.READ_EXTERNAL_STORAGE
//            ).withListener(object : MultiplePermissionsListener {
//                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
//
//                }
//                override fun onPermissionRationaleShouldBeShown(
//                    p0: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
//                    p1: PermissionToken?
//                ) {
//    //                    AlertDialog.Builder(this@MainActivity).setMessage("Iltimos dostup bering yo`qsa sizga yordam bera olmayman")
//    //                        .setPositiveButton("Ok") { dialog, which ->
//    //                            report.askAgain()
//    //                        }.setNegativeButton("No") { dialog, which ->
//    //                            dialog.dismiss()
//    //                        }.show()
//                }
//            }).check()

    }


    override fun onResume() {
        super.onResume()
        if (bool) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                var controller = findNavController(R.id.fragment)
                activityMainBinding.bottomNavigation.setOnNavigationItemSelectedListener {
                    var id = it.itemId
                    when (id) {
                        R.id.home1 -> {
                            controller.navigate(R.id.menuFragment)
                        }
                        R.id.leek -> {
                            controller.navigate(R.id.leekFragment)
                        }
                        R.id.info -> {
                            controller.navigate(R.id.infoFragment)
                        }
                    }
                    true
                }
            } else {
                AlertDialog.Builder(this).setMessage("Iltimos dostup bering yo`qsa sizga yordam bera olmayman")
                    .setCancelable(false)
                    .setPositiveButton("Ok") { dialog, which ->
                        val fragmentActivity: FragmentActivity = this
                        if (fragmentActivity != null) {
                            fragmentActivity.startActivity(
                                Intent(
                                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                    Uri.fromParts("package", fragmentActivity.packageName, null)
                                )
                            )
                        }
                    }.show()
            }
        }

    }
//    override fun onBackPressed() {
//        if(activityMainBinding.bottomNavigation.selectedItemId==R.id.home1){
//            super.onBackPressed()
//            finish()
//        }else{
//            activityMainBinding.bottomNavigation.selectedItemId = R.id.home1
//        }
//    }
}