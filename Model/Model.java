package Model;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Model {
    private ToysCollection toys;
    private FileWriterToFileTxt<Toy> fwTxt;
    List<Toy> prizeToys = new LinkedList<>();

    public Model(ToysCollection toys, FileWriterToFileTxt<Toy> fwTxt) {
        this.toys = toys;
        this.fwTxt = fwTxt;
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
        var sortToys = sortByDropFrequency(toys.getToys());
        if (sortToys.get(0).getQuantity() > 0) {
            Toy prizeToy = new Toy(prizeToys.size() + 1, sortByDropFrequency(sortToys).get(0).getToyName(),
                    1,
                    sortByDropFrequency(sortToys).get(0).getDropFrequency());
            if (prizeToys.size() != 0) {
                for (int i = 0; i < prizeToys.size(); i++) {
                    if (prizeToys.get(i).getToyName().equals(prizeToy.getToyName())) {
                        prizeToys.get(i).setQuantity(prizeToys.get(i).getQuantity() + 1);
                    } else {
                        prizeToys.add(prizeToy);
                        break;
                    }
                }
            } else {
                prizeToys.add(prizeToy);
            }
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
        System.out.println("Список выигранных игрушек: ");
        for (Toy toy : prizeToys) {
            System.out.printf("Id: %d; Название: %s, Количество: %d\n", toy.getId(), toy.getToyName(),
                    toy.getQuantity());
        }
        System.out.printf("Выберите по id игрушку, которую хотите получить: ");
        Scanner idScanner = new Scanner(System.in);
        Toy prizeToy = prizeToys.get(idScanner.nextInt() - 1);
        fwTxt.writeToFileTxt("prizes.txt", prizeToy);
        if (prizeToy.getQuantity() > 0) {
            prizeToy.setQuantity(prizeToy.getQuantity() - 1);
            return prizeToy;
        } else {
            prizeToys.remove(idScanner.nextInt() - 1);
            return prizeToy;
        }
    }

    public void showToys() {
        for (Toy toy : toys.getToys()) {
            System.out.println(toy);
        }
    }

    public ToysCollection getToys() {
        return toys;
    }

    public FileWriterToFileTxt<Toy> getFwTxt() {
        return fwTxt;
    }

    public List<Toy> getPrizeToys() {
        return prizeToys;
    }

}
