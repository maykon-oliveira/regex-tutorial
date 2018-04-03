# Tipos Quantificadores

Os quantificadores são o segundo tipo de metacaracteres, que servem para indicar o número de repetições permitidas para a entidade imediatamente anterior. Essa entidade pode ser um caractere ou metacaractere.

Em outras palavras, eles dizem a quantidade de repetições que o átomo anterior pode ter, ou seja, quantas vezes ele pode aparecer. Os quantificadores não são quantificáveis, então dois deles seguidos em uma ER é um erro, salvo quantificadores não-gulosos, que veremos depois.

## Opcional: ?

O opcional é um quantificador que para ele pode ter ou não a ocorrência da entidade anterior, pois ele a repete O ou 1 vez.

Por exemplo, a ER `5?` significa zero ou uma ocorrência do número 5. Se tiver um 5, beleza, casamento efetuado. Se não tiver, beleza também. Isso torna o 5 opcional (daí o nome), ou seja, tendo ou não, a ER casa.

Veja mais um exemplo. A ER `nadar?` tem a letra r marcada como opcional, então ela casa nada e nadar. 

Vimos o metacaractere lista e agora vimos o opcional, então, que tal fazermos uma lista opcional? Voltando um pouco àquele exemplo da palavra nada, vamos fazer a ER `nada[r!]?`. Mmmmmm... As ERs estão começando a ficar interessantes, não? Memorize desde de já: leia a ER átomo por átomo, da esquerda para a direita.

### Como ler uma ER

Uma ER se lê como um robozinho leria. Primeiro lê-se átomo por átomo, depois entende-se o todo e então se analisa as possibilidades. Na nossa ER `nada[r!]?` em questão, sua leitura fica: um n seguido de um a, seguido de um d, seguido de um a, seguido de: ou r, ou !, ambos opcionais.

Essa leitura é essencial para o entendimento da ER. Ela pode em um primeiro momento ser feita em voz alta, de maneira repetitiva, até esse processo se tornar natural. Depois ela pode ser feita mentalmente mesmo, e de maneira automática.

Feita a leitura, agora temos de entender o todo, ou seja, temos um trecho literal nada, seguido de uma lista opcional de caracteres. Para descobrirmos as possibilidades, é o nada seguido de cada um dos itens da lista e por fim seguido por nenhum deles, pois a lista é opcional.

| Expressão | Match     	      |
| :-------: | ------------------ |
| nada[r!]? | nada, nadar, nada! |
| fala[r!]? | fala, falar, fala! |

![Opcional]({{site.url}}assets/images/regex-opcional.png)

Desvendamos os segredos da ER. É claro, esta é pequena e fácil, mas o que são ER grandes senão várias partes pequenas agrupadas? O principal é dominar essa leitura por átomos. O resto é ler devagar até chegar ao final. Não há mistério. Espero que tenha entendido.

Voltemos ao nosso exemplo de marcações HTML, podemos facilmente incluir agora as marcações que fecham o trecho, em que a única diferença é que vem uma barra / antes da letra:

| Expressão		     | Match         					       |
| :---------------: | ----------------------------------- |
| \</?[\[:lower:]]> | \<p>, \<header>, \</p>, \</tr>, ... |

## Asterisco: *

Se o opcional pode ter ou não a entidade anterior, o asterisco é mais tranquilo ainda, pois para ele pode ter, não ter ou ter vários, infinitos. Em outras palavras, a entidade anterior pode aparecer em qualquer quantidade. 

| Expressão | Match  									           |
| :-------: | -------------------------------------------- |
| 10*	      | 1, 10, 100, 10000000000, ...				     |
| Deboas*!  | Deboa!, Deboasss!, Deboasssss...!		        |
| b[ip]*	   | b, bi, bipp, biiippp, bipipipipi, bippppp... |
| 6*0	      | 0, 60, 660, 6666660, ...					        |

Note que agora, com o asterisco, nossa ER já não tem mais um número 
finito de possibilidades.

![Asterisco]({{site.url}}assets/images/regex-asterisco.png)

### Metacaracteres gulosos

O que casará [ar]*a na palavra arara? Alternativas:
1. a,          [ar] zero vezes, seguido de a
2. ara,        [ar] duas vezes (a,r), seguido de a
3. arara,      [ar] quatro vezes (a,r,a,r), seguido de a
4. n.d.a.

Acertou se você escolheu a número 3. O asterisco repete em qualquer quantidade, mas ele sempre tentará repetir o máximo que conseguir. As três alternativas são válidas, mas entre casar a lista [ar] zero, duas ou quatro vezes, ele escolherá o maior número possível. Por isso se diz que o asterisco é guloso.

## Curinga: .*

Vimos que temos dois metacaracteres extremamente abrangentes, como o [ponto]({{site.url}}parte_1/representantes#ponto-) (qualquer caractere) e o [asterisco](#asterisco-) (em qualquer quantidade). E se juntarmos os dois? Teremos qualquer caractere, em qualquer quantidade. O que isso significa? Tudo? Nada? A resposta é: ambos.

O nada, pois "qualquer quantidade" também é igual a "nenhuma quantidade". Então é opcional termos qualquer caractere, não importa. Assim, uma ER que seja simplesmente .* sempre será válida e casará mesmo uma linha vazia.

O tudo, pois "qualquer quantidade" também é igual a "tudo o que tiver". E é exatamente isso o que o asterisco faz, ele é guloso, e sempre tentará casar o MÁXIMO que conseguir.

Assim, temos aqui o curinga das ERs, uma carta para se usar em qualquer situação. É muito comum, ao escrever uma expressão regular, você definir alguns padrões que procura, e lá no meio, em uma parte que não importa, pode ser qualquer coisa, você coloca um .* e depois continua a expressão normalmente. 

Por exemplo, procurar acessos de usuários em uma data qualquer: `20/03/2018.*login.`

## Mais: +

O mais tem funcionamento idêntico ao do asterisco, tudo o que vale para um se aplica ao outro. A única diferença é que o mais não é opcional, então a entidade anterior deve casar pelo menos uma vez, e pode haver várias.

Sua utilidade é quando queremos no mínimo uma repetição. Não há muito que acrescentar, é um asterisco mais exigente...

| Expressão | Match  									            |
| :-------: | --------------------------------------------- |
| 10+	      | 10, 100, 10000000000, ...					      |
| Deboas+!  | Deboas!, Deboasssss...!					         |
| b[ip]+	   | bi, bp, bipp, biiippp, bipipipipi, bippppp... |
| 6+0	      | 60, 660, 66666666660, ...					      |

![Mais]({{site.url}}assets/images/regex-mais.png)

## Chaves: {n, m}

As chaves são a solução para uma quantificação mais controlada, onde se pode especificar exatamente quantas repetições se quer da entidade anterior. Basicamente, {n,m} significa de n até m vezes, assim algo como 7{1,4} casa 7, 77, 777 e 7777. Só, nada mais que isso.

Temos também a sintaxe relaxada das chaves, em que podemos omitir a quantidade final ou ainda, especificar exatamente um número:

| Metacaractere | Repetições		    |
| :-----------: | ------------------ |
| {1,3}			 | De 1 a 3			    |
| {3,}			 | Mínimo 3 (ou mais) |
| {0,3}			 | Até 3				    |
| {3}			    | Exatamente 3		 |
| {1}			    | Exatamente 1		 |
| {0,1}			 | 0 ou 1			    |
| {0,}			 | 0 ou mais			 |
| {1,}			 | 1 ou mais			 |
| n{1,4}		    | n, nn, nnn, nnnn   |

![Chaves]({{site.url}}assets/images/regex-chaves.png)

Note que o {1} tem efeito nulo, pois 7{1} é igual a 7. Pode encher de {1} que não mudará sua lógica. Mas observe os três últimos exemplos.

Com as chaves, conseguimos simular o funcionamento de outros três metacaracteres, o opcional, o asterisco e o mais. Prefira sempre usar esses 3 ao invés das chaves, fica mais fácil.

### Resumos

1. **Opcional**
   1. O opcional é opcional.
   2. O opcional é útil para procurar palavras no singular e plural.
   3. Podemos tornar opcionais caracteres e metacaracteres.
   4. Leia a ER átomo por átomo, da esquerda para a direita.
   5. Leia a ER, entenda o todo e analise as possibilidades.
2. **Asterisco**
   1. O asterisco repete em qualquer quantidade.
   2. Quantificadores são gulosos.
   3. O curinga .* é o tudo e o nada, qualquer coisa.
3. **Mais**
   1. O mais repete em qualquer quantidade, pelo menos uma vez.
   2. O mais é igual ao asterisco, só mais exigente. 
4. **Chaves**
   1. Chaves são precisas.
   2. Você pode especificar um número exato, um mínimo, um máximo, ou uma faixa numérica.
   3. As chaves simulam os seguintes metacaracteres: * + ?.

<div class="nav-bottom">
   <button><a href="{{site.url}}parte_1/representantes">Anterior</a></button>
   <button><a href="{{site.url}}parte_1/ancoras">Próximo</a></button>
</div>