package com.eferraz.mygooglesheetsconnector.archtectureImpl.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.eferraz.mygooglesheetsconnector.archtecture.datasource.BaseReadableDataSource
import com.eferraz.mygooglesheetsconnector.archtecture.datasource.BaseWritableDataSource
import kotlinx.coroutines.flow.Flow

interface GenericRoomDatasource<T> : BaseReadableDataSource<T>, BaseWritableDataSource<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert(obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert(vararg obj: T)

    @Delete
    override fun delete(obj: T)

    override fun get(): Flow<MutableList<T>>
}