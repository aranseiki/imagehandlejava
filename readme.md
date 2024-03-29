# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.4/maven-plugin/reference/html/#build-image)
* [Spring Integration Test Module Reference Guide](https://docs.spring.io/spring-integration/reference/html/testing.html)
* [Spring Integration HTTP Module Reference Guide](https://docs.spring.io/spring-integration/reference/html/http.html)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.4/reference/htmlsingle/index.html#web)
* [Spring Integration](https://docs.spring.io/spring-boot/docs/3.2.4/reference/htmlsingle/index.html#messaging.spring-integration)
* [Spring Shell](https://spring.io/projects/spring-shell)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.2.4/reference/htmlsingle/index.html#using.devtools)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Integrating Data](https://spring.io/guides/gs/integration/)


# JSON para envio da requisição:

    {
        "imagemAtualBase64": null,
        "caminhoImagemNova": null,
        "tipoImagem": null,
        "resolucaoHorizontal": null,
        "resolucaoVertical": null
    }

# Detalhamento da especificação: 

    Os 4 campos são requisitos para o funcionamento, então devem ser obrigatórios.
    Isso deve ser enviado como corpo da requisição POST.

    Segue especificação dos campos do JSON:
    imagemAtualBase64: string sendo o próprio hash base64
    caminhoImagemNova: string sendo o caminho absoluto para salvamento da imagem nova, incluindo o nome do arquivo.
    tipoImagem: String sendo a extensão da imagem. No momento, isso funcionará somente para JPG, como dissemos, mas o plano é usar esse campo para nos aguiar sobre a tratativa de cada extensão no futuro. 
    resolucaoHorizontal: Inteiro contendo o número da resolução de resultado esperado para o lado horizontal da imagem.
    resolucaoVertical: Inteiro contendo o número da resolução de resultado esperado para o lado vertical da imagem.


# Exemplo:

    Como se trata de uma API, o cliente não necessariamente precisa ser Java. Então segue um exemplo de como pensei em usar a API do lado cliente via PowerShell:

    $postParams = @{
        imagemAtualBase64 = '30ri9j2309tj42309jtnm02tj2938rtn2398ht29bn3498hn29t4b823n9tbn239bn23t972t932b83298329b8t329tb'
        caminhoImagemNova = 'C:\dev\projects\imagehandlejava\content\novaImagem.jpg'
        tipoImagem = '.jpg'
        resolucaoVertical = 1920
        resolucaoHorizontal = 1080    
    }

    Invoke-WebRequest -Uri "http://localhost:8080/resize-image" -Method POST -Body $postParams
