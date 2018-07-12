package rn;

import entidade.gPessoa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author neimarmoises
 */
public class PessoaRN {

    public gPessoa inserir(gPessoa pessoa) {
        new JPAUtil().execInsert(pessoa, true);        
        return pessoa;
    }

    
    public List<gPessoa> listar() {
        EntityManager manager = JPAUtil.getManager();

        TypedQuery<gPessoa> query = manager.createQuery("SELECT p FROM gPessoa p",gPessoa.class);
        List<gPessoa> listaPessoas = query.getResultList();

        view.View.msg("Pessoa:");
        for (gPessoa pessoa : listaPessoas) {
            view.View.msg(
                pessoa.getIdPessoa()
                + "-" +pessoa.getNome()
                + "-" +pessoa.getCpf()
                + "-" + pessoa.getRg()
                + "-" + pessoa.getCref()
                + "-" + pessoa.getProfissao()
                + "-" + pessoa.getTipoPessoa()
             );
        }

        manager.close();

        return (listaPessoas);
    }

    
    public gPessoa buscarPorId(Long id) {
        EntityManager manager = JPAUtil.getManager();
    
        gPessoa pessoa = manager.find(gPessoa.class, id);
        manager.close();
        return pessoa;        
    }
    
    
    public gPessoa atualizar(Long id, gPessoa pessoa) throws Exception{
        EntityManager manager = JPAUtil.getManager();        
        gPessoa pessoaAtual = manager.find(gPessoa.class,id);
        
        if(pessoaAtual == null) throw new Exception();        
        manager.getTransaction().begin();
     
        if(pessoa.getProfissao()!=null && !pessoa.getProfissao().equals(id))
            pessoaAtual.setProfissao(pessoa.getProfissao());
        
        if(pessoa.getTipoPessoa()!=null && !pessoa.getTipoPessoa().equals(id))
            pessoa.setTipoPessoa(pessoa.getTipoPessoa());
        
        if(pessoa.getNome()!=null && !pessoa.getNome().isEmpty())
            pessoaAtual.setNome(pessoa.getNome());     
        
        if(pessoa.getCpf()!=null && !pessoa.getCpf().isEmpty())
            pessoaAtual.setCpf(pessoa.getCpf()); 
        
        if(pessoa.getCref()!=null && !pessoa.getCref().isEmpty())
            pessoaAtual.setCref(pessoa.getCref()); 
        
        if(pessoa.getRg()!=null && !pessoa.getRg().isEmpty())
            pessoaAtual.setRg(pessoa.getRg());
        
        manager.getTransaction().commit();
        
        manager.close();
        
        return pessoaAtual;    }
    
    
    public gPessoa deletar(Long id) throws Exception{
        EntityManager manager = JPAUtil.getManager();
        gPessoa pessoaAtual = manager.find(gPessoa.class,id);
        new JPAUtil().execDelete(manager, pessoaAtual, true);
        
        return (pessoaAtual);

    }

}