package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Cliente;

import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClienteRepositoryJson implements ClienteRepository {
    private String arquivo;
    private Gson gson;
    private List<Cliente> clientes;

    public ClienteRepositoryJson(String arquivo) {
        this.arquivo = arquivo;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.clientes = carregar();
    }

    private List<Cliente> carregar() {
        try (FileReader leitor = new FileReader(arquivo)) {
            Cliente[] clientesArray = gson.fromJson(leitor, Cliente[].class);
            if (clientesArray == null) return new ArrayList<>();
            return new ArrayList<>(Arrays.asList(clientesArray));
        } catch (IOException | NullPointerException e) {
            return new ArrayList<>();
        }
    }

    private void salvarArquivo() {
        try (PrintWriter writer = new PrintWriter(arquivo)) {
            writer.print(gson.toJson(clientes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cliente> listar() {
        return new ArrayList<>(clientes);
    }

    @Override
    public boolean salvar(Cliente cliente) {
        // Check if client already exists (update instead of add)
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getCpf().equals(cliente.getCpf())) {
                clientes.set(i, cliente); // Update existing
                salvarArquivo();
                return true;
            }
        }
        
        // Add new client
        clientes.add(cliente);
        salvarArquivo();
        return true;
    }
}