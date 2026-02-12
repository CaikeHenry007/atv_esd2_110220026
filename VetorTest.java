package listaestatica;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VetorTest {

    private Vetor vetor;
    Cliente cliente1, cliente2, cliente3, cliente4, cliente5, cliente6;

    @BeforeEach
    public void inicializar() {

        vetor = new Vetor();
        cliente1 = new Cliente("Fernando");
        cliente2 = new Cliente("Maria");
        cliente3 = new Cliente("Jose");
        cliente4 = new Cliente("Patricia");
        cliente5 = new Cliente("Ronaldo");
        cliente6 = new Cliente("Pele");
    }

    @Test
    public void testAdicionarSemElementos() {

        vetor.adicionar(cliente1);

        assertEquals(1, vetor.pegarTotalClientes());
        assertEquals("Fernando", vetor.pegar(0).getNome());
    }

    @Test
    public void testAdicionarComElementos() {

        vetor.adicionar(cliente1);
        vetor.adicionar(cliente2);
        vetor.adicionar(cliente3);

        assertEquals(3, vetor.pegarTotalClientes());
        assertEquals("Fernando", vetor.pegar(0).getNome());
        assertEquals("Maria", vetor.pegar(1).getNome());
        assertEquals("Jose", vetor.pegar(2).getNome());
    }

    @Test
    public void testPegarPosicaoValida() {

        vetor.adicionar(cliente1);
        vetor.adicionar(cliente2);

        assertEquals("Fernando", vetor.pegar(0).getNome());
        assertEquals("Maria", vetor.pegar(1).getNome());
    }

    @Test
    public void testAdicionarSemElementosEspecifica() {

        vetor.adicionarPosicaoEspecifica(cliente1, 0);

        assertEquals(1, vetor.pegarTotalClientes());
    }

    @Test
    public void testAdicionarComElementosEspecifica() {

        vetor.adicionar(cliente1);
        vetor.adicionar(cliente2);
        vetor.adicionar(cliente3);

        assertEquals(3, vetor.pegarTotalClientes());
        assertEquals("Fernando", vetor.pegar(0).getNome());
        assertEquals("Maria", vetor.pegar(1).getNome());
        assertEquals("Jose", vetor.pegar(2).getNome());
    }

    @Test
    public void testPegarPosicaoValidaEspecifica() {

        vetor.adicionar(cliente1);
        vetor.adicionar(cliente2);
        vetor.adicionar(cliente3);
        vetor.adicionar(cliente4);

        assertEquals("Fernando", vetor.pegar(0).getNome());
        assertEquals("Maria", vetor.pegar(1).getNome());
        assertEquals("Jose", vetor.pegar(2).getNome());
        assertEquals("Patricia", vetor.pegar(3).getNome());

        vetor.adicionarPosicaoEspecifica(cliente5, 2);

        assertEquals("Fernando", vetor.pegar(0).getNome());
        assertEquals("Maria", vetor.pegar(1).getNome());
        assertEquals("Ronaldo", vetor.pegar(2).getNome());
        assertEquals("Jose", vetor.pegar(3).getNome());
        assertEquals("Patricia", vetor.pegar(4).getNome());

    }

    @Test
    public void testPegarPosicaoInvalida() {

        vetor.adicionar(cliente1);
        vetor.adicionar(cliente2);

        assertThrows(IllegalArgumentException.class, () -> vetor.pegar(-1));
        assertThrows(IllegalArgumentException.class, () -> vetor.pegar(2));
    }

    @Test
    public void testContemPositivo() {

        vetor.adicionar(cliente1);
        vetor.adicionar(cliente2);

        assertTrue(vetor.contem(cliente1));
        assertTrue(vetor.contem(cliente2));
    }

    @Test
    public void testContemNegativo() {

        vetor.adicionar(cliente1);
        assertFalse(vetor.contem(cliente2));
    }

    @Test
    public void testRemoverPosicaoInvalida() {

        assertThrows(IllegalArgumentException.class, () -> vetor.remover(0));
        assertThrows(IllegalArgumentException.class, () -> vetor.remover(-1));

        vetor.adicionar(cliente1);
        assertThrows(IllegalArgumentException.class, () -> vetor.remover(3));
    }

    @Test
    public void testRemoverPosicaoValida() {

        vetor.adicionar(cliente1); // fernando
        vetor.adicionar(cliente2); // maria
        vetor.adicionar(cliente3); // jose
        vetor.remover(0);

        assertEquals(2, vetor.pegarTotalClientes());
        assertEquals("Maria", vetor.pegar(0).getNome());
        assertEquals("Jose", vetor.pegar(1).getNome());

        vetor.remover(0);

        assertEquals(1, vetor.pegarTotalClientes());
        assertEquals("Jose", vetor.pegar(0).getNome());
    }

    @Test
    public void testRemoverUltimaPosicaoInvalida() {

        vetor.adicionar(cliente1);
        assertThrows(IllegalArgumentException.class, () -> vetor.removerUltimoElemento(2));
    }

    @Test
    public void testRemoverUltimaPosicaoValida() {

        vetor.adicionar(cliente1); // fernando
        vetor.adicionar(cliente2); // maria
        vetor.adicionar(cliente3); // jose
        vetor.removerUltimoElemento(2);

        assertEquals(2, vetor.pegarTotalClientes());
        assertEquals("Fernando", vetor.pegar(0).getNome());
        assertEquals("Maria", vetor.pegar(1).getNome());

        vetor.removerUltimoElemento(1);

        assertEquals(1, vetor.pegarTotalClientes());
        assertEquals("Fernando", vetor.pegar(0).getNome());
    }

    @Test
    public void testGarantirEspacoCheio() {
        vetor.adicionar(cliente1);
        vetor.adicionar(cliente2);
        vetor.adicionar(cliente3);
        vetor.adicionar(cliente4);
        vetor.adicionar(cliente5);

        assertEquals(5, vetor.tamanhoVetor());

        vetor.adicionar(cliente6);

        assertEquals(10, vetor.tamanhoVetor());
        assertEquals("Fernando", vetor.pegar(0).getNome());
        assertEquals("Ronaldo", vetor.pegar(4).getNome());

    }

    @Test
    public void testGarantirEspacoNaoCheio() {

        vetor.adicionar(cliente1);
        vetor.adicionar(cliente2);
        vetor.adicionar(cliente3);

        assertEquals(5, vetor.tamanhoVetor());

    }
}
