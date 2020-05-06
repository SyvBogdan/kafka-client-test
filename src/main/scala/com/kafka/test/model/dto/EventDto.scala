package com.kafka.test.model.dto

import java.util.Date

case class EventDto(subscriberId: String,
                    eventId: Int,
                    subscriberType: String,
                    eventTime: Date,
                    params: Map[String, String])
