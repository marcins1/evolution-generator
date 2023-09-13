package generator.gui;

import generator.Animal;
import generator.IMapElement;
import generator.Map;
import generator.Vector2d;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Simulation {
    private final GridPane grid;
    private Map map;
    private final Integer cellSize;
    private final Text animalNumber;
    private final Text plantNumber;
    private final Text freeFields;
    private final Text averageDaysAlive;
    private final Text averageEnergy;

    public Simulation(GridPane gridPane, int width, int height, Text animalNumber, Text plantNumber, Text freeFields, Text averageEnergy, Text averageDaysAlive){
        this.grid = gridPane;
        this.map = null;
        if (width >= height){
            this.cellSize = 650 / width;
        } else {
            this.cellSize = 650 / height;
        }
        this.animalNumber = animalNumber;
        this.plantNumber = plantNumber;
        this.freeFields = freeFields;
        this.averageDaysAlive = averageDaysAlive;
        this.averageEnergy = averageEnergy;
    }

    public void setMap(Map map){
        this.map = map;
    }

    private VBox getGuiElementBox(IMapElement mapElement) {
        VBox vbox = new VBox();
        if (mapElement instanceof Animal){
            Circle circle = new Circle(this.cellSize, this.cellSize, (float)(this.cellSize / 2));
            if (((Animal) mapElement).getEnergy() >= this.map.getEnergyNeededToBeFull()){
                circle.setFill(Color.RED);
            } else {
                circle.setFill(Color.LIGHTPINK);
            }
            vbox.getChildren().add(circle);
        } else {
            Rectangle rectangle = new Rectangle(this.cellSize, this.cellSize);
            rectangle.setFill(Color.GREEN);
            vbox.getChildren().add(rectangle);
        }
        return vbox;
    }

    public void draw() {
        this.grid.setGridLinesVisible(false);
        this.grid.setGridLinesVisible(true);
        for (int i = 0; i < this.map.getWidth(); i++){
            this.grid.getColumnConstraints().add(new ColumnConstraints(this.cellSize));
        }
        for (int i = 0; i < this.map.getHeight(); i++){
            this.grid.getRowConstraints().add(new RowConstraints(this.cellSize));
        }
        this.map.getPlants().forEach(((position, plant) -> {
            this.grid.add(getGuiElementBox(plant), position.x, position.y);
        }));
        this.map.getAliveAnimals().forEach((animal -> {
            Vector2d animalPosition = animal.getPosition();
            this.grid.add(getGuiElementBox(animal), animalPosition.x, animalPosition.y);
        }));
    }

    public void clearGrid() {
        this.grid.getRowConstraints().clear();
        this.grid.getColumnConstraints().clear();
        this.grid.getChildren().clear();
    }

    public void updateStatistics(){
        this.animalNumber.setText(this.map.getNumberOfAnimals().toString());
        this.plantNumber.setText(this.map.getNumberOfPlants().toString());
        this.freeFields.setText(this.map.getFreeFields().toString());
        this.averageDaysAlive.setText(this.map.getAverageDaysAlive().toString());
        this.averageEnergy.setText(this.map.getAverageAnimalsEnergy().toString());
    }
}