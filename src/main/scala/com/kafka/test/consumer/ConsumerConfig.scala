package com.kafka.test.consumer

import java.util.Properties

trait ConsumerConfig {

  lazy val props: Properties = new Properties()
  props.put("bootstrap.servers", "192.168.1.48:9092, 192.168.1.47:9092,192.168.1.49:9092")
  props.put("group.id", "test")
  props.put("enable.auto.commit", "true")
  props.put("auto.commit.interval.ms", "1000")
  props.put("session.timeout.ms", "30000")
  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")

  def getTopic: String = "test"

}
