package com.demo.foodorderanddeliveryappkotlin

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "user.db"
        const val DATABASE_VERSION = 1

        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${UserContract.UserEntry.TABLE_NAME} (" +
                    "${UserContract.UserEntry.COLUMN_FIRST_NAME} TEXT," +
                    "${UserContract.UserEntry.COLUMN_LAST_NAME} TEXT," +
                    "${UserContract.UserEntry.COLUMN_EMAIL} TEXT," +
                    "${UserContract.UserEntry.COLUMN_PASSWORD} TEXT," +
                    "${UserContract.UserEntry.COLUMN_PHONE_NO} TEXT)"

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${UserContract.UserEntry.TABLE_NAME}"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
}
