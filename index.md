---
layout: default
---
# Expressões Regulares


> Neste nosso mundo tecnoinformatizado, onde o acesso rápido à informação desejada é algo crucial, temos nas expressões regulares uma mão amiga, que quanto mais refinada for sua construção, mais preciso e rápido será o resultado, diferenciando aqueles que as dominam daqueles que perdem horas procurando por dados que estão ao alcance da mão.
>

### [Parte 1 - Introdução]({{site.url}}parte_1/intro)

Quem não sabe nada ou acha que as expressões regulares é uma anciã linguagem de programação, deve começar pela parte 1 e após ler todo a parte básica você terá os seguintes entendimentos.

1. Enteder o que são expressões regulares.
2. Conhecer os metacaracteres.
3. Criar suas próprias expressões.

### [Parte 2 - Expressões Regulares em diferentes sabores]({{site.url}}parte_2/intro-linguagens)

Várias linguagens de programação possuem suporte às expressões regulares, seja nativo, como módulo importável, como biblioteca carregável, como objeto instanciável, como... Ou seja, opções há várias. Cada linguagem tem sua maneira específica de receber e tratar ERs, vamos dar uma geral nas mais usadas.

#### Executando os exemplos

Não há dificuldade em criar e testar suas ER. No site [Regex 101](https://regex101.com/) é possível testar as expressões regulares em diversos sabores: Python, Ruby, .NET, GO, Haskell, Java, JavaSript, etc… Nesse tutorial só vai ser usado o aplicativo [egrep](https://jneves.wordpress.com/tag/egrep/) para testarmos os exemplos, se quiser algo mais trabalhado baixe o editor de texto [EditPad Lite](https://www.editpadlite.com/download.html).

Caso opite pelo egrep você vai precisar do Linux ou algum terminal que tenha o egrep, como o [git-bash](https://git-scm.com/downloads). Todos os aquivos usados estão na pasta *examples*.

No terminal digite o comando **egrep \<expressão> \<arquivo>**.

```
egrep --color=auto 'doce.' examples/basico/doce.txt
```

O flag `--color=auto` após o egrep serve para colorir os *macthes* e só precisa coloca-lo a primeira vez que utilizar o egrep, depois pode omitir dos exemplos futuros. Sempre que a saída voltar ao normal utilize esse flag, não é legal ver a saída toda em preto e branco.

### TABELAS

#### Metacaracteres

| Metacaractere | Nome         | Dicas                                                         |
| :-----------: | ------------ | ------------------------------------------------------------- |
|    .          | Ponto        | Curinga de um caractere                                       |
|   [ ]         | Lista        | Dentro todos são normais, traço é intervalo ASCII, [:POSIX:] |
|   [^]         | Lista negada | Sempre casa algo, \[^[:POSIX:]]                               |
|    ?          | Opcional     | 0 ou 1, pode ter ou não                                       |
|    *          | Asterisco    | 1 ou mais, repetem em qualquer quantidade                     |
|    +          | Mais         | 1 ou mais, repete em qualquer quantidade, pelo menos uma vez   |
|   { , }       | Chaves       | Número exato, mínimo, máximo, ou uma faixa numérica           |
|    ^          | Circunflexo  | Casa o começo da linha, especial no começo da ER               |
|    $          | Cifrão       | Casa o fim da linha, especial no fim da ER                    |
|   \b          | Borda        | Limita uma palavra (letras, números, e sublinhado)           |
|    \          | Escape       | Escapa um meta, tira seu poder, escapar a si mesmo \\         |
|   \|          | Ou           | Indica alternativas, poder multiplicado pelo grupo            |
|   ( )         | Grupo        | Agrupa, é quantificável, poder conter outros grupos           |
|   \1          | Retrovisor   | Usado com grupo, máximo 9, conta da esquerda para direita     |
|   .*          | Curinga      | Qualquer coisa, o tudo e o nada                               |
|   ??          | Opcional     | Não-guloso, 0 ou 1, casa o mínimo possível                    |
|   *?          | Asterisco    | Não-guloso, 0 ou mais, casa o mínimo possível                 |
|   +?          | Mais         | Não-guloso, 1 ou mais, casa o mínimo possível                 |
|   { }?        | Chaves       | Não-guloso, numérico, casa o mínimo possível                  |

#### Classes POSIX

| Classe POSIX |  Similar       |    Significa           |
| :----------: | :------------: | :--------------------: |
| [:upper:]    |   [A-Z]        |    Maiúsculas          |
| [:lower:]    |   [a-z]        |    Minusculas          |
| [:alpha:]    |  [A-Za-z]      | Maiúsculas/Minusculas  |
| [:alnum:]    | [A-Za-z0-9]    |  Letras e números      |
| [:digit:]    |   [0-9]        |    Números             |
| [:xdigit:]   | [0-9A-Fa-f]    | Números hexadecimais   |
| [:punct:]    | [.,!?: ...]    |  Letras e números      |
| [:blank:]    |   [ \t]        |   Espaço e Tab         |
| [:space:]    | [ \t\n\r\f\v]  |  Caracteres brancos    |
| [:cntrl:]    |                | Caracteres controle    |
| [:granph:]   | [^ \t\n\r\f\v] | Caracteres imprimíveis |
| [:print:]    | [^\t\n\r\f\v]  | Imprimíveis e o espaço |
