package com.example.usptu_map.user_map_interface

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.usptu_map.databinding.ActivityTwoPointsRouteBinding

class TwoPointsRouteActivity : AppCompatActivity() {
    private lateinit var bindingActivityTwoPointsRoute: ActivityTwoPointsRouteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingActivityTwoPointsRoute = ActivityTwoPointsRouteBinding.inflate(layoutInflater)
        setContentView(bindingActivityTwoPointsRoute.root)

        init()
    }

    private fun init() = with(bindingActivityTwoPointsRoute) {

    }
}