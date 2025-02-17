import java.util.LinkedList;
import java.util.Queue;

class animal {
    String type;
    int arrival;

    public animal(String type, int arrival) {
        this.type = type;
        this.arrival = arrival;
    }
}

class shelter {
    Queue<animal> dogs = new LinkedList<>();
    Queue<animal> cats = new LinkedList<>();
    int order = 0;

    public void enqueue(String type) {
        order++;
        animal newAnimal = new animal(type, order);

        if (type.equals("dog")) {
            dogs.add(newAnimal);
        } else if (type.equals("cat")) {
            cats.add(newAnimal);
        } else {
            System.out.println("only dogs and cats allowed");
        }
    }

    public animal dequeueAny() {
        if (dogs.isEmpty() && cats.isEmpty()) {
            return null;
        }

        if (dogs.isEmpty()) {
            return dequeueCat();
        }

        if (cats.isEmpty()) {
            return dequeueDog();
        }

        if (dogs.peek().arrival < cats.peek().arrival) {
            return dequeueDog();
        } else {
            return dequeueCat();
        }
    }

    public animal dequeueDog() {
        if (dogs.isEmpty()) {
            return null;
        }
        return dogs.poll();
    }

    public animal dequeueCat() {
        if (cats.isEmpty()) {
            return null;
        }
        return cats.poll();
    }
}

public class demo {
    public static void main(String[] args) {
        shelter s = new shelter();

        s.enqueue("dog");
        s.enqueue("cat");
        s.enqueue("dog");
        s.enqueue("cat");

        animal adoptedAny = s.dequeueAny();
        System.out.println("adopted: " + (adoptedAny != null ? adoptedAny.type + adoptedAny.arrival : "none"));

        animal adoptedDog = s.dequeueDog();
        System.out.println("adopted dog: " + (adoptedDog != null ? adoptedDog.type + adoptedDog.arrival : "none"));

        animal adoptedCat = s.dequeueCat();
        System.out.println("adopted cat: " + (adoptedCat != null ? adoptedCat.type + adoptedCat.arrival : "none"));
    }
}
