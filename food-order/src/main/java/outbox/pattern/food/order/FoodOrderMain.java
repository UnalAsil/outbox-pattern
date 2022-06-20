package outbox.pattern.food.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class FoodOrderMain {

    public static void main(String[] args) {
        //TODO-> With the domain-driven design approach, domain and infra can be separated from each other. Since it
        // will be a simple example of outbox pattern, domain and infra were not separated from each other.
        SpringApplication.run(FoodOrderMain.class, args);
    }
}
