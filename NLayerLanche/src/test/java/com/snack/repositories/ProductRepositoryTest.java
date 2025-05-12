package com.snack.repositories;

import com.snack.entities.Product;
import org.junit.jupiter.api.*;

import java.util.List;

class ProductRepositoryTest {

    Product product;
    Product product2;
    Product product3;
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();

        Product product1 = new Product(0, "Celular", 1200, "");
        Product product2 = new Product(2, "Test2", 1000, "Image2");
        Product product3 = new Product(3, "Test3", 1100, "Image3");

        productRepository.append(product1);
        productRepository.append(product2);
        productRepository.append(product3);
    }

    //#1. Verificar se um produto é adicionado corretamente ao repositório (List)
    @Test
    @Order(1)
    public void testarAdicionarProdutoRepository() {
        product = new Product(1, "Test", 100, "");
        productRepository.append(product);
        Product product1 = productRepository.getById(1);

        Assertions.assertEquals("Test", product1.getDescription());
        Assertions.assertEquals(100, product1.getPrice());
        Assertions.assertEquals("", product1.getImage());
        Assertions.assertTrue(productRepository.exists(1));
    }

    //#2. Verificar se é possível recuperar um produto usando seu ID
    @Test
    public void verificarRecuperarProdutoId() {
        productRepository.append(product);
        product = productRepository.getById(0);

        Assertions.assertEquals(0, product.getId());
        Assertions.assertEquals("Celular", product.getDescription());
        Assertions.assertEquals(1200, product.getPrice());
        Assertions.assertEquals("", product.getImage());
    }

    //#3. Confirmar se um produto existe no repositório (List)
    @Test
    public void verificarProdutoExisteRepository() {
        productRepository.append(product);

        Assertions.assertTrue(productRepository.exists(0));
    }

    //#4. Testar se um produto é removido corretamente do repositório (List)
    @Test
    public void testarProdutoRemoveRepository() {
        product = productRepository.getById(0);
        productRepository.exists(0);
        productRepository.remove(0);

        Assertions.assertFalse(productRepository.exists(0));
    }

    //5. Verificar se um produto é atualizado corretamente
    @Test
    public void verificarProdutoAtualizadoCorretamente() {
        product = productRepository.getById(0);

        product.setId(1);
        product.setDescription("OtherDescrition");
        product.setPrice(1000);
        product.setImage("OtherImage");

        Assertions.assertEquals(1, product.getId());
        Assertions.assertEquals("OtherDescrition", product.getDescription());
        Assertions.assertEquals(1000, product.getPrice());
        Assertions.assertEquals("OtherImage", product.getImage());
    }

    //6. Testar se todos os produtos armazenados são recuperados corretamente
    @Test
    public void testarTodosProdutosArmazenadosRecuperadosCorretamente() {

        List<Product> products = productRepository.getAll();

        Assertions.assertEquals(3, products.size());

        boolean findProduct1 = products.stream().anyMatch(p -> p.getId() == 0);
        boolean findProduct2 = products.stream().anyMatch(p -> p.getId() == 2);
        boolean findProduct3 = products.stream().anyMatch(p -> p.getId() == 3);

        Assertions.assertTrue(findProduct1);
        Assertions.assertTrue(findProduct2);
        Assertions.assertTrue(findProduct3);
    }

    //#7. Verificar o comportamento ao tentar remover um produto que não existe
    @Test
    public void verificarComportamentoTentarRemoverProdutoNãoExiste() {
        productRepository.remove(1);
        Assertions.assertFalse(productRepository.exists(1));
    }

    //#8. Testar o que acontece ao tentar atualizar um produto que não está no repositório (List)
    @Test
    public void testarTentarAtualizarProdutoInexistente() {
        Product productFake = new Product(100, "TestFake", 1000, "ImageFake");

        Assertions.assertThrows(Exception.class, () -> {
            productRepository.update(100, productFake);
        });
    }

    //9. Remover um produto existente e deletar sua imagem
    @Test
    public void removerProdutoExistenteDeletarImagem() {
        productRepository.remove(0);

    }

    //10. Confirmar que o repositório retorna uma lista vazia ao ser inicializado (List)
    @Test
    public void verificarRetornoListaVaziaIniciar() {
        productRepository = new ProductRepository();

        List<Product> products = productRepository.getAll();

        Assertions.assertTrue(products.isEmpty());
    }

}