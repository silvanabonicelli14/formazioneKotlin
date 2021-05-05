package cgm.com.designpatterns

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.properties.Delegates

class ObserverPatternTest {

    private val outputStreamCaptor = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @Test
    internal fun `print text changed`() {
        var observableObject = ObservableObject(PrintTextChangedListener())
        observableObject.text = "Hello"
        outputStreamCaptor.toString().trim() shouldBe "text is changedto: Hello"
    }
}

interface ValueChangeListener{
    fun onValueChanged(newValue: String)
}

class PrintTextChangedListener: ValueChangeListener{
    override fun onValueChanged(newValue: String) {
        println("text is changedto: $newValue")
    }
}
class ObservableObject(listener: ValueChangeListener){
    var text : String by Delegates.observable(
        initialValue = "",
        onChange = {
            prop, old,new -> listener.onValueChanged(new)
        }
    )
}