package cgm.com.coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

var acquired = 0

class Resource {
    init { acquired++ } // Acquire the resource
    fun close() { acquired-- } // Release the resource
}

fun main() {
    runBlocking {
        val time = measureTimeMillis {
            val one = async {doSomethingUsefulOne()}
            val two = async {doSomethingUsefulTwo()}
            println("The answer is ${one.await() + two.await()}")
        }
        println("Completed in $time ms")
    }
    // Outside of runBlocking all coroutines have completed
    println(acquired) // Print the number of resources still acquired
}

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}