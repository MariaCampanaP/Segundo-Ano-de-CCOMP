package testes;

import model.Mensagem;
import repository.MensagemRepository;
import repository.MensagemRepositoryJson;
import repository.UsuarioRepository;
import repository.UsuarioRepositoryJson;

public class TesteMensagens {

    public static void main(String[] args) {
        MensagemRepository mensagemRepository = MensagemRepositoryJson.getInstancia();
        UsuarioRepository usuarioRepository = UsuarioRepositoryJson.getInstancia();
        Mensagem mensagem1 = new Mensagem(
                usuarioRepository.buscaPorCodigo(1),
                usuarioRepository.buscaPorCodigo(2),
                "Oi!",
                "Um exemplo de mensagem.");
        Mensagem mensagem2 = new Mensagem(
                usuarioRepository.buscaPorCodigo(2),
                usuarioRepository.buscaPorCodigo(3),
                "Tchau!",
                "Outro exemplo de mensagem.");
        Mensagem mensagem3 = new Mensagem(
                usuarioRepository.buscaPorCodigo(3),
                usuarioRepository.buscaPorCodigo(4),
                "Até mais!",
                "Mais um exemplo de mensagem.");
        mensagemRepository.salvar(mensagem1);
        mensagemRepository.salvar(mensagem2);
        mensagemRepository.salvar(mensagem3);
        for (Mensagem mensagem : mensagemRepository.listar()) {
            System.out.println(mensagem);
        }
    }

}
