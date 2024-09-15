import kotlin.random.Random

fun main() {
    val city = listOf(
        "Бийск", "Барнаул", "Москва", "Санкт-Петербург", "Новосибирск", "Екатеринбург",
        "Казань", "Нижний Новгород", "Челябинск", "Самара", "Омск", "Ростов-на-Дону",
        "Уфа", "Красноярск", "Воронеж"
    )

    while (true) {
        println("Хотите закончить работу? (введите EXIT) или составить поезд? (введите любое другое слово)")
        val input = readlnOrNull()
        if (input?.equals("EXIT", ignoreCase = true) == true) {
            break
        }

        val (start, end) = create(city)
        println("Создано направление: $start - $end")

        val pas = sell()
        println("Продано билетов: $pas")

        val train = form(pas)
        println("Сформирован поезд: ${train.joinToString { "Вагон вместимостью ${it.cap}, пассажиров ${it.pas}" }}")

        send(start, end, train)
    }
}

fun create(city: List<String>): Pair<String, String> {
    val start = city.random()
    var end = city.random()
    while (end == start) {
        end = city.random()
    }
    return Pair(start, end)
}

fun sell(): Int {
    return Random.nextInt(5, 202)
}

fun form(pass: Int): List<T> {
    val train = mutableListOf<T>()
    var rem = pass

    while (rem > 0) {
        val cap = Random.nextInt(5, 26)
        val carP = if (rem > cap) cap else rem
        train.add(T(cap, carP))
        rem -= carP
    }

    return train
}

fun send(start: String, end: String, train: List<T>) {
    println("Поезд $start - $end, состоящий из ${train.size} вагонов, отправлен.")
    train.forEach { car ->
        println("Вагон вместимостью ${car.cap}, пассажиров ${car.pas}")
    }
}

class T(capacity: Int, pas: Int) {
    var cap: Int = capacity
        get() = field
        set(value) {
            if (value in 5..25) {
                field = value
            } else {
                throw IllegalArgumentException("Вместимость вагона должна быть в диапазоне от 5 до 25.")
            }
        }

    var pas: Int = pas
        get() = field
        set(value) {
            if (value in 0..cap) {
                field = value
            } else {
                throw IllegalArgumentException("Количество пассажиров не может превышать вместимость вагона.")
            }
        }
}
