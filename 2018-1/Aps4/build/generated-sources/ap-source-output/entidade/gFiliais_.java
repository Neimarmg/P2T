package entidade;

import entidade.gMatriz;
import entidade.gUtilitarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-06T08:35:09")
@StaticMetamodel(gFiliais.class)
public class gFiliais_ { 

    public static volatile CollectionAttribute<gFiliais, gMatriz> matrizs;
    public static volatile CollectionAttribute<gFiliais, gUtilitarios> utilitarios;
    public static volatile SingularAttribute<gFiliais, String> cnpj;
    public static volatile SingularAttribute<gFiliais, Long> idFilial;
    public static volatile SingularAttribute<gFiliais, String> descricao;

}