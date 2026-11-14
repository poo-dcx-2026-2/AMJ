package br.ufpb.dcx.poo.biblioteca.inicial;

import java.util.ArrayList;
import java.util.List;
import br.ufpb.dcx.poo.biblioteca.contrato.StatusExemplar;


public class Filme {
    private final String codigo;
    private final String titulo;
    private final String autoria;
    private final String categoria;
    private final int ano;
    private final List<Exemplar> exemplares = new ArrayList<>();

    public Filme(String codigo, String titulo, String autoria, String categoria, int ano) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autoria = autoria;
        this.categoria = categoria;
        this.ano = ano;
    }

    public String getCodigo() { return codigo; }
    public String getTitulo() { return titulo; }
    public String getAutoria() { return autoria; }
    public String getCategoria() { return categoria; }
    public int getAno() { return ano; }


    public List<Exemplar> getExemplares() { return List.copyOf(exemplares); }

    void adicionarExemplar(Exemplar e) { exemplares.add(e); }

    int exemplaresDisponiveis() {
        int n = 0;
        for (Exemplar e : exemplares) {
            if (e.getStatus() == StatusExemplar.DISPONIVEL) n++;
        }
        return n;
    }

    int totalDeExemplares() { return exemplares.size(); }
}