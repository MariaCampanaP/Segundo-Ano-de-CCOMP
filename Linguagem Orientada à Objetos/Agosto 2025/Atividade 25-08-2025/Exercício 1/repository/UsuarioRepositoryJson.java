package repository;

import java.util.List;
import model.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class UsuarioRepositoryJson implements UsuarioRepository {

    private static UsuarioRepositoryJson instancia;

    public static UsuarioRepositoryJson getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioRepositoryJson("usuarios.json");
        }
        return instancia;
    }

    private String nomeArquivo;
    private Gson gson;
    private List<Usuario> usuarios;

    private UsuarioRepositoryJson(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.usuarios = carregarListaDoArquivo();
    }

    private List<Usuario> carregarListaDoArquivo() {
        try (FileReader leitor = new FileReader(nomeArquivo)) {
            usuarios = new ArrayList<>(Arrays.asList(
                    gson.fromJson(leitor, Usuario[].class)));
            return usuarios;
        } catch (IOException | NullPointerException e) {
            return new ArrayList<>(); // se o arquivo não existe ainda
        }
    }

    private void salvarNoArquivo() {
        try (PrintWriter printWriter = new PrintWriter(nomeArquivo)) {
            printWriter.print(gson.toJson(usuarios.toArray()));
        } catch (IOException e) {
            System.out.println("Erro ao salvar no arquivo");
        }
    }

    @Override
    public Usuario buscaPorCodigo(int codigo) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCodigo() == codigo) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public Usuario buscaPorLogin(String login) {
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public List<Usuario> listar() {
        return new ArrayList<>(usuarios);
    }

    @Override
    public boolean salvar(Usuario usuario) {
        Usuario usuarioComCodigo = usuario;
        //Determina o código do novo usuário caso não esteja indicado
        if (usuario.getCodigo() == -1) {
            int codigo;
            if (usuarios.isEmpty()) {
                codigo = 1;
            } else {
                int max = -1;
                for (Usuario usuario1 : usuarios) {
                    if (usuario1.getCodigo() >= max) {
                        max = usuario1.getCodigo();
                    }
                }
                codigo = max + 1;
            }
            usuarioComCodigo = new Usuario(
                    codigo,
                    usuario.getLogin(),
                    usuario.getSenha(),
                    usuario.getNome());
        } else if (buscaPorCodigo(usuario.getCodigo()) != null) {
            return false;
        }
        usuarios.add(usuarioComCodigo);
        salvarNoArquivo();
        return true;
    }

    @Override
    public boolean removerPorCodigo(int codigo) {
        Usuario usuarioParaRemover = buscaPorCodigo(codigo);
        if (usuarioParaRemover == null) {
            return false;
        }
        usuarios.remove(usuarioParaRemover);
        salvarNoArquivo();
        return true;
    }

}
