package com.kafka.test.consumer.kafka

import java.util.concurrent.{ExecutorService, Executors, TimeUnit}

import com.kafka.test.Global
import com.kafka.test.Global._

trait GlobalExecutor {
  val pool: ExecutorService = Executors.newFixedThreadPool(100)

  val scheduledExecutorService = Executors.newSingleThreadScheduledExecutor()

/*  scheduledExecutorService.scheduleAtFixedRate(() => {

    println(s"Already Consumed: ${globalCounter.get()}")
  }, 5l, 1, TimeUnit.SECONDS)*/

}
