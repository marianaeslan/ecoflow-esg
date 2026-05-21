# EcoFlow ESG — Banco de Dados Oracle (Docker)

Uma documentação enxuta e prática para subir e acessar o banco Oracle usado pelo projeto **EcoFlow ESG** em ambiente local via Docker Compose.

---

## Tecnologias

| Camada | Tecnologia |
|--------|-----------|
| Banco de dados | **Oracle Database** (container Docker) |
| Orquestração | **Docker**, **Docker Compose** |
| Ferramentas cliente | **SQL Developer**, **DBeaver** |

---

## Pré-requisitos

- **Docker** instalado
- **Docker Compose** instalado
- Cliente SQL (opcional): **SQL Developer** ou **DBeaver**

---

## Subindo o banco de dados

Na raiz do projeto execute:

```bash
# Iniciar o container em background
docker-compose up -d
```

> **Primeira execução:** aguarde alguns minutos — o Oracle precisa inicializar o banco antes de aceitar conexões.

Para acompanhar os logs e verificar o progresso:

```bash
docker logs -f ecoflow-oracle
```

Quando a mensagem **DATABASE IS READY TO USE!** aparecer, o banco estará disponível.

Para parar e remover os containers:

```bash
docker-compose down
```

---

## Credenciais de acesso

| Parâmetro | Valor |
|---|---|
| **Host** | localhost |
| **Porta** | 1521 |
| **Service Name** | FREEPDB1 |
| **Usuário** | ecoflow |
| **Senha** | ecoflow123 |

---

## Acessando visualmente com SQL Developer

1. Abra o **SQL Developer**.  
2. Clique em **New Connection**.  
3. Preencha os campos:
   - **Name:** \`ecoflow-local\`  
   - **Username:** \`ecoflow\`  
   - **Password:** \`ecoflow123\`  
   - **Connection Type:** \`Basic\`  
   - **Hostname:** \`localhost\`  
   - **Port:** \`1521\`  
   - **Service name:** \`FREEPDB1\`  
4. Clique em **Test** — deve retornar **Success**.  
5. Clique em **Save** e depois **Connect**.

> Para **DBeaver**, crie uma nova conexão Oracle com os mesmos parâmetros acima.

---

## Observações importantes

- **Persistência:** os dados são mantidos no volume Docker \`oracle-data\` e **não** são perdidos ao reiniciar o container, a menos que o volume seja removido manualmente.  
- **Criação do usuário:** o usuário **ecoflow** é criado automaticamente pelo script localizado em \`docker/oracle/init\` na primeira inicialização.  
- **Recriar usuário manualmente:** caso precise recriar o usuário, acesse o container e execute os scripts com \`sqlplus / as sysdba\` após alternar para o PDB:
  \`\`\`sql
  ALTER SESSION SET CONTAINER = FREEPDB1;
  -- então rode o script de criação do usuário
  \`\`\`
- **Tempo de inicialização:** a primeira inicialização pode levar vários minutos; monitore os logs para confirmar quando o banco estiver pronto.

---

## Estrutura do diretório relacionada ao Oracle

```
docker/
├── oracle/
│   ├── Dockerfile
│   ├── docker-compose.yml
│   └── init/
│       └── 01-create-ecoflow-user.sql
```

- **docker-compose.yml** — define o serviço \`ecoflow-oracle\` e o volume \`oracle-data\`.  
- **init/** — scripts executados na primeira inicialização para criar o PDB e o usuário \`ecoflow\`.

---

## Comandos úteis

- Ver logs do container:
  ```bash
  docker logs -f ecoflow-oracle
  ```
- Entrar no container:
  ```bash
  docker exec -it ecoflow-oracle bash
  ```
- Acessar SQL*Plus como SYSDBA (dentro do container):
  ```bash
  sqlplus / as sysdba
  ```
- Remover volumes (cuidado: apaga dados persistidos):
  ```bash
  docker-compose down -v
  ```

---

## Dicas rápidas

- Se a conexão falhar no cliente, verifique se o container está **rodando** e se a porta **1521** está liberada.  
- Use \`docker ps\` para confirmar o status do container.  
- Para depuração, verifique os arquivos de inicialização em \`docker/oracle/init\`.

---
