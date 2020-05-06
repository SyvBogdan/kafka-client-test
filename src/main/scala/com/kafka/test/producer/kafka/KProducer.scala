package com.kafka.test.producer.kafka

import java.util.Properties
import java.util.UUID.randomUUID
import java.util.concurrent.CompletableFuture

import com.kafka.test.logs.LogComponent
import com.kafka.test.producer.MessageProducer
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord, RecordMetadata}
import concurrent.ExecutionContext.Implicits.global
import scala.compat.java8.FutureConverters._
import scala.concurrent.Future

class KProducer(val props: Properties, val topic: String) extends MessageProducer[String] with LogComponent {

  val kafkaProducer: KafkaProducer[String, String] = new KafkaProducer[String, String](props)

  override def sendMessage(message: String): Future[Boolean] = {

    val result = new CompletableFuture[Boolean]() /// investigate promise
    val key = randomUUID().toString /// cid
    val input = new ProducerRecord[String, String](topic, key, message)

    kafkaProducer.send(input, (_: RecordMetadata, exception: Exception) => {

      exception match {

        case ex if ex != null =>
          result.complete(false)
          println(s"Exception was happened:$ex, with ID: $key")
        case _ =>
          result.complete(true)
          println()
          //println(s"Successfully sent message with ID: $key")
      }
    })
    toScala(result)
  }
}
