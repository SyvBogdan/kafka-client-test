package com.kafka.test.model.repository

import scala.concurrent.Future

trait BaseRepository[E, ID] {

  def get(id: ID): Future[E]

  def getAll: Future[Seq[E]]

  def put(entity: E): Future[Boolean]

}
