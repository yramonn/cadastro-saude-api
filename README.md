# cadastro-saude-api

# Sobre o projeto

Crie uma aplicação utilizando Java e Spring Boot que forneça uma API REST para manter o cadastro
de beneficiários de um plano de saúde.

# Acessar o Swagger UI

localhost:8081/swagger-ui/index.html (Por padrão, settei no application.properties para o projeto subir na porta 8081 )

# Acessar h2 Database

- http://localhost:8081/h2-console/
- spring.datasource.url=jdbc:h2:mem:CAD_SAUDE
- spring.datasource.username=admin
- spring.datasource.password=1234
- Após aplicação subir, todas tabelas serão criadas automaticamente: spring.jpa.hibernate.ddl-auto=update


# Tecnologias utilizadas
- JavaSE-11 - Version 17.0.2
- Spring Boot - Version 3.0.12
- JPA / Hibernate
- Maven
- h2 Database

# Como executar o projeto
- git clone https://github.com/yramonn/cadastro-saude-api.git
- mvn clean package install
- Credenciais do banco h2 no application.properties, que subirá no http://localhost:8081/h2-console/


# Autor

Ramon Silva
https://www.linkedin.com/in/ramon--silva/
