package outbox.pattern.food.order.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import outbox.pattern.food.order.model.FoodOrderOutbox;
import outbox.pattern.food.order.repository.FoodOrderOutboxRepository;
import outbox.pattern.food.order.service.eventpublisher.EventCallback;
import outbox.pattern.food.order.service.eventpublisher.EventPublisher;

import java.util.List;

@Service
public class FoodOrderOutboxProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(FoodOrderOutboxProcessor.class);
    @Autowired
    private FoodOrderOutboxRepository orderOutboxRepository;

    @Autowired
    private EventPublisher eventPublisher;

    @Scheduled(fixedRate = 300)
    public void publishAllPeriodically() {
        //TODO-> Since this process is not transactional, the same food order can be published more than once. We can
        // solve this problem by making the consumer and the message idempotent.
        List<FoodOrderOutbox> allFoodOutboxes = orderOutboxRepository.findAll();
        allFoodOutboxes.forEach(e -> eventPublisher.publish(e, new EventCallback() {
            @Override
            public void onSuccess() {
                orderOutboxRepository.delete(e);
            }

            @Override
            public void onFailure(Throwable ex) {
                LOG.error("Error on publish message to call back. Message : {}", e, ex);
            }
        }));

    }

}
