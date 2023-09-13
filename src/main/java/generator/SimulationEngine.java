package generator;

import generator.gui.Simulation;
import javafx.application.Platform;
import java.util.Random;

public class SimulationEngine implements Runnable{
    private final Map map;
    private final Integer numberOfPlantsGrowingEachDay;
    private final Integer startingNumberOfAnimalsEnergy;
    private final IMutationVariant mutationVariant;
    private final String behaviorVariant;
    private final int moveDelay = 150;
    private boolean simulationIsPaused;
    private boolean simulationIsRunning;
    private final Simulation simulation;

    public SimulationEngine(Integer width, Integer height, String mapVariant, Integer startingNumberOfPlants,
                            Integer energyProvidedByEating, Integer numberOfPlantsGrowingEachDay,
                            String plantGenerationVariant, Integer startingNumberOfAnimals,
                            Integer startingNumberOfAnimalsEnergy, Integer energyNeededToBeFull,
                            Integer energyNeededToCreateOffspring, Integer minNumberOfMutations,
                            Integer maxNumberOfMutations, String mutationVariant, Integer genomeLength,
                            String behaviorVariant, Simulation simulation){
        IPlantGenerationVariant newGenerationVariant = VariantManagement.getPlantGenerationVariant(plantGenerationVariant, width, height);
        this.map = new Map(width, height, VariantManagement.getMapVariant(mapVariant, width, height,
                energyNeededToCreateOffspring), energyProvidedByEating, newGenerationVariant, energyNeededToBeFull,
                energyNeededToCreateOffspring, genomeLength, minNumberOfMutations, maxNumberOfMutations);
        newGenerationVariant.setMap(this.map);
        this.numberOfPlantsGrowingEachDay = numberOfPlantsGrowingEachDay;
        this.startingNumberOfAnimalsEnergy = startingNumberOfAnimalsEnergy;
        this.mutationVariant = VariantManagement.getMutationVariant(mutationVariant);
        this.behaviorVariant = behaviorVariant;

        this.map.plantGenerationVariant.spawnPlants(startingNumberOfPlants);

        Random random = new Random();
        for (int i = 0; i < startingNumberOfAnimals; i++){
            this.map.place(new Animal(this.map, new Vector2d(random.nextInt(width), random.nextInt(height)), startingNumberOfAnimalsEnergy, VariantManagement.getBehaviorVariant(this.behaviorVariant, null, genomeLength), this.mutationVariant));
        }
        this.simulationIsPaused = true;
        this.simulationIsRunning = true;
        simulation.setMap(this.map);
        this.simulation = simulation;
        simulation.draw();
        simulation.updateStatistics();
    }

    public void run(){
        while (simulationIsRunning) {
            if (!this.simulationIsPaused) {
                Platform.runLater(() -> {
                    this.map.removeDeadAnimals();
                    this.map.getAliveAnimals().forEach(Animal::move);
                    this.map.getPlants().forEach(((vector2d, plant) -> {
                        this.map.tryToEatPlant(plant);
                    }));
                    this.map.removePlants();
                    this.map.reproduce();
                    this.map.plantGenerationVariant.spawnPlants(numberOfPlantsGrowingEachDay);
                    this.simulation.updateStatistics();
                    simulation.clearGrid();
                    simulation.draw();
                });

            }
            try{
                Thread.sleep(moveDelay);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void switchPause(){
        this.simulationIsPaused = !this.simulationIsPaused;
    }
}
