package generator;

import java.util.Random;

public class ForestedEquatorsPlantGenerationVariant extends AbstractPlantGenerationVariant{
    private final Integer yDownLimit;
    private final Integer yUpLimit;
    public ForestedEquatorsPlantGenerationVariant(Integer width, Integer height) {
        super(width * height);
        Integer downLimit = (height - 1) / 2;
        Integer upLimit = downLimit + 1;
        int counter = 0;
        while (((float)(upLimit - downLimit) * (float)width / ((float)width * (float)height)) < 0.2){
            if (counter % 2 == 0){
                upLimit++;
            } else {
                downLimit--;
            }
            counter++;
        }
        this.yDownLimit = downLimit;
        this.yUpLimit = upLimit;
    }

    @Override
    public void spawnPlants(Integer quantity) {
        if (this.map.getNumberOfPlants() + quantity > this.maxNumberOfPlants){
            quantity = this.maxNumberOfPlants - this.map.getNumberOfPlants();
        }
        Random random = new Random();
        for (int i = 0; i < quantity; i++) {
            Vector2d newPosition = new Vector2d(0, 0);
            do {
                if (Math.random() >= 0.2) {
                    newPosition.x = random.nextInt(this.map.getWidth());
                    newPosition.y = random.nextInt(this.yUpLimit - this.yDownLimit) + this.yDownLimit;
                } else {
                    newPosition.x = random.nextInt(this.map.getWidth());
                    newPosition.y = random.nextInt(this.map.getHeight() - (this.yUpLimit - this.yDownLimit));
                    if (newPosition.y >= this.yDownLimit) {
                        newPosition.y += this.yUpLimit - this.yDownLimit;
                    }
                }
            } while (this.map.isOccupiedByPlant(newPosition));
            this.map.place(new Plant(newPosition));
        }
    }
}
