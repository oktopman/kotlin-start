import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CaptureTest {

    @Test
    fun `람다 외부의 변수의 값을 변경할 수 있다`() {
        var count = 0
        run {
            count++
        }
        assertEquals(count, 1)

    }

    @Test
    fun `람다에서 외부의 변수 값을 변경할수 있는 이유는 변경 가능한 변수를 필드로 하는 클래스를 객체화 하여 사용하는 것이다`() {
        var counter = Ref(0)
        run {
            counter.value++
        }
        assertEquals(counter.value, 1)
    }
}

data class Person(val name: String, val age: Int = 1)

class Ref<T>(var value: T)