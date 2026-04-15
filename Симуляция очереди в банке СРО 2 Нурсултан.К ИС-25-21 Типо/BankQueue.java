import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class BankQueue {
    public static void main(String[] args) {

        Queue<String> queue = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        //  Таймер (каждую 1 минуту = 60000 мс)
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!queue.isEmpty()) {
                    String served = queue.poll();
                    System.out.println("\n[ТАЙМЕР] Обслужен клиент: " + served);
                }
            }
        }, 60000, 60000); // 1 минута

        while (true) {
            System.out.println("\n МЕНЮ  (ПИШИТЕ НА ЛАТИНСКОМ ЯЗЫКЕ.Например Ali,Nursultan,Bekzat)");
            System.out.println("1. Добавить клиента");
            System.out.println("2. Обслужить клиента");
            System.out.println("3. Показать очередь");
            System.out.println("4. Узнать свое место в очереди");
            System.out.println("5. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка Enter

            switch (choice) {
                case 1:
                    System.out.print("Введите имя клиента: ");
                    String name = scanner.nextLine();
                    queue.add(name);
                    System.out.println("Клиент " + name + " добавлен в очередь.");
                    break;

                case 2:
                    if (queue.isEmpty()) {
                        System.out.println("Очередь пуста.");
                    } else {
                        String servedClient = queue.poll();
                        System.out.println("Обслужен клиент: " + servedClient);
                    }
                    break;

                case 3:
                    if (queue.isEmpty()) {
                        System.out.println("Очередь пуста.");
                    } else {
                        System.out.println("Текущая очередь:");
                        int i = 1;
                        for (String client : queue) {
                            System.out.println(i + ". " + client);
                            i++;
                        }
                    }
                    break;

                case 4:
                    if (queue.isEmpty()) {
                        System.out.println("Очередь пуста.");
                    } else {
                        System.out.print("Введите ваше имя: ");
                        String searchName = scanner.nextLine();

                        int position = 1;
                        boolean found = false;

                        for (String client : queue) {
                            if (client.equalsIgnoreCase(searchName)) {
                                System.out.println(searchName + ", вы в очереди под номером: " + position);
                                found = true;
                                break;
                            }
                            position++;
                        }

                        if (!found) {
                            System.out.println("Клиент с именем " + searchName + " не найден в очереди.");
                        }
                    }
                    break;

                case 5:
                    System.out.println("Программа завершена.");
                    timer.cancel(); // остановка таймера
                    scanner.close();
                    return;

                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}
