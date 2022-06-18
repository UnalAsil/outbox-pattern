package outbox.pattern.food.order.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FoodOrderOutbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "event_id")
    private int eventId;

    @Column(name = "food_requests")
    private String foodRequests;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
