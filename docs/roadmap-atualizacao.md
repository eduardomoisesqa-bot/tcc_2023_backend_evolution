# Roadmap de atualização do repositório

## Visão geral

Este roadmap organiza a evolução do projeto com foco em estabilidade, qualidade de código, padronização da API e preparação para continuação do desenvolvimento.

O objetivo principal é transformar o projeto atual em uma base mais profissional, fácil de manter e pronta para continuar aprendendo e entregando valor.

## Status atual validado

A validação foi executada com o comando abaixo:

```bash
cd "c:\Users\Notebook\OneDrive\Desktop\atualizacao_tcc\tcc_2023\BACKEND\backend_tcc2023" ; mvn test
```

Resultado verificado:
- 17 testes executados
- 0 falhas
- 0 erros
- Build concluído com sucesso

Isso confirma que a base do backend já compila e tem cobertura de testes básica funcionando.

---

## Diagnóstico inicial

### O que o sistema já faz
- Cadastro, busca, atualização e exclusão de clientes
- Cadastro, busca, atualização e exclusão de serviços prestados
- Validação de dados com Bean Validation
- Tratamento de erros básicos via controller advice
- Uso de banco em memória H2 para desenvolvimento local

### Pontos de atenção
- Dependências duplicadas no Maven
- Rotas e endpoints sem padronização completa
- Exclusão de serviços não executa a remoção real
- Tratamento de validação com status HTTP inadequado
- Ausência de camada de serviço para serviços prestados
- Frontend ainda não estruturado

---

## Fase 1 - Base e estabilidade

### Objetivo
Deixar o projeto mais limpo, estável e preparado para evoluir.

### Tarefas
- [ ] Remover dependências duplicadas do arquivo pom.xml
- [ ] Validar versões do Spring Boot e Java
- [ ] Revisar imports e warnings do projeto
- [ ] Manter o build em estado verde com testes automatizados
- [ ] Registrar mudanças em commits pequenos e consistentes

### Resultado esperado
Projeto com base mínima organizada, sem ruídos e com build confiável.

---

## Fase 2 - Padronização da API REST

### Objetivo
Organizar melhor a estrutura da API para facilitar manutenção e integração.

### Tarefas
- [ ] Definir padrão de endpoints com prefixo /api
- [ ] Padronizar caminhos de clientes e serviços
- [ ] Ajustar retorno dos controllers para ResponseEntity
- [ ] Centralizar códigos HTTP esperados em cada operação
- [ ] Revisar nomes e convenções de métodos

### Resultado esperado
API com estrutura mais previsível, documentável e fácil de testar.

---

## Fase 3 - Correção de regras de negócio

### Objetivo
Garantir que o sistema execute corretamente o comportamento esperado pelo usuário.

### Tarefas
- [ ] Corrigir exclusão de serviços prestados
- [ ] Validar existência antes de atualizar e deletar registros
- [ ] Substituir RuntimeException genérica por exceções mais claras
- [ ] Melhorar erros de not found e bad request
- [ ] Ajustar mensagens de retorno para clientes/consumidores da API

### Resultado esperado
Fluxos de negócio consistentes e previsíveis.

---

## Fase 4 - Estruturar camada de serviço

### Objetivo
Separar regras de negócio da infraestrutura e aumentar qualidade do código.

### Tarefas
- [ ] Criar service para serviços prestados
- [ ] Centralizar regras de validação e manipulação de dados
- [ ] Simplificar controllers para somente orquestrar requisições
- [ ] Reduzir acoplamento entre controlador e repositório

### Resultado esperado
Arquitetura mais limpa, escalável e mais próxima de um padrão enterprise.

---

## Fase 5 - Testes e qualidade

### Objetivo
Aumentar confiança na aplicação e reduzir regressões.

### Tarefas
- [ ] Testar cenários de cliente inexistente
- [ ] Testar cenários de serviço inexistente
- [ ] Cobrir atualização e remoção com dados inválidos
- [ ] Adicionar testes de integração com MockMvc
- [ ] Validar contratos de resposta da API

### Resultado esperado
Projeto mais seguro para evoluir sem quebrar funcionalidades existentes.

---

## Fase 6 - Frontend e evolução do produto

### Objetivo
Completar o ciclo funcional da aplicação.

### Tarefas
- [ ] Estruturar a pasta do frontend em FRONTEND/clientes-app
- [ ] Criar telas para cadastro e listagem de clientes
- [ ] Criar telas para cadastro e listagem de serviços
- [ ] Integrar frontend com API backend
- [ ] Validar fluxo completo do sistema

### Resultado esperado
Aplicação completa com interface e backend funcionando em conjunto.

---

## Fase 7 - Deploy e ambiente

### Objetivo
Preparar o projeto para execução em ambientes reais.

### Tarefas
- [ ] Separar configurações por perfil (dev/test/prod)
- [ ] Definir banco de dados adequado para produção
- [ ] Organizar documentação de execução
- [ ] Configurar variáveis de ambiente
- [ ] Validar processo de inicialização da aplicação

### Resultado esperado
Projeto pronto para evoluir em ambiente mais profissional.

---

## Ordem recomendada de execução

1. Fase 1 - Base e estabilidade
2. Fase 2 - Padronização da API
3. Fase 3 - Correção de regras de negócio
4. Fase 4 - Camada de serviço
5. Fase 5 - Testes e qualidade
6. Fase 6 - Frontend
7. Fase 7 - Deploy e ambiente

Essa ordem reduz riscos e garante que cada evolução seja construída sobre uma base sólida.

---

## Aprendizados esperados

Ao longo do roadmap, a equipe deve focar em:
- entender o problema antes de corrigir
- validar mudanças com testes
- manter commits pequenos e objetivos
- separar responsabilidade por camada
- evoluir de forma incremental e controlada

---

## Próximo passo recomendado

Começar pela Fase 1, com a limpeza do pom.xml e a revisão da base do projeto, antes de avançar para regras de negócio e frontend.

Esse passo é essencial para preparar o repositório para uma próxima fase de desenvolvimento, mantendo qualidade e previsibilidade.
