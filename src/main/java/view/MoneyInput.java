package view;

import java.util.Scanner;

public class MoneyInput {

    private final Scanner scanner = new Scanner(System.in);

    public String inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextLine();
    }
}
