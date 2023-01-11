import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe

class CollectionTest : StringSpec({
    "takeWhile 테스트" {
        val numbers = listOf(3, 5, 6, 9, 10, 11)
        val numbers3 = listOf(8, 9, 10, 11, 12, 13)
        val numbers4 = listOf(1, 8, 2, 3, 4, 5)
        val winningNumber = WinningNumber(listOf(1, 2, 3, 4, 5, 6))

        val takeWhile = numbers.takeWhile { winningNumber.numbers.contains(it) }.size
        val takeWhile3 = numbers3.takeWhile { winningNumber.numbers.contains(it) }.size
        val takeWhile4 = numbers4.takeWhile { winningNumber.numbers.contains(it) }.size

        takeWhile shouldBe 3
        takeWhile3 shouldBe 0
        takeWhile4 shouldBe 1
    }

    "groupBy 테스트" {
        val dtos: List<Dto> = listOf(
            Dto(itemId = 1, keyword = "나이키", count = 90),
            Dto(itemId = 1, keyword = "반팔", count = 80),
            Dto(itemId = 1, keyword = "상의", count = 70),
            Dto(itemId = 1, keyword = "바지", count = 60),
            Dto(itemId = 1, keyword = "트레이닝", count = 50),
            Dto(itemId = 2, keyword = "나이키", count = 90),
            Dto(itemId = 2, keyword = "하의", count = 80),
            Dto(itemId = 2, keyword = "장갑", count = 70),
            Dto(itemId = 2, keyword = "양말", count = 60),
            Dto(itemId = 2, keyword = "신발", count = 50)
        )

        val itemIdMap: Map<Long, List<Dto>> = dtos.groupBy { it.itemId }
        itemIdMap.size shouldBe 2
        itemIdMap[1]!! shouldContainAll listOf(
            Dto(itemId = 1, keyword = "나이키", count = 90),
            Dto(itemId = 1, keyword = "반팔", count = 80),
            Dto(itemId = 1, keyword = "상의", count = 70),
            Dto(itemId = 1, keyword = "바지", count = 60),
            Dto(itemId = 1, keyword = "트레이닝", count = 50)
        )

        itemIdMap[2]!! shouldContainAll listOf(
            Dto(itemId = 2, keyword = "나이키", count = 90),
            Dto(itemId = 2, keyword = "하의", count = 80),
            Dto(itemId = 2, keyword = "장갑", count = 70),
            Dto(itemId = 2, keyword = "양말", count = 60),
            Dto(itemId = 2, keyword = "신발", count = 50)
        )

        val itemIdMap2: Map<Long, List<Dto2>> = dtos.groupBy({ it.itemId }, { Dto2(it.itemId, it.keyword, it.count) })
        itemIdMap2[1]!! shouldContainAll listOf(
            Dto2(itemId = 1, keyword = "나이키", count = 90),
            Dto2(itemId = 1, keyword = "반팔", count = 80),
            Dto2(itemId = 1, keyword = "상의", count = 70),
            Dto2(itemId = 1, keyword = "바지", count = 60),
            Dto2(itemId = 1, keyword = "트레이닝", count = 50)
        )
    }

    "mapValues 테스트" {
        val map = mapOf(
            1 to listOf(
                Dto(itemId = 1, keyword = "나이키", count = 90),
                Dto(itemId = 1, keyword = "반팔", count = 80),
                Dto(itemId = 1, keyword = "상의", count = 70),
                Dto(itemId = 1, keyword = "바지", count = 60),
                Dto(itemId = 1, keyword = "트레이닝", count = 50)
            ),
            2 to listOf(
                Dto(itemId = 2, keyword = "나이키", count = 90),
                Dto(itemId = 2, keyword = "하의", count = 80),
                Dto(itemId = 2, keyword = "장갑", count = 70),
                Dto(itemId = 2, keyword = "양말", count = 60),
                Dto(itemId = 2, keyword = "신발", count = 50)
            )
        )

        val mapValues = map.mapValues { it.value.map { k -> Dto2(k.itemId, k.keyword, k.count) } }
        mapValues.size shouldBe 2
        mapValues[1]!! shouldContainAll listOf(
            Dto2(itemId = 1, keyword = "나이키", count = 90),
            Dto2(itemId = 1, keyword = "반팔", count = 80),
            Dto2(itemId = 1, keyword = "상의", count = 70),
            Dto2(itemId = 1, keyword = "바지", count = 60),
            Dto2(itemId = 1, keyword = "트레이닝", count = 50)
        )

        mapValues[2]!! shouldContainAll listOf(
            Dto2(itemId = 2, keyword = "나이키", count = 90),
            Dto2(itemId = 2, keyword = "하의", count = 80),
            Dto2(itemId = 2, keyword = "장갑", count = 70),
            Dto2(itemId = 2, keyword = "양말", count = 60),
            Dto2(itemId = 2, keyword = "신발", count = 50)
        )
    }
})

class WinningNumber(val numbers: List<Int>)
data class Dto(val itemId: Long, val keyword: String, val count: Long)
data class Dto2(val itemId: Long, val keyword: String, val count: Long)