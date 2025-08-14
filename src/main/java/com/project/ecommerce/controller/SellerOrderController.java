package com.project.ecommerce.controller;
import com.project.ecommerce.dto.OrderDTO;
import com.project.ecommerce.dto.SellerProductDetailsDTO;
import com.project.ecommerce.service.SellerOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerOrderController {

    private final SellerOrderService sellerOrderService;

    public SellerOrderController(SellerOrderService sellerOrderService) {
        this.sellerOrderService = sellerOrderService;

    }

    // view product details by ID
    @GetMapping("/products/{productId}")
    public ResponseEntity<SellerProductDetailsDTO> getProductDetails(@PathVariable("productId") Long productId,
                                                                     @RequestHeader("X-User-Id") Long userId) {

        SellerProductDetailsDTO productDetails = sellerOrderService.getProductDetailsById(productId);
        return ResponseEntity.ok(productDetails);
    }


    // view orders history for seller by seller ID
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getSellerOrders(@RequestParam Long sellerId,
                                                          @RequestHeader("X-User-Id") Long userId) {

        List<OrderDTO> orders = sellerOrderService.getOrdersBySellerId(sellerId);
        return ResponseEntity.ok(orders);
    }


    // update order status by order ID
    @PutMapping("/orders/{orderId}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable("orderId") Long orderId,
                                                    @RequestParam("status") String status,
                                                    @RequestHeader("X-User-Id") Long userId) {

        String result = sellerOrderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(result);
    }



    // filter by most/least products for seller
    @GetMapping("/products/stats")
    public ResponseEntity<List<SellerProductDetailsDTO>> getProductsBySalesCount(
            @RequestParam(value = "sort", defaultValue = "desc") String sortOrder,
            @RequestHeader("X-User-Id") Long userId) {


        Long sellerId = 1L;
        List<SellerProductDetailsDTO> products = sellerOrderService.getProductsStatsSorted(sellerId, sortOrder);
        return ResponseEntity.ok(products);
    }
}
