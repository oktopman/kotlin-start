class Calculator {
}

val trim = """[^.\d-+*/]""".toRegex()
// [...] : 대괄호에 등장는 문자중에 하나
// [^...] : 등장하는 문자열이 아닌것
// \d : 숫자 라는 뜻. 0,1,2,3,4,5,6,7,8,9 를 뜻함
// accept list. [^.\d-+*/] 대괄호 안에 있는것만 허용되고 나머지것은 다 안돼.
// 정규식은 기본 삼연산. 접합, 반복, 선택
// 괄호안에 있는식을 하나의 문자로 인식.

fun trim(value: String): String {
    return value.replace(trim, "")
}

fun replaceMinusToPlusMinus(value: String) = value.replace("-", "+-")