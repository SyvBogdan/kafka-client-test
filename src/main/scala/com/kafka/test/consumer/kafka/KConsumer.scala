package com.kafka.test.consumer.kafka

import java.time.Duration
import java.time.temporal.ChronoUnit.MILLIS
import java.util.Collections.singletonList
import java.util.concurrent.atomic.AtomicLong

import scala.collection.JavaConverters._
import com.kafka.test.consumer.MessageConsumer
import org.apache.kafka.clients.consumer.{ConsumerRecord, ConsumerRecords, KafkaConsumer}

import scala.util.{Failure, Success, Try}

trait KConsumer extends MessageConsumer[String] {
  val id: Int
  val topic: String

  val globalConsumed: AtomicLong
  val kafkaConsumer: KafkaConsumer[String, String]

  // consumer thread implementation
  override def subscribe(): Unit = {
    val result = Try({
      kafkaConsumer.subscribe(singletonList(topic))
    })

    result match {
      case Success(_) =>
        println(s"Successfully subscribed to consumer $id")

        while (true) {
          val records = kafkaConsumer.poll(Duration.of(100l, MILLIS))
          for (record: ConsumerRecord[String, String] <- records.asScala) {
            globalConsumed.incrementAndGet()
            println(s"ConsumerId = $id, offset =${record.offset()} recordId = ${record.key}, recordVal =${record.value()}")
          }
        }
      case Failure(exception) =>
        println(s"Failed subscribed to consumer $id")
    }
  }

  override def run(): Unit = {
    val subscribeResult: Unit = subscribe()
  }
}
