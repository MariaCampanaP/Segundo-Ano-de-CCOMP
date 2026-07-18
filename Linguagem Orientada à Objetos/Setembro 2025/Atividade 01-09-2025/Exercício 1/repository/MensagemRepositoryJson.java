package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.MensagemDTO;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Mensagem;
import model.Usuario;

public class MensagemRepositoryJson implements MensagemRepository {

    private static MensagemRepositoryJson instancia;

    public static MensagemRepositoryJson getInstancia() {
        if (instancia == null) {
            instancia = new MensagemRepositoryJson("mensagensDTO.json");
        }
        return instancia;
    }

    private UsuarioRepository usuarioRepository;
    private String nomeArquivo;
    private Gson gson;
    private List<MensagemDTO> mensagensDTO;

    private MensagemRepositoryJson(String nomeArquivo) {
        this.usuarioRepository = UsuarioRepositoryJson.getInstancia();
        this.nomeArquivo = nomeArquivo;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.mensagensDTO = carregarListaDoArquivo();
    }

    private List<MensagemDTO> carregarListaDoArquivo() {
        try (FileReader leitor = new FileReader(nomeArquivo)) {
            mensagensDTO = new ArrayList<>(Arrays.asList(
                    gson.fromJson(leitor, MensagemDTO[].class)));
            return mensagensDTO;
        } catch (IOException | NullPointerException e) {
            return new ArrayList<>(); // se o arquivo não existe ainda
        }
    }

    private void salvarNoArquivo() {
        try (PrintWriter printWriter = new PrintWriter(nomeArquivo)) {
            printWriter.print(gson.toJson(mensagensDTO.toArray()));
        } catch (IOException e) {
            System.out.println("Erro ao salvar no arquivo");
        }
    }

    @Override
    public Mensagem buscarPorCodigo(int codigo) {
        for (MensagemDTO mensagemDTO : mensagensDTO) {
            if (mensagemDTO.getCodigo() == codigo) {
                return deDTOParaMensagem(mensagemDTO);
            }
        }
        return null;
    }

    @Override
    public List<Mensagem> listar() {
        List<Mensagem> mensagens = new ArrayList<>();
        for (MensagemDTO mensagemDTO : mensagensDTO) {
            mensagens.add(deDTOParaMensagem(mensagemDTO));
        }
        return mensagens;
    }

    @Override
    public boolean salvar(Mensagem mensagem) {
        MensagemDTO mensagemDTO = null;
        if (mensagem.getRemetente() == null) {
            return false;
        }
        Usuario remetenteReal = usuarioRepository.buscaPorCodigo(mensagem.getRemetente().getCodigo());
        if (remetenteReal == null) {
            return false;
        }
        if (mensagem.getDestinatario() == null) {
            return false;
        }
        Usuario destinatarioReal = usuarioRepository.buscaPorCodigo(mensagem.getDestinatario().getCodigo());
        if (destinatarioReal == null) {
            return false;
        }
        if (!remetenteReal.equals(mensagem.getRemetente())) {
            return false;
        }
        if (!destinatarioReal.equals(mensagem.getDestinatario())) {
            return false;
        }
        if (mensagem.getCodigo() == -1) {
            int codigo;
            if (mensagensDTO.isEmpty()) {
                codigo = 1;
            } else {
                int max = -1;
                for (MensagemDTO mensagemDTO1 : mensagensDTO) {
                    if (mensagemDTO1.getCodigo() >= max) {
                        max = mensagemDTO1.getCodigo();
                    }
                }
                codigo = max + 1;
            }
            mensagemDTO = new MensagemDTO(
                    codigo,
                    mensagem.getRemetente().getCodigo(),
                    mensagem.getDestinatario().getCodigo(),
                    mensagem.getTitulo(),
                    mensagem.getCorpo());
        } else if (buscarPorCodigo(mensagem.getCodigo()) != null) {
            return false;
        }
        mensagensDTO.add(mensagemDTO);
        salvarNoArquivo();
        return true;
    }

    @Override
    public boolean removerPorCodigo(int codigo) {
        Mensagem mensagem = buscarPorCodigo(codigo);
        if (mensagem == null) {
            return false;
        }
        mensagensDTO.remove(new MensagemDTO(mensagem));
        salvarNoArquivo();
        return true;
    }

    /*Converte um objeto do tipo MensagemDTO para um objeto Mensagem,
    assume que o objeto recebido como parâmetro não é nulo*/
    private Mensagem deDTOParaMensagem(MensagemDTO mensagemDTO) {
        Usuario remetente = usuarioRepository.buscaPorCodigo(mensagemDTO.getCodRemetente());
        Usuario destinatario = usuarioRepository.buscaPorCodigo(mensagemDTO.getCodDestinatario());
        Mensagem mensagem = new Mensagem(mensagemDTO.getCodigo(),
                remetente,
                destinatario,
                mensagemDTO.getTitulo(),
                mensagemDTO.getCorpo());
        return mensagem;
    }

}
