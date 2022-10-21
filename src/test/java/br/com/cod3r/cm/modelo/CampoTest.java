package br.com.cod3r.cm.modelo;
import br.com.cod3r.cm.excecao.ExplosaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CampoTest
{
    private Campo campo;

    @BeforeEach
    void iniciarCampo()
    {
        campo = new Campo(3 ,3);
    }

    @Test
    void adcionarVizinhoEsquerda()
    {
        Campo vizinho = new Campo(3, 2);
        boolean resultado = campo.adcionarVizinho(vizinho);

        assertTrue(resultado);
    }

    @Test
    void adicionarVizinhoDireita()
    {
        Campo vizinho = new Campo(3, 4);
        boolean resultado = campo.adcionarVizinho(vizinho);

        assertTrue(resultado);
    }

    @Test
    void adicionarVizinhoEmCima()
    {
        Campo vizinho = new Campo(2, 3);
        boolean resultado = campo.adcionarVizinho(vizinho);

        assertTrue(resultado);
    }

    @Test
    void adicionarVizinhoEmBaixo()
    {
        Campo vizinho = new Campo(4, 3);
        boolean resultado = campo.adcionarVizinho(vizinho);

        assertTrue(resultado);
    }

    @Test
    void adicionarVizinhoDistancia2()
    {
        Campo vizinho = new Campo(2, 2);
        boolean resultado = campo.adcionarVizinho(vizinho);

        assertTrue(resultado);
    }

    @Test
    void adicionarVizinhoErrado()
    {
        Campo vizinho = new Campo(1, 1);
        boolean resultado = campo.adcionarVizinho(vizinho);

        assertFalse(resultado);
    }

    @Test
    void valorPadraoAtributoMarcadoTeste()
    {
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacao()
    {
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacaoDuasChamadas()
    {
        campo.alternarMarcacao();
        campo.alternarMarcacao();

        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAbrirNaoMinadoNaoMarcado()
    {
        assertTrue(campo.abrir());
    }

    @Test
    void testeAbrirNaoMinadoMarcado()
    {
        campo.alternarMarcacao();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoMarcado()
    {
        campo.alternarMarcacao();
        campo.minar();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoNaoMarcado()
    {
        campo.minar();
        assertThrows(ExplosaoException.class, () ->
        {
            campo.abrir();
        });
    }

    @Test
    void testeAbrirComVizinhos1()
    {
        Campo campo11 = new Campo(1, 1);
        Campo campo22 = new Campo(2, 2);

        campo22.adcionarVizinho(campo11);
        campo.adcionarVizinho(campo22);
        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isAberto());
    }

    @Test
    void testeAbrirComVizinhos2()
    {
        Campo campo11 = new Campo(1, 1);
        Campo campo12 = new Campo(1, 1);
        campo12.minar();

        Campo campo22 = new Campo(2, 2);
        campo22.adcionarVizinho(campo11);
        campo22.adcionarVizinho(campo12);

        campo.adcionarVizinho(campo22);
        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isFechado());
    }
}