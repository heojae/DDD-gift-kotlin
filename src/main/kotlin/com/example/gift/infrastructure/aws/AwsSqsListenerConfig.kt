package com.example.gift.infrastructure.aws

import com.amazonaws.services.sqs.AmazonSQSAsync
import lombok.extern.slf4j.Slf4j
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory
import org.springframework.cloud.aws.messaging.listener.QueueMessageHandler
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer
import org.springframework.context.annotation.Bean
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.messaging.handler.annotation.support.PayloadMethodArgumentResolver
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.stereotype.Component


@Slf4j
@Component
class AwsSqsListenerConfig {
    @Bean
    fun simpleMessageListenerContainerFactory(amazonSQSAsync: AmazonSQSAsync): SimpleMessageListenerContainerFactory {
        val factory = SimpleMessageListenerContainerFactory()
        factory.setAmazonSqs(amazonSQSAsync)
        factory.setAutoStartup(true)
        return factory
    }

    @Bean
    fun simpleMessageListenerContainer(
        simpleMessageListenerContainerFactory: SimpleMessageListenerContainerFactory,
        queueMessageHandler: QueueMessageHandler,
        messageThreadPoolTaskExecutor: ThreadPoolTaskExecutor
    ): SimpleMessageListenerContainer {
        val container = simpleMessageListenerContainerFactory.createSimpleMessageListenerContainer()
        container.setMessageHandler(queueMessageHandler)
        container.setTaskExecutor(messageThreadPoolTaskExecutor)
        return container
    }

    @Bean
    @ConditionalOnMissingBean(QueueMessageHandlerFactory::class)
    fun queueMessageHandlerFactory(amazonSQSAsync: AmazonSQSAsync): QueueMessageHandlerFactory {
        val factory = QueueMessageHandlerFactory()
        factory.setAmazonSqs(amazonSQSAsync)
        val messageConverter = MappingJackson2MessageConverter()
        messageConverter.isStrictContentTypeMatch = false
        factory.setArgumentResolvers(
            listOf<HandlerMethodArgumentResolver>(
                PayloadMethodArgumentResolver(
                    messageConverter
                )
            )
        )
        return factory
    }

    @Bean
    @ConditionalOnMissingBean(QueueMessageHandler::class)
    fun queueMessageHandler(queueMessageHandlerFactory: QueueMessageHandlerFactory): QueueMessageHandler {
        return queueMessageHandlerFactory.createQueueMessageHandler()
    }

    @Bean
    fun messageThreadPoolTaskExecutor(): ThreadPoolTaskExecutor {
        val taskExecutor = ThreadPoolTaskExecutor()
        taskExecutor.setThreadNamePrefix("sqs-")
        taskExecutor.corePoolSize = 8
        taskExecutor.maxPoolSize = 100
        taskExecutor.afterPropertiesSet()
        return taskExecutor
    }
}