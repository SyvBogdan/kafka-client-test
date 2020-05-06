package com.kafka.test.model

import com.kafka.test.model.entity.User
import slick.basic.DatabaseConfig
import slick.basic.DatabaseConfig.forConfig
import slick.jdbc.JdbcProfile
import slick.lifted.{AbstractTable, TableQuery}
import slick.model.Table
import slick.profile.RelationalProfile
import slick.relational.RelationalTableComponent

object DbObjectComponent {
  val dataBaseConfig: DatabaseConfig[JdbcProfile] = forConfig("mariaDbTest")

  type CTable[T] = RelationalTableComponent#Table[T]

  type CTableQuery[E <: AbstractTable[_]] = TableQuery[E]
}
