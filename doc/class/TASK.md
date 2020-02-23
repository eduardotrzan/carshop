# Atividades da Oficina

## Agenda
### Explicar estrutura do projeto
- Razao de cada modulo
- Estrutura liquibase

### Apresentar Lombok (https://projectlombok.org/)
- Anotaçõs lombok
- Vanilla class gerada apartir das Anotaçõs (https://projectlombok.org/features/Builder)

### Test Containers (https://www.testcontainers.org/)
- O que é?
- Por quê test containers e nao emulador de DB?

### Swagger - Api Documentation (https://swagger.io/tools/swagger-ui/)
- Importância
- Live Demo

### Named Query - JPQL (https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.at-query)
- Importância
- Diferença para convenção spring para nome dos métodos (https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation)
- Live Demo

### Spring Application Event (https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#context-functionality-events)
- Importância
- Diferença para fluxo síncrono
- Live Demo


----


### Atividades

#### Swagger - Api Documentation
- Adicione swagger no projeto
- Documente os endpoints existentes usando swagger
- Acesse localmente o swagger-ui

#### Named Query
- Mapei a tabela serviço em um POJO e associe com o POJO de marcação
- Crie um endpoint para marcação que retorne os serviços pendentes para um determinado carro

#### Evento: Serviço
- Crie um spring event simples que quando uma marcação for criada adicione por padrão `CHECK_TIRES`


----


### Atividades Opcionais
#### Serviço
- Data a tabela serviço, utilize liquibase para adicionar uma nova coluna de custo do serviço.
- Explore opções que permitam adicionar somente valores não nulos. Explique por quê de sua escolha. Se a tabela serviço tiver centenas de milhões de entradas, qual o impacto da performance de sua alteração?
- Crie/Altere um endpoint REST que receba o valor do serviço e adicione a uma marcaçao. Explique por quê de sua escolha por tal estrutura.
- Crie uma validação para que nao haja 2 ou mais tipos de serviços duplicados para a mesma marcação e que o status do serviço nao seja cancelado
- Adicione ao novo (ou alterado) endpoint uma documentação Swagger

#### Relatôrio
- Crie um evento que quando todos os servicos de uma marcação forem finalizados, a marcação é dada como finalizada e calcule o valor total da marcação. Atividade deve ser feita usando uma named query.
- Crie um endpoint que retorne o relatório do serviço feito
- Adicione ao novo endpoint uma documentação Swagger

#### Evento
- Altere o evento simples usando Spring para evento assíncrono usando `ThreadPoolTaskExecutor`
- Altere o evento simples ou multi-thread para eventos usando Kafka com https://spring.io/projects/spring-kafka
