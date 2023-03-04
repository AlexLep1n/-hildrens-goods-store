package Model;

public class Toy {
    private int id;
    private String toyName;
    private int quantity;
    private int dropFrequency;

    public Toy(int id, String toyName, int quantity, int dropFrequency) {
        this.id = id;
        this.toyName = toyName;
        this.quantity = quantity;
        if (dropFrequency < 0 || dropFrequency > 100) {
            System.out.println("Введите число от 0 до 100");
        }
        this.dropFrequency = dropFrequency;
    }

    public int getId() {
        return id;
    }

    public String getToyName() {
        return toyName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getDropFrequency() {
        return dropFrequency;
    }

    @Override
    public String toString() {
        return "Id: " + id + ", Название: " + toyName + ", Количество: " + quantity + ", Частота выпадения: "
                + dropFrequency + "%";
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
