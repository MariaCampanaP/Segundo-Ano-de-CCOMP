package testes;

import model.Usuario;
import repository.UsuarioRepository;
import repository.UsuarioRepositoryJson;

public class TesteUsuarios {

    public static void main(String[] args) {
        UsuarioRepository usuarioRepository = UsuarioRepositoryJson.getInstancia();
        Usuario usuarioUm = new Usuario(1, "abner123", "abner123", "Abner");
        Usuario usuarioDois = new Usuario(2, "bia", "senhamuitolonga", "Beatriz");
        Usuario usuarioTres = new Usuario(3, "carlitos", "a", "Carlos");
        Usuario usuarioQuatro = new Usuario(4, "dorinha", "dora123", "Dora");
        usuarioRepository.salvar(usuarioUm);
        usuarioRepository.salvar(usuarioDois);
        usuarioRepository.salvar(usuarioTres);
        usuarioRepository.salvar(usuarioQuatro);
        Usuario[] usuarios = new Usuario[]{usuarioUm, usuarioDois};
        imprimir(usuarioRepository);
        //System.out.println("");
        //usuarioRepository.removerPorCodigo(2);
        //imprimir(usuarioRepository);

    }

    private static void imprimir(UsuarioRepository usuarioRepository) {
        for (Usuario usuario : usuarioRepository.listar()) {
            System.out.println(usuario);
        }
    }

}
