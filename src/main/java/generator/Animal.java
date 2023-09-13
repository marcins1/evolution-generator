package generator;

import java.util.Objects;
import java.util.Random;

public class Animal extends AbstractWorldMapElement{
    private MapDirection direction;
    private final Map map;
    private Integer energy;
    private Integer numberOfChilds;
    private boolean isAlive;
    private Integer daysAlive;
    private final IBehaviorVariant behaviorVariant;
    private final IMutationVariant mutationVariant;
    private final Integer identificationNumber;

    public Animal(Map map, Vector2d initialPosition, Integer initialEnergy, IBehaviorVariant behaviorVariant,
                  IMutationVariant mutationVariant){
        super(initialPosition);
        Random random = new Random();
        this.direction = MapDirection.NORTH.rotate(random.nextInt(8));
        this.map = map;
        this.energy = initialEnergy;
        this.numberOfChilds = 0;
        this.behaviorVariant = behaviorVariant;
        this.mutationVariant = mutationVariant;
        this.daysAlive = 0;
        this.isAlive = true;
        this.identificationNumber = this.map.getNextIdentificationNumber();
    }

    public MapDirection getDirection(){
        return direction;
    }
    public void setDirection(MapDirection newDirection){
        this.direction = newDirection;
    }
    public Integer getEnergy(){
        return this.energy;
    }
    public void setEnergy(Integer newEnergy){
        this.energy = newEnergy;
    }
    public Integer getDaysAlive(){
        return this.daysAlive;
    }
    public Integer getNumberOfChilds(){
        return this.daysAlive;
    }
    public Integer[] getGenes(){
        return this.behaviorVariant.getGenes();
    }
    public Integer getID(){
        return this.identificationNumber;
    }

    public Animal reproduce(Animal secondAnimal){
        if (this.energy >= this.map.getEnergyNeededToBeFull() && secondAnimal.getEnergy() >= this.map.getEnergyNeededToBeFull()){
            Integer[] genes1 = this.getGenes();
            Integer[] genes2 = secondAnimal.getGenes();
            int genomeLength = genes1.length;
            Random random = new Random();
            int side = random.nextInt(2);
            int cut;
            Integer[] newGenes = new Integer[genomeLength];
            cut = (int)((float)genomeLength * ((float)this.energy / (float)(this.energy + secondAnimal.getEnergy())));
            int i;
            if (side == 0){
                for (i = 0; i < cut; i++){
                    newGenes[i] = genes1[i];
                }
                while (i < genomeLength){
                    newGenes[i] = genes2[i];
                    i++;
                }
            } else {
                for (i = 0; i < genomeLength - cut; i++){
                    newGenes[i] = genes2[i];
                }
                while (i < genomeLength){
                    newGenes[i] = genes1[i];
                    i++;
                }
            }
            int energyNeededToCreateOffspring = this.map.getEnergyNeededToCreateOffspring();
            int energyToDelete = (int)((float)energyNeededToCreateOffspring * ((float)this.energy / ((float)this.energy + (float)secondAnimal.getEnergy())));
            this.energy -= energyToDelete;
            secondAnimal.setEnergy(secondAnimal.getEnergy() - (energyNeededToCreateOffspring - energyToDelete));
            this.incrementNumberOfChilds();
            secondAnimal.incrementNumberOfChilds();
            this.checkIfAlive();
            secondAnimal.checkIfAlive();
            this.mutationVariant.mutateTheGenes(newGenes, this.map.getMinNumberOfMutations(), this.map.getMaxNumberOfMutations());
            IBehaviorVariant newAnimalBehaviorVariant = new FullPredestinationBehaviorVariant(newGenes);
            return new Animal(this.map, this.position, energyNeededToCreateOffspring, newAnimalBehaviorVariant, this.mutationVariant);
        } else {
            return null;
        }
    }

    public void move(){
        this.daysAlive++;
        this.direction = this.direction.rotate(this.behaviorVariant.getNextRotation());
        Vector2d oldPosition = this.position;
        this.map.variant.setNewAnimalStatistics(this, this.position.add(this.direction.toUnitVector()));
        positionChanged(oldPosition);
        this.checkIfAlive();
    }

    public void checkIfAlive(){
        if (this.energy <= 0){
            this.isAlive = false;
            this.removeObserver(this.map);
            this.map.addAnimalToRemove(this);
        }
    }

    public void incrementNumberOfChilds(){
        this.numberOfChilds++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(identificationNumber, animal.identificationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificationNumber);
    }
}
