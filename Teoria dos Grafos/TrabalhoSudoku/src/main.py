print("\nUniversidade Estadual do Paraná - UNESPAR")
print("Alunos: Maria Rita Campana Peixoto e Mizael Augusto Soares Oliveira")
print("Professora: Lailla Milainny Siqueira Bine")
print("\nTrabalho de Teoria dos Grafos - 1° Bimestre")
print("Curso: Bacharelado em Ciência da Computação\n")
print("-" * 60)

# Importações das classes e bibliotecas necessárias
# Importa a classe TabuleiroSudoku para resolver sudokus
from vertice import TabuleiroSudoku
# Para medir o tempo de execução dos algoritmos
import time

# Função para carregar sudoku de arquivo
def carregar_sudoku(nome_arquivo):
    try:
        # Abre o arquivo no modo leitura
        with open(nome_arquivo, 'r') as arquivo:
            # Lê cada linha, remove espaços, separa por vírgulas e converte para inteiros
            return [[int(x) for x in linha.strip().split(',')] for linha in arquivo]
    except:
        # Tratamento de erro caso o arquivo não possa ser carregado
        print(f"Erro ao carregar {nome_arquivo}")
        # Retorna None em caso de erro
        return None

# Função para mostrar sudoku formatado
def mostrar_sudoku(sudoku):
    print("\n Sudoku:")
    print("---" * 11)
    # Itera sobre cada linha do sudoku
    for i, linha in enumerate(sudoku):
        # Adiciona linha divisória a cada 3 linha (exceto na primeira)
        if i % 3 == 0 and i != 0:
            print("---" * 11)

        # Inicia string da linha com separador vertical
        linha_str = "|"
        # Itera sobre cada número na linha
        for j, num in enumerate(linha):
            if j % 3 == 0 and j != 0:
                linha_str += " |"
            # Adiciona número ou ponto (se for -1/ vazio) à string
            linha_str += f" {num if num != -1 else '.'} "
        # Fecha a linha com separador vertical
        linha_str += "|"
        print(linha_str)
    # Imprime linha divisória inferior
    print("---" * 11)

# Função para testar algoritmo
def testar_algoritmo(nome, sudoku, max_iteracoes):
    print(f"\nTestando {nome}\n")
    print("-" * 40)

    # Cria instância do tabuleiro de Sudoku
    tabuleiro = TabuleiroSudoku(sudoku)

    # Conta células vazias (-1) no sudoku
    vazias = sum(1 for linha in sudoku for num in linha if num == -1)
    print(f"Células vazias: {vazias}/81")

    # Marca tempo inicial
    inicio = time.time()
    # Executa algoritmo escolhido
    if nome == "BFS":
        resultado = tabuleiro.coloracao_em_largura(max_iteracoes)
    else:
        resultado = tabuleiro.coloracao_em_profundidade(max_iteracoes)
        # Calcula tempo decorrido
    tempo = time.time() - inicio

    # Exibe resultados
    print(f"Tempo: {tempo:.2f}s")
    print(f"Resultado: {'Sucesso' if resultado else 'Falha'}")

    # Se encontrou solução, mostra o sudoku resolvido
    if resultado:
        print("Solução encontrada:")
        # Mostrar a solução formatada
        mostrar_sudoku(tabuleiro.get_numeros())

    return resultado, tempo

# Programa principal
def main():
    # Carregar instâncias
    instancias = []
    # Lista de arquivos de instâncias de sudoku
    arquivos = ['./instances/inst1.txt', './instances/inst2.txt',
                './instances/inst3.txt']

    print("\nCarregando instâncias...")
    # Itera sobre cada arquivo da na lista
    for i, arquivo in enumerate(arquivos, 1):
        # Carrega sudoku do arquivo
        sudoku = carregar_sudoku(arquivo)
        if sudoku:
            # Conta células vazias no sudoku carregado
            vazias = sum(1 for linha in sudoku for num in linha if num == -1)
            print(f"Instância {i}: {vazias} células vazias")
            # Adiciona à lista de instâncias
            instancias.append(sudoku)
        else:
            print(f"Instância {i}: Erro ao carregar")
            # Adiciona None em caso de erro
            instancias.append(None)

    # Menu 
    while True:
        print("\n" + "-" * 50)
        print("\nMENU PRINCIPAL\n")
        print("1. Testar Instância 1")
        print("2. Testar Instância 2")
        print("3. Testar Instância 3")
        print("4. Sair\n")
        print("-" * 50)

        # Solicita opção do usuário
        opcao = input("\nEscolha uma opção: ")

        # Sai do programa se a opção for 4
        if opcao == "4":
            print("Programa encerrado!")
            break

        # Se a opção for válida (1-4), testa a instância correspondente
        elif opcao in ["1", "2", "3"]:
            num = int(opcao)
            # Obtém sudoku da instância escolhida
            sudoku = instancias[num - 1]

            # Verifica se a instância foi carregada corretamente
            if sudoku is None:
                print("Instância não carregada!")
                continue

            print(f"\nInstância {num}:")
            # Mostra o sudoku inicial
            mostrar_sudoku(sudoku)

            # Solicita número de iterações (usa padrão se vazio)
            max_iter = int(input("Número de iterações (padrão: 100000): ") or "100000")

            # Testa ambos algoritmos na instância 
            testar_algoritmo("BFS", sudoku, max_iter)
            testar_algoritmo("DFS", sudoku, max_iter)

        # Opção inválida
        else:
            print("Opção inválida!")

        # Pausa antes de voltar ao menu
        input("\nPressione Enter para continuar...")

# Executar
if __name__ == "__main__":
    main()
