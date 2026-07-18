package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
    
    public static MensagemRepositoryJson getInstancia(){
        if(instancia == null){
            instancia = new MensagemRepositoryJson("mensagens.json");
        }
        return instancia;
    }
    
    private String nomeArquivo;
    private Gson gson;
    private List<Mensagem> mensagens;
    
    private UsuarioRepository usuarioRepository;
    
    private MensagemRepositoryJson(String nomeArquivo){
        this.nomeArquivo = nomeArquivo;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.mensagens = carregarListaDoArquivo();
        this.usuarioRepository = UsuarioRepositoryJson.getInstancia();
    }
    
    private List<Mensagem> carregarListaDoArquivo(){
        try(FileReader leitor = new FileReader(nomeArquivo)){
            return new ArrayList<>(Arrays.asList(gson.fromJson(leitor, Mensagem[].class)));
        }catch (IOException | NullPointerException e){
            return new ArrayList<>();
        }
    }
    
    private void salvarNoArquivo(){
        try(PrintWriter printWriter = new PrintWriter(nomeArquivo)){
            printWriter.print(gson.toJson(mensagens.toArray()));
        }catch (IOException e){
            System.out.println("Erro ao salvar mensagens");
        }
    }
    
    @Override
    public Mensagem buscaPorCodigo(int codigo){
        for(Mensagem m : mensagens){
            if(m.getCodigo() == codigo)return m;
        }
        return null;
    }
    
    @Override
    public List<Mensagem>listar(){
        return new ArrayList<>(mensagens);
    }
    
    @Override
    public boolean salvar(Mensagem mensagem){
        Usuario remetente = usuarioRepository.buscaPorCodigo(mensagem.getRemetente().getCodigo());
        Usuario destinatario = usuarioRepository.buscaPorCodigo(mensagem.getDestinatario().getCodigo());
        
        if(remetente == null || destinatario == null){
            System.out.println("Remetente/Destinatario invalidos!");
            return false;
        }
        
        if (mensagem.getCodigo() == -1){
            int max = mensagens.stream().mapToInt(Mensagem::getCodigo).max().orElse(0);
            mensagem = new Mensagem(max + 1, mensagem.getTitulo(), mensagem.getCorpo(), remetente, destinatario);
        }else if(buscaPorCodigo(mensagem.getCodigo()) != null){
            return false;
        }
        
        mensagens.add(mensagem);
        salvarNoArquivo();
        return true;
    }
    
    @Override
    public boolean removerPorCodigo(int codigo){
        Mensagem msg = buscaPorCodigo(codigo);
        if(msg == null)return false;
        mensagens.remove(msg);
        salvarNoArquivo();
        return true;
    }
    
}
