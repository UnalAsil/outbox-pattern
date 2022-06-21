package outbox.pattern.food.order.service.eventpublisher;

public interface EventCallback {

    void onSuccess();

    void onFailure(Throwable ex);
}
