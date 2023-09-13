package generator;

import java.util.Random;

public class FullRandomnessMutationVariant implements IMutationVariant{
    @Override
    public Integer[] mutateTheGenes(Integer[] genes, int minNumberOfMutations, int maxNumberOfMutations) {
        Random random = new Random();
        int genesToChange = random.nextInt(maxNumberOfMutations - minNumberOfMutations) + minNumberOfMutations;
        for (int i = 0; i < genesToChange; i++){
            genes[random.nextInt(genes.length)] = random.nextInt(8);
        }
        return genes;
    }
}
