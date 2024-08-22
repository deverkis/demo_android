package cn.kt66.codelabs1

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

//数组
val rockPlanets = arrayOf<String>("Mercury", "Venus", "Earth", "Mars")

// 使用类型推断
val gasPlanets = arrayOf("Jupiter", "Saturn", "Uranus", "Neptune")

//添加两个数组
val solarSystem = rockPlanets + gasPlanets

@Composable
fun Unit3Test3() {
    //test1()
    Text("Unit3Test3")
    test2()
}

fun test1(){
    //访问数组中的元素
    println(solarSystem[0])
    println(solarSystem[1])
    //还可以按索引设置数组元素的值。
    solarSystem[3] = "Little Earth"
    println(solarSystem[3])

    //我们尝试将 Pluto 添加到 solarSystem 数组。在索引 8 处添加 Pluto，因为它是数组中的第 9 个元素。  它会抛出 ArrayIndexOutOfBounds 异常

    //您想增加数组的现有大小,则需要创建一个新数组。
    val newSolarSystem = solarSystem + arrayOf("Pluto")
    println(newSolarSystem[8])
}

fun test2(){
//列表是有序且可调整大小的集合，

    //List 是一个接口，用于定义与只读有序项集合相关的属性和方法。
    //MutableList 通过定义修改列表的方法（例如添加和移除元素）来扩展 List 接口。
    val solarSystem = listOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
    //List 具有 size 属性，用于获取列表中的元素数量。
    println(solarSystem.size)
    //访问列表中的元素
    // 1 使用下标语法输出索引 2 处的行星。
    println(solarSystem[2])
    // 2 通过对 solarSystem 列表调用 get()，输出索引 3 处的元素。
    println(solarSystem.get(2))
    //indexOf() 方法搜索特定元素的索引
    println(solarSystem.indexOf("Earth"))//2
    println(solarSystem.indexOf("Pluto"))// -1

    //使用 for 循环遍历列表元素
    for(planet in solarSystem){
        println(planet) //Mercury...
    }

    //向列表中添加元素
    //更改为调用 mutableListOf()，而不是 listOf()。
    val list2 = mutableListOf("Mercury", "Venus", "Earth", "Mars", "Jupiter")
    list2.add("Pluto")
    //有些科学家提出一种理论：过去曾有一颗名为 Theia 的行星，该行星后来与地球发生碰撞并形成月球。在索引 3（介于 "Earth" 和 "Mars" 之间）处插入 "Theia"。
    list2.add(3,"Theia")

    //更新特定索引处的元素
    list2[3] = "Future Moon"

    //从列表中移除元素
    list2.removeAt(2) // 移除索引2的
    list2.remove("Future Moon") // 如果找到匹配元素，则会将其移除。
    //List 可提供 contains() 方法，该方法可在列表中存在某个元素时返回 Boolean
    println(list2.contains("Future Moon")) //false
    //更简洁的语法是使用 in 运算符。您可以使用元素、in 运算符和集合来检查该元素是否在列表中。
    println("Future Moon" in list2) // false

}

fun test3(){
    //集是指没有特定顺序且不允许出现重复值的集合。
    val solarSystem = mutableSetOf("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
    //使用 size 属性输出该集的大小。
    println(solarSystem.size)
    //与 MutableList 类似，MutableSet 具有 add() 方法。
    solarSystem.add("Pluto")
        //contains() 函数接受一个参数，并检查该集中是否包含指定的元素。如果是，则返回 true。
    println(solarSystem.contains("Pluto"))

    //remove() 函数接受一个参数，并从集中移除指定的元素。
    solarSystem.remove("Pluto")
    //请记住，集是无序的集合。您无法按索引从集中移除某个值，因为集没有索引
}

fun test4(){
    //Map 是由键和值组成的集合。之所以称之为映射，
    //映射的键具有唯一性，但映射的值不具有唯一性。
    //首先是键，后跟 to 运算符，而后是值。每个键值对均以英文逗号隔开。
    val solarSystem = mutableMapOf(
        "Mercury" to 0,
        "Venus" to 0,
        "Earth" to 1,
        "Mars" to 2,
    )
    //与列表和集一样，Map 提供包含键值对数量的 size 属性。输出 solarSystem 映射的大小。
    println(solarSystem.size)
    //您可以使用下标语法设置其他键值对。将 "Pluto" 键的值设置为 5。
    solarSystem["Pluto"] = 5
    //您可以使用下标语法来获取值。输出键 "Pluto" 的卫星数量。
    println(solarSystem["Pluto"])
    //您还可以使用 get() 方法访问值。
    println(solarSystem.get("Theia")) // null
    //remove() 方法可移除具有指定键的键值对。
    solarSystem.remove("Pluto")
}
