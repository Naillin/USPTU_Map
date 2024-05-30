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
        lateinit var universityProducts: List<Building>
        lateinit var combinedBuildingList: List<Building>
        private fun initializeBuildings() {
            universityBuildings = listOf( //закрепить полигоны из PolygonsMapPoints
                Building(getString(appContext, R.string.dinning_hall), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(DINING_ROOM),ParcelablePoint.fromMapKitPoint(
                    ENTRANCES_BUILDINGS[16]), "dining", PolygonsMapPoints.dinningRoom.map { ParcelablePoint.fromMapKitPoint(it) })
            )

            academicBuildings = listOf(
                AcademicBuilding(getString(appContext, R.string.first_corpus), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CORPUSES[0]), ParcelablePoint.fromMapKitPoint(
                    ENTRANCES_BUILDINGS[0]), listOf("Department 3", "Department 3"), 0, PolygonsMapPoints.firstCorpus.map { ParcelablePoint.fromMapKitPoint(it) }),
                AcademicBuilding(getString(appContext, R.string.second_corpus), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CORPUSES[1]),ParcelablePoint.fromMapKitPoint(
                    ENTRANCES_BUILDINGS[1]), listOf("Department 3", "Department 4"), 0, PolygonsMapPoints.secondCorpus.map{ ParcelablePoint.fromMapKitPoint(it)}),
                AcademicBuilding(getString(appContext, R.string.third_corpus), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CORPUSES[2]),ParcelablePoint.fromMapKitPoint(
                    ENTRANCES_BUILDINGS[2]), listOf("Department 5", "Department 6"), 0, PolygonsMapPoints.thirdCorpus.map{ ParcelablePoint.fromMapKitPoint(it)}),
                AcademicBuilding(getString(appContext, R.string.fourth_corpus), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CORPUSES[3]),ParcelablePoint.fromMapKitPoint(
                    ENTRANCES_BUILDINGS[3]), listOf("Department 7", "Department 8"), 0, PolygonsMapPoints.fourthCorpus.map{ ParcelablePoint.fromMapKitPoint(it)}),
                AcademicBuilding(getString(appContext, R.string.seven_corpus), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CORPUSES[4]),ParcelablePoint.fromMapKitPoint(
                    ENTRANCES_BUILDINGS[4]), listOf("Department 9", "Department 10"), 0, PolygonsMapPoints.sevenCorpus.map{ ParcelablePoint.fromMapKitPoint(it) }),
                AcademicBuilding(getString(appContext, R.string.eight_corpus), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CORPUSES[5]),ParcelablePoint.fromMapKitPoint(
                    ENTRANCES_BUILDINGS[5]), listOf("Department 11", "Department 12"), 0, PolygonsMapPoints.eighthCorpus.map{ ParcelablePoint.fromMapKitPoint(it)}),
                AcademicBuilding(getString(appContext, R.string.eleven_corpus), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CORPUSES[6]),ParcelablePoint.fromMapKitPoint(
                    ENTRANCES_BUILDINGS[6]), listOf("Department 13", "Department 14"), 0, PolygonsMapPoints.elevenCorpus.map{ ParcelablePoint.fromMapKitPoint(it)}),
                AcademicBuilding(getString(appContext, R.string.ufkOne_corpus), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CORPUSES[7]),ParcelablePoint.fromMapKitPoint(
                    ENTRANCES_BUILDINGS[7]), listOf("Department 15", "Department 16"), 0, PolygonsMapPoints.ufkOneCorpus.map{ ParcelablePoint.fromMapKitPoint(it)}),
                AcademicBuilding(getString(appContext, R.string.ufkTwo_corpus), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CORPUSES[8]),ParcelablePoint.fromMapKitPoint(
                    ENTRANCES_BUILDINGS[8]), listOf("Department 17", "Department 18"), 0, PolygonsMapPoints.ufkTwoCorpus.map{ ParcelablePoint.fromMapKitPoint(it)})
            )

            universityDormitories = listOf(
                Dormitory(getString(appContext, R.string.first_dormitory), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[0]),ParcelablePoint.fromMapKitPoint(
                    ENTRANCES_BUILDINGS[9]), 0, PolygonsMapPoints.firstDormitory.map{ ParcelablePoint.fromMapKitPoint(it)}),
                Dormitory(getString(appContext, R.string.second_dormitory), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[1]),ParcelablePoint.fromMapKitPoint(
                    ENTRANCES_BUILDINGS[10]), 0, PolygonsMapPoints.secondDormitory.map{ ParcelablePoint.fromMapKitPoint(it)}),
                Dormitory(getString(appContext, R.string.third_dormitory), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[2]),ParcelablePoint.fromMapKitPoint(
                    ENTRANCES_BUILDINGS[11]), 0, PolygonsMapPoints.thirdDormitory.map{ ParcelablePoint.fromMapKitPoint(it)}),
                Dormitory(getString(appContext, R.string.fourth_dormitory), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[3]),ParcelablePoint.fromMapKitPoint(
                    ENTRANCES_BUILDINGS[12]), 0, PolygonsMapPoints.fourthDormitory.map{ ParcelablePoint.fromMapKitPoint(it)}),
                Dormitory(getString(appContext, R.string.five_dormitory), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[4]),ParcelablePoint.fromMapKitPoint(
                    ENTRANCES_BUILDINGS[13]), 0, PolygonsMapPoints.fiveDormitory.map{ ParcelablePoint.fromMapKitPoint(it)}),
                Dormitory(getString(appContext, R.string.six_dormitory), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[5]),ParcelablePoint.fromMapKitPoint(
                    ENTRANCES_BUILDINGS[14]), 0, PolygonsMapPoints.sixDormitory.map{ ParcelablePoint.fromMapKitPoint(it)}),
                Dormitory(getString(appContext, R.string.ten_dormitory), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(UNIVERSITY_DORMITORY[6]),ParcelablePoint.fromMapKitPoint(
                    ENTRANCES_BUILDINGS[15]), 0, PolygonsMapPoints.tenDormitory.map{ ParcelablePoint.fromMapKitPoint(it)})
            )

            universityCafe = listOf(
                Building(getString(appContext, R.string.samarkand_cafe), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CAFE[0]), ParcelablePoint.fromMapKitPoint(CAFE[0]),"cafe"),
                Building(getString(appContext, R.string.medina_cafe), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CAFE[1]),ParcelablePoint.fromMapKitPoint(CAFE[1]), "cafe"),
                Building(getString(appContext, R.string.dinning_hall_cafe), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CAFE[2]),ParcelablePoint.fromMapKitPoint(CAFE[2]), "cafe"),
                Building(getString(appContext, R.string.shaurma_cafe), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CAFE[3]), ParcelablePoint.fromMapKitPoint(CAFE[3]),"cafe"),
                Building(getString(appContext, R.string.dodo_pizza_cafe), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CAFE[4]),ParcelablePoint.fromMapKitPoint(CAFE[4]), "cafe"),
                Building(getString(appContext, R.string.kfc_cafe), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CAFE[5]), ParcelablePoint.fromMapKitPoint(CAFE[5]),"cafe"),
                Building(getString(appContext, R.string.fuji_cafe), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CAFE[6]), ParcelablePoint.fromMapKitPoint(CAFE[6]),"cafe"),
                Building(getString(appContext, R.string.bread_yard_cafe), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CAFE[7]), ParcelablePoint.fromMapKitPoint(CAFE[7]),"cafe"),
                Building(getString(appContext, R.string.farfor_pizza_cafe), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CAFE[8]),ParcelablePoint.fromMapKitPoint(CAFE[8]), "cafe"),
                Building(getString(appContext, R.string.companion_coffee_cafe), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CAFE[9]), ParcelablePoint.fromMapKitPoint(CAFE[9]),"cafe"),
                Building(getString(appContext, R.string.aloha_coffe_cafe), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CAFE[10]), ParcelablePoint.fromMapKitPoint(CAFE[10]),"cafe"),
                Building(getString(appContext, R.string.coffee_like_cafe), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CAFE[11]), ParcelablePoint.fromMapKitPoint(CAFE[11]),"cafe"),
                Building(getString(appContext, R.string.mum_is_making_coffee_cafe), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CAFE[12]), ParcelablePoint.fromMapKitPoint(CAFE[12]),"cafe")
            )

            universityChill = listOf(
                Building(getString(appContext, R.string.library_chill), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CHILL[0]),ParcelablePoint.fromMapKitPoint(CHILL[0]), "chill"),
                Building(getString(appContext, R.string.colizeum_chill), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(CHILL[1]),ParcelablePoint.fromMapKitPoint(CHILL[1]), "chill")
            )

            universityProducts = listOf(
                Building(getString(appContext, R.string.kalinka_products), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(PRODUCTS[0]),ParcelablePoint.fromMapKitPoint(PRODUCTS[0]), "products"),
                Building(getString(appContext, R.string.red_and_white_products), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(PRODUCTS[1]), ParcelablePoint.fromMapKitPoint(PRODUCTS[1]),"products"),
                Building(getString(appContext, R.string.monetka_products), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(PRODUCTS[2]),ParcelablePoint.fromMapKitPoint(PRODUCTS[2]), "products"),
                Building(getString(appContext, R.string.pyaterochka_products), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(PRODUCTS[3]), ParcelablePoint.fromMapKitPoint(PRODUCTS[3]),"products"),
                Building(getString(appContext, R.string.pobeda_products), getString(appContext, R.string.stringDefault), ParcelablePoint.fromMapKitPoint(PRODUCTS[4]), ParcelablePoint.fromMapKitPoint(PRODUCTS[4]),"products")
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
            Point(54.814710, 56.060517), //УФК2

            //DORMITORY
            Point(54.817357,56.058161),//1
            Point(54.817553,56.059842),//2
            Point(54.817021,56.060734),//3
            Point(54.816312,56.060729),//4
            Point(54.815026,56.061668),//5
            Point(54.815904,56.062262),//6
            Point(54.814738,56.061407),//10

            //DINNING ROOM
            Point(54.816826,56.059048)

        )

        val CAFE = listOf(
            Point(54.818243, 56.061043), //Самарканд
            Point(54.818347,56.062131),//Медина
            Point(54.818420,56.06267),//столовая
            Point(54.818434,56.063042), //Шаурма 1
            Point(54.818561,56.064369), //DODO PIZZA
            Point(54.818071,56.063044), //KFC
            Point(54.817995,56.062572), //Фудзияма
            Point(54.816454,56.063516), //Хлебный дворик
            Point(54.815408,56.058114), //Farfor pizza
            Point(54.818253,56.060352), //Copmanion coffee
            Point(54.818209,56.060746), //Aloha coffee
            Point(54.817526,56.060021), // Coffee Like
            Point(54.818295,56.064579) // Мама варит кофе
        )

        val CHILL = listOf(
            Point(54.816715, 56.063635),// библиотека
            Point(54.813542,56.057447) //colizeum
        )

        val PRODUCTS = listOf(
            Point(54.818544,56.064104), //Калинка
            Point(54.816698,56.063909), //КБ
            Point(54.816535,56.063791), //Монетка
            Point(54.815806,56.063180), //Пятерочка
            Point(54.816152,56.058339) //Победа
        )
    }
}