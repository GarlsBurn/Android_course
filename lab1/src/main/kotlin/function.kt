fun main(){
    val func = fun(param1: Int): Int{
        return param1
    }

    val func2 = { param1: Int, param2: Int -> param1 + param2}

    repeat(2) {
        println(func(2))
    }
    println(func2.invoke(1, 5))

    val list = listOf(1, 5, 6)
    list.forEach{
            print(it)
        }
    }
