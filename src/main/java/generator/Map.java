package generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;

public class Map implements IPositionChangeObserver {
    private final HashMap<Vector2d, HashSet<Animal>> animals;
    private final HashSet<Animal> aliveAnimals;
    private final ArrayList<Animal> deadAnimals;
    private final HashMap<Vector2d, Plant> plants;
    private final ArrayList<Animal> animalsToRemove;
    private final ArrayList<Plant> plantsToRemove;
    private final Integer height;
    private final Integer width;
    private Integer numberOfAnimals;
    private Integer numberOfDeadAnimals;
    private Integer numberOfPlants;
    private final Integer plantEnergy;
    public final IMapVariant variant;
    public final IPlantGenerationVariant plantGenerationVariant;
    private final Integer energyNeededToBeFull;
    private final Integer energyNeededToCreateOffspring;
    private final Integer minNumberOfMutations;
    private final Integer maxNumberOfMutations;
    private Integer nextAnimalID;
    private Integer daysAliveSum;

    public Map(Integer width, Integer height, IMapVariant variant, Integer plantEnergy,
               IPlantGenerationVariant plantGenerationVariant, Integer energyNeededToBeFull,
               Integer energyNeededToCreateOffspring, Integer genomeLength, Integer minNumberOfMutations,
               Integer maxNumberOfMutations){
        this.width = width;
        this.height = height;
        this.animals = new HashMap<Vector2d, HashSet<Animal>>();
        this.aliveAnimals = new HashSet<Animal>();
        this.deadAnimals = new ArrayList<Animal>();
        this.plants = new HashMap<Vector2d, Plant>();
        this.variant = variant;
        this.numberOfAnimals = 0;
        this.numberOfPlants = 0;
        this.plantEnergy = plantEnergy;
        this.plantGenerationVariant = plantGenerationVariant;
        this.energyNeededToBeFull = energyNeededToBeFull;
        this.energyNeededToCreateOffspring = energyNeededToCreateOffspring;
        this.minNumberOfMutations = minNumberOfMutations;
        this.maxNumberOfMutations = maxNumberOfMutations;
        this.nextAnimalID = 0;
        this.plantsToRemove = new ArrayList<Plant>();
        this.animalsToRemove = new ArrayList<Animal>();
        this.numberOfDeadAnimals = 0;
        this.daysAliveSum = 0;
    }

    public HashMap<Vector2d, HashSet<Animal>> getAnimals(){
        return this.animals;
    }
    public HashSet<Animal> getAliveAnimals(){
        return this.aliveAnimals;
    }
    public HashMap<Vector2d, Plant> getPlants(){
        return this.plants;
    }
    public Integer getHeight(){
        return this.height;
    }
    public Integer getWidth(){
        return this.width;
    }
    public Integer getNumberOfPlants(){
        return this.numberOfPlants;
    }
    public Integer getEnergyNeededToBeFull(){
        return this.energyNeededToBeFull;
    }
    public Integer getEnergyNeededToCreateOffspring(){
        return this.energyNeededToCreateOffspring;
    }
    public Integer getMinNumberOfMutations(){
        return this.minNumberOfMutations;
    }
    public Integer getMaxNumberOfMutations(){
        return this.maxNumberOfMutations;
    }
    public Integer getNumberOfAnimals(){
        return this.numberOfAnimals;
    }
    public Integer getFreeFields(){
        return this.width * this.height - this.animals.size() - this.numberOfPlants;
    }
    public Integer getAverageAnimalsEnergy(){
        AtomicReference<Integer> sum = new AtomicReference<>(0);
        this.aliveAnimals.forEach((animal -> {
            sum.updateAndGet(v -> v + animal.getEnergy());
        }));
        if (this.numberOfAnimals != 0){
            return sum.get() / this.numberOfAnimals;
        }
        return 0;
    }
    public Integer getAverageDaysAlive(){
        if (this.numberOfDeadAnimals != 0){
            return this.daysAliveSum / this.numberOfDeadAnimals;
        }
        return 0;
    }
    public Integer getNextIdentificationNumber(){
        this.nextAnimalID++;
        return this.nextAnimalID - 1;
    }

    public void place(Animal animal){
        Vector2d position = animal.getPosition();
        if (isInBorders(position)){
            animal.addObserver(this);
            if (animals.get(position) == null){
                animals.put(position, new HashSet<>());
            }
            this.animals.get(position).add(animal);
            this.aliveAnimals.add(animal);
            this.numberOfAnimals++;
        } else {
            throw new IllegalArgumentException("Can't place animal on position: " + position);
        }
    }

    public void place(Plant plant){
        Vector2d position = plant.getPosition();
        if (isInBorders(position) && !isOccupiedByPlant(position)){
            this.plants.put(position, plant);
            this.numberOfPlants++;
        } else {
            throw new IllegalArgumentException("Can't place plant on position: " + position);
        }
    }

    public void removePlants(){
        this.plantsToRemove.forEach(plant -> {
            this.plants.remove(plant.getPosition());
        });
        this.plantsToRemove.clear();
    }

    public void removeDeadAnimals(){
        this.animalsToRemove.forEach(animal -> {
            this.animals.get(animal.getPosition()).remove(animal);
            this.aliveAnimals.remove(animal);
            if (this.animals.get(animal.getPosition()).size() == 0){
                this.animals.remove(animal.getPosition());
            }
            this.numberOfAnimals--;
            this.numberOfDeadAnimals++;
            this.daysAliveSum += animal.getDaysAlive();
        });
        this.animalsToRemove.clear();
    }

    public boolean isInBorders(Vector2d position){
        return position.x >= 0 && position.x < this.width && position.y >= 0 && position.y < this.height;
    }

    public boolean isOccupiedByPlant(Vector2d position){
        return plants.get(position) != null;
    }

    public void tryToEatPlant(Plant plant){
        if (this.animals.get(plant.getPosition()) != null){
            TreeSet<Animal> animals = getSortedAnimals(plant.getPosition());
            Animal animal = animals.last();
            animal.setEnergy(animal.getEnergy() + this.plantEnergy);
            this.plantsToRemove.add(plant);
            this.numberOfPlants--;
        }
    }

    public TreeSet<Animal> getSortedAnimals(Vector2d position){
        if (this.animals.get(position) != null){
            TreeSet<Animal> sorted = new TreeSet<Animal>(new AnimalComparator());
            sorted.addAll(this.animals.get(position));
            return sorted;
        }
        return null;
    }

    public void reproduce(){
        this.animals.forEach((key, value) -> {
            TreeSet<Animal> animals = getSortedAnimals(key);
            Animal animal1 = animals.pollLast();
            Animal animal2 = animals.pollLast();
            if (animal2 != null){
                Animal newAnimal = animal1.reproduce(animal2);
                if (newAnimal != null){
                    place(newAnimal);
                }
            }
        });
    }

    public void addAnimalToRemove(Animal animal){
        this.animalsToRemove.add(animal);
    }

    @Override
    public void positionChanged(IMapElement mapElement, Vector2d oldPosition){
        Animal animal = (Animal) mapElement;
        this.animals.get(oldPosition).remove(animal);
        if (this.animals.get(oldPosition).size() == 0){
            this.animals.remove(oldPosition);
        }
        if (animals.get(animal.getPosition()) == null){
            animals.put(animal.getPosition(), new HashSet<Animal>());
        }
        this.animals.get(animal.getPosition()).add(animal);
    }
}
