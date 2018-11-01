# Desafios IDwall

Os desafios estão divididos em parte I e parte II de acordo com os seguintes tags:

+ [Parte I](https://github.com/richardcsantana/desafios/releases/tag/1.0)
+ [Parte II](https://github.com/richardcsantana/desafios/releases/tag/2.0)

O projeto do bot para o telegram precisa de um botToken a ser inserido no arquivo [aplication.yml](/crawlers/src/main/resources/application.yml) no campo bot-token

Os projetos utilizam o maven.

Para rodar uma das aplicações utilize os comandos na pasta raiz do projeto a ser rodado:

    mvn clean package
Seguido de:

    java -jar target/StringFormatter-1.0-SNAPSHOT.jar
    ou
    java -jar target/crawler-1.0-SNAPSHOT.jar

Caso queira criar a imagem docker da solução rode:

    docker build . -t <nomedoprojeto>