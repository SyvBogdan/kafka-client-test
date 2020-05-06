package com.kafka.test

import com.kafka.test.components.ConsumerComponentRegistry

object AppConsumer extends ConsumerComponentRegistry {

  def main(args: Array[String]): Unit = {

    kafkaConsumersComponent.initConsumers
  }
}
