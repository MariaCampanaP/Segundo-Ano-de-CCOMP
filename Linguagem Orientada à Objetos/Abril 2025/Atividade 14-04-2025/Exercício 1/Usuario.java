/* Na classe Usuario, implemente a interface Comparable < Usuario > e o método 
   compareTo(Usuario usuario). Seu método deve tratar um usuário a como menor 
   que outro b caso o código de a seja menor que o código de b.
   Dica: A classe Integer implementa a interface Comparable < Integer >.
*/

package br.edu.unespar.listas;

public class Usuario implements Comparable<Usuario> {

    private String nome;
    private int codigo;

    public Usuario(String nome, int codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
     @Override
    public int compareTo(Usuario outroUsuario) {
        if(this.codigo < outroUsuario.codigo){
            return -1;
        }else if (this.codigo > outroUsuario.codigo){
            return 1;
        }else{
            return 0;
        }
    }
}