package com.example.usptu_map.project_objects.coordinates

import android.content.Context
import androidx.core.content.ContextCompat.getString
import com.example.usptu_map.R
import com.example.usptu_map.project_objects.base_entities.AcademicBuilding
import com.example.usptu_map.project_objects.base_entities.Building
import com.example.usptu_map.project_objects.base_entities.Dormitory
import com.example.usptu_map.project_objects.base_entities.ParcelablePoint
import com.yandex.mapkit.geometry.Point

class MapPoints() {
    companion object {
        private lateinit var appContext: Context
        fun initialize(context: Context) {
            appContext = context.applicationContext
            initializeBuildings()
        }

        //Центр карты
        val CENTER_USPTU_CITY_COORD: Point = Point(54.818329, 56.058558)

        //Границы карты от центра в пол километра
        val SOUTHWEST_USPTU_CITY_COORD: Point = Point(54.813824 + 0.000010, 56.050740 + 0.000010)
        val NORTHEAST_USPTU_CITY_COORD: Point = Point(54.822834 - 0.000010, 56.066376 - 0.000010)

        val DINING_ROOM : Point = Point(54.816945,56.059144)

        lateinit var universityBuildings: List<Building>
        lateinit var academicBuildings: List<AcademicBuilding>
        lateinit var universityDormitories: List<Dormitory>
        lateinit var universityCafe: List<Building>
        lateinit var universityChill: List<Building>
        lateinit var combinedBuildingList: List<Building>
        private fun initializeBuildings() {
            universityBuildings = listOf( //закрепить полигоны из PolygonsMapPoints
                Building(getString(appContext, R.string.dinning_hall), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(DINING_ROOM), "dining", PolygonsMapPoints.dinningRoom.map { ParcelablePoint.fromMapKitPoint(it) })
            )

            academicBuildings = listOf(
                AcademicBuilding(getString(appContext, R.string.first_corpus), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CORPUSES[0]), listOf("Department 3", "Department 3"), 0, PolygonsMapPoints.firstCorpus.map { ParcelablePoint.fromMapKitPoint(it) }),
                AcademicBuilding(getString(appContext, R.string.second_corpus), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CORPUSES[1]), listOf("Department 3", "Department 4"), 0, PolygonsMapPoints.secondCorpus.map{ ParcelablePoint.fromMapKitPoint(it)}),
                AcademicBuilding(getString(appContext, R.string.third_corpus), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CORPUSES[2]), listOf("Department 5", "Department 6"), 0, PolygonsMapPoints.thirdCorpus.map{ ParcelablePoint.fromMapKitPoint(it)}),
                AcademicBuilding(getString(appContext, R.string.fourth_corpus), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CORPUSES[3]), listOf("Department 7", "Department 8"), 0, PolygonsMapPoints.fourthCorpus.map{ ParcelablePoint.fromMapKitPoint(it)}),
                AcademicBuilding(getString(appContext, R.string.seven_corpus), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CORPUSES[4]), listOf("Department 9", "Department 10"), 0, PolygonsMapPoints.sevenCorpus.map{ ParcelablePoint.fromMapKitPoint(it) }),
                AcademicBuilding(getString(appContext, R.string.eight_corpus), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CORPUSES[5]), listOf("Department 11", "Department 12"), 0, PolygonsMapPoints.eighthCorpus.map{ ParcelablePoint.fromMapKitPoint(it)}),
                AcademicBuilding(getString(appContext, R.string.eleven_corpus), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CORPUSES[6]), listOf("Department 13", "Department 14"), 0, PolygonsMapPoints.elevenCorpus.map{ ParcelablePoint.fromMapKitPoint(it)}),
                AcademicBuilding(getString(appContext, R.string.ufkOne_corpus), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CORPUSES[7]), listOf("Department 15", "Department 16"), 0, PolygonsMapPoints.ufkOneCorpus.map{ ParcelablePoint.fromMapKitPoint(it)}),
                AcademicBuilding(getString(appContext, R.string.ufkTwo_corpus), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CORPUSES[8]), listOf("Department 17", "Department 18"), 0, PolygonsMapPoints.ufkTwoCorpus.map{ ParcelablePoint.fromMapKitPoint(it)})
            )

            universityDormitories = listOf(
                Dormitory(getString(appContext, R.string.first_dormitory), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[0]), 0, PolygonsMapPoints.firstDormitory.map{ ParcelablePoint.fromMapKitPoint(it)}),
                Dormitory(getString(appContext, R.string.second_dormitory), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[1]), 0, PolygonsMapPoints.secondDormitory.map{ ParcelablePoint.fromMapKitPoint(it)}),
                Dormitory(getString(appContext, R.string.third_dormitory), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[2]), 0, PolygonsMapPoints.thirdDormitory.map{ ParcelablePoint.fromMapKitPoint(it)}),
                Dormitory(getString(appContext, R.string.fourth_dormitory), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[3]), 0, PolygonsMapPoints.fourthDormitory.map{ ParcelablePoint.fromMapKitPoint(it)}),
                Dormitory(getString(appContext, R.string.five_dormitory), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[4]), 0, PolygonsMapPoints.fiveDormitory.map{ ParcelablePoint.fromMapKitPoint(it)}),
                Dormitory(getString(appContext, R.string.six_dormitory), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[5]), 0, PolygonsMapPoints.sixDormitory.map{ ParcelablePoint.fromMapKitPoint(it)}),
                Dormitory(getString(appContext, R.string.ten_dormitory), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[6]), 0, PolygonsMapPoints.tenDormitory.map{ ParcelablePoint.fromMapKitPoint(it)})
            )

            universityCafe = listOf(
                Building(getString(appContext, R.string.samarkand_cafe), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CAFE[0]), "cafe"),
                Building(getString(appContext, R.string.medina_cafe), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CAFE[1]), "cafe"),
                Building(getString(appContext, R.string.dinning_hall_cafe), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CAFE[2]), "cafe"),
                Building(getString(appContext, R.string.shaurma_cafe), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CAFE[3]), "cafe"),
                Building(getString(appContext, R.string.dodo_pizza_cafe), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CAFE[4]), "cafe"),
                Building(getString(appContext, R.string.kfc_cafe), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CAFE[5]), "cafe"),
                Building(getString(appContext, R.string.fudziyama_cafe), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CAFE[6]), "cafe"),
            )

            universityChill = listOf(
                Building(getString(appContext, R.string.library_chill), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CHILL[0]), "chill"),
                Building(getString(appContext, R.string.colizeum_chill), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CHILL[1]), "chill"),
            )



            combinedBuildingList = listOf(
                universityBuildings,
                academicBuildings,
                universityDormitories,
                universityCafe,
                universityChill
            ).flatten() //Объединит все выбранные элементы в один список
        }

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

        val CAFE = listOf(
            Point(54.818243, 56.061043), //Самарканд
            Point(54.818347,56.062131),//Медина
            Point(54.818420,56.06267),//столовая
            Point(54.818434,56.063042), //Шаурма 1
            Point(54.818561,56.064369), //DODO PIZZA
            Point(54.818071,56.063044), //KFC
            Point(54.817995,56.062572), //Фудзияма
        )

        val CHILL = listOf(
            Point(54.816715, 56.063635),// библиотека
            Point(54.813542,56.057447), //colizeum
        )
    }
}