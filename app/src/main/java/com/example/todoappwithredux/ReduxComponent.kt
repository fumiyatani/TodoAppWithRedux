package com.example.todoappwithredux

import kotlinx.coroutines.flow.Flow


/**
 * Store から発行される
 * アプリ全体の状態を表現する
 * DataClass として定義する
 */
interface StateType

/**
 * View から発行される
 * 状態変更を表現する
 * Sealed Class として定義することで、 Reducer をシンプルにする
 */
interface ActionType

interface ReducerType<STATE: StateType, ACTION: ActionType> {
    fun reduce(currentState: STATE, action: ACTION): STATE
}

interface StoreType<STATE: StateType> {
    suspend fun dispatch(action: ActionType)
    suspend fun observable(): Flow<STATE>// Coroutine の Flow で返却するために collect にしている
    fun addMiddleware(middleware: MiddlewareType)
    fun removeMiddleware(middleware: MiddlewareType)
}

interface MiddlewareType {
    suspend fun before(): ActionType
    suspend fun after(): ActionType
}