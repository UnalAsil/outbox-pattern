package outbox.pattern.food.base;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@ToString
public class FoodOrderDTO implements Serializable {
    private final int eventId;
    private final String foodRequests;
    private final LocalDateTime createdAt;
}
