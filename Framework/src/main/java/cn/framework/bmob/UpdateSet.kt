package cn.framework.bmob

import cn.bmob.v3.BmobObject

data class UpdateSet(
    var desc: String = "",
    var path: String = "",
    var versionCode: Int = 0
) : BmobObject() {
    override fun toString(): String {
        return "UpdateSet(desc='$desc', path='$path', versionCode=$versionCode)"
    }

}
