package generator;

import java.util.Random;

public class SlightCorrectionMutationVariant implements IMutationVariant{
    @Override
    public Integer[] mutateTheGenes(Integer[] genes, int minNumberOfMutations, int maxNumberOfMutations) {
        Random random = new Random();
        int genesToChange = random.nextInt(maxNumberOfMutations - minNumberOfMutations) + minNumberOfMutations;
        int index;
        for (int i = 0; i < genesToChange; i++){
            index = random.nextInt(genes.length);
            if (random.nextInt(2) == 0){
                genes[index] = (genes[index] - 1) % 8;
            } else {
                genes[index] = (genes[index] + 1) % 8;
            }
        }
        return genes;
    }
}
