package com.linkedin.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    private static final String CUSTOMER_NAME = "Yidan Wang";
    @InjectMocks
    OrderService orderService;

    @Mock
    OrderRepository orderRepository;

    @Test
    public void createOrderTest() {
        Order result = orderService.createOrder(CUSTOMER_NAME);
        verify(orderRepository).save(any(Order.class));
        assertEquals(CUSTOMER_NAME, result.getCustomerName());
    }

    @Test
    public void updateOrderTestSuccess() {
        Order order = new Order(CUSTOMER_NAME);
        when(orderRepository.findById(anyInt())).thenReturn(order);

        orderService.updateOrder(order);

        verify(orderRepository).findById(anyInt());
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    public void updateOrderTestFailure(){
        Order order = new Order(CUSTOMER_NAME);
        when(orderRepository.findById(anyInt())).thenReturn(null);

        IllegalArgumentException e = assertThrows( IllegalArgumentException.class, () -> {
            orderService.updateOrder(order);
        });
        assertEquals("Order not found", e.getMessage());
    }

    @Test
    public void getOrderTestFailure(){
        when(orderRepository.findById(anyInt())).thenReturn(null);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            orderService.getOrder(1);
        });
        assertEquals("Order not found", e.getMessage());
    }

    @Test
    public void getOrderTestSuccess() {
        Order order = new Order(CUSTOMER_NAME);
        when(orderRepository.findById(anyInt())).thenReturn(order);
        verify(orderRepository, never()).findById(anyInt());
        assertEquals(order, orderService.getOrder(1));
    }
}
