package cgm.com.designpatterns

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ObserverPatternTest2 {
    @Test
    internal fun name() {
        val e = MyEvent<TestArgs>()
        var myArgsTestArgs: TestArgs? = null

        e.addListener{
            args: TestArgs ->
            myArgsTestArgs = args
        }
        val actualArgs = TestArgs(0)
        e.invoke(actualArgs)
        myArgsTestArgs shouldBe actualArgs
    }
}

class TestArgs(val x: Int)

class MyEvent<T> {
    val listeners = mutableListOf<(T) -> Unit>()

    fun addListener(listener: (T) -> Unit) {
        listeners.add(listener)
    }

    operator fun plus(listener: (T) -> Unit){
        addListener(listener)
    }

    fun invoke(args: T) {
        listeners.forEach {it(args)}
    }
}