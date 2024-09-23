package com.tayyib.myapplication.repository

import com.tayyib.myapplication.api.Api
import com.tayyib.myapplication.models.forgotpassword.ForgotPasswordResponse
import com.tayyib.myapplication.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ForgotPasswordRepository @Inject constructor(private val api: Api){

    suspend fun forgotPassword(email: String, language: String): Flow<Resource<ForgotPasswordResponse>>
    {
        return flow{
            emit(Resource.Loading())

            val response = try{
                api.forgotPassword(email,language)
            }
            catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Network error. Please check your internet connection and try again.", null))
                return@flow
            } catch (e: HttpException) {
                val errorMessage = when (e.code()) {
                    401 -> "Unauthorized. Please check your credentials."
                    403 -> "Forbidden. You don't have permission to access this resource."
                    404 -> "Resource not found. Please check the server endpoint."
                    500 -> "Internal server error. Please try again later."
                    else -> "HTTP error ${e.code()}. Please try again."
                }
                emit(Resource.Error(errorMessage, null))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("An error occurred: ${e.message}", null))
                return@flow
            }
            emit(Resource.Success(response))


        }

    }

}