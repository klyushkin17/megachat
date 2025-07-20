package com.example.chat_impl.data.dataSources

import android.content.Context
import com.example.chat_impl.data.local.dataStore.UserDataStore
import com.example.core.errors.DataError
import com.example.core.network.Result
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class UserDataSourceImp @Inject constructor(
    private val userDataStore: UserDataStore
): UserDataSource {

    override suspend fun getUserId(context: Context): Result<String, DataError.Local> =
        try {
            val userId = userDataStore.getUserId()
            Result.Success(userId)
        } catch (e: IllegalStateException) {
            Result.Error(DataError.Local.DATA_NOT_FOUND)
        } catch (e: IOException) {
            Result.Error(DataError.Local.IO_ERROR)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.Error(DataError.Local.UNKNOWN)
        }
}