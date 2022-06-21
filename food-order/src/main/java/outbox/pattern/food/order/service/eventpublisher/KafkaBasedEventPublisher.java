package outbox.pattern.food.order.service.eventpublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import outbox.pattern.food.base.FoodOrderDTO;
import outbox.pattern.food.order.model.FoodOrderOutbox;

@Component
public class KafkaBasedEventPublisher implements EventPublisher {

    @Autowired
    private KafkaTemplate<String, FoodOrderDTO> kafkaPublisher;

    @Override
    public void publish(FoodOrderOutbox foodOrderOutbox, EventCallback callback) {
        ListenableFuture<SendResult<String, FoodOrderDTO>> topic = kafkaPublisher.send("TOPIC",
                extractDTO(foodOrderOutbox));
        topic.addCallback(new ListenableFutureCallback<SendResult<String, FoodOrderDTO>>() {
            @Override
            public void onFailure(Throwable ex) {
                callback.onFailure(ex);
            }

            @Override
            public void onSuccess(SendResult<String, FoodOrderDTO> result) {
                callback.onSuccess();
            }
        });

    }

    private FoodOrderDTO extractDTO(FoodOrderOutbox foodOrderOutbox) {

        return new FoodOrderDTO(foodOrderOutbox.getId(), foodOrderOutbox.getFoodRequests(),
                foodOrderOutbox.getCreatedAt());
    }
}
