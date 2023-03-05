package Model;

import java.util.List;

public class ToysCollection {
    private List<Toy> toys;

    public ToysCollection(List<Toy> toys) {
        this.toys = toys;
    }

    public List<Toy> getToys() {
        return toys;
    }

    public int numberOfToys(List<Toy> toys) {
        return toys.size();
    }

    public Toy getIndex(int index) {
        return toys.get(index);
    }

    @Override
    public String toString() {
        return "Выигранные игрушки:\n" + toys;
    }
}
