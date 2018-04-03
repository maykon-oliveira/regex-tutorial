# Introdução

## Termilogia

Você verá a sigla ER referenciando-se a Expressões Regulares, como também varias outas nomenclaturas que podem ser encontradas em outras fontes, como: expreg, regexp, regex e RE, Aqui simplismente sera usado ER para fazer referencia as expressões regulares.

Outro termo que é interessante ressaltar é o casar ("match"). Casamento aqui não é juntar os trapos, mas significa bater, conferir, combinar, igualar, encontrar, encaixar.

Também temos o padrão ("pattern"), que é nosso objetivo quando fazemos uma ER: examinar o texto e identificar as partes que casam com a especificação dada. Esse padrão pode ser uma palavra, várias, uma linha vazia, um número, ou seja, o que quer que precise ser encontrado pela nossa ER. 

## Onde utilizar regex?

As expressões regulares servem para uma infinidade de tarefas, pois elas são úteis sempre que inclui procura e substituição em textos puros ou linguagens de programação, também para a validação de formulários, realce de sintaxe e filtragem de informação, exemplos: 

- Número IP
- E-mail
- Nome de usuário e senha
- Datas, Horários
- Dados entre \<tags>
- RG, CPF, Cartão de Crédito, Número de telefone
- E assim vai...

## Metacaracteres

Uma expressão regular descreve um conjunto de cadeias de caracteres, de forma concisa, sem precisar listar todos os elementos do conjunto. Por exemplo, um conjunto contendo as cadeias "Handel", "Händel" e "Haendel" pode ser descrito pelo padrão H(ä\|ae?)ndel. Sei que você não entedeu bunufas!.

Já pra matar a curiosidade, aqui estão os metacaracteres-padrão que veremos adiante:

```
.     ?     *     ^     $     |      []     ()     {}     \
```

Cada simbolozinho desses tem sua função específica, que pode mudar dependendo do contexto no qual está inserido, e podemos agregá-los uns com os outros, combinando suas funções e fazendo construções mais complexas.

Se esforça pra decorar os nomes deles, por que eles vão fazer parte da sua vida. Eles estão divididos em quatro grupos distintos, de acordo com características comuns entre eles. Vamos examinar cada grupo separadamente.

- Tipos Representantes

| Metacaractere | Nome         | Função                         |
| :-----------: | ------------ | ------------------------------ |
|    .          | Ponto        | Um caractere qualquer          |
|  [...]        | Lista        | Lista de caracteres permitidos |
|  [^...]       | Lista nagada | Lista de caracteres proibidos  |

- Tipos Quantificadores

| Metacaractere | Nome      | Função           |
| :-----------: | --------- | ---------------- |
|    ?          | Opcional  | Zero ou um       |
|  *            | Asterisco | Zero, um ou mais |
|  +            | Mais      | Um ou mais       |
|  {n, m}       | Chaves    | De n até m       |

- Tipos Âncoras

| Metacaractere | Nome         | Função                   |
| :-----------: | ------------ | ------------------------ |
|    ^          | Circunflexo  | Início da linha          |
|  $            | Cifrão       | Fim da linha             |
|  \b           | Borda        | Início ou fim de palavra |

- Outros

| Metacaractere | Nome       | Função                          |
| :-----------: | ---------- | ------------------------------- |
|      \c       | Escape     | Torna literal o caractere c     |
|      \|       | Ou         | Ou um ou outro                  |
|      ( )      | Grupo      | Delimita um grupo               |
|   \1 ... \9   | Retrovisor | Textos casados nos grupos 1...9 |


<div class="nav-bottom">
	<button><a href="{{site.url}}">Anterior</a></button>
	<button><a href="{{site.url}}parte_1/representantes">Próximo</a></button>
</div>



Fonte [Aurélio Marinho](http://aurelio.net/regex/) a autoridade máxima Brasileira a respeitos das Expressões Regulares.