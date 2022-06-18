package outbox.pattern.food.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import outbox.pattern.food.order.model.FoodOrderOutbox;

public interface FoodOrderOutboxRepository extends JpaRepository<FoodOrderOutbox, Long> {
}
