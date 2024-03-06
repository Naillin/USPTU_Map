package com.example.usptu_map.project_objects

import com.yandex.mapkit.geometry.Point

object Сoordinates {
    //Центр карты
    val CENTER_USPTU_CITY_COORD: Point = Point(54.818329, 56.058558)

    //Границы карты от центра в пол километра
    val SOUTHWEST_USPTU_CITY_COORD: Point = Point(54.813824 + 0.000010, 56.050740  + 0.000010)
    val NORTHEAST_USPTU_CITY_COORD: Point = Point(54.822834 - 0.000010, 56.066376 - 0.000010)

    val DINING_ROOM :Point = Point(54.816945,56.059144)

    //КОРПУСА
    val CORPUSES = listOf(
        Point(54.818545, 56.058506),//1
        Point(54.817290, 56.061571),//2
        Point(54.817669, 56.061149),//3
        Point(54.8165320, 56.0624580),//4
        Point(54.820161, 56.058817),//7
        Point(54.8194760, 56.0683220),//8
        Point(54.8174690, 56.0588440),//11
        Point(54.816022, 56.060243),//УФК1
        Point(54.814870, 56.060504) //УФК2
    )

    //ОБЩЕЖИТИЯ
    val UNIVERSITY_DORMITORY = listOf(
        Point(54.81726, 56.05812), //1
        Point(54.817552, 56.059662),//2
        Point(54.817002, 56.060883),//3
        Point(54.816286, 56.060848),//4
        Point(54.815860, 56.061125),//УЧЕБНО ЖИЛТЩНЫЙ КОМПЛЕКС
        Point(54.815087, 56.061719),//5
        Point(54.815987, 56.062137),//6
        Point(54.814532, 56.061292)//10
    )
}