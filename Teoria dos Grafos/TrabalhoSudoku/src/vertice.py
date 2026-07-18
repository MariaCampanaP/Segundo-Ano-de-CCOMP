""" Importa biblioteca para geração de números aleatórios,
importa deque para BFS """
import random
from collections import deque
import copy

# Classe que representa um vértice/célula do sudoku
class Vertice:
    def __init__(self, num=-1):
        # Número do vértice (-1 representa célula vazia)
        self.num = num
        # Lista de vértices adjacentes (mesma linha, coluna ou quadrante)
        self.adj = []
        # Marca se o vértice foi visitado (para buscas)
        self.visitado = False

    def add_aresta(self, vertice):
        # Adiciona um vértice à lista de adjacências se ainda não estiver presente
        if vertice not in self.adj:
            self.adj.append(vertice)

    def __repr__(self):
        # Representação string: mostra número ou "." se for vazio
        return str(self.num) if self.num != -1 else "."
    
# Classe principal que representa o tabuleiro completo do sudoku
class TabuleiroSudoku:
    def __init__(self, numeros=None, ordem = 3):
        # Ordem do sudoku (3 para sudoku 9x9 tradicional)
        self.ordem = ordem
        # Tamanho total do tabuleiro (ordem² x ordem²)
        self.tamanho = ordem * ordem
        # Se não fornecer números, cria tabuleiro vazio
        if numeros is None:
            numeros = [[-1] * self.tamanho for _ in range(self.tamanho)]
        # Cria matriz de vértices com os números fornecidos
        self.tabuleiro = [[Vertice(numeros[i][j]) for j in range(self.tamanho)] for i in range(self.tamanho)]
        # Gera as arestas entre vértices adjacentes
        self.gerar_arestas()

    def get_numeros(self):
        # Retorna matriz apenas com os números
        return [[self.tabuleiro[i][j].num for j in range(self.tamanho)] for i in range(self.tamanho)]

    def set_numeros(self, numeros):
        # Atualiza os números de todos os vértices do tabuleiro
        for i in range(self.tamanho):
            for j in range(self.tamanho):
                self.tabuleiro[i][j].num = numeros[i][j]

    def gerar_arestas(self):
        # Cria as conexões entre vértices que estão na mesma linha, coluna ou quadrante
        for i in range(self.tamanho):
            for j in range(self.tamanho):
                v = self.tabuleiro[i][j]
                # Adiciona arestas para toda a linha (exceto própria posição)
                for k in range(self.tamanho):
                    if k != j:
                        v.add_aresta(self.tabuleiro[i][k])
                #Adiciona arestas para toda a coluna (exceto própria posição)
                for k in range(self.tamanho):
                    if k != i:
                        v.add_aresta(self.tabuleiro[k][j])
                # Adiciona arestas para todo o quadrante 3x3 (exceto própria posição)
                qi, qj = i // self.ordem, j // self.ordem
                for m in range(qi * self.ordem, (qi + 1) * self.ordem):
                    for n in range(qj * self.ordem, (qj + 1) * self.ordem):
                        if m != i or n != j:
                            v.add_aresta(self.tabuleiro[m][n])

    def get_numeros_validos(self, i, j):
        # Retorna números válidos para uma posição específica
        v = self.tabuleiro[i][j]
        # Coleta todos os números já usados nos vértices adjacentes
        usados = {adj.num for adj in v.adj if adj.num != -1}
        # Retorna números de 1 a 9 que não estão nos adjacentes
        return [num for num in range(1, self.tamanho + 1) if num not in usados]

    def encontrar_posicao(self, vertice):
        """ Encontra coordenadas (i,j) de um vértice
        e procura em todo o tabuleiro pelo vértice especificado """
        for i in range(self.tamanho):
            for j in range(self.tamanho):
                if self.tabuleiro[i][j] is vertice:
                    return (i, j)
        return None

    def checar_adjs(self, v):
        # Verifica se algum vértice adjacente tem o mesmo número
        return all(adj.num != v.num for adj in v.adj if adj.num != -1)

    def checar_tabuleiro_completo(self):
        """ Verifica se o tabuleiro está completamente preenchido
        e se existe alguma célula vazia (-1) """
        for i in range(self.tamanho):
            for j in range(self.tamanho):
                if self.tabuleiro[i][j].num == -1:
                    return False
        return True

    def checar_resposta(self):
        """ Primeiro vertifica se está completo,
        depois verifica se a solução está correta"""
        if not self.checar_tabuleiro_completo():
            return False
        
        # Verifica linhas: todos os números de 1 a 9 devem aparecer exatamente uma vez
        for i in range(self.tamanho):
            linha = [self.tabuleiro[i][j].num for j in range(self.tamanho)]
            if len(set(linha)) != self.tamanho:
                return False
        # Verifica colunas: todos os números de 1 a 9 devem aparecer exatamente uma vez
        for j in range(self.tamanho):
            coluna = [self.tabuleiro[i][j].num for i in range(self.tamanho)]
            if len(set(coluna)) != self.tamanho:
                return False
        # Verifica quadrantes 3x3: todos os números de 1 a 9 devem aparecer exatamente uma vez
        for qi in range(0, self.tamanho, self.ordem):
            for qj in range(0, self.tamanho, self.ordem):
                bloco = [self.tabuleiro[i][j].num 
                        for i in range(qi, qi + self.ordem) 
                        for j in range(qj, qj + self.ordem)]
                if len(set(bloco)) != self.tamanho:
                    return False
        return True

    def reset_visitados(self):
        """ Reseta todos os vértices para não visitados e
        limpa flag de visitação para nova execução do algoritmo """
        for i in range(self.tamanho):
            for j in range(self.tamanho):
                self.tabuleiro[i][j].visitado = False

    def coloracao_em_largura(self, max_iteracoes=100000):
        # Tenta resolver por BFS até o número máximo de iterações
        for iteracao in range(max_iteracoes):
            
            # Faz backup do estado atual do tabuleiro
            backup = copy.deepcopy(self.get_numeros())
            self.reset_visitados()
            
            # Encontra todas as células vazias
            celulas_vazias = []
            for i in range(self.tamanho):
                for j in range(self.tamanho):
                    if self.tabuleiro[i][j].num == -1:
                        celulas_vazias.append((i, j))
            
            # Se não há células vazias, verifica se a solução está correta
            if not celulas_vazias:
                if self.checar_resposta():
                    return True
                continue
            
            # BFS começando de uma célula vazia aleatória
            start_i, start_j = random.choice(celulas_vazias)
            start_v = self.tabuleiro[start_i][start_j]
            
            # Obtém números válidos para a célula inicial
            numeros_validos = self.get_numeros_validos(start_i, start_j)
            if not numeros_validos:
                continue
                
            # Escolhe um número aleatório válido para a célula inicial
            start_v.num = random.choice(numeros_validos)
            start_v.visitado = True
            
            # Inicia fila BFS com a célula inicial
            fila = deque([start_v])
            valido = True
            
            # Processa a fila enquanto houver elementos e não houver conflitos
            while fila and valido:
                u = fila.popleft()
                
                # Para cada vértice adjacente não visitado e vazio
                for w in u.adj:
                    if not w.visitado and w.num == -1:
                        w_pos = self.encontrar_posicao(w)
                        if w_pos:
                            # Obtém números válidos para o vértice adjacente
                            numeros_validos_w = self.get_numeros_validos(w_pos[0], w_pos[1])
                            if numeros_validos_w:
                                # Escolhe um número aleatório válido
                                w.num = random.choice(numeros_validos_w)
                                # Verifica se não causa conflito
                                if not self.checar_adjs(w):
                                    valido = False
                                    break
                                w.visitado = True
                                fila.append(w)
                            else:
                                # Sem números válidos - solução inválida
                                valido = False
                                break
            
            # Se conseguiu preencher todo o tabuleiro sem conflitos, verifica se a solução está correta
            if valido and self.checar_tabuleiro_completo() and self.checar_resposta():
                return True
            
            # Restaura backup se não encontrou solução válida  
            self.set_numeros(backup)
            
        return False

    def coloracao_em_profundidade(self, max_iteracoes=100000):
        # Tenta resolver por DFS até o número máximo de iterações
        for iteracao in range(max_iteracoes):
            
            # Faz backup do estado atual 
            backup = copy.deepcopy(self.get_numeros())
            self.reset_visitados()
            
            # Encontra todas as células vazias
            celulas_vazias = []
            for i in range(self.tamanho):
                for j in range(self.tamanho):
                    if self.tabuleiro[i][j].num == -1:
                        celulas_vazias.append((i, j))
            
            # Verifica se já está completo
            if not celulas_vazias:
                if self.checar_resposta():
                    return True
                continue
            
            # Escolhe célula vazia aleatória para iniciar DFS
            start_i, start_j = random.choice(celulas_vazias)
            start_v = self.tabuleiro[start_i][start_j]
            
            # Obtém números válidos para célula inicial
            numeros_validos = self.get_numeros_validos(start_i, start_j)
            if not numeros_validos:
                continue
                
            # Escolhe número aleatório válido
            start_v.num = random.choice(numeros_validos)
            start_v.visitado = True
            
            # Inicia pilha DFS com célula inicial
            pilha = [start_v]
            valido = True
            
            # Processa pilha enquanto houver elementos e não houver conflitos
            while pilha and valido:
                # Olha o topo sem remover
                u = pilha[-1] 
                encontrou_nao_visitado = False
                
                # Procura por adjacentes não visitados
                for w in u.adj:
                    if not w.visitado and w.num == -1:
                        w_pos = self.encontrar_posicao(w)
                        if w_pos:
                            numeros_validos_w = self.get_numeros_validos(w_pos[0], w_pos[1])
                            if numeros_validos_w:
                                # Escolhe número válido e empilha
                                w.num = random.choice(numeros_validos_w)
                                if not self.checar_adjs(w):
                                    valido = False
                                    break
                                w.visitado = True
                                pilha.append(w)
                                encontrou_nao_visitado = True
                                # DFS: explora primeiro filho encontrado
                                break
                            else:
                                valido = False
                                break
                
                # Se não encontrou adjacentes não visitados, desempilha 
                if not encontrou_nao_visitado:
                    pilha.pop()
            
            # Verifica se encontrou solução válida
            if valido and self.checar_tabuleiro_completo() and self.checar_resposta():
                return True
            
            # Restaura backup se não funcionou
            self.set_numeros(backup)
            
        return False

    def __str__(self):
        # Representação visual formatada do tabuleiro
        result = []
        for i in range(self.tamanho):
            # Adiciona linha divisória horizontal entre quadrantes
            if i > 0 and i % self.ordem == 0:
                result.append("-" * (self.tamanho * 3 + self.ordem - 1))
            linha = []
            for j in range(self.tamanho):
                # Adiciona separador vertical entre quadrantes
                if j > 0 and j % self.ordem == 0:
                    linha.append("|")
                # Formata número (ou ponto se vazio) com 2 espaços
                num_str = str(self.tabuleiro[i][j].num) if self.tabuleiro[i][j].num != -1 else "."
                linha.append(f"{num_str:>2}")
            result.append(" ".join(linha))
        return "\n".join(result)
