# Tipos Representantes

O primeiro grupo de metacaracteres são os do tipo representante, estes são metacaracteres cuja função é representar um ou mais caracteres. Todos os metacaracteres desse tipo casam a posição de um único caractere, e não mais que um.

## Ponto: .

Nas ER o ponto é um dos metacaracteres mais utilizados, ele casa com qualquer coisa, não importa com quem seja. Pode ser um número, uma letra, um Tab, um @, o que vier ele traça, pois o ponto casa qualquer coisa.

##### Exemplos

Suponhamos uma regex que contenha os caracteres "doce" e o metacaractere ponto, assim: "doce.". Quando procuramos essa ER no arquivo *doce.txt*. Veja como testar os exemplos [aqui]({{site.url}}#executando-os-exemplos).

`$ egrep 'doce.' doce.txt`

Nesse pequeno trecho de texto, nossa ER casou seis vezes: " . , ?". Veja que o . também casa com o .

Como exemplos de uso do ponto, em um texto normal em português, você pode procurar palavras que você não se lembra se acentuou ou não, que podem começar com maiúsculas ou não ou que foram escritas errado:

| Expressão |         Match         |
| :-------: | :-------------------: |
|    n.o    |     não, nao, ...     |
|  .tring   |  String, string, ...  |
|   .lass   |   Class, class, ...   |
|    <.>    | \<p>, \<i>, \<B>, ... |

![Ponto]({{site.url}}assets/images/regex-ponto.png)

#### Exercício

<div class="collapse">
  <ul class="collapse-list">
    <li>
      <label class="collapse-btn" for="collapse-1">
        Em java o comentário simples é feita pela "//". Faça uma ER que procure por comentários no arquivo *Decoder.java*. So continue se acertar essa!
      </label>
      <input class="collapse-open" type="checkbox" id="collapse-1" aria-hidden="true" hidden>
      <div class="collapse-painel container-resposta">
        <div class="collapse-inner">
          <p>Vixee! não coneguiu?</p>
          <p>- (U.U')</p>
          <p>Primeiro, o que você quer procurar?</p>
          <p>- Duas barras em um codigo Java...</p>
          <p>Então você só bota "//" pra procurar! É simples assim, não precisa de nenhum metacaractere ainda.</p>
          <pre><code>$ egrep '//' Decoder.java</code></pre>
        </div>
      </div>
    </li>
  </ul>
</div>

## Lista: [ ]

Bem mais exigente que o ponto, a lista não casa com qualquer um. Ela sabe exatamente o que quer, e não aceita nada diferente daquilo, a lista casa com quem ela conhece. A ordem dos caracteres dentro da lista não importa.

Ela guarda dentro de si os caracteres permitidos para casar, então algo como [Tt]eclado limita nosso casamento a Teclado ou teclado.

![Lista]({{site.url}}assets/images/regex-lista.png)

No exemplo anterior do ponto, sobre acentuação, tínhamos a ER `n.o`. Entretanto, além dos casamentos desejados, ela é muito abrangente, e também casa coisas indesejáveis como neo, n-o, n5o e n o. Por que o ponto casa com qualquer coisa.

Para que nossa ER fique mais específica, trocamos o ponto pela lista, para casar apenas "não" e "nao". 

```
$ egrep 'n[ãa]o' <arquivo>
```

E, assim como o `n.o`, todos os outros exemplos anteriores do ponto casam muito mais que o desejado usando a lista. É importante frizar que dentro da lista, todo mundo é normal. Então qualquer outro metacaractere dentro da lista é apenas um caractere normal, e não um metacaractere. 

| Expressão |     Match      |
| :-------: | :------------: |
|  n[ão]o   |    não, nao    |
| [Ss]tring | String, string |
| [Cc]lass  |  Class, class  |
|  <[pi]>   |   \<p>, \<i>   |

#### Exercício

<div class="collapse">
  <ul class="collapse-list">
    <li>
      <label class="collapse-btn" for="collapse-2">
        A palavra cinza tem diferença na escrita quando traduzido para o inglês britânico ou americano (grey ou gray). Faça uma ER que encontre todas as ocorrências desse tipo no arquivo *cinza.txt*.
      </label>
      <input class="collapse-open" type="checkbox" id="collapse-2" aria-hidden="true" hidden>
      <div class="collapse-painel container-resposta">
        <div class="collapse-inner">
          <p>Se você prestou atenção a única diferença nas duas palavras são as letras "ae". Então é so colocar dentro de uma lista essas letras</p>
          <pre><code>$ egrep 'gr[ae]y' &lt;arquivo&gt;</code></pre>
          <p>Onde tiver gray ou grey no texto vai casar!</p>
        </div>
      </div>
    </li>
  </ul>
</div>

### Intervalos em listas

Até agora, vimos que a lista abriga todos os caracteres permitidos em uma posição. Então como seria uma lista que dissesse que em uma determinada posição poderia haver apenas números? 

\- Hmmm... [0123456789] ?

Acertou! Porém existe uma forma mais fácil. Mas antes imagine ai uma ER pra validar uma data no formato dd/MM. Pensou? Parece com isso?

\[0123456789]\[0123456789]/\[0123456789]\[0123456789]

Se fosse assim eu mesmo parava por aqui. Mas como eu disse, existe uma forma mais fácil. Lembra que eu disse que dentro da lista, todo mundo é um caracter normal? Pois é, aqui temos a primeira exceção à regra.

Todo mundo, fora o traço. Se tivermos um traço(-) entre dois caracteres, isso representa todo o intervalo entre eles. É assim, olhe: [0123456789] é igual a [0-9]. É simples assim. Aquele tracinho indica um intervalo entre 0 a 9. Então voltando a data...

\[0-9]\[0-9]/\[0-9]\[0-9]

Os intervalos respeitam a ordem numérica da tabela ASCII, então basta tê-la em mãos para ver que um intervalo como A-z não pega somente as maiúsculas e minúsculas, como era de se esperar.

Para sua comodidade, a tabela está [aqui]({{site.url}}), e nela podemos ver que A-z pega também" [ \) ^_ ' " e não pega os caracteres acentuados como "áéóõç''.

### POSIX

Infelizmente, não há um intervalo válido para pegarmos todos os caracteres acentuados de uma vez. Como para nós brasileiros se a-z não casar letras acentuadas não serve para muita coisa, temos uns curingas para usar dentro de listas que são uma mão na roda.

Eles são chamados de classes de caracteres POSIX. São grupos definidos por tipo, e POSIX é um padrão internacional que define esse tipo de regra, como será sua sintaxe etc. [Tabela]({{site.url}}#classes-posix)

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

Note que os colchetes fazem parte da classe e não são os mesmos colchetes da lista. Para dizer maiúsculas, fica [[:upper:]], ou seja, um [:upper:] dentro de uma lista []. 

## Lista negada: [^]

Nem tão exigente quanto a lista nem tão necessitada quanto o ponto, temos a lista negada. A lista negada é exatamente igual à lista, podendo ter caracteres literais, intervalos e classes POSIX. Tudo o que se aplica a lista normal se aplica à negada também. 

A única diferença é que ela possui lógica inversa, ou seja, ela casará com qualquer coisa, exceto com os caracteres listados. Observe que a diferença em sua notação é que o primeiro caractere da lista é um circunflexo, ele indica que essa é uma lista negada.

Então, se [0-9] são números, \[^0-9] é qualquer coisa fora números. Pode ser letras, símbolos, espaço em branco, qualquer coisa, menos números. 

![Range Lista]({{site.url}}assets/images/regex-range.png)

Como o traço e o colchete que fecha, o circunflexo é especial, então, para colocarmos um ^ literal em uma lista, precisamos pô-lo em qualquer posição que não seja a primeira. Assim [A-Z^] casa maiúsculas e o circunflexo e \[^A-Z^] é o inverso: qualquer coisa fora maiúsculas e o circunflexo. 

As classes POSIX também podem ser negadas, então \[^[:digit:]] casa "qualquer coisa fora números''.

##### Exemplos

Utilize a lista negada quando você sabe exatamente o que não pode ter em uma posição, como um erro ortográfico ou de escrita. Por exemplo, sempre após caracteres de pontuação, como a vírgula ou o ponto, devemos ter um espaço em branco os separando do resto do texto. Então vamos procurar por qualquer coisa que não seja o espaço após a pontuação no arquivo *erro_espaco.html*:

```
$ egrep '[:;,.!?][^ ]' erro_espaco.html
```

ou melhor

```
$ egrep '[[:punct:]][^ ]' erro_espaco.html
```

### Conclusão

Agora você já sabe a função de cada tipo representante, não se preocupe se não entedeu tudo por completo, a melhor forma de aprender ER é praticando e não lendo. Você ainda vai praticar muito aqui, até dar uma dor!. Espera quando começar a ver os tipos quantificadores.

### Resumos

1. **Ponto**
   1. O ponto casa com qualquer coisa.
   2. O ponto casa com o ponto.
   3. O ponto é um curinga para casar um caractere. 
2. **Lista**
   1. A lista casa com quem ela conhece e tem suas próprias regras.
   2. Dentro da lista, todo mundo é normal.
   3. Dentro da lista, traço indica intervalo.
   4. Um - literal deve ser o último item da lista.
   5. Um ]  literal deve ser o primeiro item da lista.
   6. Os intervalos respeitam a tabela ASCII (não use A-z).
   7. [:classes POSIX:] incluem acentuação, A-Z não.
3. **Lista negada**
   1. Uma lista negada segue todas as regras de uma lista normal.
   2. Um ^ literal não deve ser o primeiro item da lista.
   3. \[:classes POSIX] podem ser negadas.
   4. A lista negada sempre deve casar algo.

<div class="nav-bottom">
   <button><a href="{{site.url}}parte_1/intro">Anterior</a></button>
   <button><a href="{{site.url}}parte_1/quantificadores">Próximo</a></button>
</div>
