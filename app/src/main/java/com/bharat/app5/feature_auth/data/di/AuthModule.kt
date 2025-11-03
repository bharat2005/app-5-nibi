package com.bharat.app5.feature_auth.data.di

import com.bharat.app5.feature_auth.data.repository.AuthRepositoryIml
import com.bharat.app5.feature_auth.domain.repository.AuthRepository
import com.bharat.app5.feature_auth.domain.usecase.RegisterUserUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    fun provideFirebaseAuth() : FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseFirestore() : FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    fun provideAuthRepository(
        auth : FirebaseAuth,
        firestore : FirebaseFirestore
    ) : AuthRepository = AuthRepositoryIml(auth, firestore)

    @Provides
    fun provideRegisterUserUseCase(
        authRepository: AuthRepository
    ) : RegisterUserUseCase = RegisterUserUseCase(authRepository)


}