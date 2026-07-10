# 🔧 Sistema de Controle de Equipamentos e Manutenções Industriais (SCEMI)

Aplicação console em Java para gestão de equipamentos industriais, técnicos responsáveis e ordens de manutenção. O projeto foi desenvolvido com arquitetura em camadas (model / service / exception / util), aplicando conceitos de POO, tratamento de exceções personalizadas e regras de negócio entre entidades relacionadas.

---

## Funcionalidades

 ### 🛠️ Equipamentos
* Cadastrar equipamento (com validação de código duplicado)
* Consultar equipamento por código
* Alterar informações (nome, categoria, fabricante, modelo, setor, data)
* Excluir equipamento (exclusão lógica — bloqueada se houver manutenção em aberto)
* Listar todos os equipamentos
* Status automático: Operando, Em manutenção, Inativo

---

### 👨‍🔧 Técnicos
* Cadastrar técnico (com validação de código e matrícula duplicados)
* Consultar técnico por código
* Alterar informações (nome, matrícula, setor, telefone)
* Excluir técnico (exclusão lógica — bloqueada se houver manutenção em aberto sob sua responsabilidade)
* Listar todos os técnicos

---

### 🪛 Manutenções
* Registrar manutenção vinculando um equipamento e um técnico existentes
* Impede abrir uma nova manutenção para um equipamento que já tem uma em aberto
* Consultar manutenção por código
* Alterar situação (Aberta, Em andamento, Finalizada)
* Finalizar manutenção com data de encerramento
* Ao finalizar, o equipamento volta automaticamente ao status Operando
* Listar todas as manutenções

---

### 📊 Relatórios
* Relatório geral: totais de equipamentos, técnicos, manutenções abertas/finalizadas e equipamentos por status
* Manutenções registradas por equipamento específico

---

## 🗃️ Arquitetura

O projeto segue uma separação em camadas:

```text
src/
├── Principal.java              # Menu principal e navegação (console)
├── model/                      # Entidades e regras próprias de cada uma
│   ├── Equipamentos.java
│   ├── Tecnicos.java
│   ├── Manutencoes.java
│   └── Relatorios.java
├── service/                    # Regras de negócio e integração entre entidades
│   ├── EquipamentoService.java
│   ├── TecnicoService.java
│   ├── ManutencaoService.java
│   └── RelatorioService.java
├── exception/                  # Exceções personalizadas
│   ├── CodigoDuplicadoException.java
│   ├── EquipamentoNaoEncontradoException.java
│   ├── TecnicoNaoEncontradoException.java
│   └── ManutencaoInvalidaException.java
└── util/
    └── Entrada.java             # Scanner único e compartilhado do sistema
```
## ‼️ Destaques de design:

Entrada.sc centraliza um único Scanner estático para evitar conflitos de buffer de entrada entre as classes.
ManutencaoService é injetado em EquipamentoService e TecnicoService para impedir exclusões que quebrariam a integridade dos dados (equipamento/técnico com manutenção em aberto).
Armazenamento em memória via arrays de tamanho fixo (100 posições por entidade), sem uso de banco de dados.

---

## 💡 Tecnologias

* Java (sem dependências externas)
* Estrutura de projeto compatível com IntelliJ IDEA

---

## ⁉️ Como executar
```
Via IntelliJ IDEA

Abra a pasta do projeto na IDE.
            ↓
Execute a classe Principal.java.
            ↓
Via linha de comando
            ↓
bash# Na raiz do projeto

cd src
javac -d ../out Principal.java model/*.java service/*.java exception/*.java util/*.java
java -cp ../out Principal
```
Ou pela interface do Intellij, usando o botão de "Run".
---

## ✅ Uso

Ao rodar o programa, um menu interativo é exibido no console:
```text
==============================================
SISTEMA DE CONTROLE DE EQUIPAMENTOS
==============================================
1 - Equipamentos
2 - Técnicos
3 - Manutenções
4 - Relatórios
0 - Encerrar sistema
```
Basta digitar o número da opção desejada e seguir as instruções exibidas.

---

## 🚫 Regras de negócio implementadas

Não é possível cadastrar dois equipamentos com o mesmo código, nem dois técnicos com o mesmo código ou matrícula.
Uma manutenção só pode ser registrada para equipamentos e técnicos já cadastrados.
Um equipamento não pode ter duas manutenções em aberto simultaneamente.
Equipamentos e técnicos vinculados a manutenções em aberto não podem ser excluídos.
Uma manutenção finalizada não pode ter sua situação alterada novamente.

---

## ✍️ Status do projeto

✅ Testado e finalizado: Regras de negócio funcionando corretamente, bugs e erros resolvidos e código com comentários simple para melhor entendimento.

## Autor

*João Nascimento*
