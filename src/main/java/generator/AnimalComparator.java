package generator;

import java.util.Comparator;

public class AnimalComparator implements Comparator<Animal> {
    @Override
    public int compare(Animal firstAnimal, Animal secondAnimal) {
        if (firstAnimal.getEnergy() - secondAnimal.getEnergy() != 0){
            return Integer.compare(firstAnimal.getEnergy(), secondAnimal.getEnergy());
        }
        if (firstAnimal.getDaysAlive() - secondAnimal.getDaysAlive() != 0){
            return Integer.compare(firstAnimal.getDaysAlive(), secondAnimal.getDaysAlive());
        }
        if (firstAnimal.getNumberOfChilds() - secondAnimal.getNumberOfChilds() != 0){
            return Integer.compare(firstAnimal.getNumberOfChilds(), secondAnimal.getNumberOfChilds());
        }
        return firstAnimal.getID() - secondAnimal.getID();
    }
}

