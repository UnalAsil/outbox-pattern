package outbox.pattern.food.order.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import outbox.pattern.food.order.model.FoodOrder;
import outbox.pattern.food.order.service.FoodOrderService;

@RestController
@RequestMapping("/food-api")
public class FoodOrderController {

    @Autowired
    private FoodOrderService foodOrderService;

    @PostMapping({"/food-order"})
    public FoodOrder createFoodOrder(@RequestBody FoodOrder foodOrder) {
        return foodOrderService.createFoodOrder(foodOrder);
    }
}
