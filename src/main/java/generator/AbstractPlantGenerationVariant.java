package generator;

abstract class AbstractPlantGenerationVariant implements IPlantGenerationVariant{
    protected Map map;
    protected final Integer maxNumberOfPlants;
    public AbstractPlantGenerationVariant(Integer maxNumberOfPlants){
        this.map = null;
        this.maxNumberOfPlants = maxNumberOfPlants;
    }

    public void setMap(Map map){
        this.map = map;
    }
}
