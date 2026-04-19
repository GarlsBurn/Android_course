fun main(){
    val clazz = DemoClass("Яблоко", "Груша", "Вишня")
    clazz.printParam()
    val demo: IDemoMutable = Demo()
    println(demo.list)
    demo.mutate()
    println(demo.list)
}

class DemoClass(val param1: String, val param2:String, param3: String){
    val changeParam3 = param3.reversed()
    init {
        println("Создан класс DemoClass с параметрами $param1, $param2, $param3.")
    }

    fun printParam() {
        println("Параметры класса $param1, $param2, $changeParam3.")
    }
}

interface IDemo {
    val list: List<Int>
}

interface IDemoMutable: IDemo {
    fun mutate()
}

abstract class ADemo: IDemo {
    override val list = mutableListOf(1, 2, 3)
}

class Demo: ADemo(), IDemoMutable {
    override fun mutate() {
        list.add(4)
    }
}