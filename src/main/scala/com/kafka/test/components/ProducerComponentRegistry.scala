package com.kafka.test.components

import com.kafka.test.auth.AuthComponent
import com.kafka.test.http.api.ApiRoute
import com.kafka.test.logs.LogComponent
import com.kafka.test.model.DbComponent
import com.kafka.test.model.repository.{EventResourcesRepositoryComponent, UserRepositoryComponent}
import com.kafka.test.producer.kafka.{KProducer, MockKProducer}
import com.kafka.test.producer.{MessageProducer, ProducerComponent, ProducerConfig}
import com.kafka.test.service.EventResourcesService
import com.kafka.test.service.impl.{EventResourcesServiceComponent, SenderServiceComponent}
import slick.basic.DatabaseConfig.forConfig

trait ProducerComponentRegistry extends ProducerConfig
  with DbComponent
  with UserRepositoryComponent
  with SenderServiceComponent
  with ProducerComponent[String]
  //with AuthComponent
  with EventResourcesRepositoryComponent
  with EventResourcesServiceComponent
  with LogComponent
  with ApiRoute {

  //override val auth: Authenticator = new BasicAuth

  // singleton
  override val dataBaseConfig = forConfig(if(!profile.equals("test")) "mariaDb" else "mariaDbTest")

  // singleton
  override val userRepository: UserRepository = new UserRepositoryImpl(dataBaseConfig)

  // singleton
  override val messageProducer: MessageProducer[String] =
    if (!profile.equals("test")) new KProducer(props, getTopic) else new MockKProducer

  override val eventResourcesRepository: EventResourcesRepository = new EventResourcesRepositoryImpl

  override val eventResourcesService: EventResourcesService = new EventResourcesServiceImpl
}
