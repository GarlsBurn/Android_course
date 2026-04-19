fun main(){
    val string: String? = null
    val x: Int? = null
    plusTwo(x)
    val b: Int? = string as? Int
}

fun plusTwo(x:Int?){
    x?.let { println(it + 2) }
}