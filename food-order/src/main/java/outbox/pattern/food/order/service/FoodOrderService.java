package outbox.pattern.food.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import outbox.pattern.food.order.model.FoodOrder;
import outbox.pattern.food.order.model.FoodOrderOutbox;
import outbox.pattern.food.order.repository.FoodOrderOutboxRepository;
import outbox.pattern.food.order.repository.FoodOrderRepository;

import java.time.LocalDateTime;

@Service
public class FoodOrderService {

    @Autowired
    private FoodOrderRepository foodOrderRepository;

    @Autowired
    private FoodOrderOutboxRepository foodOrderOutboxRepository;

    @Transactional
    public FoodOrder createFoodOrder(FoodOrder foodOrder) {
        foodOrderOutboxRepository.save(extractFoodOrder(foodOrder));
        return foodOrderRepository.save(foodOrder);
    }

    private FoodOrderOutbox extractFoodOrder(FoodOrder foodOrder) {
        FoodOrderOutbox foodOrderOutbox = new FoodOrderOutbox();
        foodOrderOutbox.setFoodRequests(foodOrder.getFoodRequests());
        foodOrderOutbox.setEventId(foodOrderOutbox.getEventId());
        foodOrderOutbox.setCreatedAt(LocalDateTime.now());
        return foodOrderOutbox;
    }
}
