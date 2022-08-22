class Framework private constructor(){

    companion object{

        private var instance: Framework?=null

        @JvmStatic
        fun getInstance(){
            instance ?: synchronized(this){
                instance ?: Framework().also { instance = it }
            }
        }

    }


}