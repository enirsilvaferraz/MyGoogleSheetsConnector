package com.eferraz.mygooglesheetsconnector.archtectureImpl.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface GenericRoomDatasource<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg obj: T)

    @Delete
    fun delete(obj: T)
}