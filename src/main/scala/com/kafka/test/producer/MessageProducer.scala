package com.kafka.test.producer

import scala.concurrent.Future

trait MessageProducer[M] {
  def sendMessage(message: M): Future[Boolean]
}