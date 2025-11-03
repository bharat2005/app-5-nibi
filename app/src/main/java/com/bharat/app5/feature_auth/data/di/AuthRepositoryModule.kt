package com.bharat.app5.feature_auth.data.di

import com.bharat.app5.feature_auth.data.repository.AuthRepositoryIml
import com.bharat.app5.feature_auth.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthRepositoryModule {

    @Binds
    @Singleton
    abstract fun provideAuthRepository(
        authRepositoryIml : AuthRepositoryIml
    ) : AuthRepository
}