package com.tayyib.myapplication.repository

import com.tayyib.myapplication.api.Api
import com.tayyib.myapplication.models.hidden_type.HiddenTypeResponse
import com.tayyib.myapplication.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ChangeHiddenTypeRepository @Inject constructor(private val api: Api) {
    suspend fun changeHiddenType(token: String,tranId: String, newHiddenType: String): Flow<Resource<HiddenTypeResponse>> {

        return flow {

            emit(Resource.Loading())

            val response = try{
                api.changeHiddenType(token,tranId,newHiddenType)

            }
            catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Ağ bağlantısı hatası. Lütfen internet bağlantınızı kontrol edin.", null))
                return@flow
            } catch (e: HttpException) {
                val errorMessage = when (e.code()) {
                    401 -> "Unauthorized. Please check your credentials."
                    403 -> "Bu işlemi gerçekleştirmek için yetkiniz yok."
                    404 -> "Aradığınız kaynak bulunamadı. Lütfen URL'yi kontrol edin."
                    500 -> "Sunucuda bir hata oluştu. Lütfen daha sonra tekrar deneyin."
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