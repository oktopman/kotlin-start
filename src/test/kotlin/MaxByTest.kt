import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MaxByTest {

    @Test
    fun `collection maxby 함수 테스트`() {
        val people = listOf(Person("이름1", 20), Person("이름3", 24))
        val person = people.maxByOrNull { it.age }
        assertEquals(person?.name, "이름3")
    }
}

data class Person(val name: String, val age: Int = 1)