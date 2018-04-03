# Outros Metacaracteres

Deixando as âncoras em nossa memória, agora já sabemos como casar algo, em alguma quantidade, em algum lugar na linha. Então vamos ver outros metacaracteres que têm funções específicas e não relacionadas entre si e, portanto, não podem ser agrupados em outra classe fora a tradicional "outros".

## Escape: \

Como fazer para o metacaractere * ser literal?

Se você quiser que um metacaractere vire um caractere normal, coloque-o dentro de uma lista (tá lembrado né?), então lua[\*] casa lua*. O mesmo serve para qualquer outro metacaractere. Maaaaaa+s, para não precisar ficar toda hora criando listas de um único componente só para tirar seu valor especial, temos o metacaractere escape \ , que "escapa" um metacaractere, tirando todos os seus poderes.

Sabe a criptonita? Sim! do super-homem. O escape tem o mesmo poder. É só você colocar ele após um metacaractere, e ele vira um caractere normal, fraco, sem poder também. Escapando, \* é igual a [*] que é igual a um asterisco literal. Similarmente podemos escapar todos os metacaracteres já vistos:

```
\. \[ \] \? \+ \{ \} \A \$
```

O escape é tão poderoso que pode escapar a si próprio! O \\ casa uma barra invertida \ literal. Escapar um circunflexo ou cifrão somente é necessário caso eles estejam em suas posições especiais, como casar o texto ^destaque^, em que ambos os circunflexos são literais, mas o primeiro será considerado uma âncora de começo de linha caso não esteja escapado.

#### Exercício

<div class="collapse">
  <ul class="collapse-list">
    <li>
      <label class="collapse-btn" for="collapse-1">Então, agora que sabemos muito sobre ERs, que tal uma expressão para casar um número de RG no formato nnn.nnn.nnn-n?.</label>
      <input class="collapse-open" type="checkbox" id="collapse-1" aria-hidden="true" hidden>
      <div class="collapse-painel container-resposta">
        <div class="collapse-inner">
          <p>Preciso explicar não né? Volta e veja <a href="{{site.ur}}representantes#lista--">lista</a>, <a href="{{site.ur}}quantificadores#chaves-n-m">chaves</a> e <a href="#escape-">escape</a> de novo.</p>
          <pre><code>$ egrep '[0-9]{3}\.[0-9]{3}\.[0-9]{3}-[0-9]' &lt;arquivo&gt;</code></pre>
        </div>
      </div>
    </li>
    <li>
      <label class="collapse-btn" for="collapse-2">Faça uma ER que procure por comentários de uma linha no arquivo *simple_comment.css*.</label>
      <input class="collapse-open" type="checkbox" id="collapse-2" aria-hidden="true" hidden>
      <div class="collapse-painel container-resposta">
        <div class="collapse-inner">
          <p>Lembrando que em css os comentários são assim "/* text */"</p>
          <p>Então só escapar os *, fora o meta curinga por quer ele vai casar qualquer coisa dentro do comentário.</p>

          <pre><code>$ egrep '/\*.*\*/' &lt;arquivo&gt;</code></pre>
        </div>
      </div>
    </li>
  </ul>
</div>

## Ou: |

É muito comum em uma posição específica de nossa ER termos mais de uma alternativa possível, por exemplo, ao casar um cumprimento amistoso, podemos ter uma terminação diferente para cada parte do dia: `boa-tarde|boa-noite`.

![Grupo]({{site.url}}assets/images/regex-ou.png)

O ou, representado pela barra vertical \|, serve para esses casos em que precisamos dessas alternativas.

Essa ER se lê: "ou boa-tarde, ou boa-noite"; ou seja, "ou isso ou aquilo". Lembre-se de que a lista também é uma espécie de ou, mas apenas para uma letra, então: `[gpr]ato é gato|pato|rato`

São similares, embora nesse caso, em que apenas uma letra muda entre as alternativas, a lista é a melhor escolha.

##### Exemplos

É útil casarmos um endereço de Internet, que pode ser uma página ou um servidor FTP.

```
egrep '(http://|ftp://)' <arquivo>
```

Ou isso ou aquilo, ou aquele outro... E assim vai. Podem-se ter tantas opções quantas for preciso.

## Grupo: ( )

O grupo tem o dom de juntar vários tipos de sujeitos em um mesmo local. Dentro de um grupo, podemos ter um ou mais caracteres, metacaracteres e inclusive outros grupos!

Como em uma expressão matemática, os parênteses definem um grupo, e seu conteúdo pode ser visto como um bloco na expressão.

Todos os metacaracteres quantificadores que vimos anteriormente podem ter seu poder ampliado pelo grupo, pois ele lhes dá mais abrangência. E o ou, pelo contrário, tem sua abrangência limitada pelo grupo: pode parecer estranho, mas é essa limitação que lhe dá mais poder.

Em um exemplo simples, (ai)+ agrupa a palavra "ai" e esse grupo está quantificado pelo mais. Isso quer dizer que casamos várias repetições da palavra, como ai, aiai, aiaiai,... E assim podemos agrupar tudo o que quisermos, literais e metacaracteres, e quantificá-los:

|	 Expressão		| Match						  |
| :---------------: | --------------------------- |
|	 (ha!)+		    | ha!, ha!ha!, ha!ha!ha!, ... |
|	(\\.[0-9]){3}   | .0.6.2, .2.8.9, .7.7, ...	  |
| (www\\.)?zz\\.com | www.zz.com, zz.com		  |

E em especial, nosso amigo "ou"...

![Grupo]({{site.url}}assets/images/regex-grupo.png)

|	 Expressão		 | Match				    |
| :----------------: | ------------------------ |
| boa-(tarde\|noite) | boa-tarde, boa-noite	    |
| (#\|n\\.\|núm) 7	 | \# 7, n. 7, núm 7		|
| (in\|con)?certo	 | incerto, concerto, certo |

Note que o grupo não altera o sentido da ER, apenas serve como marcador. Podemos criar subgrupos também.

##### exemplos

Imagine que você esteja procurando o nome de um supermercado em uma listagem e não sabe se este é um mercado, supermercado ou um hipermercado.

```
egrep '(super|hiper)mercado' <arquivo>
```

Essa ER consegue casar as duas últimas possibilidades, mas note que nas alternativas super e hiper temos um trecho em comum aos dois, então podíamos agrupar apenas as diferenças su e hi: 

```
egrep '(su|hi)permercado' <arquivo>
```

Precisamos também casar apenas o mercado sem os aumentativos, então temos de agrupá-los e torná-los opcionais: 

```
egrep ((su|hi)per)?mercado
```

Pronto! Temos a ER que buscávamos e ainda esbanjamos habilidade utilizando um grupo dentro do outro.

#### Exercícios

<div class="collapse">
  <ul class="collapse-list">
    <li>
      <label class="collapse-btn" for="collapse-3">E se tivesse minimercado também no texto?</label>
      <input class="collapse-open" type="checkbox" id="collapse-3" aria-hidden="true" hidden>
      <div class="collapse-painel container-resposta">
        <div class="collapse-inner">
          <p>Sem comentários.</p>

          <pre><code>$ egrep '(mini|((su|hi)per))?mercado' &lt;arquivo&gt;</code></pre>
        </div>
      </div>
    </li>
    <li>
      <label class="collapse-btn" for="collapse-4">
      Faça uma ER que procure no arquivo *Funcoes.java* as declarações de funções que retornem int. Supondo que a função é declarada assim "tipo minhaFuncao1(parametros)".
      </label>
      <input class="collapse-open" type="checkbox" id="collapse-4" aria-hidden="true" hidden>
      <div class="collapse-painel container-resposta">
        <div class="collapse-inner">
          <p>O tipo é int, depois tem que ter pelo menos um espaço, depois qualquer caractere alphanúmericos e em qualquer quantidade, depois abre e fecha parentes com qualquer letras em qualquer quantidade. Bem genérico.</p>

          <pre><code>$ egrep 'int +[[:alnum:]]+\([A-Za-z]*\)' Funcoes.java</code></pre>
        </div>
      </div>
    </li>
    <li>
      <label class="collapse-btn" for="collapse-5">
      Suponha que você é desorganizado e tem uma pasta cheia de arquivos com diferentes extenções. Você decidiu organizar as coisas, e primeiro quer saber os nomes de todos os arquivos de imagens que estão dentro dessa pasta. Eu rodei o comando `$ ls >> arquivos_diversos.txt ` na sua pasta, e agora todos os nomes dos arquivos estão no arquivo *arquivos_diversos.txt*. Faça uma ER que case todos os nomes que terminem com extenção de imagem. 
      </label>
      <input class="collapse-open" type="checkbox" id="collapse-5" aria-hidden="true" hidden>
      <div class="collapse-painel container-resposta">
        <div class="collapse-inner">
          <p>A linha pode começar com qualquer coisa, seguido de um ponto literal, seguindo de alguma extenção de imagem.</p>

          <pre><code>$ egrep '^.*\.(gif|jpg|jpeg|bmt|)' arquivos_diversos.txt</code></pre>
        </div>
      </div>
    </li>
    <li>
      <label class="collapse-btn" for="collapse-6">Como validar um horário no formato hh:mm? Teste no arquivo *horas.txt*.</label>
      <input class="collapse-open" type="checkbox" id="collapse-6" aria-hidden="true" hidden>
      <div class="collapse-painel container-resposta">
        <div class="collapse-inner">
          <p>Lembrando que a parte da hora vai de 0, ou 00, à 23. Os minutos vão de 00 à 59</p>
			    <p>No formato Hh:mm o primeiro H é opcional, e ele pode receber 0 ou 1 ou nada, seguido de qualquer número de 0 à 9. Ou, se a hora começar com 2 têm que ter qualquer número de 0 à 3. `([01]?[0-9]|2[0-3])`</p>
          <p>Após os : pode ser qualquer número que começe no intervalo de 0 à 5, seguido de qualquer número no intervalo de 0 à 9. `([0-5][0-9])`</p>
          <p>Agrupa as horas e os minutos com : entre eles. E coloca eles dentro de outro grupo com o meta <a href="{{site.ur}}representantes#borda-">borda</a> no inicio e fim.</p>

		    <pre><code>$ egrep '\b([01]?[0-9]|2[0-3]):([0-5][0-9])\b' horas.txt</code></pre>
        </div>
      </div>
    </li>
    <li>
      <label class="collapse-btn" for="collapse-7">Como validar uma data no formato dd/mm/yyyy? Teste no arquivo *dates.txt*.</label>
      <input class="collapse-open" type="checkbox" id="collapse-7" aria-hidden="true" hidden>
      <div class="collapse-painel container-resposta">
        <div class="collapse-inner">
          <p>O campo dia vai de 01 à 31. `(0[1-9]|[12][0-9]|3[01])`</p>
			    <p>O campo mês de 01 à 12. `(0[1-9]|1[0-2])`</p>
          <p>E o ano de 0000 à 9999. `[0-9]{4}`</p>

		      <pre><code>$ egrep '\b((3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4})\b' dates.txt</code></pre>
        </div>
      </div>
    </li>
    <li>
      <label class="collapse-btn" for="collapse-8">Que tal procurar siglas no formato A.B.C em textos? Teste no arquivo *siglas.txt*.</label>
      <input class="collapse-open" type="checkbox" id="collapse-8" aria-hidden="true" hidden>
      <div class="collapse-painel container-resposta">
        <div class="collapse-inner">
          <p>Sem comentários...</p>

          <pre><code>$ egrep '\b([A-Z]\.)+([A-Z])\b ' siglas.txt</code></pre>
        </div>
      </div>
    </li>
  </ul>
</div>


## Retrovisor: \1 ... \9

Ao usar um (grupo) qualquer, você ganha um brinde, e muitas vezes nem sabe. O brinde é o trecho de texto casado pela ER que está no grupo, que fica guardado em um cantinho especial, e pode ser usado em outras partes da mesma ER!.

Confuso né? presta atenção!

Vamos começar de novo. Como o nome diz, é retrovisor porque ele "olha pra trás'; para buscar um trecho já casado. Isso é muito útil para casar trechos repetidos em uma mesma linha. Veja bem, é o trecho de texto, e não a ER.

Como exemplo, em um texto sobre passarinhos, procuramos o quero-quero. Podemos procurar literalmente por quero-quero, mas assim não tem graça :), pois somos mestres em ERs e vamos usar o grupo e o retrovisor para fazer isso ;) :

```
egrep '(quero)-\1' <arquivo>
```

Então o retrovisor \1 é uma referência ao texto casado do primeiro grupo, nesse caso, quero, ficando no fim das contas a expressão que queríamos. O retrovisor pode ser lembrado também como um link ou um ladrão, pois copia o texto do grupo.

Pois é, lembra que o escape \ servia para tirar os poderes do metacaractere seguinte. Então, a essa definição agora incluímos: a não ser que este próximo caractere seja um número de 1 a 9, então estamos lidando com um retrovisor.

Notou no pulo do gato? Podemos ter no máximo nove retrovisores por ER, então \10 é o retrovisor número 1 seguido de um zero.

#### Exercícios

<div class="collapse">
  <ul class="collapse-list">
    <li>
      <label class="collapse-btn" for="collapse-8">Faça uma ER que procure por palavras repetidas e que tenham um - entre elas. Tipo big-big.</label>
      <input class="collapse-open" type="checkbox" id="collapse-8" aria-hidden="true" hidden>
      <div class="collapse-painel container-resposta">
        <div class="collapse-inner">
          <p>Só fazer um grupo restrito a letras, e repetir no mínimo 1 vez.</p>

          <pre><code>$ egrep '([A-Za-z]+)-\1' &lt;arquivo&gt;</code></pre>
        </div>
      </div>
    </li>
    <li>
      <label class="collapse-btn" for="collapse-9">
      E se eu quiser que além de casar palavras com um -, também case com palavras repetidas sem o - ?. Tipo bombom e bom-bom. Já dei a resposta.
      </label>
      <input class="collapse-open" type="checkbox" id="collapse-9" aria-hidden="true" hidden>
      <div class="collapse-painel container-resposta">
        <div class="collapse-inner">
          <p>Só fazer um grupo restrito a letras, repetir no mínimo 1 vez e tornar o - <a href="{{site.ur}}representantes#opcional-">opcional</a>.</p>

          <pre><code>$ egrep '([A-Za-z]+)-?\1' &lt;arquivo&gt;</code></pre>
        </div>
      </div>
    </li>
  </ul>
</div>

Note como vamos construindo as ERs aos poucos, melhorando, testando e não simplesmente escrevendo tudo de uma vez. Esta é a arte ninja.

#### Mais detalhes

Como já dito, podemos usar no máximo nove retrovisores. Vamos ver uns exemplos e viajar com mais de um de nossos amigos novos. É fundamental que você entenda todos:

|			Expressão		   | Match						|
| :--------------------------: | -------------------------- |
|	(lenta)(mente) é \2 \1	   | lentamente é mente lenta	|
|	((band)eira)nte \1 \2a	   | bandeirante bandeira banda |
|	in(d)ol(or) é sem \1\2	   | indolor é sem dor			|
| ((((a)b)c)d)-1 = \1,\2,\3,\4 | abcd-1 = abcd,abc,ab,a		|

![Grupo]({{site.url}}assets/images/metacaractere-retrovisor.png)

Para não se perder nas contagens, dica: conte somente os parênteses que abrem, da esquerda para a direita. Este vai ser o número do retrovisor. E o conteúdo é o texto casado pela ER do parêntese que abre até seu correspondente que fecha.

Nos nossos exemplos ocorre a mesma coisa porque a ER dentro do grupo já é o próprio texto, sem metacaracteres. Veja, entretanto, que ([0-9])\1 casa 66 mas não 69.

1. **Escape**
	1. O escape escapa um metacaractere, tirando seu poder.
	2. \\* = [*] = asterisco literal.
	3. O escape escapa o escape, escapando-se a si próprio simultaneamente. 
2. **Ou**
	1. O ou indica alternativas.
	2. Lista para um caractere, ou para vários.
3. **Grupo**
	1. Grupos servem para agrupar.
	2. Grupos são muito poderosos.
	3. Grupos podem conter grupos.
	4. Grupos são quantificáveis.
4. **Retrovisor**
	1. O retrovisor só funciona se usado com o grupo.
	2. O retrovisor serve para procurar palavras repetidas.
	3. Numeram-se retrovisores contando os grupos da esquerda para a direita.
	4. Temos no máximo 9 retrovisores por ER.

<div class="nav-bottom">
	<button><a href="{{site.url}}parte_1/ancoras">Anterior</a></button>
</div>
