package outbox.pattern.food.delivery.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import outbox.pattern.food.base.FoodOrderDTO;

@Service
public class FoodOrderListener {

    private static final Logger LOG = LoggerFactory.getLogger(FoodOrderListener.class);

    @KafkaListener(topics = "TOPIC", groupId = "example")
    public void listenForFoodDelivery(FoodOrderDTO foodOrderDto) {
        LOG.info("Consuming message from kafka : {}", foodOrderDto);
    }


}
