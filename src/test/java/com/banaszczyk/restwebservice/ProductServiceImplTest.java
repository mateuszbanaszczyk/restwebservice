package com.banaszczyk.restwebservice;

import com.banaszczyk.restwebservice.exception.ProductAlreadyExistsException;
import com.banaszczyk.restwebservice.model.Product;
import com.banaszczyk.restwebservice.model.QuantityProduct;
import com.banaszczyk.restwebservice.service.ProductService;
import com.banaszczyk.restwebservice.service.QuantityProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductServiceImplTest {


    @Autowired
    private ProductService productService;

    @Autowired
    private QuantityProductService quantityProductService;

    private List<QuantityProduct> quantity;

    @Test
    void can_add_new_product_to_bay() throws Exception {

        //given
        Product product = new Product();
        product.setName("Soup");


        //when
       Optional<Product> addProduct = Optional.ofNullable(productService.addProduct(product));
        Optional<Product> resultProduct = productService.findByName(product.getName());


        //then
       Assertions.assertThat(addProduct.hashCode()).isEqualTo(resultProduct.hashCode());
       //assertEquals(resultProduct.hashCode(), addProduct.hashCode());  // ten zapis też działa, tylko wykorzystuje inną klasę

    }

    @Test
    void cannot_add_product_that_already_exists_to_bay() throws Exception {

        //given
        Product product = new Product();
        product.setName("Beer");


        //when
        productService.addProduct(product);

        //then
        assertThatThrownBy(()-> productService.addProduct(product))
                .isInstanceOf(ProductAlreadyExistsException.class)
                .hasMessageContaining("This product already exists in our store!");

    }

    @Test
    void can_find_product_by_id() throws Exception {

        //given
        Product product = new Product();
        product.setName("Strawberry");

        //when
        Long productID = productService.addProduct(product).getId();
        productService.findById(productID);

        //then
        assertThat((productService.findById(productID)).isPresent());

    }



    @Test
    void can_find_product_by_name() throws Exception{

        //given
        Product product = new Product();
        product.setName("Plum");
        product.setQuantity(quantityProductService.findAll());

        //when
        Optional<Product> product1 = Optional.ofNullable(productService.addProduct(product));
        Optional<Product> wantedProduct = productService.findByName("Plum");


        //then
        assertThat(product1.hashCode()).isEqualTo(wantedProduct.hashCode());

    }




    @Test
    void can_find_all_products_in_the_list() throws Exception{

        //give
        Product input1 = new Product();
        input1.setName("Watermelon");
        input1.setQuantity(List.of());
        Product input2 = new Product();
        input2.setName("Banana");
        input2.setQuantity(List.of());

        List<Product> products;
        products = productService.findAll();
        products.add(input1);
        products.add(input2);

        //when
        Product product1 = productService.addProduct(input1);
        Product product2 = productService.addProduct(input2);
        List<Product> result = productService.findAll();

        //then
        assertEquals(products.hashCode(), result.hashCode());

    }

    @Test
    void can_delete_product_by_id_from_shop() throws Exception {

        //given
        Product product = new Product();

        //when
        Long addedProductId= productService.addProduct(product).getId();
        productService.deleteById(addedProductId);

         //then
        assertThat(productService.findById(addedProductId).isEmpty());


    }
    @Test
    void can_update_existing_product() {

        //given
        Product product1 = new Product();
        product1.setName("Pomelo");
        productService.save(product1);
        Long productId = product1.getId();

        //when
        Product product2 = new Product();
        product2.setId(productId);
        product2.setName("Cola");
        productService.save(product2);


        //then
        assertThat(product1.getName()).isNotEqualTo(product2.getName());
        assertThat(product1.getId()).isEqualTo(product2.getId());

    }
}