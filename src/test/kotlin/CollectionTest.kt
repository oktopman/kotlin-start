import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CollectionTest {

    @Test
    fun `collection maxby 함수 테스트`() {
        val people = listOf(Person("이름1", 20), Person("이름3", 24))
        val person = people.maxByOrNull { it.age }
        assertEquals(person?.name, "이름3")
    }

    @Test
    fun `map filter`() {
        val num = 2
        val numbers = mapOf(1 to "one", 2 to "two")
        val number = numbers.filterKeys { it == num }
        assertEquals(number.size, 1)
        assertEquals(number[num], "two")
    }

    @Test
    fun `술어 함수를 이용한 나이 판별`() {
        val isLessThan27 = { p: Person -> p.age < 27 }
        val person = Person("이름1", 23)
        assertTrue { isLessThan27(person) }
    }

    @Test
    fun `all 함수를 사용해 모든 사람의 나이가 25살 아래인지 확인한다`() {
        val people = listOf(Person("이름1", 20), Person("이름3", 24))
        assertTrue { people.all { it.age < 25 } }
    }

    @Test
    fun `any 함수를 사용해 20살 이상인 사람이 한명이라도 있는지 확인한다`() {
        val people = listOf(Person("이름1", 20), Person("이름3", 24))
        assertTrue { people.any { it.age > 20 } }
    }

    @Test
    fun `firstOrNull 를 이용하여 20살 이상인 첫번째 사람을 확인한다`() {
        val people = listOf(Person("이름1", 20), Person("이름3", 20))
        val person = people.firstOrNull { it.age == 20 }
        assertEquals(person?.name, "이름1")
    }

    @Test
    fun `groupby 를 이용하여 동일한 나이가 2명까지만 허용하고 필터링된 사람들의 나이를 출력한다`() {
        val limit = 2
        val people = listOf(
            Person("이름1", 20),
            Person("이름2", 22),
            Person("이름3", 20),
            Person("이름4", 20),
            Person("이름5", 21),
            Person("이름6", 21),
            Person("이름7", 22),
            Person("이름8", 22)
        )
        val peopleMap = people.groupBy { it.age }
        val peopleRemoveDuplicate = peopleMap.filter { it.value.count() <= limit }
        peopleRemoveDuplicate.forEach { (key, value) -> value.forEach { assertEquals(it.age, key) }}
    }

}

data class Person(val name: String, val age: Int = 1)