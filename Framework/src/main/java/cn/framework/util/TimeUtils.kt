package cn.framework.util

class TimeUtils {

    companion object {

        @JvmStatic
        fun formatTime(ms:Long):String{
            val hours = (ms%(1000*60*60*24))/(1000*60*60)
            val minutes = (ms%(1000*60*60))/(1000*60)
            val seconds = (ms%(1000*60))/(1000)
            val h = if (hours<10){
                "0$hours"
            }else{
                "$hours"
            }
            val m = if (minutes<10){
                "0$minutes"
            }else{
                "$minutes"
            }
            val s = if (seconds<10){
                "0$seconds"
            }else{
                "$seconds"
            }
            return "${h} : ${m} :  $s"
        }

    }

}