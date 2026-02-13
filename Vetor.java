package listaestatica;

import java.util.Arrays;

public class Vetor {

     private static final int CAPACIDADE_INICIAL = 5;
     private Cliente[] clientes;
     private int totalDeClientes;

     public Vetor() {
          clientes = new Cliente[CAPACIDADE_INICIAL];
          totalDeClientes = 0;
     }

     private boolean foiExpandido = false;

     private void garatirEspaco() {
          if (totalDeClientes == clientes.length) {
               clientes = Arrays.copyOf(clientes, clientes.length * 2);
               foiExpandido = true;
          }
     }

     private void reduzirEspaco() {
          if (!foiExpandido) {
               return;
          }
          if (totalDeClientes <= clientes.length * 0.25) {
               int novoTamanho = clientes.length / 2;
               clientes = Arrays.copyOf(clientes, novoTamanho);
          }
     }

     public void adicionar(Cliente novoCliente) {
          garatirEspaco();
          clientes[totalDeClientes] = novoCliente;
          totalDeClientes++;
     }

     public void adicionarPosicaoEspecifica(Cliente novoCliente, int posicaoEspecifica) {
          garatirEspaco();
          if (!posicaoValidaInserir(posicaoEspecifica)) {
               throw new IllegalArgumentException("Posicao Invalida");
          }
          for (int i = totalDeClientes; i > posicaoEspecifica; i--) {
               clientes[i] = clientes[i - 1];
          }
          clientes[posicaoEspecifica] = novoCliente;
          totalDeClientes++;
     }

     public int pegarTotalClientes() {
          return totalDeClientes;
     }

     public int tamanhoVetor() {
          return clientes.length;
     }

     private boolean posicaoValida(int posicao) {
          return posicao >= 0 && posicao < totalDeClientes;
     }

     private boolean posicaoValidaInserir(int posicao) {
          return posicao >= 0 && posicao <= totalDeClientes;
     }

     public Cliente pegar(int posicao) {
          if (!posicaoValida(posicao)) {
               throw new IllegalArgumentException("Posicao Invalida");
          }
          return clientes[posicao];
     }

     public Cliente pegarRemoverTodos(int posicao) {
          if (posicao >= totalDeClientes) {
               return null;
          }
          return clientes[posicao];
     }

     public boolean contem(Cliente clienteBuscado) {
          for (int i = 0; i < totalDeClientes; i++) {
               if (clientes[i].equals(clienteBuscado)) {
                    return true;
               }
          }
          return false;
     }

     public void remover(int posicaoRemover) {
          if (!posicaoValida(posicaoRemover)) {
               throw new IllegalArgumentException("Posicao Invalida");
          }
          for (int i = posicaoRemover; i < totalDeClientes - 1; i++) {
               clientes[i] = clientes[i + 1];
          }
          clientes[totalDeClientes - 1] = null;
          totalDeClientes--;

          reduzirEspaco();
     }

     public void removerUltimoElemento(int posicaoRemoverUltima) {
          if (!posicaoValida(posicaoRemoverUltima)) {
               throw new IllegalArgumentException("Posicao Invalida");
          }
          clientes[totalDeClientes - 1] = null;
          totalDeClientes--;

          reduzirEspaco();
     }

     public void removerPrimeiroElemento(int posicaoRemoverPrimeira) {
          if (!posicaoValida(posicaoRemoverPrimeira)) {
               throw new IllegalArgumentException("Posicao Invalida");
          }
          for (int i = posicaoRemoverPrimeira; i < totalDeClientes - 1; i++) {
               clientes[i] = clientes[i + 1];
          }
          totalDeClientes--;

          reduzirEspaco();
     }

     public void removerTodosElementos() {
          for (int i = 0; i < totalDeClientes - 1; i++) {
               clientes[i] = null;
          }
          totalDeClientes = 0;
     }

}
