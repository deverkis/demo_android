import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

class Cookie(
    val name: String,
    val softBaked:Boolean,
    val hasFilling:Boolean,
    val price:Double
)

val cookies = listOf(
    Cookie(
        name = "Chocolate Chip",
        softBaked = false,
        hasFilling = false,
        price = 1.69
    ),
    Cookie(
        name = "Banana Walnut",
        softBaked = true,
        hasFilling = false,
        price = 1.49
    ),
    Cookie(
        name = "Vanilla Creme",
        softBaked = false,
        hasFilling = true,
        price = 1.59
    ),
    Cookie(
        name = "Chocolate Peanut Butter",
        softBaked = false,
        hasFilling = true,
        price = 1.49
    ),
    Cookie(
        name = "Snickerdoodle",
        softBaked = true,
        hasFilling = false,
        price = 1.39
    ),
    Cookie(
        name = "Blueberry Tart",
        softBaked = true,
        hasFilling = true,
        price = 1.79
    ),
    Cookie(
        name = "Sugar and Sprinkles",
        softBaked = false,
        hasFilling = false,
        price = 1.39
    )
)

@Composable
fun Unit3Test4(){
    Text("Unit3 Test 4")
    test1()
    test2()
    test3()
}
fun test1(){
    //使用 forEach() 循环遍历列表
    //forEach(action: (T) -> Unit)
    cookies.forEach {
        println("Menu item: ${it.name}")
        //Sugar and Sprinkles - $1.39
    }
    //如需访问属性并将其嵌入字符串，您需要一个表达式。您可以用大括号将表达式括住，使其成为字符串模板的一部分。
}

fun test2(){
    //借助 map() 函数，您可以将一个集合转换为元素数量相同的新集合。
    val fullMenu  = cookies.map{
        "${it.name} - $${it.price}"
    }
    fullMenu.forEach {
        println(it)
    }
}

fun test3(){
    //借助 filter() 函数，您可以创建集合的子集
    //添加一个布尔表达式，以检查饼干的 softBaked 属性是否等于 true
    val softBakedMenu  = cookies.filter{
       it.softBaked
    }
    println("Soft cookies:")
    softBakedMenu.forEach {
        println("${it.name} - $${it.price}")
    }
}