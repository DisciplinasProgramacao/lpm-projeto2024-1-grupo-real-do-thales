package com.example.realthales;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.realthales.models.Mesa;

public class MesaTest {
    private Mesa mesa;

    @BeforeEach
    public void setUp() {
        mesa = new Mesa(4);
    }

    @Test
    public void testMesaInicializacao() {
        Mesa mesa2 = new Mesa(1);
        assertEquals(2, mesa2.getCapacidade());
        
        Mesa mesa3 = new Mesa(3);
        assertEquals(3, mesa3.getCapacidade());
    }

    @Test
    public void testOcuparMesa() {
        mesa.ocupar();
        assertFalse(mesa.estahLiberada(2));
    }

    @Test
    public void testDesocuparMesa() {
        mesa.ocupar();
        mesa.desocupar();
        assertTrue(mesa.estahLiberada(2));
    }

    @Test
    public void testEstahLiberada() {
        assertTrue(mesa.estahLiberada(3));
        assertFalse(mesa.estahLiberada(5));
        mesa.ocupar();
        assertFalse(mesa.estahLiberada(3));
    }

    @Test
    public void testToString() {
        String descricao = mesa.toString();
        assertEquals("Mesa 01 (4 pessoas), liberada.", descricao);
        
        mesa.ocupar();
        descricao = mesa.toString();
        assertEquals("Mesa 01 (4 pessoas), ocupada.", descricao);
    }
}
