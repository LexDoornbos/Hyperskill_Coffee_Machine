package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // set initial values
        int totalMoney = 550;
        int water = 400;
        int milk = 540;
        int beans = 120;
        int emptyCups = 9;
        // print status of machine.
//        printStatus(totalMoney, water, milk, beans, emptyCups);

        boolean loop = true;
        while (loop) {
            // ask user what to do.
            System.out.printf("%nWrite action (buy, fill, take, remaining, exit):%n");
            String action = scanner.next();

            switch (action) {
                case "buy" -> {
                    int[] usedItems = buyCoffee(water, milk, beans, emptyCups);
                    totalMoney += usedItems[0];
                    water -= usedItems[1];
                    milk -= usedItems[2];
                    beans -= usedItems[3];
                    emptyCups -= usedItems[4];
//                    printStatus(totalMoney, water, milk, beans, emptyCups);
                }
                case "fill" -> {
                    int[] addedItems = fillMachine();
                    water += addedItems[0];
                    milk += addedItems[1];
                    beans += addedItems[2];
                    emptyCups += addedItems[3];
//                    printStatus(totalMoney, water, milk, beans, emptyCups);
                }
                case "take" -> {
                    totalMoney = takeMoney(totalMoney);
//                    printStatus(totalMoney, water, milk, beans, emptyCups);
                }
                case "remaining" -> printStatus(totalMoney, water, milk, beans, emptyCups);
                case "exit" -> loop = false;
                default -> System.out.println("Wrong input. Try again.");
            }
        }
    }

    public static void printStatus(int totalIncome, int water, int milk, int beans, int emptyCups) {
        System.out.printf("""
                %nThe coffee machine has:
                %d ml of water
                %d ml of milk
                %d g of coffee beans
                %d disposable cups
                $%d of money
                """, water, milk, beans, emptyCups, totalIncome);
    }

    public static int[] buyCoffee(int water, int milk, int beans, int emptyCups) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String coffeeType = scanner.nextLine();
        int[] usedItems = {};

        switch (coffeeType) {
            case "1" -> {
                int[] needed = {250, 0 , 16, 1};
                String notEnough;
                notEnough = checkEnough(needed, water, milk, beans, emptyCups);
                if(notEnough.equals("water") || notEnough.equals("milk") || notEnough.equals("beans") || notEnough.equals("cups")) {
                    System.out.println("Sorry, not enough " + notEnough + "!");
                    usedItems = new int[]{0 ,0 ,0 ,0 ,0};
                }
                else {
                    System.out.println("I have enough resources, making you a coffee!");
                    usedItems = new int[]{4, 250, 0, 16, 1};
                }
            }
            case "2" -> {
                int[] needed = {350, 75 , 20, 1};
                String notEnough;
                notEnough = checkEnough(needed, water, milk, beans, emptyCups);
                if(notEnough.equals("water") || notEnough.equals("milk") || notEnough.equals("beans") || notEnough.equals("cups")) {
                    System.out.println("Sorry, not enough " + notEnough + "!");
                    usedItems = new int[]{0 ,0 ,0 ,0 ,0};
                }
                else {
                    System.out.println("I have enough resources, making you a coffee!");
                    usedItems = new int[]{7, 350, 75, 20, 1};
                }
            }
            case "3" -> {
                int[] needed = {200, 100 , 12, 1};
                String notEnough;
                notEnough = checkEnough(needed, water, milk, beans, emptyCups);
                if(notEnough.equals("water") || notEnough.equals("milk") || notEnough.equals("beans") || notEnough.equals("cups")) {
                    System.out.println("Sorry, not enough " + notEnough + "!");
                    usedItems = new int[]{0 ,0 ,0 ,0 ,0};
                }
                else {
                    System.out.println("I have enough resources, making you a coffee!");
                    usedItems = new int[]{6, 200, 100, 12, 1};
                }
            }
            case "back" -> usedItems = new int[]{0, 0, 0, 0, 0};
            default -> {
                System.out.println("Wrong input!");
                buyCoffee(water, milk, beans, emptyCups);
            }
        }

        return usedItems;
    }
    public static int[] fillMachine() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water you want to add: ");
        int addedWater = scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        int addedMilk = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add: ");
        int addedBeans = scanner.nextInt();
        System.out.println("Write how many disposable cups you want to add: ");
        int addedCups = scanner.nextInt();
        return new int[]{addedWater, addedMilk, addedBeans, addedCups};
    }
    public static int takeMoney(int totalMoney) {
        System.out.printf("I gave you $%d%n", totalMoney);
        totalMoney = 0;
        return totalMoney;
    }

    public static String checkEnough(int[] needed, int water, int milk, int beans, int emptyCups) {
        String notEnough;
        if(needed[0] > water) {
            notEnough = "water";
        } else if(needed[1] > milk) {
            notEnough = "milk";
        } else if(needed[2] > beans) {
            notEnough = "beans";
        } else if(needed[3] > emptyCups) {
            notEnough = "cups";
        } else {
            notEnough = "";
        }
        return notEnough;
    }
}