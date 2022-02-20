import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DailyAdviceServer {

    String[] adviceList = {"Перестать жрать на ночь", "Не грызть ногти", "Не ныть", "Прекрати завидовать",
            "Займись спортом", "Начать читать книги", "Учись чему-нибудь", "Поменять дурацкую работу",
            "Найти хобби", "Куить новую одежду", "Вынести мусор", "Погладить кота", "Позвонить в ЖЭК",
            "Купить абонемент в зал", "Не жрать", "Не бухать", "Оторвать зад от дивана", "Научиться говорить НЕТ",
            "Не тупить", "Любить себя", "Не курить"};

    public void go() {
        try {
            ServerSocket serverSock = new ServerSocket(4242);

            while (true) {

                Socket sock = serverSock.accept();

                PrintWriter writer = new PrintWriter(sock.getOutputStream());
                String advice = getAdvice();
                writer.println(advice);
                writer.close();
                System.out.println(advice);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String getAdvice() {
        int random = (int) (Math.random() * adviceList.length);
        return adviceList[random];
    }

    public static void main(String[] args) {
        DailyAdviceServer server = new DailyAdviceServer();
        server.go();
    }
}
