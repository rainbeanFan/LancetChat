package cn.framework.bmob

import cn.bmob.v3.BmobObject

const val PUSH_TEXT = 0
const val PUSH_IMAGE = 1
const val PUSH_MUSIC = 2
const val PUSH_VIDEO = 3

data class SquareSet(
    var pushType: Int = PUSH_TEXT,
    var userId: String = "",
    var pushTime: Long = 0,
    var text: String = "",
    var mediaUrl: String = "",
) : BmobObject() {
    override fun toString(): String {
        return "SquareSet(pushType=$pushType, userId='$userId', pushTime=$pushTime, text='$text', mediaUrl='$mediaUrl')"
    }

}
