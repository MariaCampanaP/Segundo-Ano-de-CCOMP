<img width="100%" src="https://capsule-render.vercel.app/api?type=waving&height=120&color=023fa1&section=header"/>

# ☕︎︎ Projeto - Jogo da Vida em Java - Versão Adaptada

Uma releitura do clássico **Jogo da Vida** de John Horton Conway, programada em **Java**, com múltiplos tipos de células e comportamentos customizados. O projeto explora os princípios da **Programação Orientada a Objetos (POO)** de forma prática, didática e interativa.

## ☕︎︎ Visão Geral

Este projeto simula um **ecossistema celular** que evolui ao longo de gerações em um tabuleiro. Cada célula pode nascer, sobreviver ou morrer com base no número de vizinhos vivos - mas com um diferencial: **diferentes tipos de células com comportamentos únicos**, criando padrões imprevisíveis e desafiadores.

Desenvolvido como trabalho acadêmico na disciplina de **Linguagens Orientadas a Objetos**, no curso de **Ciência da Computação** da **UNESPAR**.

## ☕︎︎ Funcionalidades

- Simulação geracional baseada em regras personalizadas;
- Vários tipos de células com comportamentos únicos;
- Tabuleiro com tamanho personalizado;
- Interface via terminal clara e intuitiva;
- Projeto extensível e orientado a objetos.

## ☕︎︎ Tipos de Células

| Tipo        | Símbolo | Comportamento |
|-------------|---------|---------------|
| **Clássica**   | `+`     | Segue as regras originais de Conway |
| **Forte**      | `@`     | Só morre com menos de 2 vizinhos vivos; revive com mais de 4 |
| **Tímida**     | `&`     | Morre se tiver qualquer vizinho vivo; revive se todos estiverem mortos |
| **Matemática** | `#`     | Vive se número de vizinhos for ímpar; revive se for par |
| **Borda**      | `.`     | Sempre morta; usada para delimitar o tabuleiro |

Todas as células mortas são representadas por `.`.

## ☕︎︎ Estrutura do Código

### `Celula.java`
Classe abstrata que define a estrutura e comportamento base de uma célula:
- **Estado:** `vida` (1 = viva, 0 = morta);
- **Métodos:**
  - `setVida(int vida)`: atualiza o estado de vida;
  - `toString()`: retorna `.` se a célula estiver morta ou o símbolo correspondente se estiver viva.
  - `simbolo()`: método abstrato que define o símbolo da célula quando viva.
  - `celulaProximaIteracao(int vizinhos)`: método abstrato que define como a célula evolui com base no número de vizinhos vivos.

 ### Subclasses
 - `CelulaClassica`;
 - `CelulaForte`;
 - `CelulaTimida`;
 - `CelulaMatematica`;
 - `CelulaBorda`.

Cada uma sobrescreve o método `celulaProximaInteracao()` com sua lógica específica.

### `Tabuleiro.java`
Classe responsável por **gerenciar o estado geral** do jogo:
- Contém a **matriz bidimensional** de células;
- Realiza o cálculo da **próxima geração**;
- Conta os **vizinhos vivos** de cada célula;
- Atualiza e **imprime o estado atual** do tabuleiro.

### `Trabalho1Equipe_6`
Classe **principal** responsável por:
- Ler a **entrada do usuário**: dimensões, tipos e estados das células, número de iterações;
- Inicializar o **tabuleiro** com base nos dados informados;
- Executar a **simulação** geração por geração, exibindo o estado do tabuleiro a cada iteração.

## ☕︎︎ Exemplos (Saída do Console)

### `Entrada`:

```
5 5
@ @ @ @ @
@ @ @ @ @
@ @ @ @ @
@ @ @ @ @
@ @ @ @ @
0 0 1 0 0
1 0 0 0 0
1 0 1 0 0
0 1 0 1 0
0 0 0 0 0
1

```

### `Saída:`

```
. . . . . . .     
. . . @ . . . 
. @ . . . . . 
. @ . @ . . . 
. . @ . @ . . 
. . . . . . . 
. . . . . . .

```

### `Pós Iteração:`

```

. . . . . . . 
. . . . . . . 
. . . . . . . 
. @ . @ . . . 
. . @ . . . . 
. . . . . . . 
. . . . . . . 

```

Cada célula é representada por um caractere:
- `'@'`: célula viva;
- `'.'`: célula morta.

## ☕︎︎ Sobre o Trabalho e seus Objetivos de Aprendizado
- **Sobre:**
  - Este projeto foi desenvolvido como **trabalho em trio** para a disciplina de **Programação Orientada a Objetos** no curso de graduação de **Ciência da Computação** da **UNESPAR (Universidade Estadual do Paraná)**.
- **
 
## ☕︎︎ Tecnologias Utilizadas 
- Netbeans.

## ☕︎︎ Equipe

Desenvolvido por:

- [Luna](https://github.com/Luna-Osti)
- [Maria Rita](https://github.com/MariaCampanaP)
- [Vallentina](https://github.com/Vallentinanina)

## ☕︎︎ Como Usar 

1. Clone o repositório para o seu computador:
   
   ```bash
   git clone https://github.com/MariaCampanaP/Jogo-da-Vida-Java-Primeiro-Bimestre.git
   ```
<img width="100%" src="https://capsule-render.vercel.app/api?type=waving&height=120&color=023fa1&section=footer"/>

