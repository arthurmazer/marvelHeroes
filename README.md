# Marvel Heroes App 

Um simples app de portfólio que carrega os heróis da Marvel e mostra os HQ's que cada um participou.
A base de dados utilizada foi a API da Marvel (https://developer.marvel.com/), feito usando Kotlin com MVVM, e algumas bibliotecas como LiveData (Jetpack), RxJava e Retrofit.

## Importante

Para utilizar o aplicativo, você deve referenciar suas **chaves de desenvolvedor** no arquivo [grade.properties](https://github.com/arthurmazer/marvelHeroes/blob/master/gradle.properties), do contrário o aplicativo **não** irá funcionar


## Pontos de melhorias
Como o app é só de apresentação, tem alguns pontos ainda que podem ser melhorados.
- A arquitetura pode ser melhorada utilizando os padrões do clean architecture.
- A separação dos arquivos no projeto está simples para facilitar a consulta, pode ser melhorado junto com a arquitetura
- Os placeholder de loading das imagens é uma imagem padrão.
- O layout esta apresentável mas poderia ser melhor, especialmente o landscape
- Poderia ser usado Room para persistir as buscas já feitas para fazer um cache local.
- Adicionar mais detalhes na seção de cada herói selecionado.

### Screenshots

![screen1](https://i.imgur.com/Al6ppVJ.jpg)
