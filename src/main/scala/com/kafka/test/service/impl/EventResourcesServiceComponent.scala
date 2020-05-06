package com.kafka.test.service.impl

import com.kafka.test.model.dto.ResourceDto
import com.kafka.test.model.repository.EventResourcesRepositoryComponent
import com.kafka.test.service.EventResourcesService

import scala.collection.mutable
import scala.concurrent.Await.result
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success, Try}

trait EventResourcesServiceComponent {

  this: EventResourcesRepositoryComponent =>

  val eventResourcesService: EventResourcesService

  class EventResourcesServiceImpl extends EventResourcesService {

    val resources = new mutable.HashMap[String, mutable.Map[String, mutable.Map[Int, mutable.AbstractBuffer[ResourceDto]]]]()


    Try(result(eventResourcesRepository.getEventDirections("write"), Duration.Inf)) match {
      case Success(eventDirections) =>
        for (eventDirection <- eventDirections) {
          resources.getOrElseUpdate(eventDirection.login, new mutable.HashMap[String, mutable.Map[Int, mutable.AbstractBuffer[ResourceDto]]]())
            .getOrElseUpdate(eventDirection.brokerType, new mutable.HashMap[Int, mutable.AbstractBuffer[ResourceDto]]())
            .getOrElseUpdate(eventDirection.eventId, new mutable.ListBuffer[ResourceDto]) += ResourceDto(eventDirection.resourceType, eventDirection.resourceName)
        }
      case Failure(ex) => println(s"Failed: $ex")
    }

    override def toResources(login: String, brokerType: String, eventId: Int): Future[Seq[ResourceDto]] = Future {

      resources.get(login) match {
        case Some(brokers) => brokers.get(brokerType) match {
          case Some(events) => events.get(eventId) match {
            case Some(resourceList) => resourceList
            case None => List[ResourceDto]()
          }
          case None => List[ResourceDto]()
        }
        case None => List[ResourceDto]()
      }
    }

    private def clear(): Unit = resources.clear
  }

}
