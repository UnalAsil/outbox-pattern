package outbox.pattern.food.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import outbox.pattern.food.order.model.FoodOrder;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
}
