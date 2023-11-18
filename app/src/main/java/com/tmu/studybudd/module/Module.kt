package com.tmu.studybudd.module

import com.tmu.studybudd.repo.student.ChatRepo
import com.tmu.studybudd.repo.student.ChatRepoImpl
import com.tmu.studybudd.repo.student.StudentLoginRepo
import com.tmu.studybudd.repo.student.LoginRepoImpl
import com.tmu.studybudd.repo.student.PreferenceRepo
import com.tmu.studybudd.repo.student.PreferenceRepoImpl
import com.tmu.studybudd.repo.student.StudentAndGroupRepo
import com.tmu.studybudd.repo.student.StudentAndGroupRepoImpl
import com.tmu.studybudd.repo.mcq.MCQRepo
import com.tmu.studybudd.repo.mcq.MCQRepoImpl
import com.tmu.studybudd.repo.teacher.TeacherLoginRepo
import com.tmu.studybudd.repo.teacher.TeacherLoginRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun providePreference(): PreferenceRepo {
        return PreferenceRepoImpl
    }

    @Singleton
    @Provides
    fun provideStudentAndGroupRepo(): StudentAndGroupRepo {
        return StudentAndGroupRepoImpl
    }

    @Singleton
    @Provides
    fun provideLoginRepo(): StudentLoginRepo {
        return LoginRepoImpl
    }

    @Singleton
    @Provides
    fun provideChatRepo(): ChatRepo {
        return ChatRepoImpl
    }

    @Singleton
    @Provides
    fun provideMCQRepo(): MCQRepo {
        return MCQRepoImpl
    }

    @Singleton
    @Provides
    fun provideTeacherLoginRepo(): TeacherLoginRepo {
        return TeacherLoginRepoImpl
    }
}
