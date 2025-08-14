package com.project.ecommerce.controller;

import com.project.ecommerce.dto.CartItemRequest;
import com.project.ecommerce.dto.CartItemResponse;
import com.project.ecommerce.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buyer/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/items")
    public ResponseEntity<?> getCartItems(@RequestHeader("X-User-Id") Long userId) {

        // get cart items for the user
        List<CartItemResponse> cartItems = cartService.getCartItemsResponse(userId);

        return ResponseEntity.ok(cartItems);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@Valid @RequestBody CartItemRequest cartItemRequest,
                                       @RequestHeader("X-User-Id") Long userId) {

        // add item to cart
        cartService.addToCart(userId, cartItemRequest);

        return ResponseEntity.ok("Item added to cart successfully!");
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<?> removeFromCart(@PathVariable Long productId, @RequestHeader("X-User-Id") Long userId) {

        // remove cart item for user
        cartService.removeFromCart(userId, productId);

        return ResponseEntity.ok("Item removed from cart successfully!");
    }

    @DeleteMapping("/clear")
    public ResponseEntity<?> clearCart(@RequestHeader("X-User-Id") Long userId) {

        // clear all cart items for the user
        cartService.clearCart(userId);

        return ResponseEntity.ok("Cart cleared successfully!");

    }
}
