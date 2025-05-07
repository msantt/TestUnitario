package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProdutoTest {

    Produto produto;
    Produto produtoNovo;

    @BeforeEach
    public void setUp() {
        produto = new Produto("Produto", 10, 10);
    }

    //#1. Testar criação de produto com valores válidos.
    @Test
    public void criacaoProdutoValorValido() {
        Assertions.assertEquals("Produto", produto.getNome());
        Assertions.assertEquals(10, produto.getPreco());
        Assertions.assertEquals(10, produto.getEstoque());
    }

    //#2. Testar criação de produto com preço negativo (deve ser inválido).
    @Test
    public void criacaoProdutoValorNegativo() {
        produtoNovo = new Produto("Produto Teste", -10, 5);
        Assertions.assertTrue(produtoNovo.getPreco() >= 0, "O estoque do produto não deve ser negativo");
    }

    //#3. Testar criação de produto com estoque negativo (deve ser inválido).
    @Test
    public void testCriacaoProdutoComEstoqueNegativo() {
        produtoNovo = new Produto("Produto Teste", 10.0, -5);
        Assertions.assertTrue(produto.getEstoque() > 0, "O estoque do produto não deve ser negativo");
    }


    //#4. Testar alteração do nome do produto para um valor válido.
    @Test
    public void mudarNomeProdutoValorValido() {
        produto.setNome("Produto2");
        Assertions.assertEquals("Produto2", produto.getNome());
    }

    //#5. Testar alteração do preço do produto para um valor válido.
    @Test
    public void mudarValorProdutoValorValido() {
        produto.setPreco(20);
        Assertions.assertEquals(20, produto.getPreco());
    }

    //#6. Testar alteração do estoque para um valor positivo.
    @Test
    public void mudarEstoqueProdutoValorValido() {
        produto.setEstoque(30);
        Assertions.assertEquals(30, produto.getEstoque());
    }

    //#7. Testar alteração do preço do produto para um valor negativo (deve falhar).
    @Test
    public void mudarValorProdutoValorNegativo() {
        produto.setPreco(-20);
        Assertions.assertTrue(produto.getPreco() < 0);
        Assertions.assertEquals(10, produto.getEstoque());
        Assertions.assertEquals("Produto", produto.getNome());
    }

}
