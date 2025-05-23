
```markdown

# Projeto Calculadora de Recursos de Edifícios

Este projeto é uma API Spring Boot para calcular a diferença de recursos necessários para atualizar edifícios em um jogo.
A API permite adicionar, atualizar, deletar e listar edifícios, além de calcular a diferença de recursos entre estágios de um edifício.

## Tecnologias Utilizadas
-Java
-Spring (Spring Boot, Spring Web)
-Flyway
-MySql

## Estrutura do Projeto

- `controller`: Contém os controladores REST.
- `service`: Contém a lógica de negócios.
- `repository`: Contém as interfaces de repositório JPA.
- `model`: Contém as entidades JPA.
- `exception`: Contém as classes de exceção personalizadas.

## Endpoints

### Listar Edifícios

```
GET /buildings
```

### Adicionar Edifício

```
POST /buildings
```
Corpo da Requisição:
```json
{
  "stage": 1,
  "name": "city",
  "buildingType": "MAIN_HALL",
  "qtdFood": 100,
  "qtdOil": 50,
  "qtdIron": 30,
  "qtdMinerals": 20,
  "qtdUranium": 10,
  "troopType": "Wastland King",
  "recruitingSpeed": 5,
  "woundedCapacity": 10,
  "healingSpeed": 2,
  "fleetLimitBonus": 1
}
```

### Calcular Diferença de Recursos

```
POST /buildings/calculate-difference?desiredStage=5
```
Corpo da Requisição:
```json
{
  "stage": 1,
  "name": "city",
  "buildingType": "MAIN_HALL"
 
}
```

### Buscar Edifício por ID

```
GET /buildings/{buildingId}
```

### Atualizar Edifício

```
PUT /buildings/{buildingId}
```
Corpo da Requisição:
```json
{
  "stage": 2,
  "name": "city",
  "buildingType": "MAIN_HALL",
  "qtdFood": 200,
  "qtdOil": 100,
  "qtdIron": 60,
  "qtdMinerals": 40,
  "qtdUranium": 20
}
```

### Deletar Edifício

```
DELETE /buildings/{buildingId}
```

## Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/usuario/projeto-calculadora-recursos.git
   ```

2. Navegue até o diretório do projeto:
   ```bash
   cd projeto-calculadora-recursos
   ```

3. Execute o projeto com o Maven:
   ```bash
   mvn spring-boot:run
   ```

4. Acesse a API em `http://localhost:8080`.

## Contribuição

1. Faça um fork do projeto.
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`).
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`).
4. Faça push para a branch (`git push origin feature/nova-feature`).
5. Abra um Pull Request.

## Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo `LICENSE` para mais detalhes.
```

Este texto fornece uma visão geral do projeto, instruções de uso e informações sobre como contribuir. Caso você tenha mais detalhes específicos ou queira adicionar algo, sinta-se à vontade para modificar conforme necessário.
```
