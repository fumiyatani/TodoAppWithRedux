package com.example.todoappwithredux.ui

import com.example.todoappwithredux.ActionType
import com.example.todoappwithredux.MiddlewareType
import com.example.todoappwithredux.StoreType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class AppStore(
    // 初期値の受け取り
    initialState: AppState
) : StoreType<AppState> {

    // TODO MutableState or MutableSharedFlow のどちらかを使う必要がある。
    //  ただどっちを使うかがわからないので調べる
    //  一応スレッドセーフらしいからこれで良いのかもしれない
    // 状態の保持
    private val stateFlow: MutableStateFlow<AppState> = MutableStateFlow(initialState)

    override suspend fun dispatch(action: ActionType) {
        /* 状態の変更 */
        val nextState = AppReducer.reduce(stateFlow.value, action as AppAction)
        stateFlow.emit(nextState)
    }

    override suspend fun observable(): Flow<AppState> {
        // 状態の通知
        return stateFlow
    }

    override fun addMiddleware(middleware: MiddlewareType) {
        // nop
    }

    override fun removeMiddleware(middleware: MiddlewareType) {
        // nop
    }

    companion object {
        fun getInstance(): AppStore {
            return AppStore(AppState())
        }
    }
}