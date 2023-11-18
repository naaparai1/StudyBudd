package com.tmu.studybudd

import android.app.Application
import com.tmu.studybudd.fakedb.FakeDB
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

@HiltAndroidApp
class StudyBuddApp : Application() {
    override fun onCreate() {
        super.onCreate()
        CoroutineScope(IO).launch {
            FakeDB.loadFakeDatas(this@StudyBuddApp)
        }
    }
}
