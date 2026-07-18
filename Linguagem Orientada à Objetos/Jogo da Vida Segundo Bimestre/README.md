<img width="100%" src="https://capsule-render.vercel.app/api?type=waving&height=120&color=023fa1&section=header"/>

# ☕︎︎ Projeto - Jogo da Vida em Java - Versão Gráfica (2° Bimestre) 

Uma evolução do projeto anterior - O **Jogo da Vida** de John Horton Conway - agora com uma **interface gráfica interativa em Java Swing** e novas funcionalidades.
O trabalho reforça os conceitos de **Programação Orientada a Objetos (POO)** e introduz práticas de **tratamento de exceções, organização modular e interação com o usuário**.

## ☕︎︎ Visão Geral

O projeto simula um **ecossistema celular** em um tabuleiro, onde células vivem, morrem ou renascem conforme suas vizinhas.
Nesta segunda versão, o jogo ganhou uma **interface visual** dividida em telas de controle e edição, **leitura e gravação de arquivos** e um sistema de **tratamento de erros** robusto.

Desenvolvido como trabalho acadêmico na disciplina de **Linguagens Orientadas a Objetos**, no curso de **Ciência da Computação** da **UNESPAR**.

## ☕︎︎ Funcionalidades

- Interface gráfica construída com **Java Swing**
- **Tela principal** com o tabuleiro e controles de iteração
- **Tela de edição** para modificar células vivas e mortas
- Opções de **abrir/salvar** estados do jogo em arquivo
- **Tratamento completo de exceções** (arquivos inválidos, erros de edição, etc.)
- Controle de **avanço automático** com intervalo personalizável
- **Bloqueio de ações conflitantes**, garantindo execução segura
- Correções e melhorias com base no feedback do **primeiro bimestre**

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
- Armazena a **matriz bidimensional de células**
- Conta vizinhos vivos
- Calcula a próxima geração
- Exibe o estado no **painel gráfico**

### `InterfaceMenuPrincipal.java`
Classe que implementa a **interface gráfica principal** do projeto, utilizando **Java Swing**
Principais elementos:
- Exibição visual do tabuleiro com as células e bordas
- Botões para avançar uma iteração e avançar múltiplas iterações com intervalo personalizado
- Campos de entrada para: quantidade de iterações e intervalo de tempo
- Menu interativo com as opções: Abrir o arquivo (.txt) com as configurações de jogo, salvar o estado atual, editar o tabuleiro (abrindo uma janela modal de edição), mostrar informações sobre os tipos de células e fechar a aplicação.
- Durante execuções automáticas, os botões de iteração são temporariamente desativados, evitando erros e conflitos
  
### `Trabalho1Equipe_6`
Classe **principal** responsável por:
- Inicializar a interface (InterfaceMenuPrincipal)
- Carregar o tabuleiro padrão ou arquivo de entrada
- Iniciar a simulação do jogo

## ☕︎︎ Exemplos (Saída do Console)

<img width="969" height="723" alt="Captura de tela 2025-10-15 182353" src="https://github.com/user-attachments/assets/6a452caf-7cd1-4b12-8eeb-3e8878ac98c7" />

<img width="968" height="728" alt="Captura de tela 2025-10-15 182618" src="https://github.com/user-attachments/assets/b4501898-a79b-4f5b-9969-64521efb2f53" />

<img width="966" height="721" alt="Captura de tela 2025-10-15 182638" src="https://github.com/user-attachments/assets/c327be62-fff3-40fc-b238-b5588d97c703" />

Cada célula é representada por um caractere:
- `'@'`: célula viva;
- `'.'`: célula morta.

## ☕︎︎ Sobre o Trabalho e seus Objetivos de Aprendizado
- **Sobre:**
  - Este projeto foi desenvolvido como **trabalho em trio** para a disciplina de **Programação Orientada a Objetos** no curso de graduação de **Ciência da Computação** da **UNESPAR (Universidade Estadual do Paraná)**.
- **Objetivos**:
  - Praticar **Encapsulamento**, **Herança**, **Polimorfismo** e **Abstração**;
  - Implementar sistemas modulares e extensíveis;
  - Aplicar POO em um projeto funcional e interativo;
  - Compreender regras de vizinhança e estados em autômatos celulares.
 
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
   git clone https://github.com/MariaCampanaP/Jogo-da-Vida-Java-Segundo-Bimestre.git
   ```
<img width="100%" src="https://capsule-render.vercel.app/api?type=waving&height=120&color=023fa1&section=footer"/>

