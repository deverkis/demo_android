import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Unit3Test15() {
    Text("Unit 3 Test 1.5")
    test15()
}

data class Event(
    val title: String,
    val description: String? = null,
    val daypart: Daypart,
    val durationInMinutes: Int,
)


fun test15() {
    val test2 = mutableListOf(
        Event(
            title = "Wake up",
            description = "Time to get up",
            daypart = Daypart.MORNING,
            durationInMinutes = 0
        ),
        Event(
            title = "Eat breakfast",
            daypart = Daypart.MORNING,
            durationInMinutes = 15
        ),
        Event(
            title = "Learn about Kotlin",
            daypart = Daypart.AFTERNOON,
            durationInMinutes = 30
        ),
        Event(title = "Practice Compose", daypart = Daypart.AFTERNOON, durationInMinutes = 60),
        Event(
            title = "Watch latest DevBytes video",
            daypart = Daypart.AFTERNOON,
            durationInMinutes = 10
        ),
        Event(
            title = "Check out latest Android Jetpack library",
            daypart = Daypart.EVENING,
            durationInMinutes = 45
        )
    )
    val test3 = test2.filter {
        it.durationInMinutes < 5
    }
    println("You have ${test3.size} short events.")

    val test4 = test2.groupBy { it.daypart }
    test4.forEach { (day, events) ->
        println("$day:${events.size} events")
    }

    //println("Last event of the day: ${test2[test2.size-1].title}")
    println("Last event of the day: ${test2.last().title}")

    println("${test2.last().durationEvent}")

}

//解决这种重复问题
val Event.durationEvent: String
    get() = if (this.durationInMinutes < 60) {
        "short"
    } else {
        "long"
    }

enum class Daypart {
    MORNING, AFTERNOON, EVENING
}