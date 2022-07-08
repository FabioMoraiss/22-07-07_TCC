package modelo;

public class espaco {
    private Integer id;
    private double metros_quadrados;
    private boolean estado_alugado;
    private boolean kiosque_loja;
    private localizacao localizacao;
    
    // GETTERS AND SETTERS START 🎈

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getMetros_quadrados() {
        return this.metros_quadrados;
    }

    public void setMetros_quadrados(double metros_quadrados) {
        this.metros_quadrados = metros_quadrados;
    }

    public boolean isEstado_alugado() {
        return this.estado_alugado;
    }

    public boolean getEstado_alugado() {
        return this.estado_alugado;
    }

    public void setEstado_alugado(boolean estado_alugado) {
        this.estado_alugado = estado_alugado;
    }

    public boolean isKiosque_loja() {
        return this.kiosque_loja;
    }

    public boolean getKiosque_loja() {
        return this.kiosque_loja;
    }

    public void setKiosque_loja(boolean kiosque_loja) {
        this.kiosque_loja = kiosque_loja;
    }

    public localizacao getLocalizacao() {
        return this.localizacao;
    }

    public void setLocalizacao(localizacao localizacao) {
        this.localizacao = localizacao;
    }
// GETTERS AND SETTERS AND 🎈
}
