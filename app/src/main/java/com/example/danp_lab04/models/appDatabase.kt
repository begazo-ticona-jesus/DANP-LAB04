package com.example.danp_lab04.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.danp_lab04.R
import com.example.danp_lab04.entities.CountryEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.io.BufferedReader

@Database(
    entities = [CountryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
    //abstract fun countryDao(jsonArray: JSONArray): CountryDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "country_database"
                ).addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        INSTANCE?.let { database ->
                            CoroutineScope(Dispatchers.IO).launch {
                                val jsonArray = getJsonArrayFromResource(context, R.raw.countries)
                                val countryDao = database.countryDao()

                                for (i in 0 until jsonArray.length()) {
                                    val jsonObject = jsonArray.getJSONObject(i)
                                    val countryEntity = CountryEntity(
                                        jsonObject.getInt("countryId"),
                                        jsonObject.getString("name_en"),
                                        jsonObject.getString("name_es"),
                                        jsonObject.getString("continent_en"),
                                        jsonObject.getString("continent_es"),
                                        jsonObject.getString("capital_en"),
                                        jsonObject.getString("capital_es"),
                                        jsonObject.getString("dial_code"),
                                        jsonObject.getString("code_2"),
                                        jsonObject.getString("code_3"),
                                        jsonObject.getString("tld"),
                                        jsonObject.getInt("km2")
                                    )
                                    countryDao.insert(countryEntity)
                                }
                            }
                        }
                    }
                }).build()

                INSTANCE = instance
                instance
            }
        }
    }
}

fun getJsonArrayFromResource(context: Context, resourceId: Int): JSONArray {
    val inputStream = context.resources.openRawResource(resourceId)
    val jsonString = inputStream.bufferedReader().use(BufferedReader::readText)
    return JSONArray(jsonString)
}