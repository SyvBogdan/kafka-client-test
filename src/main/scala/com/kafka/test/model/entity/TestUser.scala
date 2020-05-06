package com.kafka.test.model.entity

object TestUser {

  def main(args: Array[String]): Unit = {

    val user = User(1, "user1", "enc")

    val User(id, login, pass, desc) = user

    println(login)

  }

}
