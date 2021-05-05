package cgm.com.designpatterns

import io.kotest.matchers.*
import org.junit.jupiter.api.Test

class DecoratorPatternTests {
    @Test
    internal fun `should render the window`() {
        val window = MyWindow2()
        window.render() shouldBe """
             ************
             *          *
             *          *
             *          *
             ************
         """.trimMargin()
    }

    @Test
    internal fun `should render the window with a status bar`() {
        val window = MyStatusBar(MyWindow2())
        window.render() shouldBe """
             ************
             *          *
             *          *
             *          *
             ************
             ............
             ............
         """.trimMargin()
    }

    @Test
    internal fun `should render the window with a status bar and a scroll bar`() {
        val window = MyScrollBar(MyStatusBar(MyWindow2()))
        window.render() shouldBe """
             ************||
             *          *||
             *          *||
             *          *||
             ************||
             ............||
             ............||""".trimMargin()
    }

    @Test
    internal fun `should render the window with a scrollbar`() {
        val window = MyScrollBar(MyWindow2())
        window.render() shouldBe """
             ************||
             *          *||
             *          *||
             *          *||
             ************||""".trimMargin()
    }

    @Test
    internal fun `should render the window with a scroll bar and a status bar`() {
        val window = MyStatusBar(MyScrollBar(MyWindow2()))
        window.render() shouldBe """
             ************||
             *          *||
             *          *||
             *          *||
             ************||
             ............
             ............""".trimMargin()
    }

}

//class MyWindowWithStatusAndScrollBar : MyWindowWithStatus() {
//    override fun render(): String {
//        return super.render()
//            .split("\n")
//            .joinToString("\n") { "$it||" }
//    }
//}
//
//open class MyWindowWithStatus: MyWindow() {
//    override fun render(): String {
//        return """${super.render()}
//             ............
//             ............""".trimMargin()
//    }
//}

//open class MyWindow {
//    open fun render(): String = """
//             ************
//             *          *
//             *          *
//             *          *
//             ************
//         """.trimMargin()
//}

class MyWindow2 : Window {

    private val w: MutableList<Window> = mutableListOf()

    override fun render(): String = """
             ************
             *          *
             *          *
             *          *
             ************
         """.trimMargin()

    override fun add(window: Window) {
        w.add(window)
    }
}

class MyScrollBar(private val window: Window): Window by window {
    override fun render(): String {
        return window.render()
            .split("\n")
            .joinToString("\n") { "$it||" }
    }
    // Invece che fare override -->  Window by window  //eseque implicitamente fowards
    // override fun add(window: Window) =  window.add(window)
}

class MyStatusBar(private val window: Window): Window by window {
    override fun render(): String {
        return """${window.render()}
             ............
             ............""".trimMargin()
    }

}

interface Window {
    fun render(): String
    fun add(window: Window)
}

infix fun Any.ciao(x:Any) :Any = TODO("") //un estension method su un argomento solo puo' diventare un infix
//per usarlo: "1" ciao 2 equivale a "1".ciao(2)
//fun Any.ciao(x:Any) :Any = TODO("")