package generator;

import java.util.Random;

public class ABitOfMadnessBehaviorVariant extends AbstractBehaviorVariant{
    public ABitOfMadnessBehaviorVariant(Integer numberOfGenes) {
        super(numberOfGenes);
    }
    public ABitOfMadnessBehaviorVariant(Integer[] genes) {
        super(genes);
    }

    @Override
    public Integer getNextRotation() {
        Integer nextRotation = this.genes[this.activeGene];
        if (Math.random() < 0.2){
            Integer newActiveGene;
            Random random = new Random();
            do{
                newActiveGene = random.nextInt(this.genes.length);
            } while (newActiveGene.equals(this.activeGene));
            this.activeGene = newActiveGene;
        } else {
            this.activeGene = (this.activeGene + 1) % this.genes.length;
        }
        return nextRotation;
    }
}
