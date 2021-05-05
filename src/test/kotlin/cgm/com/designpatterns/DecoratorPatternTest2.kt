package cgm.com.designpatterns

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DecoratorPatternTest2 {
    @Test
    internal fun `print hello person`() {
        val world = MyPrintHello()
        world.printEng("Bob") shouldBe "Hello Bob"
    }

    @Test
    internal fun `print hello person to lower case`() {
        val world = PrintAllToLower(MyPrintHello())
        world.printEng("Bob") shouldBe "hello bob"
    }

    @Test
    internal fun `print hello person to upper case`() {
        val world = PrintAllToUpper(MyPrintHello())
        world.printEng("Bob") shouldBe "HELLO BOB"
    }
    @Test
    internal fun `print hello person only person name to upper case`() {
        val world = PrintNameToUpperCase(MyPrintHello())
        world.printEng("Bob") shouldBe "Hello BOB"
    }

    @Test
    internal fun `print hello person only person name to lower case`() {
        val world = PrintNameToLowerCase(MyPrintHello())
        world.printEng("Bob") shouldBe "Hello bob"
    }

    @Test
    internal fun `PrintNameToLowerCase hello person ITA `() {
        val world = PrintNameToLowerCase(MyPrintHello())
        world.printIta("Bob") shouldBe "Ciao Bob"
    }

    @Test
    internal fun `MyPrintHello hello person ITA `() {
        val world = MyPrintHello()
        world.printIta("Bob") shouldBe "Ciao Bob"
    }
}

class PrintNameToLowerCase(private val printHello: PrintEng): PrintEng by printHello  {
    override fun printEng(personName: String): String {
        return  printHello.printEng(personName)
            .split(" ")
            .joinToString(" ") { if (it == personName) it.toLowerCase() else it }
    }
}

class PrintNameToUpperCase(private val printHello: PrintEng): PrintEng by printHello {
    override fun printEng(personName: String): String {
        return  printHello.printEng(personName)
                .split(" ")
                .joinToString(" ") { if (it == personName) it.toUpperCase() else it }
    }
}

class PrintAllToUpper(private val printHello: PrintEng) : PrintEng by printHello {
    override fun printEng(personName: String): String {
        return printHello.printEng(personName).toUpperCase()
    }
}

class PrintAllToLower(private val printHello: PrintEng): PrintEng by printHello{
    override fun printEng(personName: String): String {
        return printHello.printEng(personName).toLowerCase()
    }
}

class MyPrintHello: PrintEng {
    override fun printEng(personName: String): String {
        return "Hello $personName"
    }

    override fun printIta(personName: String): String {
        return "Ciao $personName"
    }
}

interface PrintEng{
    fun printEng(personName: String): String
    fun printIta(personName: String): String
}
