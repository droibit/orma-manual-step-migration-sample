package com.github.droibit.ormpmigration.sample

import com.github.gfx.android.orma.annotation.Column
import com.github.gfx.android.orma.annotation.PrimaryKey
import com.github.gfx.android.orma.annotation.Setter
import com.github.gfx.android.orma.annotation.Table

@Table
data class Book(
  @PrimaryKey(autoincrement = true) @Setter("id") var id: Long,
  @Column @Setter("title") var title: String
)