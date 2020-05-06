package com.kafka.test.consumer

trait MessageConsumer[T] extends Runnable{

  def subscribe(): Unit
}
