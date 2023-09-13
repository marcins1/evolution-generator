package generator;

public class GlobeMapVariant extends AbstractMapVariant{
    public GlobeMapVariant(Integer mapHeight, Integer mapWidth) {
        super(mapHeight, mapWidth);
    }

    @Override
    public void setNewAnimalStatistics(Animal animal, Vector2d newPosition) {
        if (newPosition.x < 0){
            newPosition.x = this.width - 1;
        } else if (newPosition.x >= this.width){
            newPosition.x = 0;
        }
        if (newPosition.y < 0){
            newPosition.y = 0;
            animal.setDirection(animal.getDirection().rotate(4));
        } else if (newPosition.y >= this.height){
            newPosition.y = this.height - 1;
            animal.setDirection(animal.getDirection().rotate(4));
        }
        animal.setPosition(newPosition);
        animal.setEnergy(animal.getEnergy() - 1);
    }
}
