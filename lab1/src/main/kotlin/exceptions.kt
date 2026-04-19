fun throwExceptions(){
    throw IllegalStateException("Exceptions")
}



fun main(){
    try {
        throwExceptions()
    } catch (e: Exception){
        e.printStackTrace()
    } finally {
        println("Finally")
    }
}