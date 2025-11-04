package com.bharat.app5.feature_legal.data.di

import com.bharat.app5.feature_legal.data.repository.LegalRepositoryIml
import com.bharat.app5.feature_legal.domain.repository.LegalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class LegalModule {

    @Binds
    @ViewModelScoped
    abstract fun bindLegalRepository(
        legalRepository : LegalRepositoryIml
    ) : LegalRepository


}