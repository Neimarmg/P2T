package entidade;

import entidade.gUtilitarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-06T08:35:09")
@StaticMetamodel(gPessoa.class)
public class gPessoa_ { 

    public static volatile SingularAttribute<gPessoa, Long> idPessoa;
    public static volatile CollectionAttribute<gPessoa, gUtilitarios> tipoPessoa;
    public static volatile SingularAttribute<gPessoa, String> rg;
    public static volatile CollectionAttribute<gPessoa, gUtilitarios> profissao;
    public static volatile SingularAttribute<gPessoa, String> cpf;
    public static volatile SingularAttribute<gPessoa, String> nome;
    public static volatile SingularAttribute<gPessoa, Boolean> ativa;
    public static volatile SingularAttribute<gPessoa, String> cref;

}