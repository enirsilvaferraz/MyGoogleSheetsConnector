package com.eferraz.mygooglesheetsconnector.archtecture.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface GenericDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg obj: T)

    @Delete
    fun delete(obj: T)

    suspend fun get(): MutableList<T>
}