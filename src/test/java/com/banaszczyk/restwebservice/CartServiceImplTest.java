package com.banaszczyk.restwebservice;

import com.banaszczyk.restwebservice.exception.ProductAlreadyInCartException;
import com.banaszczyk.restwebservice.model.Product;
import com.banaszczyk.restwebservice.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CartServiceImplTest {

    @Autowired private CartService cartService;

    @Test
    void can_add_new_product_to_cart() throws ProductAlreadyInCartException {
        //given
        Product product = new Product();
        product.setName("Banana");

        //when
        cartService.addProduct(product);
        Product resultProduct = cartService.getProductsInCart().get(0);

        //then
        assertEquals(product, resultProduct);
    }
    @Test
    void can_not_add_product_that_is_already_in_cart() throws ProductAlreadyInCartException {
        //given
        Product product = new Product();
        product.setName("Coconut");
        //when
        cartService.addProduct(product);

        //then;
        assertThatThrownBy(() -> cartService.addProduct(product))
                .isInstanceOf(ProductAlreadyInCartException.class)
                .hasMessageContaining("Coconut is already in cart");
    }

    @Test
    void can_remove_Product_from_cart() throws ProductAlreadyInCartException {
        //given
        Product product = new Product();
        product.setName("Pomelo");

        //when
        cartService.addProduct(product);
        cartService.removeProduct(product);

        //then
        assertThat(cartService.getProductsInCart()).isEmpty();
    }

    @Test
    void can_get_Products_In_Cart() throws ProductAlreadyInCartException {
        //given
        Product product = new Product();
        product.setName("Apple");

        //when
        cartService.addProduct(product);

        //then
        assertEquals(List.of(product), cartService.getProductsInCart());
    }


    @Test
    void get_total_price_of_products_in_cart() throws ProductAlreadyInCartException {
        //given
        BigDecimal price = BigDecimal.valueOf(24.99);
        Product product = new Product();
        product.setPrice(price);

        //when
        cartService.addProduct(product);
        BigDecimal totalPrice = cartService.getTotal();

        //then
        assertEquals(price, totalPrice);

    }
}