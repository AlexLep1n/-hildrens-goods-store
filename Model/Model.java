package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Model {
    private ToysCollection toys;

    public Model(ToysCollection toys) {
        this.toys = toys;
    }

    public void addToy() {
        System.out.printf("Введите название новой игрушки: ");
        Scanner nameScanner = new Scanner(System.in);
        String toyName = nameScanner.next();
        System.out.printf("Введите количество моделей новой игрушки: ");
        Scanner quantityScanner = new Scanner(System.in);
        int toyQuantity = quantityScanner.nextInt();
        System.out.printf("Введите частоту выпадения игрушки: ");
        Scanner dropFrequencyScanner = new Scanner(System.in);
        int toyDropFrequency = dropFrequencyScanner.nextInt();
        Toy newToy = new Toy(toys.getToys().size() + 1, toyName, toyQuantity, toyDropFrequency);
        toys.getToys().add(newToy);
        System.out.println(toys);
    }

    public List<Toy> sortByDropFrequency(List<Toy> toys) {
        for (int i = 0; i < toys.size() - 1; i++) {
            for (int j = 0; j < toys.size() - 1; j++) {
                if (toys.get(j).getDropFrequency() < toys.get(j + 1).getDropFrequency()) {
                    Toy buf = toys.get(j);
                    Toy temp = toys.get(j + 1);
                    toys.remove(buf);
                    toys.add(j + 1, buf);
                }
            }

        }
        return toys;
    }

    public List<Toy> choicePrizeToy() {
        List<Toy> prizeToys = new LinkedList<>();
        var sortToys = sortByDropFrequency(toys.getToys());
        if (sortToys.get(0).getQuantity() > 0) {
            Toy prizeToy = new Toy(prizeToys.size() + 1, sortByDropFrequency(sortToys).get(0).getToyName(),
                    1,
                    sortByDropFrequency(sortToys).get(0).getDropFrequency());
            if (prizeToys.contains(prizeToy)) {
                prizeToy.setQuantity(prizeToy.getQuantity() + 1);
            }
            prizeToys.add(prizeToy);
            sortToys.get(0).setQuantity(sortToys.get(0).getQuantity() - 1);
            if (sortToys.get(0).getQuantity() == 0) {
                sortToys.remove(sortToys.get(0));
            }
        } else {
            System.out.println("Игрушки закончились!");
        }
        return prizeToys;
    }

    public Toy getPrizeToy() {
        var prizeToys = choicePrizeToy();
        System.out.println("Список выигранных игрушек: ");
        for (Toy toy : prizeToys) {
            System.out.printf("Id: %d; Название: %s\n", toy.getId(), toy.getToyName());
        }
        System.out.println("Выберите по id игрушку, которую хотите получить: ");
        Scanner idScanner = new Scanner(System.in);
        Toy prizeToy = prizeToys.get(idScanner.nextInt() - 1);
        try (FileWriter fw = new FileWriter("prizes.txt")) {
            fw.write(prizeToy.toString());
        } catch (IOException e) {
            e.getStackTrace();
        }
        if (prizeToy.getQuantity() > 0) {
            prizeToy.setQuantity(prizeToy.getQuantity() - 1);
        } else {
            prizeToys.remove(idScanner.nextInt() - 1);
        }
        return prizeToy;
    }

    public ToysCollection getToys() {
        return toys;
    }

}
