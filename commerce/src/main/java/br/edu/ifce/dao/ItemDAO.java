package br.edu.ifce.dao;

import br.edu.ifce.model.Item;
import br.edu.ifce.utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ItemDAO {
    public Item BuscarItem(int id) {
        EntityManager manager = JPAUtil.getEntityManager();
        Item item = manager.find(Item.class, id);
        manager.close();
        return item;
    }

    public List<Item> ListarTodos() {
        EntityManager manager = JPAUtil.getEntityManager();
        TypedQuery<Item> consulta = manager.createQuery("SELECT it FROM Item it", Item.class);

        List<Item> itens = consulta.getResultList();
        manager.close();

        return itens;
    }

    public void Inserir(Item item) {
        EntityManager manager = JPAUtil.getEntityManager();

        manager.getTransaction().begin();
        Item i = new Item();

        i.setNome(item.getNome());
        i.setDescricao(item.getDescricao());
        i.setImagem(item.getImagem());
        i.setPreco(item.getPreco());

        manager.persist(i);
        manager.getTransaction().commit();
        manager.close();
    }

    public void Atualizar(Item item) {
        EntityManager manager = JPAUtil.getEntityManager();
                Item i = manager.find(Item.class, item.getId());

        manager.getTransaction().begin();

        i.setNome(item.getNome());
        i.setDescricao(item.getDescricao());
        i.setImagem(item.getImagem());
        i.setPreco(item.getPreco());

        manager.persist(i);
        manager.getTransaction().commit();
        manager.close();
    }

    public void Excluir(int id) {
        EntityManager manager = JPAUtil.getEntityManager();
        Item i = manager.find(Item.class, id);

        manager.getTransaction().begin();

        manager.remove(i);

        manager.getTransaction().commit();
        manager.close();
    }
}
