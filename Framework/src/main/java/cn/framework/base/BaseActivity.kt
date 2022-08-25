package cn.framework.base

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity

const val PERMISSION_REQUEST_CODE = 1000
const val PERMISSION_WINDOW_REQUEST_CODE = 1001

open class BaseActivity(): AppCompatActivity() {

    private val mStrPermissions = arrayOf(Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA,
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.CALL_PHONE,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    private var mPermissions = mutableListOf<String>()
    private var mNoPermissions = mutableListOf<String>()

    private lateinit var mPermissionsResult: OnPermissionsResult

    protected fun request(result: OnPermissionsResult){
        if(!checkAllPermissions()){
            requestAllPermissions(result)
        }
    }

    protected fun checkPermissions(permissions:String) =
        checkSelfPermission(permissions) == PackageManager.PERMISSION_GRANTED

    protected fun checkAllPermissions():Boolean{
        mPermissions.clear()
        mStrPermissions.forEach { permission->
            val checkResult = checkPermissions(permission)
            if (!checkResult){
                mPermissions.add(permission)
            }
        }
        return mPermissions.isNotEmpty()
    }

    protected fun requestPermission(permissions: Array<String>){
        requestPermissions(permissions,PERMISSION_REQUEST_CODE)
    }

    protected fun requestAllPermissions(permissionsResult: OnPermissionsResult){
        mPermissionsResult = permissionsResult
        requestPermission(mStrPermissions)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        mNoPermissions.clear()
        if (requestCode == PERMISSION_REQUEST_CODE){
            if (grantResults.isNotEmpty()){
                grantResults.forEachIndexed { index, permission ->
                    if (permission == PackageManager.PERMISSION_DENIED){
                        mNoPermissions.add(permissions[index])
                    }
                }
                if(mNoPermissions.isEmpty()){
                   mPermissionsResult.onSuccess()
                }else{
                   mPermissionsResult.onFailure(mNoPermissions)
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    protected interface OnPermissionsResult {
        fun onSuccess()
        fun onFailure(noPermissions:MutableList<String>)
    }

    protected fun checkWindowPermissions() = Settings.canDrawOverlays(this)

    protected fun requestWindowPermissions(){
        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
        startActivityForResult(intent, PERMISSION_WINDOW_REQUEST_CODE)
    }

}