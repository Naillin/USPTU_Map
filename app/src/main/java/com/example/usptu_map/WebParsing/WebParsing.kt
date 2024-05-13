package com.example.usptu_map.WebParsing

import android.content.Context
import com.example.usptu_map.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeDriverService
import org.openqa.selenium.chrome.ChromeOptions
import java.io.File
import java.io.FileOutputStream

class WebParsing(private val groupName: String, val context: Context) {
    private fun generateScheduleUrl(): String {
        val baseUrl = "https://rasp.rusoil.net/?page=schedule"
        val queryParams = mapOf(
            "value" to "$groupName;",
            "GRUPPA" to "$groupName;"
        )
        val jsonParams = queryParams.entries.joinToString(separator = ",", prefix = "{", postfix = "}") {
            "\"${it.key}\":\"${java.net.URLEncoder.encode(it.value, "UTF-8")}\""
        }
        return "$baseUrl&search=$jsonParams"
    }

    private suspend fun fetchSchedule(): Document = withContext(Dispatchers.IO) {
        val inputStream = context.resources.openRawResource(R.raw.chromedriver)
        val driverExecutable = File.createTempFile("chromedriver", "")
        driverExecutable.setExecutable(true)

        FileOutputStream(driverExecutable).use { outputStream ->
            inputStream.copyTo(outputStream)
        }

        // Установка пути к chromedriver для WebDriver
        System.setProperty("webdriver.chrome.driver", driverExecutable.absolutePath)

        val service = ChromeDriverService.Builder()
            .usingPort(7979) // Порт
            .build()
        val options = ChromeOptions()
        options.addArguments("--headless") // для работы в фоновом режиме
        val driver: WebDriver = ChromeDriver(service, options)

        try {
            val url = generateScheduleUrl()
            driver.get(url)
            Thread.sleep(5000) // Даем время для выполнения JavaScript
            val content = driver.pageSource
            Jsoup.parse(content) // Парсинг полученного HTML с помощью Jsoup
        } finally {
            driver.quit() // Закрываем драйвер
        }
    }

    private val lessons = mutableListOf<Lesson>()
    fun getSchedule(): List<Lesson> = lessons
    fun clearSchedule() = lessons.clear()
    suspend fun parseSchedule() {
        val document: Document = fetchSchedule()
        val scheduleBlocks = document.select("div.flex-col > div.rounded-[15px]")

        for (block in scheduleBlocks) {
            val name = block.select(".title").text()
            val timeText = block.select("div[class*=text-]").text()

            // Extracting the start and end time
            val timeParts = timeText.split(" ‧ ")
            val timeRange = timeParts.getOrNull(1)?.split(" – ") ?: listOf("00:00", "00:00")
            val startTime = timeRange[0]
            val endTime = timeRange[1]

            // Extracting the pair number
            val pairMatchResult = Regex("(\\d+)").find(timeParts.getOrNull(0) ?: "")
            val pairNumber = pairMatchResult?.groupValues?.get(1)?.toIntOrNull() ?: 0

            // Extracting the teacher's name, if available
            val teacherInfo = block.select(".text-[#8E8E8E]").text()
            val teacher = teacherInfo.substringBefore("‧").trim()

            // Extracting building and room, assuming format "Building-Room"
            val buildingRoomText = teacherInfo.substringAfter("‧", "").trim()
            val (building, room) = buildingRoomText.split('-').let {
                if (it.size > 1) Pair(it[0], it[1]) else Pair(null, null)
            }

            lessons.add(Lesson(name, startTime, endTime, pairNumber, teacher, building, room))
        }
    }
}