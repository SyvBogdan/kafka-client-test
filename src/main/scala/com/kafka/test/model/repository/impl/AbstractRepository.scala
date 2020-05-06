package com.kafka.test.model.repository.impl

import com.kafka.test.Global.{DbAPI, TableQueryType, TableType}
import com.kafka.test.model.DbObjectComponent
import com.kafka.test.model.DbObjectComponent._
import com.kafka.test.model.repository.BaseRepository
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

import scala.concurrent.Future
import concurrent.ExecutionContext.Implicits.global

abstract class AbstractRepository[T  <: TableType[E] , E, ID](dConfig: DatabaseConfig[JdbcProfile]) extends BaseRepository[E, ID] {

  import dConfig.profile.api._

  val dc = dConfig

  val tableQuery: TableQueryType[T]

  protected def getCriteria(id: ID, criteria: T => Rep[Boolean]): Future[E] =
    dc.db.run(tableQuery.filter(criteria).result.head)

  def getAll: Future[Seq[E]] = dc.db.run(tableQuery.result)

  def put(entity: E): Future[Boolean] = {
    dc.db.run(DBIO.seq(
      tableQuery += entity))
      .map(_ => {
        true
      }).recover { case e: Exception =>
      false
    }
  }
}