import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        try (Scanner scanner = new Scanner(System.in)) {
            int option;
            do {
                System.out.println("\n1. Adicionar Tarefa");
                System.out.println("2. Listar Tarefas");
                System.out.println("3. Marcar Tarefa como Concluída");
                System.out.println("4. Remover Tarefa");
                System.out.println("5. Sair");
                System.out.print("Escolha uma opção: ");
                option = scanner.nextInt();
                scanner.nextLine();  // Consumir a nova linha

                switch (option) {
                    case 1 -> {
                        System.out.print("Nome da Tarefa: ");
                        String taskName = scanner.nextLine();
                        taskManager.addTask(taskName);
                    }
                    case 2 -> {
                        System.out.println("Lista de Tarefas:");
                        int i = 0;
                        for (Task task : taskManager.getTasks()) {
                            System.out.println(i + ". " + task);
                            i++;
                        }
                    }
                    case 3 -> {
                        System.out.print("Índice da Tarefa a ser marcada como concluída: ");
                        int indexToComplete = scanner.nextInt();
                        taskManager.markTaskAsCompleted(indexToComplete);
                    }
                    case 4 -> {
                        System.out.print("Índice da Tarefa a ser removida: ");
                        int indexToRemove = scanner.nextInt();
                        taskManager.removeTask(indexToRemove);
                    }
                    case 5 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida.");
                }
            } while (option != 5);
        }
    }
}
