def carregar_tabuleiro_txt(caminho):
    """ Carrega um tabuleiro de Sudoku a partir de um
    arquivo de texto e retorna uma matriz 2D de inteiros."""
    # Cria uma lista vazia para armazenar a matriz do tabuleiro
    matriz = []
    # Abre o arquivo no modo leitura
    with open(caminho, "r") as f:
        # Itera sobre cada linha do arquivo
        for linha in f:
            # Divide a linha usando vírgulas como separador e converte cada elemento para inteiro
            numeros = list(map(int, linha.strip().split(",")))
            # Adiciona a lista de números (uma linha do tabuleiro) à matriz
            matriz.append(numeros)
    # Retorna a matriz completa do tabuleiro
    return matriz 