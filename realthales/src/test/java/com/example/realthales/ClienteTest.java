package com.example.realthales;

import static org.junit.jupiter.api.Assertions.*;
import com.example.realthales.models.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClienteTest {

    @BeforeEach
    public void setUp() {
        try {
            java.lang.reflect.Field field = Cliente.class.getDeclaredField("ultimoID");
            field.setAccessible(true);
            field.set(null, 0);
        } catch (Exception e) {
            fail("Erro ao redefinir o campo ultimoID: " + e.getMessage());
        }
    }

    @Test
    public void testNomeClienteValido() {
        Cliente cliente = new Cliente("João");
        assertEquals("João", cliente.toString().split(" - ")[0].split(": ")[1]);
    }

    @Test
    public void testNomeClienteInvalido() {
        Cliente cliente = new Cliente("Jo");
        assertEquals("Cliente anônimo", cliente.toString().split(" - ")[0].split(": ")[1]);
    }

    @Test
    public void testIdCliente() {
        Cliente cliente1 = new Cliente("Cliente 1");
        Cliente cliente2 = new Cliente("Cliente 2");
        assertEquals(1, Integer.parseInt(cliente1.toString().split(" - ")[1].split(": ")[1]));
        assertEquals(2, Integer.parseInt(cliente2.toString().split(" - ")[1].split(": ")[1]));
    }

    @Test
    public void testToString() {
        Cliente cliente = new Cliente("Ana");
        assertEquals("Nome: Ana - identificador: 1", cliente.toString());
    }

    @Test
    public void testHashCode() {
        Cliente cliente = new Cliente("Pedro");
        assertEquals(1, cliente.hashCode());
    }
}