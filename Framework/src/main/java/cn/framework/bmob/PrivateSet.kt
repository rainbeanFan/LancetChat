package cn.framework.bmob

import cn.bmob.v3.BmobObject

data class PrivateSet(
    var userId: String = "",
    var phone: String = ""
) : BmobObject() {
    override fun toString(): String {
        return "PrivateSet(userId='$userId', phone='$phone')"
    }

}
