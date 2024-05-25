package com.example.wearapp.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import com.example.wearapp.R
import com.example.wearapp.presentation.theme.USPTU_MapTheme
import com.google.android.gms.tasks.Task
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.DataItem
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.PutDataRequest
import com.google.android.gms.wearable.Wearable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WearApp()
        }
    }
}

@Composable
fun WearApp() {
    USPTU_MapTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.base_color)),
            contentAlignment = Alignment.Center
        ) {
            TimeText()
            ButtonList()
        }
    }
}

@Composable
fun ButtonList() {
    val buttons = listOf(
        R.drawable.lesson_route to stringResource(R.string.to_the_current_building, 1),
        R.drawable.food_building to stringResource(R.string.route_to_food, 2),
        R.drawable.relax_smile to stringResource(R.string.route_to_relax, 3),
        R.drawable.make_route to stringResource(R.string.create_route, 4)
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(buttons) { (icon, text) ->
            val context = LocalContext.current
            IconButton(icon = icon, text = text) {
                // Отправка сигнала на мобильное устройство
                sendSignalToPhone(context, text)
            }
        }
    }
}

@Composable
fun IconButton(icon: Int, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = text,
            tint = colorResource(id = R.color.accent_color),
            modifier = Modifier
                .size(20.dp)
                .weight(1f)
        )
        //Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            color = colorResource(id = R.color.accent_color),
            textAlign = TextAlign.Start,
            fontSize = 12.sp,
            modifier = Modifier
                .weight(3f)
                .padding(start = 8.dp)
        )
    }
}

fun sendSignalToPhone(context: Context, buttonText: String) {
    val dataClient: DataClient = Wearable.getDataClient(context)
    val putDataReq: PutDataRequest = PutDataMapRequest.create("/path").run {
        dataMap.putString(ConstantsProject.SEND_WEAR_KEY1, buttonText)
        asPutDataRequest()
    }
    val putDataTask: Task<DataItem> = dataClient.putDataItem(putDataReq)

    putDataTask.addOnSuccessListener {
        Log.d("MyTag", "Данные отправлены!")
    }.addOnFailureListener {
        Log.d("MyTag", "Данные не отправлены!")
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp()
}
