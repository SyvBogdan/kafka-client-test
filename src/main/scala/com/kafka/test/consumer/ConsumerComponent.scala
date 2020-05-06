package com.kafka.test.consumer

import java.util.concurrent.{ExecutorService, Executors}

import com.kafka.test.consumer.kafka.{GlobalExecutor, MessageConsumerGroup}

trait ConsumerComponent extends GlobalExecutor {

  this: MessageConsumerGroup =>

  def initConsumers: Boolean = {
    val initConsumers: Seq[MessageConsumer[_]] = groupConsumers

    for (consumer <- initConsumers) pool.submit(consumer)
    true
  }
}
