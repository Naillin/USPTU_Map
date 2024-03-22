package com.example.usptu_map.project_objects.base_entities.coordinates

import com.example.usptu_map.project_objects.base_entities.AcademicBuilding
import com.example.usptu_map.project_objects.base_entities.Building
import com.example.usptu_map.project_objects.base_entities.Dormitory
import com.example.usptu_map.project_objects.base_entities.ParcelablePoint
import com.yandex.mapkit.geometry.Point

object MapPoints {
    //Центр карты
    val CENTER_USPTU_CITY_COORD: Point = Point(54.818329, 56.058558)

    //Границы карты от центра в пол километра
    val SOUTHWEST_USPTU_CITY_COORD: Point = Point(54.813824 + 0.000010, 56.050740  + 0.000010)
    val NORTHEAST_USPTU_CITY_COORD: Point = Point(54.822834 - 0.000010, 56.066376 - 0.000010)

    val DINING_ROOM :Point = Point(54.816945,56.059144)
    val universityBildings = listOf( //закрепить полигоны из PolygonsMapPoints
        Building("Жральня", "default", ParcelablePoint.fromMapKitPoint(DINING_ROOM), "dining", )
    )

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
    val academicBuildings = listOf( //закрепить полигоны из PolygonsMapPoints
        AcademicBuilding("Building 1", "default", ParcelablePoint.fromMapKitPoint(CORPUSES[0]), listOf("Department 1", "Department 2"), 10),
        AcademicBuilding("Building 2", "default", ParcelablePoint.fromMapKitPoint(CORPUSES[1]), listOf("Department 3", "Department 4"), 8),
        AcademicBuilding("Building 3", "default", ParcelablePoint.fromMapKitPoint(CORPUSES[2]), listOf("Department 5", "Department 6"), 12),
        AcademicBuilding("Building 4", "default", ParcelablePoint.fromMapKitPoint(CORPUSES[3]), listOf("Department 7", "Department 8"), 7),
        AcademicBuilding("Building 7", "default", ParcelablePoint.fromMapKitPoint(CORPUSES[4]), listOf("Department 9", "Department 10"), 15),
        AcademicBuilding("Building 8", "default", ParcelablePoint.fromMapKitPoint(CORPUSES[5]), listOf("Department 11", "Department 12"), 9),
        AcademicBuilding("Building 11", "default", ParcelablePoint.fromMapKitPoint(CORPUSES[6]), listOf("Department 13", "Department 14"), 20),
        AcademicBuilding("UFK 1", "default", ParcelablePoint.fromMapKitPoint(CORPUSES[7]), listOf("Department 15", "Department 16"), 5),
        AcademicBuilding("UFK 2", "default", ParcelablePoint.fromMapKitPoint(CORPUSES[8]), listOf("Department 17", "Department 18"), 6)
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
    val universityDormitories = listOf( //закрепить полигоны из PolygonsMapPoints
        Dormitory("Dormitory 1", "default", ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[0]), 0),
        Dormitory("Dormitory 2", "default", ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[1]), 0),
        Dormitory("Dormitory 3", "default", ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[2]), 0),
        Dormitory("Dormitory 4", "default", ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[3]), 0),
        Dormitory("Educational and Housing Complex", "default", ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[4]), 0),
        Dormitory("Dormitory 5", "default", ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[5]), 0),
        Dormitory("Dormitory 6", "default", ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[6]), 0),
        Dormitory("Dormitory 10", "default", ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[7]), 0)
    )
}