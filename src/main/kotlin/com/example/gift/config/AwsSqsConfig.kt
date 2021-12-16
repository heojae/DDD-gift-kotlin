package com.example.gift.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class AwsSqsConfig {

    @Value("\${cloud.aws.access-key}")
    private lateinit var awsAccessKey: String

    @Value("\${cloud.aws.secret-key}")
    private lateinit var awsSecretKey: String

    @Bean
    fun amazonSQSAsync(): AmazonSQSAsync {
        val awsCredentialsProvider = AWSStaticCredentialsProvider(
            BasicAWSCredentials(awsAccessKey, awsSecretKey)
        )
        return AmazonSQSAsyncClientBuilder
            .standard()
            .withRegion(Regions.AP_NORTHEAST_2)
            .withCredentials(awsCredentialsProvider).build()
    }
}
