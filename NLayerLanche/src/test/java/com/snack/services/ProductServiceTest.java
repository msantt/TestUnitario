package com.snack.services;

import com.snack.entities.Product;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceTest {
    private ProductService productService;
    private Product product1;

    @BeforeEach
    public void setup() {
        productService = new ProductService();

        product1 = new Product(1, "Cachorro Quente", 20, "src\\test\\resources\\images\\CachorroQuente.jpg");

        productService.save(product1);
    }

    //1. Salvar um produto com imagem válida
    @Test
    @Order(1)
    public void salvarProdutoImagem(){
        Assertions.assertTrue(productService.save(product1));
    }

    //2. Salvar um produto com imagem inexistente
    @Test
    @Order(2)
    public void salvarProdutoImagemInvalida(){
        Product product2 = new Product(1,"Hamburguer" , 20, "0000.png");
        Assertions.assertFalse(productService.save(product2));
    }

    //3. Atualizar um produto existente
    @Test
    @Order(3)
    public void atualizarProdutoExistente(){
        Product product2 = new Product(1,"Hamburguer" , 20, "src/test/resources/images/Hamburguer.jpg");

        productService.update(product2);

        Assertions.assertEquals(1,product2.getId());
        Assertions.assertEquals("Hamburguer",product2.getDescription());
        Assertions.assertEquals(20,product2.getPrice());
    }

    //4. Remover um produto existente
    @Test
    @Order(4)
    public void removerProdutoExistente(){
        Product bolo = new Product(2, "Bolo", 20, "src\\test\\resources\\images\\Bolo.jpg");

        productService.save(bolo);

        Assertions.assertDoesNotThrow(()->{
            productService.remove(2);
        });
    }

    //5. Obter caminho da imagem por ID
    @Test
    @Order(5)
    public void ObterCaminhoImagemId(){
        String expectedPath = Paths.get("src", "test","java", "com","snack","repositories", "resources", "images", "CachorroQuente.jpg").toAbsolutePath().toString();

        Assertions.assertEquals(expectedPath,productService.getImagePathById(1));
    }
}