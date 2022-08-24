package cn.framework.bmob

import cn.bmob.v3.BmobUser

data class IMUser(
    var tokenPhoto: String = "",
    var tokenNickName: String = "",
    var nickName: String = "",
    var photo: String = "",
    var sex: Boolean = true,
    var desc: String = "",
    var age: Int = 0,
    var birthday: String = "",
    var constellation: String = "",
    var hobby: String = "",
    var status: String = ""

) : BmobUser() {
    override fun toString(): String {
        return "IMUser(tokenPhoto='$tokenPhoto', tokenNickName='$tokenNickName', nickName='$nickName', photo='$photo', sex=$sex, desc='$desc', age=$age, birthday='$birthday', constellation='$constellation', hobby='$hobby', status='$status')"
    }


}
