import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

class Cookie(
    val name: String,
    val softBaked: Boolean,
    val hasFilling: Boolean,
    val price: Double
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
fun Unit3Test4() {
    Text("Unit3 Test 4")
    test1()
    test2()
    test3()
    test6()
}

fun test1() {
    //使用 forEach() 循环遍历列表
    //forEach(action: (T) -> Unit)
    cookies.forEach {
        println("Menu item: ${it.name}")
        //Sugar and Sprinkles - $1.39
    }
    //如需访问属性并将其嵌入字符串，您需要一个表达式。您可以用大括号将表达式括住，使其成为字符串模板的一部分。
}

fun test2() {
    //借助 map() 函数，您可以将一个集合转换为元素数量相同的新集合。
    val fullMenu = cookies.map {
        "${it.name} - $${it.price}"
    }
    fullMenu.forEach {
        println(it)
    }
}

fun test3() {
    //借助 filter() 函数，您可以创建集合的子集
    //添加一个布尔表达式，以检查饼干的 softBaked 属性是否等于 true
    val softBakedMenu = cookies.filter {
        it.softBaked
    }
    println("Soft cookies:")
    softBakedMenu.forEach {
        println("${it.name} - $${it.price}")
    }
}

fun test4() {
    //groupBy() 函数可用于根据函数将列表转换为映射。

    //传入一个返回 it.softBaked 的 lambda 表达式。返回类型将为 Map<Boolean, List<Cookie>>。

    val groupedMenu = cookies.groupBy { it.softBaked }
    val softBakedMenu = groupedMenu[true] ?: listOf()
    var crunchyMenu = groupedMenu[false] ?: listOf()

    println("Soft cookies:")
    softBakedMenu.forEach {
        println("${it.name} - $${it.price}")
    }

    println("Crunchy cookies:")
    crunchyMenu.forEach {
        println("${it.name} - $${it.price}")
    }
}

fun test5() {
    //fold() 函数用于从集合中生成单个值。

    val totalPrice = cookies.fold(0.0) { total, cookie ->
        total + cookie.price
    }

    println("Total price: $${totalPrice}")
}

fun test6(){
    //sortedBy() 您可以指定一个 lambda 以返回作为排序依据的属性。
    val alphabeticalMenu = cookies.sortedBy {
        it.name
    }
    println("Alphabetical menu:")
    alphabeticalMenu.forEach {
        println(it.name)
    }
}