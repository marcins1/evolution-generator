package generator;

import java.util.Random;

public class BlisteringPortalMapVariant extends AbstractMapVariant{
    private final Integer energyToRemove;
    public BlisteringPortalMapVariant(Integer mapHeight, Integer mapWidth, Integer energyToRemove) {
        super(mapHeight, mapWidth);
        this.energyToRemove = energyToRemove;
    }

    @Override
    public void setNewAnimalStatistics(Animal animal, Vector2d newPosition) {
        if (newPosition.x < 0 || newPosition.x >= this.width || newPosition.y < 0 || newPosition.y >= this.height){
            Random random = new Random();
            newPosition.x = random.nextInt(this.width);
            newPosition.y = random.nextInt(this.height);
            animal.setEnergy(animal.getEnergy() - this.energyToRemove);
        }
        animal.setPosition(newPosition);
    }
}
