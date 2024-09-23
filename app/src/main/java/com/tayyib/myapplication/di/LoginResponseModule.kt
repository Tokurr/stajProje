package com.tayyib.myapplication.di

import com.tayyib.myapplication.models.login.LoginResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginResponseModule {

    @Provides
    @Singleton
    fun provideLoginResponse(): LoginResponse {
        return LoginResponse(
            id = 0,
            firstname = "",
            lastname = "",
            email = "",
            password = null,
            companyId = 0,
            type = 0,
            status = 0,
            eBALicenceUserId = null,
            informationMails = null,
            useQDMSSurvey = false,
            useBOYSSurvey = false,
            useEBASurvey = false,
            useEnsembleSurvey = false,
            isSubscribe = false,
            loginLanguageId = 0,
            useCSPSurvey = false,
            eBACertificate = false,
            qDMSCertificate = false,
            bEAMCertificate = false,
            eNSEMBLECertificate = false,
            cSPCertificate = false,
            createDate = null,
            serviceDeskNo = null,
            dontShowAgain = false,
            notifyInactive = null
        )
    }
}
