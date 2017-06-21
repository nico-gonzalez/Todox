package com.sample.android.todox.model.items

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "items")
class ItemModel(@PrimaryKey(autoGenerate = true) var id: Int = 0,
                @ColumnInfo(name = "title") var title: String? = null,
                @ColumnInfo(name = "description") var description: String? = null
)