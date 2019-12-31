package com.cham.alice.base




data class BaseResult<T>(val errorCode: Int,val data: T,  val errorMsg :String) {
    fun isSuccess() = errorCode == 0

}