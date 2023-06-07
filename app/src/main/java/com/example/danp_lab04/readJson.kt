package com.example.danp_lab04

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.lifecycle.MutableLiveData
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

@Composable
fun loadJsonFromResource(context: Context, resourceId: Int): MutableLiveData<JSONArray> {
    val jsonString = getResourceAsString(context, resourceId)
    val json = JSONArray(jsonString)
    return MutableLiveData(json)
}

private fun getResourceAsString(context: Context,resourceId: Int): String {
    val inputStream: InputStream = context.resources.openRawResource(resourceId)
    val reader = BufferedReader(InputStreamReader(inputStream))
    val stringBuilder = StringBuilder()
    reader.useLines { lines ->
        lines.forEach {
            stringBuilder.append(it)
        }
    }
    return stringBuilder.toString()
}