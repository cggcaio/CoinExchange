package com.cggcaio.coinexchange.core.base

abstract class BaseMapper<T, K> {
    abstract fun transformTo(source: T): K

    fun transformToList(source: List<T>) = source.map { item -> transformTo(source = item) }
}
