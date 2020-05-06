package com.kafka.test.consumer.kafka

import com.kafka.test.consumer.MessageConsumer

trait MessageConsumerGroup {

  val groupConsumers: IndexedSeq[MessageConsumer[_]]
}
