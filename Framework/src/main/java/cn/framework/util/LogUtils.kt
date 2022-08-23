package cn.framework.util

import android.util.Log
import cn.framework.BuildConfig
import java.io.*
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*


class LogUtils private constructor(){


    companion object{

        private val mSimpleFormatter = SimpleDateFormat("YYYY-MM-DD HH:MM:ss")

        @JvmStatic
        fun getInstance(): LogUtils {
            return SingletonHolder.holder
        }

        @JvmStatic
        fun i(str:String){
            if (BuildConfig.LOG_DEBUG){
                if (str.isNotEmpty()){
                    Log.i(BuildConfig.LOG_TAG, "info:  $str")
                    writeToFile(str)
                }
            }
        }

        @JvmStatic
        fun e(str:String){
            if (BuildConfig.LOG_DEBUG){
                if (str.isNotEmpty()){
                    Log.e(BuildConfig.LOG_TAG, "error  :$str")
                    writeToFile(str)
                }
            }
        }

        @JvmStatic
        fun writeToFile(str: String){
            val fileName = "/sdcard/LancetChat/Chat.log"
            val log = mSimpleFormatter.format(Date())+"   "+str+"\n"

            val fileParent = File("/sdcard/LancetChat/")

            if (!fileParent.exists()) fileParent.mkdirs()

            var fos:FileOutputStream?=null
            var bufferWriter:BufferedWriter?=null
            try {
                fos = FileOutputStream(fileName,true)
                bufferWriter = BufferedWriter(
                    OutputStreamWriter(fos,Charset.forName("gbk"))
                )
                bufferWriter.write(log)
            }catch (e:FileNotFoundException){
                e.printStackTrace()
            }catch (e:IOException){
                e.printStackTrace()
            }finally {
                try {
                    bufferWriter?.close()
                }catch (e:IOException){
                    e.printStackTrace()
                }
            }

        }

    }

    private object SingletonHolder{
        val holder = LogUtils()
    }

}