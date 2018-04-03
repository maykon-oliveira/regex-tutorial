var urls = [
    'https://github.com/maykonoliveira/regex-tutorial/',
    'https://github.com/frontendbr/poste-mais',
    'https://github.com/f0r34chb3t4/Livros',
    'https://regex101.com/',
    'https://www.qnap.com/pt-pt/how-to/tutorial/article/configure-seu-próprio-servidor-ftp-de-forma-fácil',
    'https://www.facebook.com/devmedia.com.br/',
    'https://getbootstrap.com/docs/4.0/examples/'
]

var regex = // ER aqui;

urls.forEach(url => {
    var matches = regex.exec(url);
    console.log({protocol: matches[1], domain: matches[2], path: matches[3],});
});