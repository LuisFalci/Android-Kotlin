package com.example.a16_room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [StudentModel::class], version = 1)
abstract class StudentDatabase : RoomDatabase() {

    //abstração da interface, temos o acesso via esta instância do banco
    abstract fun studentDAO(): StudentDAO

    //PADRÃO SINGLETON (impedimos instanciar mais de um banco)
    companion object {
        private lateinit var INSTANCE: StudentDatabase

        fun getDatabase(context: Context): StudentDatabase {
            if (!::INSTANCE.isInitialized) {
                synchronized(StudentDatabase::class.java) {
                    INSTANCE =
                        Room.databaseBuilder(context, StudentDatabase::class.java, "bluePresenceDB")
                            .addMigrations(MIGRATION_1_2)
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //IMPLEMENTAR O NECESSÁRIO
            }

        }

    }
}