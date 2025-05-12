package com.snack.applications;

import com.snack.entities.Product;
import com.snack.repositories.ProductRepository;
import com.snack.services.ProductService;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductApplicationTest {
    private ProductApplication productApplication;
    private ProductRepository productRepository;
    private ProductService productService;
    private Product product1;

    @BeforeEach
    public void setup() {
        productService = new ProductService();
        productRepository = new ProductRepository();
        productApplication = new ProductApplication(productRepository,productService);

        product1 = new Product(1, "Cachorro Quente", 14, "src\\test\\resources\\images\\CachorroQuente.jpg");

        productApplication.append(product1);
    }

    //1. Listar todos os produtos do repositório
    @Test
    @Order(1)
    public void listarProdutosRepositorio(){
        Product product2 = new Product(2, "Bolo", 30, "src\\test\\resources\\images\\Bolo.jpg");
        productApplication.append(product2);

        List<Product> products = productApplication.getAll();

        Assertions.assertTrue(products.containsAll(Arrays.asList(product1,product2)));
    }


    //2. Obter um produto por ID válido
    @Test
    @Order(2)
    public void TrazerIdProdutoValido(){
        Product product = productApplication.getById(1);

        Assertions.assertEquals(1,product.getId());
        Assertions.assertEquals(14,product.getPrice());
        Assertions.assertEquals("Cachorro Quente",product.getDescription());

        Assertions.assertEquals("src\\test\\resources\\images\\CachorroQuente.jpg",product.getImage());
    }

    //3. Retornar nulo ou erro ao tentar obter produto por ID inválido
    @Test
    @Order(3)
    public void RetornarProdutoIdInvalido(){

        Assertions.assertThrows(Exception.class,()->{
            productApplication.getById(10);
        });
    }

    //4. Verificar se um produto existe por ID válido
    @Test
    @Order(4)
    public void VerificarProdutoExisteIdValido(){

        Assertions.assertTrue(productApplication.exists(1));
    }

    //5. Retornar falso ao verificar a existência de um produto com ID inválido
    @Test
    @Order(5)
    public void RetornarProdutoExistenciaProdutoIdInvalido(){

        Assertions.assertFalse(productApplication.exists(10));

    }

    //6. Adicionar um novo produto e salvar sua imagem corretamente
    @Test
    @Order(6)
    public void AdicionarNovoProdutoSalvarImagem(){
        Product product2 = new Product(2, "Bolo", 30, "src\\test\\resources\\images\\Bolo.jpg");

        productApplication.append(product2);
        Assertions.assertTrue(productApplication.exists(2));

        Assertions.assertEquals("Bolo",product2.getDescription());
        Assertions.assertEquals(30,product2.getPrice());
        Assertions.assertEquals("src\\test\\resources\\images\\Bolo.jpg",product2.getImage());
    }

    //7. Remover um produto existente e deletar sua imagem
    @Test
    @Order(7)
    public void removerProdutoExistenteApagarImagem(){

        Assertions.assertThrows(Exception.class, ()->{
            productApplication.remove(1);
        });

        Assertions.assertFalse(productApplication.exists(1));

        Assertions.assertThrows(Exception.class,()->{
            productService.getImagePathById(1);
        });
    }

    //8. Não alterar o sistema ao tentar remover um produto com ID inexistente
    @Test
    @Order(8)
    public void NaoAlterarSistemaRemoverProdutoInexistente(){
        Assertions.assertThrows(Exception.class, () -> {
            productApplication.remove(2);
        });
        Assertions.assertTrue(productApplication.exists(1));

        String expectedPath = "src\\test\\resources\\images\\CachorroQuente.jpg";

        Assertions.assertEquals(expectedPath,productApplication.getById(1).getImage());
    }

    //9. Atualizar um produto existente e substituir sua imagem
    @Test
    @Order(9)
    public void atualizarProdutoExistenteSubstituirImagem() {
        Product productModificado = new Product(1, "Hamburguer", 20, "src\\test\\resources\\images\\Hamburguer.jpg");

        Assertions.assertThrows(Exception.class, () -> {
            productApplication.update(1, productModificado);
        });

        Product produtoUpdate = productApplication.getById(productModificado.getId());

        Assertions.assertEquals("Hamburguer", produtoUpdate.getDescription());
        Assertions.assertEquals(20, produtoUpdate.getPrice());
        Assertions.assertEquals("src\\test\\resources\\images\\Hamburguer.jpg", produtoUpdate.getImage());
    }
}