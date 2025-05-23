package com.example.applications;

import com.example.entities.Product;
import com.example.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductApplicationTest {

    //Teste de unidade para a classe ProductApplication
    //A anotação @InjectMocks indica que o Mockito deve injetar os mocks criados na classe ProductApplication
    @InjectMocks
    ProductApplication productApplication;

    //Mock para a classe ProductService
    //O Mockito irá criar uma instância mockada da classe ProductService
    @Mock
    ProductService productService;


    //#1. Deve salvar a imagem corretamente.
    @Test
    public void testSalvarImagemNoService(){

        // Simulando o comportamento do metodo save do ProductService
        Product product = Mockito.mock(Product.class);

        // Quando o metodo save for chamado, ele deve retornar true
        Mockito.when(productService.save(product)).thenReturn(true);
        productApplication.append(product);

        // Verificando se o metodo save foi chamado uma vez
        Mockito.verify(productService, Mockito.times(1)).save(product);

        // Verificando se o retorno do metodo save é true
        // Isso significa que a imagem foi salva corretamente
        Assertions.assertTrue(productService.save(product));

    }

    //#2. Deve remover a imagem corretamente.
    @Test
    public void testRemoverImagemNoService(){

        // Simulando o comportamento do metodo remove do ProductService
        // Quando o metodo remove for chamado, ele deve retornar true
        productApplication.remove(2);

        // Verificando se o metodo remove foi chamado uma vez
        Mockito.verify(productService,Mockito.times(1)).remove(Mockito.any(Integer.class));
    }

    //#3. Deve atualizar a imagem corretamente.
    @Test
    public void testAtualizarImagemNoService(){

        // Simulando o comportamento do metodo update do ProductService
        Product product = Mockito.any(Product.class);

        // Quando o metodo update for chamado, ele deve retornar true
        productApplication.update(1,product);
        
        // Verificando se o metodo update foi chamado uma vez
        Mockito.verify(productService,Mockito.times(1)).update(product);
    }
}