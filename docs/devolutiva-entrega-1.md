# Devolutiva da Entrega 1 — AMJ

## Nota: 4,0 de 10 (40 de 100 pontos)

O repositório foi criado e documentado parcialmente, mas a implementação da Entrega 1 não foi realizada. O pipeline verde não representa conclusão porque cinco testes obrigatórios continuam desabilitados.

Avaliei o último commit da branch `main` até 12/10/2026 às 23h59 (o prazo original, 05/10, mais uma semana de extensão), pela leitura do código, dos testes, do histórico de commits, dos pull requests e da documentação. 

## Pontos por critério

| Critério | O que é avaliado | Pontos | Nível |
|---|---|---|---|
| 4.1 | Correção funcional e aderência ao contrato | 10 de 20 | Parcial |
| 4.2 | Modelagem OO, responsabilidades, coesão e acoplamento | 10 de 25 | Insuficiente |
| 4.3 | Testes e evidências de depuração | 5 de 15 | Parcial |
| 4.4 | Robustez, exceções e casos-limite | 5 de 10 | Parcial |
| 4.5 | Evolução, refatoração e qualidade do código | 2 de 10 | Insuficiente |
| 4.6 | Git, build, pipeline e documentação | 5 de 10 | Insuficiente |
| 4.7 | Processo, checkpoints, explicação e autoria | 3 de 10 | Insuficiente |
| | **Total** | **40 de 100** | |

## Justificativa (onde eu tirei pontos)

- Buscar por título e operações de exemplares continuam lançando UnsupportedOperationException. Ou seja, o defeito de comparação de String permanece.

- Código e UML permanecem essencialmente iguais ao template. Não há evolução do domínio nem classe própria para usuário.

- 5 testes ainda desabilitados, sem testes próprios.

- Há 3 commits e CI verde, mas o verde resulta de cinco testes desabilitados. Não há PR, imagem UML, relato do defeito, justificativa das coleções ou declaração de IA preenchida.

- Não houve alteração nos arquivos Java de produção em relação ao repositório-base.

- Apenas Antônio aparece no histórico, não há contribuição dos outros integrantes nem revisão por colega

## Checklist de correções

- [ ] **Implementar `buscarPorTitulo`, `adicionarExemplar`, `listarExemplares` e `baixarExemplar`.** Hoje eles ainda lançam `UnsupportedOperationException`, que é a exceção que o template usa para dizer "ainda não foi feito". São operações centrais da entrega, e é por isso que 17 dos 48 testes ocultos falharam. Usem a tabela de operações do enunciado (`entrega-1.md`) como lista do que cada método deve fazer, inclusive quais exceções lançar.
- [ ] **Corrigir a comparação de `String` com `==` no método `localizar`.** O operador `==` compara se são o *mesmo objeto* na memória; `equals` compara se têm o *mesmo conteúdo*. Troquem `item.getCodigo() == codigo` por `item.getCodigo().equals(codigo)`.
- [ ] **Criar um teste de regressão para o defeito investigado.** Teste de regressão é o teste que reproduz um defeito já corrigido, para que ele não volte sem ninguém perceber. Para o defeito da comparação de `String` com `==`: montem o código em tempo de execução (por exemplo `new String("L1")` ou `"L" + 1`), cadastrem o item e verifiquem que `buscarItem` o encontra e que um segundo cadastro com o mesmo código lança `RecursoDuplicadoException`. Deem ao teste um nome que conte a história, como `buscaFuncionaComCodigoMontadoEmTempoDeExecucao`.
- [ ] **Ativar os 5 testes desabilitados.** Testes com a anotação `@Disabled` não rodam, e por isso o pipeline fica verde mesmo com as operações faltando. Removam o `@Disabled` de cada um e façam todos passarem.
- [ ] **Substituir o modelo inicial.** Criem as classes de domínio (`Item`, `Exemplar`, `Usuario`), troquem as buscas que percorrem listas por `Map` com a chave adequada e as listas paralelas de matrícula e nome por uma lista (ou mapa) de objetos `Usuario`.
- [ ] **Atualizar o `docs/modelo.puml` para o modelo de vocês** e tirar o texto que diz que o diagrama é o ponto de partida do template.
- [ ] **Escrever testes próprios**: tombo repetido, item inexistente, entradas em branco ou nulas e busca por título com trecho em maiúsculas e minúsculas.
- [ ] **Trabalhar por branch e pull request.** Em vez de fazer commit direto na `main`, cada tarefa nasce numa *branch* própria (`git switch -c nome-da-tarefa`), é enviada com `git push -u origin nome-da-tarefa` e entra na `main` por um pull request revisado por outro integrante (ver o item de revisão).
- [ ] **Dividir a implementação entre os integrantes.** Cada integrante precisa ter commits de código e de testes com o próprio usuário do GitHub. Uma divisão simples: um integrante cuida do acervo e o outro dos usuários, e cada um revisa o PR do outro. Combinem também que cada um consegue explicar a parte do colega, porque isso é perguntado no checkpoint.
- [ ] **Versionar a imagem do UML.** Além do `docs/modelo.puml` (o texto do diagrama), o enunciado pede a imagem gerada a partir dele (PNG ou SVG) dentro de `docs/`, adicionada ao repositório com `git add` e `commit`. Gerem a imagem pelo plugin PlantUML do IntelliJ ou pelo site do PlantUML e confiram que ela aparece no GitHub.
- [ ] **Justificar no README cada coleção escolhida.** Para cada `List`, `Set` ou `Map` do código, uma frase dizendo qual operação ela torna fácil. Exemplo: "usamos `Map<String, Item>` com o código como chave porque `buscarItem` precisa achar um item pelo código sem percorrer a lista inteira".
- [ ] **Escrever no README o relato do defeito.** Uma seção curta com: o que acontecia (o sintoma), como vocês reproduziram, qual era a causa e qual teste agora protege contra ele.
- [ ] **Preencher e assinar a declaração de uso de IA**, com o nome dos três integrantes.
