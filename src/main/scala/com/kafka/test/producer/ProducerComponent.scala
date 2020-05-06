package com.kafka.test.producer

import scala.concurrent.Future

trait ProducerComponent[T] {

  val messageProducer: MessageProducer[T]

  def send( input: T): Future[Boolean] = messageProducer.sendMessage(input)
}
