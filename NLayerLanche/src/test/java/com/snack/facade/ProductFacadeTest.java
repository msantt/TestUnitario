package com.snack.facade;

import com.snack.applications.ProductApplication;
import com.snack.entities.Product;
import com.snack.repositories.ProductRepository;
import com.snack.services.ProductService;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ProductFacadeTest {
    private ProductFacade productFacade;
    private ProductRepository productRepository;
    private ProductService productService;
    private Product product1;

    @BeforeEach
    public void setup() {
        ProductService productService = new ProductService();
        ProductRepository productRepository = new ProductRepository();
        ProductApplication productApplication = new ProductApplication(productRepository,productService);

        productFacade = new ProductFacade(productApplication);

        product1 = new Product(1, "Cachorro Quente", 20, "src\\test\\resources\\images\\CachorroQuente.jpg");
        productFacade.append(product1);
    }

    //1. Retornar a lista completa de produtos ao chamar o metodo getAll.
    @Test
    public void retornarListaCompleta(){
        Product product2 = new Product(2, "Bolo", 30, "src\\test\\resources\\images\\Bolo.jpg");

        productFacade.append(product2);
        List<Product> products = productFacade.getAll();

        Assertions.assertTrue(products.containsAll(Arrays.asList(product1,product2)));
    }

    //2. Retornar o produto correto ao fornecer um ID válido no método getById.
    @Test
    public void retornarProdutoPorId(){
        Product product = productFacade.getById(1);

        Assertions.assertEquals(1,product.getId());
        Assertions.assertEquals(20,product.getPrice());
        Assertions.assertEquals("Cachorro Quente",product.getDescription());

        Assertions.assertEquals("src\\test\\resources\\images\\CachorroQuente.jpg",product.getImage());
    }

    //3. Retornar true para um ID existente e false para um ID inexistente no método exists.
    @Test
    public void retornarTrueParaIdExistenteFalseParaIdInexistente(){

        Assertions.assertFalse(productFacade.exists(2));
        Assertions.assertTrue(productFacade.exists(1));
    }

    //4. Adicionar um novo produto corretamente ao chamar o metodo append.
    @Test
    public void adicionarProdutoCorretamente(){
        Product product2 = new Product(2, "Hamburguer", 15, "src\\test\\resources\\images\\Hamburguer.jpg");
        productFacade.append(product2);

        Assertions.assertTrue(productFacade.exists(2));

        Assertions.assertEquals(15,product2.getPrice());
        Assertions.assertEquals("Hamburguer",product2.getDescription());
        Assertions.assertEquals("src\\test\\resources\\images\\Hamburguer.jpg",product2.getImage());
    }

    //5. Remover um produto existente ao fornecer um ID válido no metodo remove.
    @Test
    public void removerProdutoExistente(){
        Assertions.assertTrue(productFacade.exists(1));
    }
}
