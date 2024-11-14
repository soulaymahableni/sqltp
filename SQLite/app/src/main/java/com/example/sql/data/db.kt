package com.example.sql.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context) : SQLiteOpenHelper(context, "users.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE users (name TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    fun insertUser(name: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("name", name)
        }
        db.insert("users", null, values)
        db.close()
    }

    fun getAllUsers(): List<String> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT name FROM users", null)
        val userList = mutableListOf<String>()
        while (cursor.moveToNext()) {
            userList.add(cursor.getString(0))
        }
        cursor.close()
        db.close()
        return userList
    }

    fun deleteUser(name: String) {
        val db = writableDatabase
        db.delete("users", "name = ?", arrayOf(name))
        db.close()
    }

    fun updateUser(oldName: String, newName: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("name", newName)
        }
        db.update("users", values, "name = ?", arrayOf(oldName))
        db.close()
    }
}