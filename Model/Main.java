package Model;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ToysCollection toys = new ToysCollection(new LinkedList<>(
                List.of(new Toy(1, "Покемон", 1, 25), new Toy(2, "Машинка", 2, 50), new Toy(3, "Барби", 3, 75))));

        // System.out.println(toys.getToys());
        Model model = new Model(toys);
        // model.addToy();
        // System.out.println(model.sortByDropFrequency(toys.getToys()));
        // System.out.println(model.choicePrizeToy());
        // System.out.println(model.getToys().getToys());
        model.getPrizeToy();
    }
}