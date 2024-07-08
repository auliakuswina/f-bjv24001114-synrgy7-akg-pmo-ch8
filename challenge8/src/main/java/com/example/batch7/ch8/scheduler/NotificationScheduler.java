package com.example.batch7.ch8.scheduler;

import org.foodapp.ch8.dto.ProductResponse;
import org.foodapp.ch8.service.ProductService;
import org.foodapp.ch8.serviceimpl.KafkaNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationScheduler {

    @Autowired
    private KafkaNotificationService kafkaNotificationService;

    @Autowired
    private ProductService productService;

    private static final Double PROMO_PRICE_THRESHOLD = 300.0;

    @Qualifier("taskExecutor")
    @Autowired
    private TaskExecutor taskExecutor;

    @Scheduled(cron = "${cron.expression}")
    public void sendPromoNotification() {
        taskExecutor.execute(() -> {
            List<ProductResponse> products = productService.findByPromo(PROMO_PRICE_THRESHOLD);
            for (ProductResponse product : products) {
                String promoMessage = String.format("Promo: %s at $%.2f order now!", product.getName(), product.getPrice());
                kafkaNotificationService.sendNotification(promoMessage);
            }
        });
    }
}
