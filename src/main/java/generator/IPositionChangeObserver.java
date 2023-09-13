package generator;

public interface IPositionChangeObserver {
    void positionChanged(IMapElement mapElement, Vector2d oldPosition);
}
