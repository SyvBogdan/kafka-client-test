package com.kafka.test

import java.util.concurrent.atomic.AtomicLong

import slick.jdbc.JdbcProfile

object Global {

    val globalCounter = new AtomicLong(0)

    type DbAPI = JdbcProfile#API

    type TableType[T] = DbAPI#Table[T]

    type TableQueryType[E <: TableType[_]] = DbAPI#TableQuery[E]

}
