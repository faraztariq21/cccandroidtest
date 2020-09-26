package com.assignment.cccandroidtest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.assignment.cccandroidtest.daos.EstimatesDao
import com.assignment.cccandroidtest.daos.PersonsDao
import com.assignment.cccandroidtest.models.Estimate
import com.assignment.cccandroidtest.models.Person
import com.assignment.cccandroidtest.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Person::class, Estimate::class], version = 1, exportSchema = false)
public abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun personDao(): PersonsDao
    abstract fun estimateDao(): EstimatesDao

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let { database ->
                scope.launch {
                    val estimateDao = database.estimateDao()
                    val personDao = database.personDao()

                    estimateDao.insert(Utils.estimateObject)
                    personDao.insert(Utils.personObject)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getDatabase(context: Context, coroutineScope: CoroutineScope): AppRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppRoomDatabase::class.java,
                        "word_database"
                )
                 .addCallback(AppDatabaseCallback(coroutineScope))
                 .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}