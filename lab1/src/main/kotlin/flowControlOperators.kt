fun main(){
    val x = 0
    if (x == 0){
        println("x = $x - Ноль")
    } else if(x < 0){
        println("Отрицательное")
    } else println("Положительное")


    when{
        x == 0 -> println("x = $x - Ноль результат с when")
        x < 0 -> println("Отрицательное результат с when")
        else -> println("Положительное результат с when")
    }

    if (x == 1 || x == 2){
        println("Один или два")
    }
    when{
        !(x % 3 == 0 && x < 0) -> println("Неотрицательное или меньше нуля")
    }

    var counter = 0
    while (counter < 3){
        println("Counter = $counter")
        counter++
    }

    for (i in 1..3){
        println("Counter $i")
    }
    
}