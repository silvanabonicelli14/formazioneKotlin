package cgm.com.coroutines

import kotlinx.coroutines.*

fun pippo() = runBlocking {
    val result = withTimeoutOrNull(3000L) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
        "Done" // will get cancelled before it produces this result
    }
    println("Result is $result")
}

//fun cgm.com.testcoroutines.main() = runBlocking {
//    val startTime = System.currentTimeMillis()
//    val job = launch(Dispatchers.Default) {
//        var nextPrintTime = startTime
//        var i = 0
//        while (isActive) { // cancellable computation loop
//            // print a message twice a second
//            if (System.currentTimeMillis() >= nextPrintTime) {
//                println("job: I'm sleeping ${i++} ...")
//                nextPrintTime += 500L
//            }
//        }
//    }
//    delay(1300L) // delay a bit
//    println("cgm.com.testcoroutines.main: I'm tired of waiting!")
//    job.cancelAndJoin() // cancels the job and waits for its completion
//    println("cgm.com.testcoroutines.main: Now I can quit.")
//}

//fun cgm.com.testcoroutines.main() = runBlocking {
//    val job = launch {
//        repeat(1000) { i ->
//            println("job: I'm sleeping $i ...")
//            delay(500L)
//        }
//    }
//    delay(1500L) // delay a bit
//    println("cgm.com.testcoroutines.main: I'm tired of waiting!")
//    job.cancelAndJoin() // cancel and waits for job's completion
//    println("cgm.com.testcoroutines.main: Now I can quit.")
//}

////sampleStart
//fun cgm.com.testcoroutines.main() = runBlocking {
//    repeat(100_000) { // launch a lot of coroutines
//        launch {
//            delay(5000L)
//            print(".")
//        }
//    }
//}
//sampleEnd


//fun cgm.com.testcoroutines.main() = runBlocking {
//    val job = launch { // launch a new coroutine and keep a reference to its Job
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello")
//    job.join() // wait until child coroutine completes
//    println("Done")
//}

// Sequentially executes doWorld followed by "Hello"
//fun cgm.com.testcoroutines.main() = runBlocking {
//    doWorld()
//    println("Done")
//}

// Concurrently executes both sections
suspend fun doWorld() = coroutineScope { // this: CoroutineScope
    launch {
        delay(2000L)
        println("World 2")
    }
    launch {
        delay(1000L)
        println("World 1")
    }
    println("Hello")
}
/*
* Both pieces of code inside launch { ... } blocks execute concurrently, with World 1 printed first,
* after a second from start, and World 2 printed next, after two seconds from start.
* A coroutineScope in doWorld completes only after both are complete, so doWorld returns and allows Done string to be printed only after that
* */