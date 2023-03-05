package Presenter;

import java.util.Scanner;

import Model.Model;
import Model.Toy;
import View.View;

public class Presenter {
    private Model model;
    private View view;

    public Presenter(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void start() {
        Scanner presScanner = new Scanner(System.in);
        boolean flag = true;
        int count = 0;
        while (flag) {
            view.printMenu();
            int userChoice = presScanner.nextInt();
            switch (userChoice) {
                case 1:
                    model.addToy();
                    System.out.println("Новая игрушка успешно создана!");
                    break;
                case 2:
                    var prizeToys = model.choicePrizeToy();
                    System.out.printf("Ваша призовая игрушка:\nId: %d, Название: %s\n", prizeToys.get(count).getId(),
                            prizeToys.get(count).getToyName());
                    count++;
                    break;
                case 3:
                    var ourToy = model.getPrizeToy();
                    System.out.printf("Вы забрали игрушку:\nId: %d, Название: %s\n", ourToy.getId(),
                            ourToy.getToyName());
                    if (model.getPrizeToys().contains(ourToy)) {
                        for (Toy toy : model.getPrizeToys()) {
                            if (toy.getQuantity() == 0) {
                                model.getPrizeToys().remove(ourToy);
                                for (int index = 0; index < model.getPrizeToys().size(); index++) {
                                    model.getPrizeToys().get(index).setId(model.getPrizeToys().get(index).getId() - 1);
                                }
                            }
                        }
                    }
                    break;
                case 4:
                    System.out.println("\nСписок всех разыгрываемых игрушек: ");
                    model.showToys();
                    break;
                case 5:
                    System.out.println("\nВыход из программы. До свидания!");
                    flag = false;
                    break;
            }

        }
    }

}
