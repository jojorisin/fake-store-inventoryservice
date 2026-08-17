package se.jensen.johanna.fakestoreinventoryservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

  @Value("${app.exchange.order-exchange}")
  private String ORDER_EXCHANGE;
  @Value("${app.routing-key.order-paid}")
  private String ORDER_PAID_ROUTING_KEY;
  @Value("${app.queue.commit-reservation-queue}")
  private String COMMIT_RESERVATION_QUEUE;

  @Bean
  public TopicExchange orderExchange() {
    return new TopicExchange(ORDER_EXCHANGE);
  }


  @Bean
  public Queue inventoryCommitReservationQueue() {
    return new Queue(COMMIT_RESERVATION_QUEUE, true);
  }

  @Bean
  public Binding binding(TopicExchange inventoryExchange, Queue inventoryCommitReservationQueue) {
    return BindingBuilder.bind(inventoryCommitReservationQueue).to(inventoryExchange)
        .with(ORDER_PAID_ROUTING_KEY);
  }

  @Bean
  public MessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

}
