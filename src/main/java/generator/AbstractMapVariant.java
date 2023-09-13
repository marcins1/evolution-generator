package generator;

abstract class AbstractMapVariant implements IMapVariant{
    protected Integer height;
    protected Integer width;

    public AbstractMapVariant(Integer mapHeight, Integer mapWidth){
        this.height = mapHeight;
        this.width = mapWidth;
    }
}
