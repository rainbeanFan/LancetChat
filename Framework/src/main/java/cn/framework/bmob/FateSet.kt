package cn.framework.bmob

import cn.bmob.v3.BmobObject

data class FateSet(
    var userId: String = ""
) : BmobObject() {
    override fun toString(): String {
        return "FateSet(userId='$userId')"
    }

}
