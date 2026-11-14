package br.ufpb.dcx.poo.biblioteca.inicial;

import br.ufpb.dcx.poo.biblioteca.contrato.StatusExemplar;

public class Exemplar {
    private final String tombo;
    private final String codigoDoItem;
    private StatusExemplar status = StatusExemplar.DISPONIVEL;

    public Exemplar(String tombo, String codigoDoItem) {
        this.tombo = tombo;
        this.codigoDoItem = codigoDoItem;
    }

    public String getTombo() { return tombo; }
    public String getCodigoDoItem() { return codigoDoItem; }
    public StatusExemplar getStatus() { return status; }
    public void setStatus(StatusExemplar status) { this.status = status; }
}