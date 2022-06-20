package outbox.pattern.food.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import outbox.pattern.food.order.model.FoodOrderOutbox;
import outbox.pattern.food.order.repository.FoodOrderOutboxRepository;
import outbox.pattern.food.order.service.eventpublisher.EventPublisher;

import java.util.List;

@Service
public class FoodOrderOutboxProcessor {

    @Autowired
    private FoodOrderOutboxRepository orderOutboxRepository;

    @Autowired
    private EventPublisher eventPublisher;

    @Scheduled(fixedRate = 300)
    public void publishAllPeriodically() {
        //TODO-> Since this process is not transactional, the same food order can be published more than once. We can
        // solve this problem by making the consumer and the message idempotent.
        List<FoodOrderOutbox> allFoodOutboxes = orderOutboxRepository.findAll();
        allFoodOutboxes.forEach(eventPublisher::publish);
        orderOutboxRepository.deleteAllInBatch(allFoodOutboxes);
    }


}
