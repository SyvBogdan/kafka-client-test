package com.kafka.test.service

import com.kafka.test.model.dto.ResourceDto

import scala.concurrent.Future

trait EventResourcesService {

  def toResources(login: String, brokerType: String, eventId: Int): Future[Seq[ResourceDto]]

}
