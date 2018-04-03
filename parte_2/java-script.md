# Sabor JavaScript


| Metodo |						Descrição					 |
| :------------: | :---------------------------------------------------- |
|	`exec`	| Um método `RegExp`  que execute uma pesquisa por uma correspondência em uma string. Retorna um array de informações. |
|  `test`  |	Um método `RegExp` que testa uma correspondência em uma string. Retorna true ou false.	|
|	`match`	  |					  Um método `String` que executa uma pesquisa por uma correspondência em uma string. Retorna uma array de informações ou null caso não haja uma correspondência.						 |
|	`search`	  |	Um método `String` que testa uma correspondência em uma string. Retorna o indice da correspondência ou -1 se o teste falhar.	|
|	`replace` |	Um método `String` que executa uma pesquisa por uma correspondência em uma string, e substitui a substring correspondênte por uma substring de substituição.	|
|	 `split`	   |	Um método `String`  que usa uma expressão regular ou uma string fixa para quebrar uma string dentro de um array de substrings.	|


Em JavaScript as ER são padrões utilizados para selecionar combinações de caracteres em uma string. No JavaScript, as expressões regulares também são objetos. Elas podem ser utilizadas com os métodos  `exec()` e `test()` do objeto *RegExp*, e com os métodos  `match()`, `replace()`, `search()`, e `split()` do objeto  *String*.

### Criando uma Expressão Regular

Há duas maneiras de construir uma expressão regular:

1. Usando uma expressão literal, que devem ser cercadas por barras, no começo e no fim do padrão, sem aspas.

```javascript
var regex = /^[0-9]/;
```

As expressões regulares na forma literal são compiladas quando o script é carregado. Esta forma de construção possui melhor performace quando a expressão regular utilizada é constante, ou seja, não muda durante a execução.

Logo após a segunda barra, é possível colocar os flags de maiúsculas e minúsculas, e casamento global: i e g.

```javascript
var regex = /pattern/flags;
```

```javascript
var regex = /[a-z]/;   // minúsculas
var regex = /[a-z]/i;  // minúsculas e maiúsculas
var regex = /[a-z]/g;  // minúsculas, casamento global
var regex = /[a-z]/ig; // minúsculas e maiúsculas, global
```

2. Chamando o construtor do objeto RegExp:

```javascript
var regex = new RegExp("ab+c");
```

Usando o construtor a compilação da expressão regular é realizada em tempo de execução. Use o construtor quando souber que o padrão da expressão regular irá mudar ou quando o padrão for desconhecido. É útil para compor a expressão usando variáveis ou um texto vindo do usuário.

Lembrando que o parâmetro do construtor é uma string, então você precisará escapar as contrabarras: o barra-letra \w, por exemplo, deve ser informado como\\\w. Veja como ficam os exemplos anteriores, usando string:

```javascript
var regex = new RegExp("pattern", "flags");
```

```javascript
var regex = new RegExp('[a-z]');
var regex = new RegExp('[a-z]', 'i');
var regex = new RegExp('[a-z]', 'g');
var regex = new RegExp('[a-z]', 'ig');
```
### Métodos

- [String.search()](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/search)  - Testa se casou e retorna o indexou -1
- [String.match()](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/match)   - Retorna array com o trecho casado ou null
- [String.replace()](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/replace) - Faz substituições, retorna string
- [String.split()](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/split)   - Faz divisões, retorna array
- [RegExp.exec()](https://developer.mozilla.org/pt-BR/docs/Web/JavaScript/Reference/Global_Objects/RegExp/exec)    - Retorna array com o trecho casado ou null
- [RegExp.test()](https://developer.mozilla.org/pt-BR/docs/Web/JavaScript/Reference/Global_Objects/RegExp/test)    - Testa se casou ou não (truel/false)

#### Exemplos

Testar se uma expressão casa ou não com determinado texto. Use o método `search()`.

```javascript
var er = /^java/i;
if("JavaScript".search(er) != -1) {
	console.log("Casou");
} else {
	console.log("Não casou");
}
//		-- ou --
if("JavaScript".search(/^java/i) != -1) {
	console.log("Casou");
} else {
	console.log("Não casou");
}
```
Para testar a expressão e ao mesmo tempo obter informações sobre o casamento, use o método `match()`, que retorna um array ou null. Guarde o resultado em uma variável para poder acessá-lo depois.

```javascript
var resul = "JavaScript".match(/^java/i);
if(resul) {
	console.log(resul.length); // 1
	console.log(resul.index); // 0
	console.log(resul.input);// "JavaScript"
	console.log(resul[O]);	// "Java"
} else {
	console.log("não casou");
}
```
O array resultante traz em sua posição zero (resul[0]) o trecho de texto casado pela expressão. Além do tradicional atributo length com o tamanho do array, há dois atributos adicionais: index com a posição inicial do trecho casado dentro da string original e input com a própria string original.

Se sua expressão contém grupos, o array também trará o conteúdo casado de cada grupo.

```javascript
var resul = "30/04/2018".match(/^(..)\/(..)\/(....)$/);
if(resul) {
	console.log(resul.length); // 4
	console.log(resul.index);  // 0
	console.log(resul.input);  // "30/04/2018"
	console.log(resul[0]);	   // "30/04/2018"
	console.log(resul[1]);	   // "30"
	console.log(resul[2]);	   // "04"
	console.log(resul[3]); 	   // "2018"
}
```
Se for um casamento global (modificador *g* na expressão), então tudo muda: o resultado será um array normal, sem atributos adicionais, que será povoado com todas as ocorrências encontradas, e o conteúdo dos grupos é descartado. Útil para encontrar e guardar de uma só vez todas as ocorrências.

```javascript
var resul = "um dois tres quatro".match(/\w+/g);
if(resul) {
	console.log(resul.length); // 4
	console.log(resul.index);  // "um"
	console.log(resul.input);  // "dois"
	console.log(resul[0]);     // "tres"
	console.log(resul[1]);     // "quarta"
}
```
Para lidar com strings multilinha, use o modificador *m* no final da expressão. Com ele, as âncoras ^ e $ casam cada uma das linhas da string.

```javascript
"l\n2\n3\n4".match(/^\d/g);  // ["1"]
"l\n2\n3\n4".match(/^\d/gm); // ["1, "2", "3", "4"]
```

Você pode casar a quebra de linha diretamente, usando o barra-letra \n em sua expressão.

```javascript
"l\n2\n3\n4".match(/^\d\n/g);  // ["l\n"]
"l\n2\n3\n4".match(/^\d\n/gm); // ["1\n", "2\n", 3\n"]
```

Não há o modificador s, comum em outras linguagens, que faz o metacaractere ponto também casar o \n. É possível improvisar usando [\S\s], que casa qualquer caractere, inclusive o \n.

```javascript
"l\n2\n3\n4" .match(/^l.*4$/);	   // null
"l\n2\n3\n4".match(/^l[\S\s]*4$/); // ["1\n2\n3\n4"]
```

Para fazer substituições, utilize o método `replace()`, que por padrão substitui apenas a primeira ocorrência encontrada. Se precisar de uma substituição global, ou ignorar maiúsculas e minúsculas, ou casamento multilinha, adicione os flags no final da expressão.

```javascript
"JavaScript".replace(/[a-zJ/,	'.'); // J.vaScript
"JavaScript".replace(/[a-z]/g,  '.'); // ]...S.....
"JavaScript".replace(/[a-z]/gi, '.'); // ..........
"1\n2\n3\n4".replace(/^\d/g,	'.'); // .\n2\n3\n4
"l\n2\n3\n4".replace(/^\d/gm,	'.'); // .\n.\n.\n.
"l\n2\n3\n4".replace(/\n/g,     '.'); // 1.2.3.4
"l\n2\n3\n4".replace(/./g,      '.'); // .\n.\n.\n.
"l\n2\n3\n4".replace(/[\S\s]/g, '.'); // .......
```

Os retrovisores são referenciados com um cifrão na frente. Então em vez de \1 use \$1. Há também o retrovisor especial $& que guarda todo o trecho de texto casado pela expressão.

```javascript
"Maykon Oliveira".replace(/(\w)\s(\w)/,  '$2-$1');  // Oliveira-Maykon
"12:34".replace(/(..):(..)/,  '$1h $2min');  // 12h 34min
"JavaScript".replace(/.*/,    '--$&--');	 // --JavaScript--
```

Para substituições realmente estilosas, você pode usar uma função no lugar do texto substituto. Esta função receberá um número variável de argumentos, dependendo do número de grupos de sua expressão, e deve retornar uma string.

```javascript
function data_por_extenso(texto_casado, grupol, grupo2, grupo3) {
    var dia = grupo1;
    var mes = grupo;
    var ano = grupo3;
    var meses= {
        'Ol':'Jan', '02':'Fev', '03':'Mar',
        '04':'Abr', 'OS':'Mai', '06':'Jun',
        '07':'Jul', '08':'Ago', '09':'Set',
        '10':'Out', '11':'Nov', '12':'Dez'
    };
    return dia + " de " + meses[mes] " de " + ano;
}
var texto = "Hoje é dia 30/03/2018.";
var regex = /(\d\d)\/(\d\d)\/(\d\d\d\d)/;
var resul = texto.replace(regex, data_por_extenso); // Hoje é dia 30 de Mar de 2018.
```

Acentuação é um problema. Não há suporte às classes POSIX como [:alpha:] e [:lower:]. O que temos é o barra-letra \w, que casa letras e números. Porém, não há como confiar nas informações sobre localização informadas pelo navegador, então para casar acentos é preciso usar os remendos.

```javascript
"Jáva".replace(/\w/g,      '.' )); // .á..
"Jáva".replace(/[\wÀ-ü]/g, '.'));  // ....
```

A divisão é feita com o método `split()`, que retorna um array com o texto dividido. Se um segundo argumento numérico for informado, o número de itens do array fica limitado a esse número. O texto excedente será descartado.

```javascript
"um dois três".split(/\s+/);    // ["um", "dois", "três"]
"um dois três".split(/\s+/, 2); // ["um", "dois"]
```

### Exercícios

<div class="collapse">
  <ul class="collapse-list">
    <li>
      <label class="collapse-btn" for="collapse-1">Faça uma ER que indentifique as partes de uma url. Por exemplo:<br> 
      input: "https://github.com/maykon-oliveira/regex-tutorial/" <br>
      output: protocol: "https", domain: "github.com", path: "maykon-oliveira/regex-tutorial/". <br>
      Teste a ER no arquivo *examinado-url.js*.
      </label>
      <input class="collapse-open" type="checkbox" id="collapse-1" aria-hidden="true" hidden>
      <div class="collapse-painel container-resposta">
        <p>O primeiro grupo caso o protocolo. `(\w+)`</p>
        <p>O segundo caso o dominio. `([\w\.-_][^/]+)`</p>
        <p>O terceiro caso o path. `(.*)`. Bem genêrico.</p>
        <div class="collapse-inner">
          <pre><code>var regex = /(\w+):\/\/([\w\.-_][^/]+)\/(.*)?/;</code></pre>
        </div>
      </div>
    </li>
  </ul>
</div>