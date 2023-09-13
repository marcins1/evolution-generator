package generator;

public enum MapDirection {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;

    @Override
    public String toString() {
        return switch (this){
            case NORTH -> "Polnoc";
            case NORTHEAST -> "Polnocny wschod";
            case EAST -> "Wschod";
            case SOUTHEAST -> "Poludniowy wschod";
            case SOUTH -> "Poludnie";
            case SOUTHWEST -> "Poludniowy zachod";
            case WEST -> "Zachod";
            case NORTHWEST -> "Polnocny zachod";
        };
    }

    public int toNumber(){
        return switch (this){
            case NORTH -> 0;
            case NORTHEAST -> 1;
            case EAST -> 2;
            case SOUTHEAST -> 3;
            case SOUTH -> 4;
            case SOUTHWEST -> 5;
            case WEST -> 6;
            case NORTHWEST -> 7;
        };
    }

    public MapDirection rotate(int value){
        int newValue = (this.toNumber() + value) % 8;
        return switch (newValue){
            case 0 -> NORTH;
            case 1 -> NORTHEAST;
            case 2 -> EAST;
            case 3 -> SOUTHEAST;
            case 4 -> SOUTH;
            case 5 -> SOUTHWEST;
            case 6 -> WEST;
            default -> NORTHWEST;
        };
    }

    public Vector2d toUnitVector(){
        return switch (this){
            case NORTH -> new Vector2d(0, 1);
            case NORTHEAST -> new Vector2d(1, 1);
            case EAST -> new Vector2d(1, 0);
            case SOUTHEAST -> new Vector2d(1, -1);
            case SOUTH -> new Vector2d(0, -1);
            case SOUTHWEST -> new Vector2d(-1, -1);
            case WEST -> new Vector2d(-1, 0);
            case NORTHWEST -> new Vector2d(-1, 1);
        };
    }
}
