package com.kafka.test.model.repository

import com.kafka.test.Global.{TableQueryType, TableType}
import com.kafka.test.logs.LogComponent
import com.kafka.test.model.DbComponent
import com.kafka.test.model.entity.User
import slick.ast.ColumnOption.AutoInc
import slick.basic.DatabaseConfig
import slick.dbio.DBIOAction
import slick.jdbc.JdbcProfile
import slick.lifted._

//import com.kafka.test.model.DbObjectComponent.dataBaseConfig.profile.api._

import slick.relational._
import com.kafka.test.model.repository.impl.AbstractRepository

trait UserRepositoryComponent {

  this: DbComponent =>

  import scala.concurrent.Future

  import dataBaseConfig.profile.api._

  val userRepository: UserRepository

  trait UserRepository extends BaseRepository[User, Int]

  val userQuery: TableQuery[Users] = TableQuery[Users]


  class Users(tag: Tag) extends Table[User](tag, Some("DATA"), "USERS") {

    def userId: Rep[Int] = column[Int]("USER_ID", O.PrimaryKey, AutoInc)

    def login: Rep[String] = column[String]("LOGIN", O.Unique)

    def encPass: Rep[String] = column[String]("ENC_PASS")

    def desc: Rep[String] = column[String]("DESCRIPTION")

    override def * = (userId, login, encPass, desc) <> (User.tupled, User.unapply)
  }

  class UserRepositoryImpl(databaseConfig: DatabaseConfig[JdbcProfile]) extends AbstractRepository[Users, User, Int](databaseConfig) with UserRepository with LogComponent {

    override val tableQuery: TableQuery[Users] = userQuery

    override def get(id: Int): Future[User] = getCriteria(id, _.userId === id)
  }

}
