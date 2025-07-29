# Aula: Estruturas de Dados Funcionais

Demonstra conceitos fundamentais de estruturas de dados funcionais, focando em listas imutáveis, Vectors, ArraySeq e outras estruturas funcionais.

## Visão Geral do Projeto

Este projeto oferece implementações práticas e exemplos de operações essenciais em estruturas de dados funcionais, focando em:

- **Operações em listas imutáveis** e suas características
- **Estruturas de dados funcionais** (List, Vector, ArraySeq)
- **Padrões de programação funcional** para manipulação de dados
- **Análise de desempenho** de diferentes abordagens de implementação
- **Recursão estrutural e tail-recursion** como formas naturais de processar estruturas de dados
- **Pattern matching** para decomposição de estruturas de dados

## Estrutura do Projeto

```
src/main/scala/
├── Append.scala         # Operação de anexar em listas imutáveis
├── Length.scala         # Cálculo de comprimento de listas
├── Sum.scala            # Operações de agregação em listas
├── ListBasics.scala     # Operações fundamentais e padrões de listas
├── Reverse.scala        # Diferentes implementações de reversão de listas
├── Concatenation.scala  # Múltiplas abordagens para concatenação de listas
├── VectorBasics.scala   # Demonstração completa de Vector (estrutura balanceada)
├── ArraySeqBasics.scala # Demonstração de ArraySeq (acesso O(1) verdadeiro)
├── Either.scala         # Estrutura funcional para computações que podem falhar
├── Stack.scala          # Implementação funcional de pilha (Stack)
└── Queue.scala          # Implementação funcional de fila (Queue)
```

## Implementações Principais

### 1. Operações de Anexar (`Append.scala`)
Demonstra como estender listas imutáveis anexando elementos:
- **`appendRec`**: Abordagem de recursão estrutural
- **`appendTR`**: Abordagem tail-recursive com padrão de acumulador

### 2. Cálculo de Comprimento (`Length.scala`)
Mostra diferentes abordagens para calcular o tamanho de uma lista:
- **`lengthRec`**: Recursão estrutural direta
- **`lengthTRec`**: Computação estilo iterativo usando tail recursion

### 9. Either (`Either.scala`)
Implementa o tipo funcional `Either` para representar valores que podem ser de dois tipos (usado para computações que podem falhar):
- **`Left` e `Right`**: Representam erro e sucesso, respectivamente
- **Pattern matching** para manipulação segura de resultados

### 10. Stack (`Stack.scala`)
Implementação funcional de pilha:
- **Operações O(1)**: push, pop, peek
- **Imutabilidade**: cada operação retorna uma nova pilha

### 11. Queue (`Queue.scala`)
Implementação funcional de fila:
- **NaiveListQueue**: fila simples baseada em lista
- **TwoStacksQueue**: fila eficiente usando duas listas (stacks) para operações rápidas de enqueue/dequeue
- **Testes e exemplos**: métodos `main` para demonstrar uso e comportamento

### 3. Operações de Agregação (`Sum.scala`)
Implementa operações de redução em elementos da lista:
- **`sumRec`**: Recursão estrutural para redução de elementos
- **`sumTR`**: Padrão de redução baseado em acumulador

### 4. Fundamentos de Lista (`ListBasics.scala`)
Demonstração abrangente das operações centrais de lista:
- **Construção de listas** usando cons (`::`) e métodos factory
- **Desconstrução de listas** com operações head/tail
- **Combinação de listas** através de concatenação (`++` e `:::`)
- **Princípios de imutabilidade** em ação

### 5. Reversão de Listas (`Reverse.scala`)
Múltiplas implementações para reverter listas:
- **`reverseNaive`**: Implementação naive usando append (O(n²))
- **`reverseIterative`**: Implementação iterativa eficiente (O(n))
- **`reverseTailRec`**: Implementação tail-recursive recomendada (O(n))

### 6. Concatenação de Listas (`Concatenation.scala`)
Diferentes abordagens para concatenar duas listas:
- **`concatenate`**: Recursão estrutural simples
- **`concatenateIterative`**: Implementação iterativa com loops
- **`concatenateTailRec`**: Implementação tail-recursive otimizada

### 7. Vector Fundamentals (`VectorBasics.scala`)
Demonstração completa de Vector - estrutura balanceada:
- **Acesso aleatório eficiente** (efetivamente O(1))
- **Operações de prepend/append balanceadas** (O(log n))
- **Atualizações eficientes** e imutabilidade
- **Comparação com List e ArraySeq**

### 8. ArraySeq Fundamentals (`ArraySeqBasics.scala`)
Demonstração de ArraySeq - performance máxima para acesso:
- **Acesso aleatório verdadeiro O(1)** baseado em arrays nativos
- **Uso eficiente de memória** com armazenamento contíguo
- **Operações de leitura otimizadas**
- **Quando usar vs outras estruturas**

## Conceitos de Aprendizado Principais

### Propriedades de Estruturas de Dados Funcionais
- **Imutabilidade**: Estruturas de dados não podem ser modificadas após criação
- **Persistência**: Operações retornam novas estruturas preservando a original
- **Compartilhamento Estrutural**: Uso eficiente de memória através de componentes estruturais compartilhados

### Operações e Complexidade por Estrutura
**List (Lista Ligada)**:
- **Operações O(1)**: `head`, `tail`, `isEmpty`, `cons (::)` - acesso em tempo constante
- **Operações O(n)**: `length`, `last`, `init`, `concatenation (++)` - travessia em tempo linear
- **Melhor para**: prepend frequente, processamento sequencial, pattern matching

**Vector (Trie Balanceada)**:
- **Operações O(log n)**: append, prepend, update, acesso por índice (efetivamente O(1))
- **Balanceado**: bom desempenho geral para todas as operações
- **Melhor para**: uso geral, quando padrões de acesso são desconhecidos

**ArraySeq (Array Nativo)**:
- **Operações O(1)**: acesso por índice, length, operações de leitura
- **Eficiência de memória**: armazenamento contíguo, sem overhead de ponteiros
- **Melhor para**: acesso aleatório frequente, coleções grandes onde memória importa

### Padrões de Programação Funcional
- **Recursão Estrutural**: Padrão natural para processar estruturas de dados recursivas
- **Tail Recursion**: Alternativa stack-safe para processamento estilo iterativo
- **Funções de Ordem Superior**: Funções que operam em outras funções
- **Operações Fold**: Forma genérica de reduzir estruturas de dados a valores únicos
- **Padrões de Construtor**: Construindo estruturas de dados usando operações fundamentais

## Uso

### Compilação e Execução

Este é um projeto sbt padrão com Scala 3. Você pode:

```bash
# Compilar o projeto
sbt compile

# Executar funções main específicas
sbt "runMain testAppend"         # Testar implementações de append
sbt "runMain testLength"         # Testar implementações de length
sbt "runMain testListSumTailRec" # Testar implementações de sum
sbt "runMain testReverse"        # Testar implementações de reversão
sbt "runMain testConcatenate"    # Testar implementações de concatenação
sbt "runMain VectorBasics"       # Demonstração completa de Vector
sbt "runMain ArraySeqBasics"     # Demonstração completa de ArraySeq
sbt "runMain testEither"         # Testar uso de Either
sbt "runMain testStack"          # Testar implementação de Stack
sbt "runMain testNaiveListQueue" # Testar implementação de fila simples
sbt "runMain testTwoStacksQueue" # Testar implementação de fila eficiente

```
