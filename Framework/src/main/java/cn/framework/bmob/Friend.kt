package cn.framework.bmob

import cn.bmob.v3.BmobObject

data class Friend(
    var user: IMUser? = null,
    var friendUser: IMUser? = null
) : BmobObject() {
    override fun toString(): String {
        return "Friend(user=$user, friendUser=$friendUser)"
    }

}
