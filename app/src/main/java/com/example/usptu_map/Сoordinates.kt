package com.example.usptu_map

import com.yandex.mapkit.geometry.Point

object Сoordinates {
    //Центр карты
    val CENTER_USPTU_CITY_COORD: Point = Point(54.818329, 56.058558)

    //Границы карты от центра в пол километра
    val SOUTHWEST_USPTU_CITY_COORD: Point = Point(54.813824, 56.050740)
    val NORTHEAST_USPTU_CITY_COORD: Point = Point(54.822834, 56.066376)

    //КОРПУСА
    val CORPUSES = listOf(
        Point(54.818545, 56.058506),
        Point(54.818545, 56.058506),
        Point(54.818545, 56.058506),
        Point(54.818545, 56.058506),
        Point(54.818545, 56.058506),
        Point(54.818545, 56.058506),
        Point(54.818545, 56.058506),
        Point(54.818545, 56.058506),
        Point(54.818545, 56.058506),
        Point(54.818545, 56.058506),
        Point(54.818545, 56.058506),
        Point(54.818545, 56.058506)
    )

}