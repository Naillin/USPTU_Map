package com.example.usptu_map

import android.util.Log
import org.jsoup.Jsoup

class WebParsing {
    fun getParseData() {
        val url = "https://rasp.rusoil.net/?page=schedule&search=%7B%22value%22%3A%22БПО-21-01%3B%22%2C%22GRUPPA%22%3A%22БПО-21-01%3B%22%7D" // Замените на вашу реальную ссылку

        try {
            // Загрузка и парсинг страницы по URL
            val doc = Jsoup.connect(url).get()

            // Выбор всех элементов div с определенным классом. В этом примере используется класс 'text-[14px]',
            // но вам может потребоваться адаптировать селектор в зависимости от структуры страницы и классов.
            // Помните о необходимости экранирования специальных символов в CSS селекторах, если будете их использовать.
            val elements = doc.select("div.text-[14px]") // Используйте более специфичный селектор, если нужно

            // Перебор найденных элементов и вывод их текста
            for (element in elements) {
                println(element.text())
                Log.d("MyTag", element.text())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}