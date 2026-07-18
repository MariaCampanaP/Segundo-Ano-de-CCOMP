package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.AnimalDeEstimacao;
import dto.AnimalDeEstimacaoDTO;
import model.Cliente;
import repository.ClienteRepository;

import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnimalDeEstimacaoRepositoryJson implements AnimalDeEstimacaoRepository {
    private String arquivo;
    private Gson gson;
    private List<AnimalDeEstimacao> animais;
    private ClienteRepository clienteRepo;

    public AnimalDeEstimacaoRepositoryJson(String arquivo, ClienteRepository clienteRepo) {
        this.arquivo = arquivo;
        this.clienteRepo = clienteRepo;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.animais = carregar();
    }

    private List<AnimalDeEstimacao> carregar() {
        try (FileReader leitor = new FileReader(arquivo)) {
            AnimalDeEstimacaoDTO[] dtos = gson.fromJson(leitor, AnimalDeEstimacaoDTO[].class);
            if (dtos == null) return new ArrayList<>();
            
            List<AnimalDeEstimacao> animaisCarregados = new ArrayList<>();
            for (AnimalDeEstimacaoDTO dto : dtos) {
                Cliente dono = encontrarDonoPorCpf(dto.getDonoCpf());
                if (dono != null) {
                    AnimalDeEstimacao animal = new AnimalDeEstimacao(
                        dto.getNome(), dto.getSexo(), dto.getEspecie(), dono
                    );
                    animaisCarregados.add(animal);
                    dono.adicionarAnimal(animal);
                }
            }
            return animaisCarregados;
        } catch (IOException | NullPointerException e) {
            return new ArrayList<>();
        }
    }

    private Cliente encontrarDonoPorCpf(String cpf) {
        for (Cliente cliente : clienteRepo.listar()) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    private void salvarArquivo() {
        try (PrintWriter writer = new PrintWriter(arquivo)) {
            List<AnimalDeEstimacaoDTO> dtos = new ArrayList<>();
            for (AnimalDeEstimacao animal : animais) {
                dtos.add(new AnimalDeEstimacaoDTO(
                    animal.getNome(),
                    animal.getSexo(),
                    animal.getEspecie(),
                    animal.getDono().getCpf()
                ));
            }
            writer.print(gson.toJson(dtos));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<AnimalDeEstimacao> listar() {
        return new ArrayList<>(animais);
    }

    @Override
    public boolean salvar(AnimalDeEstimacao animal) {
        animais.add(animal);
        salvarArquivo();
        return true;
    }
}