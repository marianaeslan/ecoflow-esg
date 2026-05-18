# EcoFlow ESG — Banco de Dados Oracle

## Pré-requisitos

- Docker e Docker Compose instalados
- SQL Developer ou DBeaver instalado

---

## Subindo o banco de dados

Na raiz do projeto, execute:

```bash
docker-compose up -d
```

Aguarde alguns minutos na primeira execução — o Oracle precisa inicializar o banco antes de aceitar conexões.

Para verificar se está pronto:

```bash
docker logs -f ecoflow-oracle
```

Quando aparecer a mensagem `DATABASE IS READY TO USE!`, o banco está disponível.

Para derrubar o container:

```bash
docker-compose down
```

---

## Credenciais de acesso

| Parâmetro | Valor |
|---|---|
| Host | localhost |
| Porta | 1521 |
| Service Name | FREEPDB1 |
| Usuário | ecoflow |
| Senha | ecoflow123 |

---

## Acessando visualmente (SQL Developer)

1. Abra o SQL Developer
2. Clique em **New Connection**
3. Preencha os campos:
    - **Name:** ecoflow-local
    - **Username:** ecoflow
    - **Password:** ecoflow123
    - **Connection Type:** Basic
    - **Hostname:** localhost
    - **Port:** 1521
    - **Service name:** FREEPDB1 *(marque Service name, não SID)*
4. Clique em **Test** — deve retornar *Success*
5. Clique em **Save** e depois **Connect**

---

## Observações

- Os dados são persistidos no volume `oracle-data` e não são perdidos ao reiniciar o container.
- O usuário `ecoflow` é criado automaticamente pelo script em `docker/oracle/init` ao subir o container pela primeira vez.
- Caso precise recriar o usuário manualmente, acesse o container e execute o script via `sqlplus / as sysdba` após alternar para o PDB com `ALTER SESSION SET CONTAINER = FREEPDB1`.