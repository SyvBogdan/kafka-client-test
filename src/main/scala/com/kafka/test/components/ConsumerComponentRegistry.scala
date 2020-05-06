package com.kafka.test.components

import java.util.concurrent.atomic.AtomicLong

import com.kafka.test.Global.globalCounter
import com.kafka.test.consumer.kafka.{KConsumer, MessageConsumerGroup}
import com.kafka.test.consumer.{ConsumerComponent, ConsumerConfig, MessageConsumer}
import org.apache.kafka.clients.consumer.KafkaConsumer

trait ConsumerComponentRegistry extends ConsumerConfig {

  // our beans

  // prototype

  private def getConsumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)

  // list consumer beans
  private val consumers: IndexedSeq[MessageConsumer[String]] =
    for (i <- 1 to 10) yield {
      new MessageConsumer[String] with KConsumer {
        override val id: Int = i
        override val topic: String = getTopic
        override val kafkaConsumer: KafkaConsumer[String, String] = getConsumer
        override val globalConsumed: AtomicLong = globalCounter
      }
    }

  //main consumer component
  val kafkaConsumersComponent: ConsumerComponent = new ConsumerComponent with MessageConsumerGroup {
    override val groupConsumers = consumers
  }
}
