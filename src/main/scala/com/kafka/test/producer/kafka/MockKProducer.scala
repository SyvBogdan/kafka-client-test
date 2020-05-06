package com.kafka.test.producer.kafka

import com.kafka.test.logs.LogComponent
import com.kafka.test.producer.MessageProducer

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class MockKProducer extends MessageProducer[String] with LogComponent{

  override def sendMessage(message: String): Future[Boolean] = Future {true}
}
