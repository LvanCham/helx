package com.cham.alice.entity

/**
 * Hello World
 * Date: 2019/12/31
 * Author: Cham
 */
data class RowDataEntity<T>(
    val curPage: Int,
    val datas: List<T>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)



