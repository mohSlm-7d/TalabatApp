package com.demo.foodorderanddeliveryappkotlin
import com.demo.foodorderanddeliveryappkotlin.UserDbHelper
import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri

class UserContentProvider : ContentProvider() {
    private lateinit var dbHelper: SQLiteOpenHelper

    companion object {
        private const val USERS = 1
        private const val USER_ID = 2
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(UserContract.AUTHORITY, UserContract.PATH, USERS)
            uriMatcher.addURI(UserContract.AUTHORITY, "${UserContract.PATH}/#", USER_ID)
        }
    }

    override fun onCreate(): Boolean {
        dbHelper = UserDbHelper(context!!)
        return true
    }

    override fun query(
        uri: Uri, projection: Array<out String>?, selection: String?,
        selectionArgs: Array<out String>?, sortOrder: String?
    ): Cursor? {
        val db = dbHelper.readableDatabase
        val match = uriMatcher.match(uri)
        val cursor: Cursor?

        when (match) {
            USERS -> {
                cursor = db.query(
                    UserContract.UserEntry.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
                )
            }
            USER_ID -> {
                val email = uri.lastPathSegment
                val whereClause = "${UserContract.UserEntry.COLUMN_EMAIL} = ?"
                val whereArgs = arrayOf(email)
                cursor = db.query(
                    UserContract.UserEntry.TABLE_NAME,
                    projection,
                    whereClause,
                    whereArgs,
                    null,
                    null,
                    sortOrder
                )
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }

        cursor?.setNotificationUri(context?.contentResolver, uri)
        return cursor
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val db = dbHelper.writableDatabase
        val match = uriMatcher.match(uri)
        val insertedId: Long

        when (match) {
            USERS -> {
                insertedId = db.insert(UserContract.UserEntry.TABLE_NAME, null, values)
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }

        context?.contentResolver?.notifyChange(uri, null)
        return Uri.withAppendedPath(UserContract.CONTENT_URI, insertedId.toString())
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val db = dbHelper.writableDatabase
        val match = uriMatcher.match(uri)
        val updatedRows: Int

        when (match) {
            USERS -> {
                updatedRows = db.update(UserContract.UserEntry.TABLE_NAME, values, selection, selectionArgs)
            }
            USER_ID -> {
                val email = uri.lastPathSegment
                val whereClause = "${UserContract.UserEntry.COLUMN_EMAIL} = ?"
                val whereArgs = arrayOf(email)
                updatedRows = db.update(UserContract.UserEntry.TABLE_NAME, values, whereClause, whereArgs)
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }

        context?.contentResolver?.notifyChange(uri, null)
        return updatedRows
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val db = dbHelper.writableDatabase
        val match = uriMatcher.match(uri)
        val deletedRows: Int

        when (match) {
            USERS -> {
                deletedRows = db.delete(UserContract.UserEntry.TABLE_NAME, selection, selectionArgs)
            }
            USER_ID -> {
                val email = uri.lastPathSegment
                val whereClause = "${UserContract.UserEntry.COLUMN_EMAIL} = ?"
                val whereArgs = arrayOf(email)
                deletedRows = db.delete(UserContract.UserEntry.TABLE_NAME, whereClause, whereArgs)
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }

        context?.contentResolver?.notifyChange(uri, null)
        return deletedRows
    }

    override fun getType(uri: Uri): String? {
        val match = uriMatcher.match(uri)

        return when (match) {
            USERS -> "vnd.android.cursor.dir/${UserContract.AUTHORITY}.${UserContract.PATH}"
            USER_ID -> "vnd.android.cursor.item/${UserContract.AUTHORITY}.${UserContract.PATH}"
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }
}
