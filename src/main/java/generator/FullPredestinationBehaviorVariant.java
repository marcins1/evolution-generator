package generator;

public class FullPredestinationBehaviorVariant extends AbstractBehaviorVariant{
    public FullPredestinationBehaviorVariant(Integer numberOfGenes) {
        super(numberOfGenes);
    }
    public FullPredestinationBehaviorVariant(Integer[] genes) {
        super(genes);
    }

    @Override
    public Integer getNextRotation() {
        Integer nextRotation = this.genes[this.activeGene];
        this.activeGene = (this.activeGene + 1) % this.genes.length;
        return nextRotation;
    }
}
