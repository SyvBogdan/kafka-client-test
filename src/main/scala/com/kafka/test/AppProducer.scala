package com.kafka.test

import java.util.concurrent.Executors

import akka.http.scaladsl.server.{HttpApp, Route}
import com.kafka.test.components.ProducerComponentRegistry
import com.kafka.test.crypto.Encrypter
import com.kafka.test.crypto.Encrypter._
import com.kafka.test.model.entity.{EventDirection, User}

import scala.collection.immutable
import scala.concurrent.Await._
import scala.concurrent.duration.Duration
import scala.concurrent.duration.Duration.Inf
import scala.util.Random

object AppProducer extends HttpApp with ProducerComponentRegistry {

  def main(args: Array[String]): Unit = {

    val exec = Executors.newFixedThreadPool(100)

    /*exec.submit(new Runnable {
     override def run(): Unit = {
       println("StartProduce")
       //for (f <- 1 to 10000000) {
       // sending request async
       println("sending .....")
       //val res = kafkaProducerComponent.send(f.toString)
       // for test waiting result, but in prod all process with non-blocking pipeline
       // r: Boolean = result(res, Duration.apply(10, SECONDS))
       // sequence(List(res, Future(f)))
       // }
     }
   })*/

  }

  override protected def routes: Route = apiRoute


}
