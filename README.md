# Aula: Estruturas de Dados Funcionais

Demonstra conceitos fundamentais de estruturas de dados funcionais, focando especificamente em listas imutáveis e (continua...)

## Visão Geral do Projeto

Este projeto oferece implementações práticas e exemplos de operações essenciais em estruturas de dados funcionais, focando em:

- **Operações em listas imutáveis** e suas características
- **Padrões de programação funcional** para manipulação de dados
- **Análise de desempenho** de diferentes abordagens de implementação
- **Recursão estrutural** como forma natural de processar estruturas de dados
- **Pattern matching** para decomposição de estruturas de dados

## Estrutura do Projeto

```
src/main/scala/
├── Append.scala      # Operação de anexar em listas imutáveis
├── Length.scala      # Cálculo de comprimento de listas
├── Sum.scala         # Operações de agregação em listas
└── ListBasics.scala  # Operações fundamentais e padrões de listas
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

## Conceitos de Aprendizado Principais

### Propriedades de Estruturas de Dados Funcionais
- **Imutabilidade**: Estruturas de dados não podem ser modificadas após criação
- **Persistência**: Operações retornam novas estruturas preservando a original
- **Compartilhamento Estrutural**: Uso eficiente de memória através de componentes estruturais compartilhados

### Operações de Lista e Complexidade
- **Operações O(1)**: `head`, `tail`, `isEmpty`, `cons (::)` - acesso em tempo constante
- **Operações O(n)**: `length`, `last`, `init`, `concatenation (++)` - travessia em tempo linear
- **Recursão Estrutural**: Padrão natural para processar estruturas de dados recursivas
- **Tail Recursion**: Alternativa stack-safe para processamento estilo iterativo

### Padrões de Programação Funcional
- **Funções de Ordem Superior**: Funções que operam em outras funções
- **Operações Fold**: Forma genérica de reduzir estruturas de dados a valores únicos
- **Padrões de Construtor**: Construindo estruturas de dados usando operações fundamentais

## Uso

### Compilação e Execução

Este é um projeto sbt padrão com Scala 3. Você pode:

```bash
# Compilar o projeto
sbt compile

# Executar uma função main específica
sbt "runMain testAppend"      # Testar implementações de append
sbt "runMain testLength"      # Testar implementações de length
sbt "runMain testListSumTailRec"  # Testar implementações de sum

# Iniciar REPL do Scala 3
sbt console

# Executar testes
sbt test
```

### Exemplo de Uso

```scala
// Construção básica de listas e operações
val list1 = List(1, 2, 3)
val list2 = 0 :: list1                    // List(0, 1, 2, 3)
val list3 = list1 ++ List(4, 5)          // List(1, 2, 3, 4, 5)

// Operações estruturais
val result1 = appendRec(List(1, 2, 3), 4)  // List(1, 2, 3, 4)
val length1 = lengthRec(List("a", "b", "c"))  // 3
val sum1 = sumRec(List(1, 2, 3, 4, 5))  // 15

// Implementações alternativas (tail-recursive)
val result2 = appendTR(List(1, 2, 3), 4)  // List(1, 2, 3, 4)
val length2 = lengthTRec(List("a", "b", "c"))  // 3
val sum2 = sumTR(List(1, 2, 3, 4, 5))  // 15
```