package outbox.pattern.food.order.service.eventpublisher;

import org.springframework.stereotype.Service;
import outbox.pattern.food.order.model.FoodOrderOutbox;

@Service
public interface EventPublisher {

    //TODO->Could be generic event publisher.
    void publish(FoodOrderOutbox foodOrderOutbox);
}
