package com.example.consumercardsnotifier.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.util.ErrorHandler;

/**
 * - This is a configuration class to create  different  beans to configure the ActiveMQ
 * - It´s going to create the connection with  ActiveMQ and the listener that is going to read the messages
 */
@Configuration
public class ActiveMqConfig {

    @Value("${activemq.broker-url}")
    private String BROKER_URL;

    @Value("${activemq.broker-username}")
    private String BROKER_USERNAME;

    @Value("${activemq.broker-password}")
    private String BROKER_PASSWORD;

    /**
     * - A ConnectionFactory is an an Administered object, and is used for creating Connections.
     * - This class also implements QueueConnectionFactory and TopicConnectionFactory.
     * - You can use this connection to create both QueueConnections and TopicConnections.
     *
     * @return ActiveMQConnectionFactory
     */
    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(BROKER_URL);
        connectionFactory.setUserName(BROKER_PASSWORD);
        connectionFactory.setPassword(BROKER_USERNAME);
        return connectionFactory;
    }

    /**
     * - Strategy interface that specifies a converter between Java objects and JMS messages.
     * - Check out SimpleMessageConverter for a default implementation, converting between the
     *   'standard' message payloads and JMS Message types.
     *
     * @return MessageConverter
     */
    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    /**
     * - The JmsListenerContainerFactory is responsible for creating the listener container responsible
     *   for a particular endpoint.
     *
     * @return  JmsListenerContainerFactory<?>
     */
    @Bean
    public SimpleJmsListenerContainerFactory myFactory() {
        SimpleJmsListenerContainerFactory factory =
                new SimpleJmsListenerContainerFactory();
        // anonymous class
        factory.setErrorHandler(new ErrorHandler() {
            @Override
            public void handleError(Throwable t) {
                System.err.println("An error has occurred in the transaction: " + t.getMessage());
                t.printStackTrace();
            }
        });
        factory.setConnectionFactory(connectionFactory());
        factory.setMessageConverter(jacksonJmsMessageConverter());

        return factory;
    }
}