package com.kafka.test.model

import slick.basic.DatabaseConfig
import slick.basic.DatabaseConfig.forConfig
import slick.jdbc.JdbcProfile

trait DbComponent {

  val dataBaseConfig: DatabaseConfig[JdbcProfile]

}

object DbComponent{

  val dataBaseConfig = forConfig("mariaDbTest")
}
