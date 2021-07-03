package br.edu.ifce.dao;

import br.edu.ifce.model.Usuario;
import br.edu.ifce.utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UsuarioDAO {
    public void Inserir(Usuario usuario) {
        EntityManager manager = JPAUtil.getEntityManager();
        manager.getTransaction().begin();
        manager.persist(usuario);
        manager.getTransaction().commit();
        manager.close();
    }

    public boolean VerificarSeEmailJaCadastrado(String email) {
        EntityManager manager = JPAUtil.getEntityManager();
        TypedQuery<Usuario> consulta = manager.createQuery("SELECT u FROM Usuario u WHERE u.email = ?1", Usuario.class);
        consulta.setParameter(1, email);
        List<Usuario> usuarios = consulta.getResultList();
        manager.close();

        return usuarios.size() > 0;
    }

    public Usuario BuscarPorEmailESenha(String email, String senha) {
        EntityManager manager = JPAUtil.getEntityManager();
        TypedQuery<Usuario> consulta = manager.createQuery("SELECT u FROM Usuario u WHERE u.email = ?1 AND u.senha = ?2", Usuario.class);
        consulta.setParameter(1, email);
        consulta.setParameter(2, senha);

        List<Usuario> usuarios = consulta.getResultList();
        manager.close();

        if(usuarios.size() == 0)
            return null;
        return usuarios.get(0);
    }
}
