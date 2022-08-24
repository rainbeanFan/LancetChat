package cn.framework.bmob

import cn.bmob.v3.exception.BmobException

interface UploadPhotoListener {

    fun uploadPhotoSuccess()

    fun uploadPhotoFailure(e: BmobException)

}