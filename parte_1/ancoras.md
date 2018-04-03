# Tipos Âncora

Vamos agora falar sobre os metacaracteres do tipo âncora. Por que âncora? Porque eles não casam caracteres ou definem quantidades, em vez disso, eles marcam uma posição específica na linha. Assim, eles não podem ser quantificados, então o mais, o asterisco e as chaves não têm influência sobre âncoras.

## Circunflexo: ^

O circunflexo também é o marcador de lista negada, mas apenas dentro da lista (e no começo), fora dela, ele é a âncora que marca o começo de uma linha.

^[0-9]

![Ponto]({{site.url}}assets/images/regex-circunflexo.png)

Isso quer dizer: a partir do começo da linha, case um número, ou seja, procuramos linhas que começam com números. O contrário seria: 

^\[^0-9]

Ou seja, procuramos linhas que não começam com números. O primeiro circunflexo é a âncora e o segundo é o "negador" da lista. É claro que o circunflexo como marcador de começo de linha só é especial se estiver no começo da ER por que não faz sentido procurarmos uma palavra seguida de um começo de linha, pois se tiver uma palavra antes do começo de uma linha, ali não é o começo da linha! Desse modo, a ER: 

[0-9J^

Casa um número seguido de um circunflexo literal, em qualquer posição da linha. E essa ER casa o que?.

^^ 

Essa ER procura por linhas que começam com um circunflexo.

#### Exercício

<div class="collapse">
  <ul class="collapse-list">
    <li>
      <label class="collapse-btn" for="collapse-1">
        Faça uma ER que procure em um texto linhas que tenham a fala de alguém.
      </label>
      <input class="collapse-open" type="checkbox" id="collapse-1" aria-hidden="true" hidden>
      <div class="collapse-painel container-resposta">
        <div class="collapse-inner">
          <p>Em português todos sabem que a fala de alguém começa com travessão -</p>
          <pre><code>$ egrep '^-' &lt;arquivo&gt;</code></pre>
        </div>
      </div>
    </li>
  </ul>
</div>

## Cifrão: $

Complementar ao circunflexo, o cifrão marca o fim de uma linha e só é válido no final de uma ER. Como o exemplo anterior, [0-9]$ casa linhas que terminam com um número.

![Ponto]({{site.url}}assets/images/regex-cifrao.png)

E o que você me diz da ER `^$`? 

\- Hmmmm... Começo de linha, com o fim de linha... Uma linha vazia?

Acertou! É sempre bom ter essa ER na manga, pois procurar por linhas em branco é uma tarefa comum nas mais diversas situações.

| Expressão  | Match	                               |
| :--------: | --------------------------------------- |
| .....$     | Últimos 5 caracteres de uma linha       |
| ^.{20,60}$ | Linhas que tenham de 20 a 60 caracteres |

#### Exercícios

<div class="collapse">
  <ul class="collapse-list">
    <li>
      <label class="collapse-btn" for="collapse-2">Como você faria pra restringir uma tag &lt;input&gt; para somente números?</label>
      <input class="collapse-open" type="checkbox" id="collapse-2" aria-hidden="true" hidden>
      <div class="collapse-painel container-resposta">
        <div class="collapse-inner">
          <p>Se eu quero restringir uma tag input para somente números, preciso de um metacaractere para representar números, nada melhor que a <a href="{{site.ur}}representantes#lista--">lista</a> com todos os caracteres permitidos</p>
          <p>Também vou precisar de um metacaractere que quantifique as repetições, como pode ter vários números, inclusive nenhum, podemos usar o <a href="{{site.ur}}quantificadores#asterisco-">asterisco</a>.</p>
          <pre><code>$ egrep '^[0-9]*$' &lt;arquivo&gt;</code></pre>

          <p>Se o mínimo de repetições fosse um, podiamos usar o <a href="{{site.ur}}quantificadores#mais-">mais +</a>.</p>
          <p>Se tivesse uma faixa de repetições, podiamos usar as <a href="{{site.ur}}quantificadores#chaves-n-m">chaves {,}</a></p>
        </div>
      </div>
    </li>
    <li>
      <label class="collapse-btn" for="collapse-3">E para validar um e-mail? Desafiador! Teste no arquivo *emails.txt*.</label>
      <input class="collapse-open" type="checkbox" id="collapse-3" aria-hidden="true" hidden>
      <div class="collapse-painel container-resposta">
        <div class="collapse-inner">
          <p>Existem várias formas de fazer isso, provavelmente você quer ver a resposta e comparar com o seu, mas talvez não esteja exatamente igual. Optei por uma simples para começar, mas essa não é a melhor forma de validar email e sim simples.</p>
          <p>Pessando antes do @, podemos aceitar qualquer caracter que seja letra, número, e também alguns outros símbolos. As repetições tem que ser no mínimo 1, então o + casa bem.</p>
          <p>Após o @, podemos aceitar apenas letras minúsculas e um ponto entre eles. A repetição do domínio pode ter no mínimo 1 caractere, depois do domínio tem que ter duas ou três ocorrências.</p>
          
          <pre><code>$ egrep '([A-Za-z0-9]|[-_])+@[a-z]+\.[a-z]{2,3}' emails.txt</code></pre>

          <p>Relembrando que essa é uma das formas!</p>
        </div>
      </div>
    </li>
  </ul>
</div>

## Borda: \b

A borda, que como o próprio nome já diz, marca uma borda, mais especificamente, uma borda de palavra. Ela marca os limites de uma palavra, ou seja, onde ela começa e/ou termina.

Muito útil para casar palavras exatas, e não partes de palavras. Veja como se comportam as ERs nas palavras dia, diafragma, melodia, radial e bom-dia!:

| Expressão | Match    		                            |
| :-------: | ----------------------------------------- |
| dia       | dia, diafragma, melodia, radial, bom-dia! |
| \bdia     | dia, diafragma, bom-dia!                  |
| dia\b     | dia, melodia, bom-dia!                    |
| \bdia\b   | dia, bom-dia!                             |

![Ponto]({{site.url}}assets/images/regex-borda.png)

A borda força um começo ou a terminação de uma palavra. Entenda que "palavra" aqui é um conceito que engloba [A-Za-z0-9_] apenas, ou seja, letras, números e o sublinhado.

Por isso `\bdia\b` também casa bom-dia!, pois o traço e a exclamação não são parte de uma palavra.

#### Exercício

<div class="collapse">
  <ul class="collapse-list">
    <li>
      <label class="collapse-btn" for="collapse-4">Faça uma ER que procure por códigos binários.</label>
      <input class="collapse-open" type="checkbox" id="collapse-4" aria-hidden="true" hidden>
      <div class="collapse-painel container-resposta">
        <div class="collapse-inner">
          <p>Sem comentários.</p>
          <pre><code>$ egrep '\b[01]+\b' &lt;arquivo&gt;</code></pre>
        </div>
      </div>
    </li>
  </ul>
</div>

### Resumos

1. **Circunflexo**
  1. Serve para procurar palavras no começo da linha.
  2. Só é especial no começo da ER (e de uma lista).
2. **Cifrão**
  1. Serve para procurar palavras no fim da linha.
  2. Só é especial no final da ER.
3. **Borda**
  1. A borda marca os limites de uma palavra.
  2. O conceito "palavra" engloba letras, números e o sublinhado.
  3. A borda é útil para casar palavras exatas e não parciais.

<div class="nav-bottom">
	<button><a href="{{site.url}}parte_1/quantificadores">Anterior</a></button>
  <button><a href="{{site.url}}parte_1/outros">Próximo</a></button>
</div>
