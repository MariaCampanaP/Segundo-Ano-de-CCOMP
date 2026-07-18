package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pedido {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(optional = false)
    private Cliente comprador;

    @ManyToOne(optional = false)
    private Funcionario atendente;

    @OneToMany(mappedBy = "pedido", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<Item> itens;

    public Pedido() {
        // Construtor padrão obrigatório pelo JPA
    }

    public Pedido(Cliente comprador, Funcionario atendente) {
        this.comprador = comprador;
        this.atendente = atendente;
        this.itens = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public Cliente getComprador() {
        return comprador;
    }

    public void setComprador(Cliente comprador) {
        this.comprador = comprador;
    }

    public Funcionario getAtendente() {
        return atendente;
    }

    public void setAtendente(Funcionario atendente) {
        this.atendente = atendente;
    }

    public Set<Item> getItens() {
        return itens;
    }

    public void setItens(Set<Item> itens) {
        this.itens = itens;
    }

    public void adicionarItem(Item item) {
        itens.add(item);
        item.setPedido(this);
    }
}
