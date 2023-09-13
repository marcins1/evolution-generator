package generator.gui;

import generator.SimulationEngine;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        int cellWidth = 250;

        Label variableLabel = new Label("Simulation variables");

        Label widthLabel = new Label("Width");
        widthLabel.setMinWidth(cellWidth);
        TextField widthField = new TextField("30");
        HBox widthBox = new HBox(widthLabel, widthField);
        widthBox.setAlignment(Pos.CENTER);

        Label heightLabel = new Label("Height");
        heightLabel.setMinWidth(cellWidth);
        TextField heightField = new TextField("30");
        HBox heightBox = new HBox(heightLabel, heightField);
        heightBox.setAlignment(Pos.CENTER);

        Label mapVariantLabel = new Label("Map variant");
        mapVariantLabel.setMinWidth(cellWidth);
        ChoiceBox<String> choiceMapVariant = new ChoiceBox<>();
        choiceMapVariant.setMinWidth(150);
        choiceMapVariant.getItems().addAll("Globe", "Blistering portal");
        choiceMapVariant.setValue("Globe");
        HBox mapVariantBox = new HBox(mapVariantLabel, choiceMapVariant);
        mapVariantBox.setAlignment(Pos.CENTER);

        Label plantsLabel = new Label("Starting number of plants");
        plantsLabel.setMinWidth(cellWidth);
        TextField plantsField = new TextField("10");
        HBox plantsBox = new HBox(plantsLabel, plantsField);
        plantsBox.setAlignment(Pos.CENTER);

        Label plantEnergyLabel = new Label("Energy provided by eating one plant");
        plantEnergyLabel.setMinWidth(cellWidth);
        TextField plantEnergyField = new TextField("30");
        HBox plantEnergyBox = new HBox(plantEnergyLabel, plantEnergyField);
        plantEnergyBox.setAlignment(Pos.CENTER);

        Label plantNumberLabel = new Label("Number of plants growing each day");
        plantNumberLabel.setMinWidth(cellWidth);
        TextField plantNumberField = new TextField("5");
        HBox plantNumberBox = new HBox(plantNumberLabel, plantNumberField);
        plantNumberBox.setAlignment(Pos.CENTER);

        Label plantVariantLabel = new Label("Plant growth variant");
        plantVariantLabel.setMinWidth(cellWidth);
        ChoiceBox<String> choicePlantVariant = new ChoiceBox<>();
        choicePlantVariant.setMinWidth(150);
        choicePlantVariant.getItems().addAll("Forested equators", "Toxic corpses");
        choicePlantVariant.setValue("Forested equators");
        HBox plantVariantBox = new HBox(plantVariantLabel, choicePlantVariant);
        plantVariantBox.setAlignment(Pos.CENTER);

        Label animalNumberLabel = new Label("Starting number of animals");
        animalNumberLabel.setMinWidth(cellWidth);
        TextField animalNumberField = new TextField("10");
        HBox animalNumberBox = new HBox(animalNumberLabel, animalNumberField);
        animalNumberBox.setAlignment(Pos.CENTER);

        Label animalEnergyLabel = new Label("Animal starting energy");
        animalEnergyLabel.setMinWidth(cellWidth);
        TextField animalEnergyField = new TextField("20");
        HBox animalEnergyBox = new HBox(animalEnergyLabel, animalEnergyField);
        animalEnergyBox.setAlignment(Pos.CENTER);

        Label fullEnergyLabel = new Label("Energy needed to consider the animal as full (and ready to breed)");
        fullEnergyLabel.setMinWidth(cellWidth);
        fullEnergyLabel.setMaxWidth(cellWidth);
        fullEnergyLabel.setMinHeight(40);
        fullEnergyLabel.setWrapText(true);
        TextField fullEnergyField = new TextField("10");
        HBox fullEnergyBox = new HBox(fullEnergyLabel, fullEnergyField);
        fullEnergyBox.setAlignment(Pos.CENTER);

        Label offspringEnergyLabel = new Label("Energy of the parents expended to create the offspring");
        offspringEnergyLabel.setMinWidth(cellWidth);
        offspringEnergyLabel.setMaxWidth(cellWidth);
        offspringEnergyLabel.setMinHeight(40);
        offspringEnergyLabel.setWrapText(true);
        TextField offspringEnergyField = new TextField("10");
        HBox offspringEnergyBox = new HBox(offspringEnergyLabel, offspringEnergyField);
        offspringEnergyBox.setAlignment(Pos.CENTER);

        Label minimalMutationsLabel = new Label("Minimal number of mutations");
        minimalMutationsLabel.setMinWidth(cellWidth);
        TextField minimalMutationsField = new TextField("0");
        HBox minimalMutationsBox = new HBox(minimalMutationsLabel, minimalMutationsField);
        minimalMutationsBox.setAlignment(Pos.CENTER);

        Label maximumMutationsLabel = new Label("Maximum number of mutations");
        maximumMutationsLabel.setMinWidth(cellWidth);
        TextField maximumMutationsField = new TextField("3");
        HBox maximumMutationsBox = new HBox(maximumMutationsLabel, maximumMutationsField);
        maximumMutationsBox.setAlignment(Pos.CENTER);

        Label mutationVariantLabel = new Label("Mutation variant");
        mutationVariantLabel.setMinWidth(cellWidth);
        ChoiceBox<String> choiceMutationVariant = new ChoiceBox<>();
        choiceMutationVariant.setMinWidth(150);
        choiceMutationVariant.getItems().addAll("Full randomness", "Slight correction");
        choiceMutationVariant.setValue("Full randomness");
        HBox mutationVariantBox = new HBox(mutationVariantLabel, choiceMutationVariant);
        mutationVariantBox.setAlignment(Pos.CENTER);

        Label genomeLengthLabel = new Label("Animal genome length");
        genomeLengthLabel.setMinWidth(cellWidth);
        TextField genomeLengthField = new TextField("10");
        HBox genomeLengthBox = new HBox(genomeLengthLabel, genomeLengthField);
        genomeLengthBox.setAlignment(Pos.CENTER);

        Label animalBehaviorLabel = new Label("Variant of animal behavior");
        animalBehaviorLabel.setMinWidth(cellWidth);
        ChoiceBox<String> choiceAnimalBehavior = new ChoiceBox<>();
        choiceAnimalBehavior.setMinWidth(150);
        choiceAnimalBehavior.getItems().addAll("Full predestination", "A bit of madness");
        choiceAnimalBehavior.setValue("Full predestination");
        HBox animalBehaviorBox = new HBox(animalBehaviorLabel, choiceAnimalBehavior);
        animalBehaviorBox.setAlignment(Pos.CENTER);


        VBox variables = new VBox(variableLabel, widthBox, heightBox, mapVariantBox, plantsBox, plantEnergyBox, plantNumberBox,
                plantVariantBox, animalNumberBox, animalEnergyBox, fullEnergyBox, offspringEnergyBox, minimalMutationsBox,
                maximumMutationsBox, mutationVariantBox, genomeLengthBox, animalBehaviorBox);
        variables.setAlignment(Pos.CENTER);
        variables.setSpacing(10);

        Button button = new Button("Run simulation");
        button.setMaxWidth(150);

        VBox applicationBox = new VBox(variables, button);
        applicationBox.setAlignment(Pos.CENTER);
        applicationBox.setSpacing(20);

        Scene scene = new Scene(applicationBox, 600, 700);
        primaryStage.setScene(scene);
        primaryStage.show();

        button.setOnAction(e -> {
            openNewSimulation(Integer.parseInt(widthField.getText()), Integer.parseInt(heightField.getText()), choiceMapVariant.getValue(),
                    Integer.parseInt(plantsField.getText()), Integer.parseInt(plantEnergyField.getText()), Integer.parseInt(plantNumberField.getText()),
                    choicePlantVariant.getValue(), Integer.parseInt(animalNumberField.getText()), Integer.parseInt(animalEnergyField.getText()),
                    Integer.parseInt(fullEnergyField.getText()), Integer.parseInt(offspringEnergyField.getText()), Integer.parseInt(minimalMutationsField.getText()),
                    Integer.parseInt(maximumMutationsField.getText()), choiceMutationVariant.getValue(), Integer.parseInt(genomeLengthField.getText()),
                    choiceAnimalBehavior.getValue());
        });
    }

    public void openNewSimulation(Integer width, Integer height, String mapVariant, Integer startingNumberOfPlants,
                                  Integer energyProvidedByEating, Integer numberOfPlantsGrowingEachDay,
                                  String plantGenerationVariant, Integer startingNumberOfAnimals,
                                  Integer startingNumberOfAnimalsEnergy, Integer energyNeededToBeFull,
                                  Integer energyNeededToCreateOffspring, Integer minNumberOfMutations,
                                  Integer maxNumberOfMutations, String mutationVariant, Integer genomeLength,
                                  String behaviorVariant){
        Stage stage = new Stage();

        Text animalNumber1 = new Text("Animals alive:");
        animalNumber1.setWrappingWidth(150);
        Text animalNumber2 = new Text("0");
        animalNumber2.setWrappingWidth(50);
        HBox animalNumberBox = new HBox(animalNumber1, animalNumber2);

        Text plantNumber1 = new Text("Number of plants:");
        plantNumber1.setWrappingWidth(150);
        Text plantNumber2 = new Text("0");
        plantNumber2.setWrappingWidth(50);
        HBox plantNumberBox = new HBox(plantNumber1, plantNumber2);

        Text freeFields1 = new Text("Free fields:");
        freeFields1.setWrappingWidth(150);
        Text freeFields2 = new Text("0");
        freeFields2.setWrappingWidth(50);
        HBox freeFieldsBox = new HBox(freeFields1, freeFields2);

        Text averageEnergy1 = new Text("Average animals energy:");
        averageEnergy1.setWrappingWidth(150);
        Text averageEnergy2 = new Text("0");
        averageEnergy2.setWrappingWidth(50);
        HBox averageEnergyBox = new HBox(averageEnergy1, averageEnergy2);

        Text averageDaysAlive1 = new Text("Average length of life:");
        averageDaysAlive1.setWrappingWidth(150);
        Text averageDaysAlive2 = new Text("0");
        averageDaysAlive2.setWrappingWidth(50);
        HBox averageDaysAliveBox = new HBox(averageDaysAlive1, averageDaysAlive2);

        VBox statistics = new VBox(animalNumberBox, plantNumberBox, freeFieldsBox, averageEnergyBox, averageDaysAliveBox);
        statistics.setMinWidth(100);

        GridPane newGridPane = new GridPane();
        newGridPane.setStyle("-fx-background-color: '#609874'; -fx-grid-lines-visible: true; -fx-border-width: 2px; -fx-border-color: black");
        Simulation newSimulation = new Simulation(newGridPane, width, height, animalNumber2, plantNumber2, freeFields2, averageEnergy2, averageDaysAlive2);
        SimulationEngine simulationEngine = new SimulationEngine(width, height, mapVariant, startingNumberOfPlants,
                energyProvidedByEating, numberOfPlantsGrowingEachDay, plantGenerationVariant, startingNumberOfAnimals,
                startingNumberOfAnimalsEnergy, energyNeededToBeFull, energyNeededToCreateOffspring,
                minNumberOfMutations, maxNumberOfMutations, mutationVariant, genomeLength, behaviorVariant,
                newSimulation);

        Button button = new Button("Start");
        button.setMaxWidth(150);

        button.setOnAction(e -> {
            simulationEngine.switchPause();
            if (Objects.equals(button.getText(), "Pause")){
                button.setText("Resume");
            } else {
                button.setText("Pause");
            }
        });

        HBox simulation = new HBox(statistics, newGridPane);
        simulation.setAlignment(Pos.CENTER);
        VBox box = new VBox(simulation, button);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(10);

        Scene scene = new Scene(box, 1000, 750);
        stage.setScene(scene);
        stage.show();
        Thread engineThread = new Thread(simulationEngine);
        engineThread.start();
    }
}