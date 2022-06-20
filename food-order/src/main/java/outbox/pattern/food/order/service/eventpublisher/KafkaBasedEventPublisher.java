package outbox.pattern.food.order.service.eventpublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import outbox.pattern.food.base.FoodOrderDTO;
import outbox.pattern.food.order.model.FoodOrderOutbox;

@Component
public class KafkaBasedEventPublisher implements EventPublisher {

    @Autowired
    private KafkaTemplate<String, FoodOrderDTO> kafkaPublisher;

    @Override
    public void publish(FoodOrderOutbox foodOrderOutbox) {
        kafkaPublisher.send("TOPIC", extractDTO(foodOrderOutbox));
    }

    private FoodOrderDTO extractDTO(FoodOrderOutbox foodOrderOutbox) {

        return new FoodOrderDTO(foodOrderOutbox.getId(), foodOrderOutbox.getFoodRequests(),
                foodOrderOutbox.getCreatedAt());
    }
}
