package Model;

/**
 *
 * @author neimarmoises
 */
public class TipoUtilitarios {
    int codTipoUtilitario;
    String descTipoUtilitario;

    public TipoUtilitarios(int codTipoUtilitario, String descTipoUtilitario) {
        this.codTipoUtilitario = codTipoUtilitario;
        this.descTipoUtilitario = descTipoUtilitario;
    }

    public int getCodTipoUtilitario() {
        return codTipoUtilitario;
    }

    public void setCodTipoUtilitario(int codTipoUtilitario) {
        this.codTipoUtilitario = codTipoUtilitario;
    }

    public String getDescTipoUtilitario() {
        return descTipoUtilitario;
    }

    public void setDescTipoUtilitario(String descTipoUtilitario) {
        this.descTipoUtilitario = descTipoUtilitario;
    }
    
    
    
}
