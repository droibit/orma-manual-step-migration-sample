package com.github.droibit.ormpmigration.sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.gfx.android.orma.migration.ManualStepMigration.ChangeStep
import com.github.gfx.android.orma.migration.ManualStepMigration.Helper
import com.github.gfx.android.orma.migration.OrmaMigration
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val orma = OrmaDatabase.builder(this)
        .name("book.db")
        .migrationEngine(
            OrmaMigration.builder(this)
                .schemaHashForSchemaDiffMigration(OrmaDatabase.SCHEMA_HASH)
                .step(1, object : ChangeStep() {
                  override fun change(helper: Helper) = Unit
                })
                .build()
        ).build()
    thread {
      orma.migrate()
//      orma.insertIntoBook(Book(id = 0, title = "test1"))
//      orma.insertIntoBook(Book(id = 0, title = "test2"))
//      val books = orma.selectFromBook().toList()
//      Log.d(BuildConfig.BUILD_TYPE, books.toString())
    }
  }
}
