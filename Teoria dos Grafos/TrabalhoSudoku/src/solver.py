# Importa a estrutura de dados deque (fila dupla) para BFS
from collections import deque 

class SolverSudoku:
    def __init__(self, tabuleiro):
        """ Construtor da classe SolverSudoku
        que armazena o tabuleiro que será resolvido"""
        self.tabuleiro = tabuleiro

    def bfs(self, max_iter = 100000):
        # Algoritmo de Busca em Largura
        backup_original = self.tabuleiro.get_numeros()
        # Faz backup do estado original do tabuleiro
        
        # Loop principal que executa até o número máximo de iterações
        for iteracao in range(max_iter):
            # Cria uma fila vazia para o algoritmo
            fila = deque()
            # Cria um conjunto para armazenar vértices visitados durante esta tentativa
            visitados = set()
            
            # Encontra todos os vértices vazios no tabuleiro atual
            vertices_vazios = []
            # Percorre toda a matriz do tabuleiro
            for i in range(self.tabuleiro.ordem * self.tabuleiro.ordem):
                for j in range(self.tabuleiro.ordem * self.tabuleiro.ordem):
                    # Verifica se a célula está vazia (valor - 1)
                    if self.tabuleiro.tabuleiro[i][j].num == -1:
                        # Adiciona o vértice vazio à lista
                        vertices_vazios.append(self.tabuleiro.tabuleiro[i][j])
            
            # Se não há vértices vazios, verifica se está correto
            if not vertices_vazios:
                # Se a solução está correta, retorna sucesso
                if self.tabuleiro.checar_resposta():
                    return True
                else:
                    # Se está completo mas incorreto, reinicia completamente e continua para a próxima iteração
                    self.tabuleiro.set_numeros(backup_original)
                    continue
            
            # Escolhe um vértice vazio para iniciar
            vertice_inicial = vertices_vazios[0]
            
            # Obtém todos os números válidos para este vértice inicial
            numeros_validos = self.tabuleiro.get_numeros_validos(
                # Encontra a coordenada i do vértice inicial
                self.encontrar_posicao(vertice_inicial)[0],
                # Encontra a coordenada j do vértice inicial
                self.encontrar_posicao(vertice_inicial)[1]
            )
            
            # Para cada número válido no vértice inicial, tenta uma solução
            for num in numeros_validos:
                # Atribui o número atual ao vértice inicial
                vertice_inicial.num = num
                
                # BFS a partir deste vértice
                fila.append(vertice_inicial)
                visitados.add(vertice_inicial)
                sucesso = True
                
                # Executa BFS enquanto houver vértices na fila e não houver conflitos
                while fila and sucesso:
                    # Remove o primeiro vértice da fila
                    u = fila.popleft()
                    
                    # Itera sobre todos os vértices adjacentes ao vértice atual
                    for w in u.adj:
                        # Verifica se o vértice adjacente não foi visitado e está vazio
                        if w not in visitados and w.num == -1:
                            # Obtém os números válidos para o vértice adjacente
                            numeros_w = self.tabuleiro.get_numeros_validos(
                                self.encontrar_posicao(w)[0],
                                self.encontrar_posicao(w)[1]
                            )
                            
                            # Se há números válidos disponíveis
                            if numeros_w:
                                # Escolhe o primeiro número válido
                                w.num = numeros_w[0]
                                # Marca o vértice como visitado e adiciona à fila para processar seus adjacentes
                                visitados.add(w)
                                fila.append(w)
                            else:
                                # Se não há números válidos, marca como falha
                                sucesso = False
                                # Saí do loop interno
                                break
                
                # Se preencheu tudo com sucesso, verifica se a solução final está correta
                if sucesso and self.tabuleiro.checar_tabuleiro_completo():
                    if self.tabuleiro.checar_resposta():
                        # Retorna sucesso se a solução estiver correta
                        return True
                
                # Limpa apenas os vértices que foram preenchidos nesta tentativa específica
                self.limpar_visitados(visitados)
                # Limpa o conjunto de visitados para a próxima tentativa
                visitados.clear()
                # Limpa a fila para a próxima tentativa
                fila.clear()
            
            # Se nenhum número funcionou para este vértice, reinicia
            self.tabuleiro.set_numeros(backup_original)
            
        # Retorna falha após todas as iterações
        return False

    def dfs(self, max_iter=100000):
        # Algoritmo de Busca em Profundidade
        backup_original = self.tabuleiro.get_numeros()
        # Faz backup do estado original do tabuleiro
        
        # Loop principal de iterações
        for iteracao in range(max_iter):
            # Cria uma pilha vazia para o algoritmo
            pilha = []
            # Conjunto para vértices visitados
            visitados = set()
            
            # Encontra o primeiro vértice vazio no tabuleiro
            vertice_inicial = None
            # Percorre o tabuleiro procurando por células vazias
            for i in range(self.tabuleiro.ordem * self.tabuleiro.ordem):
                for j in range(self.tabuleiro.ordem * self.tabuleiro.ordem):
                    if self.tabuleiro.tabuleiro[i][j].num == -1:
                        vertice_inicial = self.tabuleiro.tabuleiro[i][j]
                        break
                # Se encontrou um vértice vazio, para a busca
                if vertice_inicial:
                    break
            
            # Se não encontrou vértices vazios, verifica se a solução está correta
            if not vertice_inicial:
                if self.tabuleiro.checar_resposta():
                    return True
                else:
                    # Reinicia completamente e continua para a próxima iteração
                    self.tabuleiro.set_numeros(backup_original)
                    continue
            
            # Encontra a posição do vértice inicial
            pos = self.encontrar_posicao(vertice_inicial)
            # Obtém números válidos para o vértice inicial
            numeros_validos = self.tabuleiro.get_numeros_validos(pos[0], pos[1])
            
            # Testa cada número válido no vértice inicial
            for num in numeros_validos:
                # Atribui o número ao vértice
                vertice_inicial.num = num
                # Adiciona à pilha e marca como visitado
                pilha.append(vertice_inicial)
                visitados.add(vertice_inicial)
                sucesso = True
                
                # Executa DFS enquanto houver elementos na pilha
                while pilha and sucesso:
                    # Olha o vértice no topo da pilha sem remover
                    u = pilha[-1]
                    encontrou_proximo = False
                    
                    # Procura próximo vértice adjacente não visitado
                    for w in u.adj:
                        if w not in visitados and w.num == -1:
                            # Encontra posição do vértice adjacente
                            pos_w = self.encontrar_posicao(w)
                            # Obtém números válidos 
                            numeros_w = self.tabuleiro.get_numeros_validos(pos_w[0], pos_w[1])
                            
                            if numeros_w:
                                # Escolhe o primeiro número válido
                                w.num = numeros_w[0]
                                visitados.add(w)
                                pilha.append(w)
                                encontrou_proximo = True
                                break
                            else:
                                # Sem números válidos, marca falha
                                sucesso = False
                                break
                    
                    # Se não encontrou próximo vértice, remove o topo da pilha
                    if not encontrou_proximo:
                        pilha.pop()

                # Verifica se completou o tabuleiro com sucesso
                if sucesso and self.tabuleiro.checar_tabuleiro_completo():
                    if self.tabuleiro.checar_resposta():
                        return True
                
                # Limpa apenas os vértices modificados nesta tentativa
                self.limpar_visitados(visitados)
                visitados.clear()
                pilha.clear()
            
            # Reinicia o tabuleiro se nenhum número funcionou
            self.tabuleiro.set_numeros(backup_original)
            
        return False

    def encontrar_posicao(self, vertice):
        """Encontra as coordenadas (i, j) de um vértice no tabuleiro
        e percorre toda a matriz do tabuleiro"""
        for i in range(self.tabuleiro.ordem * self.tabuleiro.ordem):
            for j in range(self.tabuleiro.ordem * self.tabuleiro.ordem):
                # Verifica se é o mesmo objeto vértice
                if self.tabuleiro.tabuleiro[i][j] is vertice:
                    # Retorna as coordenadas (i, j)
                    return (i, j)
        # Retorna None se não encontrado
        return None

    def limpar_visitados(self, visitados):
        """Limpa apenas os vértices que foram preenchidos 
        durante a tentativa atual"""
        # Para cada vértice no conjunto de visitados
        for vertice in visitados:
            # Reseta o número para vazio (-1)
            vertice.num = -1
            # Reseta o flag de visitado
            vertice.visitado = False