package com.spr.reactivedemo.services;

import com.spr.reactivedemo.module.Order;
import com.spr.reactivedemo.repo.MedicineRepository;
import com.spr.reactivedemo.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class OrderService {
    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Mono<Order> placeOrder(Long userId, Long medicineId, int quantity) {
        return medicineRepository.findById(medicineId)
                .flatMap(medicine -> {
                    if (quantity > medicine.getMaxQuantity()) {
                        return Mono.error(new IllegalArgumentException("Quantity exceeds restriction for this medicine"));
                    }

                    BigDecimal totalPrice = medicine.getPrice().multiply(BigDecimal.valueOf(quantity));

                    Order order = new Order();
                    order.setUserId(userId);
                    order.setMedicineId(medicineId);
                    order.setQuantity(quantity);
                    order.setTotalPrice(totalPrice);

                    return orderRepository.save(order);
                });
    }
}
