package com.kafka.test.model.repository

import com.kafka.test.model.DbComponent
import com.kafka.test.model.entity.EventDirection
import slick.jdbc.GetResult

import scala.concurrent.Future

trait EventResourcesRepositoryComponent {

  this: DbComponent =>

  import dataBaseConfig.profile.api._

  private implicit val getEventDirectionResult = GetResult(r => EventDirection(r.nextString, r.nextInt(), r.nextString, r.nextString, r.nextString))

   val eventResourcesRepository: EventResourcesRepository

  trait EventResourcesRepository {

    def getEventDirections(mode: String) : Future[Vector[EventDirection]]

  }

  class EventResourcesRepositoryImpl extends EventResourcesRepository {

    override def getEventDirections(mode: String) = dataBaseConfig.db.run(sql"""
        SELECT U.LOGIN,
        ER.EVENT_ID,
        RES.BROKER_TYPE,
        RES.RESOURCE_TYPE,
        RES.RESOURCE_NAME

        FROM DATA.USERS U
        JOIN DATA.USER_ROLES UR ON U.USER_ID = UR.USER_ID
        JOIN DATA.ROLES R ON UR.ROLE_ID = R.ROLE_ID
        JOIN DATA.RESOURCES RES ON R.RESOURCE_ID = RES.RESOURCE_ID
        JOIN DATA.EVENT_RESOURCES er ON RES.RESOURCE_ID = ER.RESOURCE_ID

        WHERE PERMISSION_MODE IN ($mode, 'all')

        GROUP BY U.LOGIN, ER.EVENT_ID, RES.BROKER_TYPE, RES.RESOURCE_NAME
        """.as[EventDirection])
  }
}
