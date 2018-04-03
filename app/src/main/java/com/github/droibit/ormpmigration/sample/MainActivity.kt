package com.github.droibit.ormpmigration.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.github.gfx.android.orma.migration.ManualStepMigration.ChangeStep
import com.github.gfx.android.orma.migration.ManualStepMigration.Helper
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val orma = OrmaDatabase.builder(this)
        .name("book.db")
        .migrationStep(1, object : ChangeStep() {
          override fun change(helper: Helper) {
            Log.d(BuildConfig.BUILD_TYPE, "Steps to execute migration at version up. It is not executed.")
          }
        })
        .migrationStep(3, object : ChangeStep() {
          override fun change(helper: Helper) {
            Log.d(BuildConfig.BUILD_TYPE, "Migration, version 3")
            // Dummy query to update db version.
            helper.execSQL("DROP TABLE IF EXISTS hoge")
          }
        })
        .build()
    thread {
      orma.migrate()
//      orma.insertIntoBook(Book(id = 0, title = "test1"))
//      orma.insertIntoBook(Book(id = 0, title = "test2"))
//      val books = orma.selectFromBook().toList()
//      Log.d(BuildConfig.BUILD_TYPE, books.toString())
    }
  }
}
