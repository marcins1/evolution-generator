package generator;

public class VariantManagement {
    public static IMapVariant getMapVariant(String name, Integer width, Integer height, Integer energyToRemove){
        return switch (name){
            case "Globe" -> new GlobeMapVariant(height, width);
            default -> new BlisteringPortalMapVariant(height, width, energyToRemove);
        };
    }

    public static IPlantGenerationVariant getPlantGenerationVariant(String name, Integer width, Integer height){
        return switch (name){
            default -> new ForestedEquatorsPlantGenerationVariant(width, height);
        };
    }

    public static IMutationVariant getMutationVariant(String name){
        return switch (name){
            case "Full randomness" -> new FullRandomnessMutationVariant();
            default -> new SlightCorrectionMutationVariant();
        };
    }

    public static IBehaviorVariant getBehaviorVariant(String name, Integer[] genes, Integer numberOfGenes){
        if (genes != null){
            return switch (name){
                case "Full predestination" -> new FullPredestinationBehaviorVariant(genes);
                default -> new ABitOfMadnessBehaviorVariant(genes);
            };
        } else {
            return switch (name){
                case "Full predestination" -> new FullPredestinationBehaviorVariant(numberOfGenes);
                default -> new ABitOfMadnessBehaviorVariant(numberOfGenes);
            };
        }

    }
}
