package com.tayyib.myapplication.repository


import android.annotation.SuppressLint
import com.tayyib.myapplication.api.Api
import com.tayyib.myapplication.models.login.LoginRequest
import com.tayyib.myapplication.models.login.LoginResponse
import com.tayyib.myapplication.models.login.TokenResponse
import com.tayyib.myapplication.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import com.tayyib.myapplication.models.login.AuthResponse


class UserRepository @Inject constructor(private val api: Api,private var loginResponse: LoginResponse) {





    @SuppressLint("SuspiciousIndentation")
    suspend fun getTokenAndLogin(email: String, userPassword: String): Flow<Resource<AuthResponse>> {
        return flow {
            emit(Resource.Loading())
        val  response  =  try {
          val  tokenResponse: TokenResponse = api.getToken(
                    userId = "Mobil.Integration.Us",
                    password = "x(_MblBss.00??541*z=="
                )


            val  token = tokenResponse.token

                println("token : $token")


             val loginRequest = LoginRequest(email, userPassword)

             val  loginResult =   api.userLogin(token, loginRequest)

             println("id : ${loginResult.id}")



                    // LoginResponse nesnesini güncelle
            loginResponse.id = loginResult.id
            loginResponse.firstname = loginResult.firstname
            loginResponse.lastname = loginResult.lastname
            loginResponse.email = loginResult.email
            loginResponse.password = loginResult.password
            loginResponse.companyId = loginResult.companyId
            loginResponse.type = loginResult.type
            loginResponse.status = loginResult.status
            loginResponse.eBALicenceUserId = loginResult.eBALicenceUserId
            loginResponse.informationMails = loginResult.informationMails
            loginResponse.useQDMSSurvey = loginResult.useQDMSSurvey
            loginResponse.useBOYSSurvey = loginResult.useBOYSSurvey
            loginResponse.useEBASurvey = loginResult.useEBASurvey
            loginResponse.useEnsembleSurvey = loginResult.useEnsembleSurvey
            loginResponse.isSubscribe = loginResult.isSubscribe
            loginResponse.loginLanguageId = loginResult.loginLanguageId
            loginResponse.useCSPSurvey = loginResult.useCSPSurvey
            loginResponse.eBACertificate = loginResult.eBACertificate
            loginResponse.qDMSCertificate = loginResult.qDMSCertificate
            loginResponse.bEAMCertificate = loginResult.bEAMCertificate
            loginResponse.eNSEMBLECertificate = loginResult.eNSEMBLECertificate
            loginResponse.cSPCertificate = loginResult.cSPCertificate
            loginResponse.createDate = loginResult.createDate
            loginResponse.serviceDeskNo = loginResult.serviceDeskNo
            loginResponse.dontShowAgain = loginResult.dontShowAgain
            loginResponse.notifyInactive = loginResult.notifyInactive

            AuthResponse(tokenResponse, loginResult)


        } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Ağ bağlantısı hatası. Lütfen internet bağlantınızı kontrol edin.", null))
            return@flow
            } catch (e: HttpException) {
            val errorMessage = when (e.code()) {
                400-> "Girilen e-posta adresi veya şifre hatalıdır."
                401 -> "Girilen e-posta adresi veya şifre hatalıdır."
                403 -> "Bu işlemi gerçekleştirmek için yetkiniz yok."
                404 -> "Aradığınız kaynak bulunamadı. Lütfen URL'yi kontrol edin."
                500 -> "Sunucuda bir hata oluştu. Lütfen daha sonra tekrar deneyin."
                else -> "HTTP error ${e.code()}. Please try again."
            }



            emit(Resource.Error(errorMessage, null))
            return@flow
            }

        catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Bir hata oluştu lütfen tekrar deneyin: ${e.message}", null))
            return@flow
            }
            emit(Resource.Success(response))
        }
    }







}
