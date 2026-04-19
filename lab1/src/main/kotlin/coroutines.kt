import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.delay


fun main() = runBlocking{
        printWithDelay()
}

suspend fun printWithDelay(){
    repeat(5) {
        delay(500)
        println("Вывод с задержкой $it")
    }
}