package app;

import CLI.MenuManagerCLI;

public class Main {

    public static void main(String[] args) {

        MenuManagerCLI menuManager = new MenuManagerCLI();


        menuManager.run();

        System.out.println(" Hệ thống đã thoát. Cảm ơn bạn!");
    }
}