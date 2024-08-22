import androidx.compose.runtime.Composable


//重构代码以使用泛型
class Question<T>(val questionText: String, val answer: T, val difficulty: Difficulty)

//使用单例对象


//伴生对象
//将 StudentProgress 设为 Quiz 类的伴生对象。
class Quiz : ProgressPrintable {
    override val progressText: String
        get() = "${answered} of ${total} answered"

    override fun printProgressBar() {
        repeat(Quiz.answered) {
            print("▓")
        }
        repeat(Quiz.total - Quiz.answered) {
            print("▒")
        }
        println()
        println(progressText)
    }

    fun printQuiz(){
//        println(q1.questionText)
////        println(q1.answer)
        //使用 let() 替换过长的对象名称
        q1.let {
            println(it.questionText)
            println(it.answer)
        }
    }

    val q1 = Question<String>("a", "b", Difficulty.HARD)
    val q2 = Question<Boolean>("bb", false, Difficulty.EASY)

    //使用 companion 关键字标记 StudentProgress 对象。
    companion object StudentProgress {
        var total: Int = 10
        var answered: Int = 3
    }
}

//定义一个名为 progressText 且类型为 String 的 Quiz.StudentProgress 扩展属性。
//val Quiz.StudentProgress.progressText:String
//    get()="${answered} of ${total} answered"

//fun Quiz.StudentProgress.printProgressBar(){
//    repeat(Quiz.answered){
//        print("▓")
//    }
//    repeat(Quiz.total - Quiz.answered) {
//        print("▒")
//    }
//    println()
//    println(Quiz.progressText)
//}


//使用枚举常量
enum class Difficulty {
    EASY, MEDIUM, HARD
}

//使用接口重写扩展函数
interface ProgressPrintable {
    val progressText: String
    fun printProgressBar()
}
@Composable
fun Unit3Test1() {
    //println("${Quiz.answered} of ${Quiz.total} answered")
    //println(Quiz.progressText)
    //Quiz.printProgressBar()
    Quiz().printProgressBar()
    val quiz = Quiz()
    quiz.printQuiz()
    //使用 apply() 在没有变量的情况下调用对象方法
    Quiz().apply {
        printQuiz()
    }
}