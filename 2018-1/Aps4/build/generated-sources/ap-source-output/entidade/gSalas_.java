package entidade;

import entidade.gPredio;
import entidade.gUtilitarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-06T08:35:09")
@StaticMetamodel(gSalas.class)
public class gSalas_ { 

    public static volatile SingularAttribute<gSalas, String> pavimento;
    public static volatile SingularAttribute<gSalas, String> sala;
    public static volatile SingularAttribute<gSalas, Integer> capacidade;
    public static volatile SingularAttribute<gSalas, Long> idSala;
    public static volatile CollectionAttribute<gSalas, gPredio> predios;
    public static volatile SingularAttribute<gSalas, Boolean> ativa;
    public static volatile CollectionAttribute<gSalas, gUtilitarios> tipoSala;

}