package cn.kt66.codelabs1.data

interface Engine {
    fun start()
}

class ElectricEngine : Engine {
    override fun start() {
        println("ElectricEngine started")
    }
}

class Car(private val engine: Engine) {
    fun start() {
        engine.start()
    }
}

fun main() {
    val engine = ElectricEngine()
    //传入所需对象的过程称为依赖项注入 (DI)。这种方法也称为控制反转。
//依赖项注入是指在运行时提供依赖项，而不是将其硬编码到调用类中。
    val car = Car(engine)
    car.start()

}

