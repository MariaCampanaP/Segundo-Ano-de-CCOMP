# Importa a classe Vertice do módulo vertice e importa o módulo random para geração de números aleatórios
from vertice import Vertice
import random

class TabuleiroSudoku:
    # Construtor da classe TabuleiroSudoku
    def __init__(self, matriz, ordem = 3):
        # Define a ordem do Sudoku (padrão 3 para Sudoku 9x9)
        self.ordem = ordem
        """ Cria a matriz do tabuleiro populando com objetos Vertices
        baseado nos valores da matriz de entrada """
        self.tabuleiro = [[Vertice(matriz[i][j]) for j in range(ordem * ordem)] for i in range(ordem * ordem)]
        # Gera as arestas (conexões) entre os vértices do grafo
        self.gerar_arestas()

    # Retorna uma matriz apenas com os números do tabuleiro (sem os objetos Vertice)
    def get_numeros(self):
        return [[self.tabuleiro[i][j].num for j in range(self.ordem * self.ordem)] for i in range(self.ordem * self.ordem)]

    # Atualiza os números de todos os vértices do tabuleiro
    def set_numeros(self, numeros):
        # Percorre todas as posições do tabuleiro
        for i in range(self.ordem * self.ordem):
            for j in range(self.ordem * self.ordem):
                # Define o número do vértice na posição (i, j)
                self.tabuleiro[i][j].num = numeros[i][j]

    # Gera as arestas do grafo que representa o sudoku
    def gerar_arestas(self):
        # Calcula o tamanho do tabuleiro (n x n)
        n = self.ordem * self.ordem
        # Percorre todas as células do tabuleiro
        for i in range(n):
            for j in range(n):
                # Obtém o vértice na posição atual
                v = self.tabuleiro[i][j]
                # Linha: conecta com todos os outros vértices na mesma linha
                for k in range(n):
                    # Evita conectar o vértice consigo mesmo
                    if k != j:
                        # Adiciona aresta com vértice na mesma linha
                        v.add_aresta(self.tabuleiro[i][k])
                # Coluna: conecta com todos os outros vértices na mesma coluna
                for l in range(n):
                    # Evita conectar o vértice consigo mesmo
                    if l != i:
                        # Adiciona aresta com vértice na mesma coluna
                        v.add_aresta(self.tabuleiro[l][j])
                """ Quadrante: conecta com todos os outros vértices no mesmo quadrante 3 x 3
                e calcula o índice do quadrante (0, 0), (0, 1), (0, 2), etc. """
                qi, qj = i // self.ordem, j // self.ordem
                # Percorre todas as células do quadrante atual
                for m in range(qi * self.ordem, (qi + 1) * self.ordem):
                    for n2 in range(qj * self.ordem, (qj + 1) * self.ordem):
                        # Evita conectar o vértice consigo mesmo
                        if not (m == i and n2 == j):
                            # Adiciona aresta com vértice no mesmo quadrante
                            v.add_aresta(self.tabuleiro[m][n2])

    # Escolhe um número válido para um vértice específico
    def escolher_num(self, v):
        # Cria conjunto com números já usados pelos vértices adjacentes
        usados = {adj.num for adj in v.adj if adj.num != -1}
        # Cria lista com números disponíveis (não usados pelos adjacentes)
        disponiveis = [i for i in range(1, self.ordem * self.ordem + 1) if i not in usados]
        # Se há números disponíveis, escolhe um aleatoriamente e atribui ao vértice
        if disponiveis:
            v.num = random.choice(disponiveis)

    # Verifica se uma vértice tem conflitos com seus vértices adjacentes
    def checar_adjs(self, v):
        # Retorna True se nenhum vértice adjacente tem o mesmo número
        return all(adj.num != v.num for adj in v.adj)

    # Verifica se a solução atual está correta
    def checar_resposta(self):
        n = self.ordem * self.ordem
        # Verifica todas as linhas
        for i in range(n):
            # Coleta todos os números da linha i
            linha = [self.tabuleiro[i][j].num for j in range(n)] 
            # Verifica se há números repetidos ou células vazias (-1)
            if len(set(linha)) != n or -1 in linha:
                return False
        # Verifica todas as colunas
        for j in range(n):
            # Coleta todos os números da coluna j
            coluna = [self.tabuleiro[i][j].num for i in range(n)]
            # Verifica se há números repetidos ou células vazias (-1)
            if len(set(coluna)) != n or -1 in coluna:
                return False
        # Verifica todos os quadrantes 3 x 3
        for qi in range(0, n, self.ordem):
            for qj in range(0, n, self.ordem):
                bloco = []
                # Coleta todos os números do quadrante atual
                for i in range(qi, qi + self.ordem):
                    for j in range(qj, qj + self.ordem):
                        bloco.append(self.tabuleiro[i][j].num)
                # Verifica se há números repetidos ou células vazias (-1)
                if len(set(bloco)) != n or -1 in bloco:
                    return False
        # Se passou por todas as verificações, a solução está correta
        return True

    # Seleciona um vértice aleatório do tabuleiro
    def escolher_vertice_aleatorio(self):
        n = self.ordem * self.ordem
        # Importa random novamente para garantir que está disponível
        import random
        i, j = random.randint(0, n - 1), random.randint(0, n - 1)
        # Retorna o vértice na posição aleatória
        return self.tabuleiro[i][j]

    # Representação em string do tabuleiro
    def __str__(self):
        # Converte cada linha em string e junta com quebras de linha
        return "\n".join(" ".join(str(v) for v in linha) for linha in self.tabuleiro)