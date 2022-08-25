package cn.framework.bmob

import android.content.Context
import cn.bmob.v3.Bmob
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobSMS
import cn.bmob.v3.BmobUser
import cn.bmob.v3.datatype.BmobFile
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.*
import java.io.File

const val BMOB_SDK_ID = "b0ac56a8f8dd1d9889c8851845134422"
const val BMOB_NEW_DOMAIN = "bmob_new_domain"

class BmobManager private constructor() {

    companion object {

        private object SingletonHolder {
            val INSTANCE = BmobManager()
        }

        @JvmStatic
        fun getInstance(): BmobManager {
            return SingletonHolder.INSTANCE
        }

    }

    fun initBmob(context: Context) {
//        Bmob.resetDomain(BMOB_NEW_DOMAIN)
//        Bmob.initialize(context, BMOB_SDK_ID)
    }

    fun isLogin() = BmobUser.isLogin()

    /**
     * 获取本地对象
     */
    fun getUser() = BmobUser.getCurrentUser(IMUser::class.java)

    /**
     * 同步控制台信息至本地缓存
     */
    fun fetchUserInfo(listener: FetchUserInfoListener<BmobUser>) = BmobUser.fetchUserInfo(listener)

    /**
     * 发送短信验证码
     */
    fun requestSMS(phone: String, listener: QueryListener<Int>) =
        BmobSMS.requestSMSCode(phone, "", listener)

    /**
     * 通过手机号码注册或登录
     */
    fun signOrLoginByPhoneNO(phone: String, code: String, listener: LogInListener<IMUser>) =
        BmobUser.signOrLoginByMobilePhone(phone, code, listener)

    /**
     * 通过账号密码登录
     */
    fun loginByAccount(userName: String, pw: String, listener: SaveListener<IMUser>) {
        val imUser = IMUser()
        imUser.username = userName
        imUser.setPassword(pw)
        imUser.login(listener)
    }

    /**
     * 上传头像
     */
    fun uploadFirstPhoto(nickName: String, file: File, listener: UploadPhotoListener) {
        val imUser = getUser()

        val bmobFile = BmobFile(file)
        bmobFile.uploadblock(object : UploadFileListener() {
            override fun done(e: BmobException?) {
                if (e == null) {
                    imUser.apply {
                        this.nickName = nickName
                        this.photo = bmobFile.fileUrl
                        this.tokenNickName = nickName
                        this.tokenPhoto = bmobFile.fileUrl
                    }.update(object : UpdateListener() {
                        override fun done(e: BmobException?) {
                            if (e == null) {
                                listener.uploadPhotoSuccess()
                            } else {
                                listener.uploadPhotoFailure(e)
                            }
                        }
                    })
                } else {
                    listener.uploadPhotoFailure(e)
                }
            }

        })

    }

    /**
     * 根据手机号查询用户
     */
    fun queryPhoneUser(phone: String, listener: FindListener<IMUser>) {
        baseQuery("mobilePhoneNumber", phone, listener)
    }

    /**
     * 根据objectId查询用户
     */
    fun queryObjectIdUser(objectId: String, listener: FindListener<IMUser>) {
        baseQuery("objectId", objectId, listener)
    }

    fun queryMyFriend(listener: FindListener<Friend>) {
        BmobQuery<Friend>().apply {
            addWhereEqualTo("user", getUser())
            findObjects(listener)
        }
    }

    fun queryAllUser(listener: FindListener<IMUser>) {
        BmobQuery<IMUser>().apply {
            findObjects(listener)
        }
    }

    fun queryAllSquare(listener: FindListener<SquareSet>) {
        BmobQuery<SquareSet>().apply {
            setLimit(500)
        }.findObjects(listener)
    }

    fun queryPrivateSet(listener: FindListener<PrivateSet>) {
        BmobQuery<PrivateSet>().apply {
            findObjects(listener)
        }
    }

    fun queryFateSet(listener: FindListener<FateSet>) {
        BmobQuery<FateSet>().apply {
            findObjects(listener)
        }
    }

    fun baseQuery(key: String, value: String, listener: FindListener<IMUser>) {
        BmobQuery<IMUser>().apply {
            addWhereEqualTo(key, value)
        }.findObjects(listener)
    }

    fun addFriend(user: IMUser, listener: SaveListener<String>) {
        Friend().apply {
            this.user = getUser()
            friendUser = user
        }.save(listener)
    }

    fun addPrivateSet(listener: SaveListener<String>) {
        PrivateSet().apply {
            userId = getUser().objectId
            phone = getUser().mobilePhoneNumber
        }.save(listener)
    }

    fun addFateSet(listener: SaveListener<String>) {
        FateSet().apply {
            userId = getUser().objectId
        }.save(listener)
    }

    fun delFateSet(id: String, listener: UpdateListener) {
        FateSet().apply {
            objectId = id
        }.delete(listener)
    }

    fun delPrivateSet(id: String, listener: UpdateListener) {
        PrivateSet().apply {
            objectId = id
        }.delete(listener)
    }

    fun pushSquare(mediaType: Int, text: String, path: String, listener: SaveListener<String>) {
        SquareSet().apply {
            userId = getUser().objectId
            pushTime = System.currentTimeMillis()
            this.text = text
            mediaUrl = path
            pushType = mediaType
        }.save(listener)
    }

    fun addFriend(id: String, listener: SaveListener<String>) {
        queryObjectIdUser(id, object : FindListener<IMUser>() {
            override fun done(users: MutableList<IMUser>?, e: BmobException?) {
                if (e == null) {
                    if (!users.isNullOrEmpty()) {
                        addFriend(users[0], listener)
                    }
                }
            }
        })
    }

    fun deleteFriend(id: String, listener: UpdateListener) {
        queryMyFriend(object : FindListener<Friend>() {
            override fun done(friends: MutableList<Friend>?, e: BmobException?) {
                if (e == null) {
                    friends?.forEach { friend ->
                        if (friend.objectId.equals(id)) {
                            Friend().apply {
                                objectId = friend.objectId
                            }.delete(listener)
                        }
                    }
                }
            }
        })
    }

    fun addUpdateSet(versionCode: Int, path: String, desc: String) {
        UpdateSet().apply {
            this.versionCode = versionCode
            this.path = path
            this.desc = desc
        }.save(object : SaveListener<String>() {
            override fun done(s: String?, e: BmobException?) {

            }
        })
    }

    /**
     * 查询更新
     */
    fun queryUpdateSet(listener: FindListener<UpdateSet>) {
        val bmobQuery = BmobQuery<UpdateSet>()
        bmobQuery.findObjects(listener)
    }


}