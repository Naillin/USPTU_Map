package com.example.usptu_map.project_objects.coordinates

import com.example.usptu_map.project_objects.base_entities.AcademicBuilding
import com.example.usptu_map.project_objects.base_entities.Building
import com.example.usptu_map.project_objects.base_entities.Dormitory
import com.example.usptu_map.project_objects.base_entities.ParcelablePoint
import com.example.usptu_map.project_objects.coordinates.PolygonsMapPoints.dinningRoom
import com.example.usptu_map.project_objects.coordinates.PolygonsMapPoints.eighthCorpus
import com.example.usptu_map.project_objects.coordinates.PolygonsMapPoints.elevenCorpus
import com.example.usptu_map.project_objects.coordinates.PolygonsMapPoints.firstCorpus
import com.example.usptu_map.project_objects.coordinates.PolygonsMapPoints.firstCorpusArea
import com.example.usptu_map.project_objects.coordinates.PolygonsMapPoints.firstDormitory
import com.example.usptu_map.project_objects.coordinates.PolygonsMapPoints.fiveDormitory
import com.example.usptu_map.project_objects.coordinates.PolygonsMapPoints.fourthCorpus
import com.example.usptu_map.project_objects.coordinates.PolygonsMapPoints.fourthDormitory
import com.example.usptu_map.project_objects.coordinates.PolygonsMapPoints.secondCorpus
import com.example.usptu_map.project_objects.coordinates.PolygonsMapPoints.secondCorpusArea
import com.example.usptu_map.project_objects.coordinates.PolygonsMapPoints.secondDormitory
import com.example.usptu_map.project_objects.coordinates.PolygonsMapPoints.sevenCorpus
import com.example.usptu_map.project_objects.coordinates.PolygonsMapPoints.sixDormitory
import com.example.usptu_map.project_objects.coordinates.PolygonsMapPoints.tenDormitory
import com.example.usptu_map.project_objects.coordinates.PolygonsMapPoints.thirdCorpus
import com.example.usptu_map.project_objects.coordinates.PolygonsMapPoints.thirdDormitory
import com.example.usptu_map.project_objects.coordinates.PolygonsMapPoints.ufkOneCorpus
import com.example.usptu_map.project_objects.coordinates.PolygonsMapPoints.ufkTwoCorpus
import com.yandex.mapkit.geometry.Point

object MapPoints {
    //Центр карты
    val CENTER_USPTU_CITY_COORD: Point = Point(54.818329, 56.058558)

    //Границы карты от центра в пол километра
    val SOUTHWEST_USPTU_CITY_COORD: Point = Point(54.813824 + 0.000010, 56.050740  + 0.000010)
    val NORTHEAST_USPTU_CITY_COORD: Point = Point(54.822834 - 0.000010, 56.066376 - 0.000010)

    val DINING_ROOM :Point = Point(54.816945,56.059144)
    val universityBildings = listOf( //закрепить полигоны из PolygonsMapPoints
        Building("Жральня", "default", ParcelablePoint.fromMapKitPoint(DINING_ROOM), "dining", dinningRoom.map { ParcelablePoint.fromMapKitPoint(it) })
    )

    //КОРПУСА
    val CORPUSES = listOf(
        Point(54.818545, 56.058506),//1
        Point(54.817290, 56.061571),//2
        Point(54.817669, 56.061149),//3
        Point(54.8165320, 56.0624580),//4
        //5 - не в студ.городке
        Point(54.820161, 56.058817),//7
        //6 - не в студ.городке
        Point(54.8194760, 56.0683220),//8
        //9 - не в студ.городке
        //10 - не в студ.городке
        Point(54.8174690, 56.0588440),//11
        Point(54.816022, 56.060243),//УФК1
        Point(54.814870, 56.060504) //УФК2
    )

    //Входы в здания
    val ENTRANCES_BUILDINGS = listOf(
        Point(54.818400, 56.058580),//1
        Point(54.817365, 56.061632),//2
        Point(54.817892, 56.061634),//3
        Point(54.816922, 56.062446),//4
        //5 - не в студ.городке
        Point(54.820182, 56.059102),//7
        //6 - не в студ.городке
        Point(54.819362, 56.068747),//8
        //9 - не в студ.городке
        //10 - не в студ.городке
        Point(54.817564, 56.058810),//11
        Point(54.816225, 56.060184),//УФК1
        Point(54.814710, 56.060517) //УФК2


    )

    val academicBuildings = listOf( //закрепить полигоны из PolygonsMapPoints
        AcademicBuilding("Building 1", "default", ParcelablePoint.fromMapKitPoint(CORPUSES[0]), listOf("Department 1", "Department 2"), 0, firstCorpus.map { ParcelablePoint.fromMapKitPoint(it) }),
        AcademicBuilding("Building 2", "default", ParcelablePoint.fromMapKitPoint(CORPUSES[1]), listOf("Department 3", "Department 4"), 0, secondCorpus.map{ParcelablePoint.fromMapKitPoint(it)}),
        AcademicBuilding("Building 3", "default", ParcelablePoint.fromMapKitPoint(CORPUSES[2]), listOf("Department 5", "Department 6"), 0, thirdCorpus.map{ParcelablePoint.fromMapKitPoint(it)}),
        AcademicBuilding("Building 4", "default", ParcelablePoint.fromMapKitPoint(CORPUSES[3]), listOf("Department 7", "Department 8"), 0,fourthCorpus.map{ParcelablePoint.fromMapKitPoint(it)}),
        AcademicBuilding("Building 7", "default", ParcelablePoint.fromMapKitPoint(CORPUSES[4]), listOf("Department 9", "Department 10"), 0, sevenCorpus.map{ParcelablePoint.fromMapKitPoint(it) }),
        AcademicBuilding("Building 8", "default", ParcelablePoint.fromMapKitPoint(CORPUSES[5]), listOf("Department 11", "Department 12"), 0, eighthCorpus.map{ParcelablePoint.fromMapKitPoint(it)}),
        AcademicBuilding("Building 11", "default", ParcelablePoint.fromMapKitPoint(CORPUSES[6]), listOf("Department 13", "Department 14"), 0, elevenCorpus.map{ParcelablePoint.fromMapKitPoint(it)}),
        AcademicBuilding("UFK 1", "default", ParcelablePoint.fromMapKitPoint(CORPUSES[7]), listOf("Department 15", "Department 16"), 0, ufkOneCorpus.map{ParcelablePoint.fromMapKitPoint(it)}),
        AcademicBuilding("UFK 2", "default", ParcelablePoint.fromMapKitPoint(CORPUSES[8]), listOf("Department 17", "Department 18"), 0, ufkTwoCorpus.map{ParcelablePoint.fromMapKitPoint(it)})
    )

    //ОБЩЕЖИТИЯ
    val UNIVERSITY_DORMITORY = listOf(
        Point(54.81726, 56.05812), //1
        Point(54.817552, 56.059662),//2
        Point(54.817002, 56.060883),//3
        Point(54.816286, 56.060848),//4
        Point(54.815087, 56.061719),//5
        Point(54.815987, 56.062137),//6
        Point(54.814532, 56.061292)//10
    )
    val universityDormitories = listOf( //закрепить полигоны из PolygonsMapPoints
        Dormitory("Dormitory 1", "default", ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[0]), 0, firstDormitory.map{ParcelablePoint.fromMapKitPoint(it)}),
        Dormitory("Dormitory 2", "default", ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[1]), 0, secondDormitory.map{ParcelablePoint.fromMapKitPoint(it)}),
        Dormitory("Dormitory 3", "default", ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[2]), 0, thirdDormitory.map{ParcelablePoint.fromMapKitPoint(it)}),
        Dormitory("Dormitory 4", "default", ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[3]), 0, fourthDormitory.map{ParcelablePoint.fromMapKitPoint(it)}),
        Dormitory("Dormitory 5", "default", ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[4]), 0, fiveDormitory.map{ParcelablePoint.fromMapKitPoint(it)}),
        Dormitory("Dormitory 6", "default", ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[5]), 0, sixDormitory.map{ParcelablePoint.fromMapKitPoint(it)}),
        Dormitory("Dormitory 10", "default", ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[6]), 0, tenDormitory.map{ParcelablePoint.fromMapKitPoint(it)})
    )

    val combinedBuildingList = listOf(
        universityBildings,
        academicBuildings,
        universityDormitories
    ).flatten() //Объединит все выбранные элементы в один список
}