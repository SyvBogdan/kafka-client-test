package com.kafka.test.service.impl

import com.kafka.test.producer.ProducerComponent

import scala.concurrent.Future

trait SenderServiceComponent {

  this: ProducerComponent[String] =>

  def sendMessage(message: String): Future[Boolean] = {
    send(message)
  }
}
