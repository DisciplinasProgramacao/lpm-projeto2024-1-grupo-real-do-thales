package com.example.realthales;

import com.example.realthales.models.Cardapio;
import com.example.realthales.models.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class CardapioTest {

    private Cardapio cardapio;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        cardapio = new Cardapio();
    }

    @Test
    void testCarregarCardapio() {
        assertNotNull(cardapio);
        assertEquals(11, cardapio.getProdutos().size(), "O cardapio deve conter 11 produtos.");
    }

    @Test
    void testToString() {
        String cardapioStr = cardapio.toString();
        assertTrue(cardapioStr.contains("CARDÁPIO"), "A string do cardapio deve conter 'CARDÁPIO'.");
    }

    @Test
    void testGetProduto() {
        Produto produto = cardapio.getProduto(1);
        assertNotNull(produto, "O produto na posição 1 não deve ser nulo.");
        assertEquals("Moqueca de Palmito", produto.getNome(), "O nome do produto na posição 1 deve ser 'Moqueca de Palmito'.");
    }
}