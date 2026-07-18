/* A classe Collections possui um método estático sort que é capaz de ordenar 
   listas de objetos comparáveis. Modifique o método main do projeto para que
   ordene a lista de usuários usando esse método antes de imprimir seus nomes.
*/

package br.edu.unespar.listas;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Listas {
    
    public static void main(String[] args) {
        List<Usuario> usuarios = new LinkedList<>();
        usuarios.add(new Usuario("João", 123));
        usuarios.add(new Usuario("Maria", 321));
        usuarios.add(new Usuario("Pedro", 135));
        usuarios.add(new Usuario("José", 246));
        
        Collections.sort(usuarios);
        
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getNome());
        }
    }
}
