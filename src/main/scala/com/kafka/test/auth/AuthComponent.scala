package com.kafka.test.auth

import scala.concurrent.Future
import concurrent.ExecutionContext.Implicits.global
trait AuthComponent {

  val auth: Authenticator

  trait Authenticator {
    def authenticate(login: String, pass: String): Future[Boolean]
  }

  class BasicAuth extends Authenticator{
    override def authenticate(login: String, pass: String): Future[Boolean] = Future[Boolean]{true}
  }
}
