package com.kafka.test.http.api

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import com.kafka.test.auth.AuthComponent
import com.kafka.test.consumer.ConsumerComponent
import com.kafka.test.logs.LogComponent
import com.kafka.test.model.repository.UserRepositoryComponent
import com.kafka.test.producer.{ProducerComponent, ProducerConfig}
import com.kafka.test.service.impl.SenderServiceComponent

import scala.util.{Failure, Success}

trait ApiRoute {

  this: SenderServiceComponent with LogComponent
    with ProducerConfig =>

  println(getCurrentCLass)

  val apiRoute: Route = path("hello") {

    get {
      onComplete(sendMessage("hello")) {
        case Success(value) => complete(s"Sent was Successfull $value")
        case Failure(ex) => complete(s"Error: $ex")
      }
    }
  }
}
