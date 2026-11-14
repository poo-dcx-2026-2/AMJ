package br.ufpb.dcx.poo.biblioteca.contrato;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import br.ufpb.dcx.poo.biblioteca.contrato.*;
import br.ufpb.dcx.poo.biblioteca.contrato.excecoes.*;
import br.ufpb.dcx.poo.biblioteca.inicial.Exemplar;
import br.ufpb.dcx.poo.biblioteca.inicial.Filme;

public class AcervoServiceImpl implements AcervoService {

    private final Map<String, Filme> filmes = new LinkedHashMap<>();

    private final Map<String, Exemplar> exemplaresPorTombo = new HashMap<>();


    private static void exigirTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("O campo '" + campo + "' é obrigatório.");
        }
    }

    private Filme obterFilme(String codigo) throws RecursoNaoEncontradoException {
        Filme f = filmes.get(codigo);
        if (f == null) {
            throw new RecursoNaoEncontradoException("Filme não encontrado: " + codigo);
        }
        return f;
    }

    private static ItemView paraView(Filme f) {
        return new ItemView(f.getCodigo(), f.getTitulo(), f.getAutoria(), f.getCategoria(),
                f.getAno(), f.totalDeExemplares(), f.exemplaresDisponiveis());
    }


    @Override
    public void cadastrarItem(String codigo, String titulo, String autoria, String categoria, int ano)
            throws RecursoDuplicadoException {

        exigirTexto(codigo, "codigo");
        exigirTexto(titulo, "titulo");
        exigirTexto(autoria, "autoria");
        exigirTexto(categoria, "categoria");
        if (ano <= 0) {
            throw new IllegalArgumentException("O ano deve ser positivo.");
        }
        if (filmes.containsKey(codigo)) {
            throw new RecursoDuplicadoException("Já existe um filme com o código " + codigo);
        }
        filmes.put(codigo, new Filme(codigo, titulo, autoria, categoria, ano));
    }

    @Override
    public ItemView buscarItem(String codigo) throws RecursoNaoEncontradoException {
        return paraView(obterFilme(codigo));
    }

    @Override
    public List<ItemView> listarItens() {
        List<ItemView> lista = new ArrayList<>();
        for (Filme f : filmes.values()) lista.add(paraView(f));
        return lista;
    }

    @Override
    public List<ItemView> buscarPorTitulo(String trecho) {
        List<ItemView> lista = new ArrayList<>();
        if (trecho == null) return lista;
        String t = trecho.toLowerCase();
        for (Filme f : filmes.values()) {
            if (f.getTitulo().toLowerCase().contains(t)) lista.add(paraView(f));
        }
        return lista;
    }

    @Override
    public List<ItemView> buscarPorCategoria(String categoria) {
        List<ItemView> lista = new ArrayList<>();
        if (categoria == null) return lista;
        for (Filme f : filmes.values()) {
            if (f.getCategoria().equalsIgnoreCase(categoria)) lista.add(paraView(f));
        }
        return lista;
    }


    @Override
    public void adicionarExemplar(String codigoDoItem, String tombo)
            throws RecursoNaoEncontradoException, RecursoDuplicadoException {
        Filme f = obterFilme(codigoDoItem);
        exigirTexto(tombo, "tombo");
        if (exemplaresPorTombo.containsKey(tombo)) {
            throw new RecursoDuplicadoException("Já existe um exemplar com o tombo " + tombo);
        }
        Exemplar e = new Exemplar(tombo, codigoDoItem);
        f.adicionarExemplar(e);
        exemplaresPorTombo.put(tombo, e);
    }

    @Override
    public List<ExemplarView> listarExemplares(String codigoDoItem) throws RecursoNaoEncontradoException {
        Filme f = obterFilme(codigoDoItem);
        List<ExemplarView> lista = new ArrayList<>();
        for (Exemplar e : f.getExemplares()) {
            lista.add(new ExemplarView(e.getTombo(), e.getCodigoDoItem(), e.getStatus()));
        }
        return lista;
    }

    @Override
    public void baixarExemplar(String tombo)
            throws RecursoNaoEncontradoException, OperacaoNaoPermitidaException {
        Exemplar e = exemplaresPorTombo.get(tombo);
        if (e == null) {
            throw new RecursoNaoEncontradoException("Exemplar não encontrado: " + tombo);
        }
        if (e.getStatus() == StatusExemplar.EMPRESTADO) {
            throw new OperacaoNaoPermitidaException("Exemplar emprestado não pode ser baixado: " + tombo);
        }
        if (e.getStatus() == StatusExemplar.INDISPONIVEL) {
            throw new OperacaoNaoPermitidaException("O exemplar já foi baixado: " + tombo);
        }
        e.setStatus(StatusExemplar.INDISPONIVEL);
    }


    Exemplar exemplarInterno(String tombo) {
        return exemplaresPorTombo.get(tombo);
    }
}