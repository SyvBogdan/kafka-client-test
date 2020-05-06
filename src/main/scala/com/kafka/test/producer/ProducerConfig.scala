package com.kafka.test.producer

import java.util.Properties

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerConfig._

trait ProducerConfig {

  val profile = "test"

  lazy val props: Properties = new Properties()
  props.put("bootstrap.servers", "192.168.1.48:9092, 192.168.1.47:9092,192.168.1.49:9092")
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put(DELIVERY_TIMEOUT_MS_CONFIG, "5000")
  props.put(TRANSACTION_TIMEOUT_CONFIG, "5000")
  props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, "3000")
  props.put(ProducerConfig.LINGER_MS_CONFIG, "2000")
  props.put("acks", "all")


  def getTopic: String = {
    Thread.sleep(1000)
    "test"
  }

}
