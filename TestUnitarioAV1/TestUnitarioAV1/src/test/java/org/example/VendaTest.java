package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VendaTest {

    Produto produto;
    Produto produtoInexistente;
    Produto produtoZerado;
    Venda venda;

    @BeforeEach
    public void setUp() {
        produto = new Produto("Produto",10,10);
        produtoInexistente = null;
        produtoZerado = new Produto("ProdutoZerado", 0, 0);
        venda = new Venda(produto,2);
    }

    //#8. Testar venda com quantidade menor que o estoque disponível.
    @Test
    public void vendaProdutoQuantidadeMenorEstoque(){
        Assertions.assertTrue(venda.realizarVenda());

        Assertions.assertEquals(produto, venda.getProduto());
        Assertions.assertEquals(8, produto.getEstoque());
        Assertions.assertEquals(2, venda.getQuantidadeVendida());
    }

    //#9. Testar venda com quantidade igual ao estoque disponível.
    @Test
    public void vendaProdutoQuantidadeIgualEstoque(){
        venda = new Venda(produto, 10);
        boolean vendaRealizada = venda.realizarVenda();

        Assertions.assertTrue(vendaRealizada,"Venda não foi realizada com sucesso");
        Assertions.assertEquals(0,produto.getEstoque());
    }

    //#10. Testar venda com quantidade maior que o estoque disponível (deve falhar).
    @Test
    public void vendaProdutoQuantidadeMaiorEstoque(){
        venda = new Venda(produto, 20);
        venda.realizarVenda();

        Assertions.assertFalse(venda.realizarVenda());
    }

    //#11. Testar cálculo do total da venda corretamente.
    @Test
    public void valorTotalVenda(){
        venda.realizarVenda();
        Assertions.assertEquals(20,venda.getTotalVenda());
    }

    //#12. Testar aumento de estoque do produto após uma venda.
    @Test
    public void aumentoEstoquePosVenda(){
        venda.realizarVenda();
        produto.aumentarEstoque(2);
        Assertions.assertEquals(10,produto.getEstoque());
    }

    //#13. Testar diminuição do estoque do produto após uma venda bem-sucedida
    @Test
    public void diminuirEstoqueAposVenda(){
        venda.realizarVenda();
        Assertions.assertEquals(8,produto.getEstoque());
        Assertions.assertTrue(produto.getEstoque() < 10, "Valor não corresponde ao presente no estoque");

    }


    //#14. Testar realizar venda de um produto que não existe (deve falhar).
    @Test
    public void venderDeProdutoInexistente(){
        venda = new Venda(produtoInexistente, 10);
        Assertions.assertThrows(Exception.class, () -> {
            venda.realizarVenda();
        });
    }

    //#15. Testar criação de venda com quantidade negativa (deve falhar).
    @Test
    public void venderProdutoNegativoQuantidade(){
        venda = new Venda(produto,-20);
        Assertions.assertTrue(venda.realizarVenda());
    }

    //#16. Testar alteração do estoque após a tentativa de venda com estoque insuficiente.
    @Test
    public void venderEstoquePosVendaInvalidaPorProdutoInsuficiente(){
        venda = new Venda(produto,20);
        venda.realizarVenda();
        Assertions.assertFalse(venda.realizarVenda());
        produto.aumentarEstoque(2);
        Assertions.assertEquals(12,produto.getEstoque());
    }

    //#17 Testar criação de vários produtos e realizar vendas com estoque compartilhado.
    @Test
    public void venderProdutoCompartilhado(){
        Venda venda1 = new Venda(produto,2);
        Venda venda2 = new Venda(produto,3);

        Assertions.assertTrue(venda1.realizarVenda());
        Assertions.assertTrue(venda2.realizarVenda());
        Assertions.assertEquals(5, produto.getEstoque());
    }

    //#18. Testar calcular total de venda quando o preço do produto for alterado antes da venda.
    @Test
    public void TotalVendaAlterarPrecoProduto(){
        produto.setPreco(20);
        venda.realizarVenda();
        Assertions.assertEquals(40,venda.getTotalVenda());
    }

    //#19. Testar comportamento da venda quando o estoque inicial é zero.
    @Test
    public void vendaProdutoEstoqueZerado(){
        venda = new Venda(produtoZerado,10);
        Assertions.assertFalse(venda.realizarVenda());
    }


    //#20. Testar aumento do estoque após uma reposição e verificar se a venda é bem-sucedida posteriormente.
    @Test
    public void aumentarEstoqueEReporEstoque(){
        produto.aumentarEstoque(10);
        Assertions.assertEquals(20,produto.getEstoque());

        venda = new Venda(produto,10);
        Assertions.assertTrue(venda.realizarVenda());
    }

}
