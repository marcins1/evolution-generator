package generator;

import java.util.Random;

abstract class AbstractBehaviorVariant implements IBehaviorVariant{
    protected final Integer[] genes;
    protected Integer activeGene;

    public AbstractBehaviorVariant(Integer numberOfGenes){
        this.genes = generateGenes(numberOfGenes);
        Random random = new Random();
        this.activeGene = random.nextInt(numberOfGenes);
    }

    public AbstractBehaviorVariant(Integer[] genes){
        this.genes = genes;
        Random random = new Random();
        this.activeGene = random.nextInt(genes.length);
    }

    private Integer[] generateGenes(Integer numberOfGenes){
        Random random = new Random();
        Integer[] generating = new Integer[numberOfGenes];
        for (int i = 0; i < numberOfGenes; i++){
            generating[i] = random.nextInt(8);
        }
        return generating;
    }

    public Integer[] getGenes(){
        return this.genes;
    }
}
