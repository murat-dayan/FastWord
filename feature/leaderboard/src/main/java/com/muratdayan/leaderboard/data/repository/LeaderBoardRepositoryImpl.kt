package com.muratdayan.leaderboard.data.repository

import com.muratdayan.common.AppError
import com.muratdayan.common.DataError
import com.muratdayan.common.Result
import com.muratdayan.domain.model.UserDataModel
import com.muratdayan.leaderboard.domain.repository.LeaderBoardRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LeaderBoardRepositoryImpl  @Inject constructor(
    private val supabaseClient: SupabaseClient
): LeaderBoardRepository{
    override fun getEveryone(): Flow<Result<List<UserDataModel>, AppError>> = flow {
        try {
            val response = supabaseClient
                .from("users")
                .select(Columns.raw("*"))
                .decodeList<UserDataModel>()

            if (response.isEmpty()){
                emit(Result.Error(DataError.Remote.ServerError))
            }else{
                emit(Result.Success(response))
            }
        }catch (e:Exception){
            emit(Result.Error(DataError.Remote.ServerError))
        }
    }


}