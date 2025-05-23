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
public class ProdutoApplicationTest {
    @InjectMocks
    ProductApplication productApplication;

    @Mock
    ProductService productService;


    //Deve salvar a imagem corretamente.
    @Test
    public void testSalvarImagemNoService(){
        Product product = Mockito.mock(Product.class);
        Mockito.when(productService.save(product)).thenReturn(true);
        productApplication.append(product);
        Mockito.verify(productService, Mockito.times(1)).save(product);
        Assertions.assertTrue(productService.save(product));
    }

    //Deve remover a imagem corretamente.
    @Test
    public void testRemoverImagemNoService(){
        productApplication.remove(1);
        Mockito.verify(productService,Mockito.times(1)).remove(Mockito.any(Integer.class));
    }

    //Deve atualizar a imagem corretamente.
    @Test
    public void testAtualizarImagemNoService(){
        Product product = Mockito.any(Product.class);
        productApplication.update(1,product);
        Mockito.verify(productService,Mockito.times(1)).update(product);
    }
}