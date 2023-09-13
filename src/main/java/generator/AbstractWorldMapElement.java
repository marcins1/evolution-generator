package generator;

import java.util.ArrayList;

abstract class AbstractWorldMapElement implements IMapElement {
    protected Vector2d position;
    protected ArrayList<IPositionChangeObserver> observers;

    public AbstractWorldMapElement(Vector2d position){
        this.position = position;
        this.observers = new ArrayList<IPositionChangeObserver>();
    }

    public Vector2d getPosition(){
        return this.position;
    }

    public void setPosition(Vector2d newPosition){
        this.position = newPosition;
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    void addObserver(IPositionChangeObserver observer){
        this.observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer){
        this.observers.remove(observer);
    }

    void positionChanged(Vector2d oldPosition){
        for (IPositionChangeObserver observer:this.observers) {
            observer.positionChanged(this, oldPosition);
        }
    }
}
