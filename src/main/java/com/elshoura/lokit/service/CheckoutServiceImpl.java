package com.elshoura.lokit.service;

import com.elshoura.lokit.errors.exception.NotFoundException;
import com.elshoura.lokit.errors.exception.QuantityExceedsException;
import com.elshoura.lokit.errors.exception.UserForbiddenException;
import com.elshoura.lokit.models.dto.request.CheckoutRequest;
import com.elshoura.lokit.models.dto.response.OrderResponse;
import com.elshoura.lokit.models.entitys.*;
import com.elshoura.lokit.repository.*;
import com.elshoura.lokit.utils.enums.OrderStatus;
import com.elshoura.lokit.utils.enums.PaymentMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private static final BigDecimal TAX_RATE = new BigDecimal("45.00");
    private static final BigDecimal SHIPPING = BigDecimal.ZERO;

    private final CartService cartService;
    private final OrderService orderService;

    private final CartItemRepository cartItemRepository;
    private final AddressRepository addressRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductVariantRepository productVariantRepository;

    public OrderResponse checkout(Long userId, CheckoutRequest request) {



        Cart cart = cartService.getOrCreateCart(userId);

        List<CartItem> cartItems = cartItemRepository.findByCartId(cart.getId());

        if (cartItems.isEmpty()) {
            throw new NotFoundException("Cart is empty");
        }

        Address address = addressRepository.findById(request.getAddressId())
                .orElseThrow(() -> new NotFoundException("Address not found"));

        if (!address.getUser().getId().equals(userId)) {
            throw new UserForbiddenException("Address does not belong to this user");
        }

        BigDecimal subtotal = calculateSubtotal(cartItems);
        BigDecimal tax = TAX_RATE;
        BigDecimal shipping = SHIPPING;
        BigDecimal totalPrice = subtotal.add(tax).add(shipping);

        Order order = Order.builder()
                .user(cart.getUser())
                .address(address)
                .status(OrderStatus.ORDER_PLACED)
                .paymentMethod(PaymentMethod.CASH)
                .subtotal(subtotal)
                .tax(tax)
                .shipping(shipping)
                .totalPrice(totalPrice)
                .notes(request.getNotes())
                .createdAt(LocalDateTime.now())
                .build();

        Order savedOrder = orderRepository.save(order);

        for (CartItem cartItem : cartItems) {

            ProductVariant variant = cartItem.getVariant();

            if (cartItem.getQuantity() > variant.getStock()) {
                throw new QuantityExceedsException("Not enough stock for variant id: " + variant.getId());
            }

            BigDecimal price = variant.getPrice();
            BigDecimal lineTotal = calculateLineTotal(cartItem);

            OrderItem orderItem = OrderItem.builder()
                    .order(savedOrder)
                    .variant(variant)
                    .price(price)
                    .quantity(cartItem.getQuantity())
                    .lineTotal(lineTotal)
                    .build();

            orderItemRepository.save(orderItem);

            variant.setStock(variant.getStock() - cartItem.getQuantity());
            productVariantRepository.save(variant);
        }

        cartItemRepository.deleteAll(cartItems);

        return orderService.getMyOrderById(userId, savedOrder.getId());
    }

    private BigDecimal calculateSubtotal(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(this::calculateLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateLineTotal(CartItem cartItem) {
        return cartItem.getVariant()
                .getPrice()
                .multiply(BigDecimal.valueOf(cartItem.getQuantity()));
    }
    }
