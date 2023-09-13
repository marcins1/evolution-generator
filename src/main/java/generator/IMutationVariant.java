package generator;

public interface IMutationVariant {
    Integer[] mutateTheGenes(Integer[] genes, int minNumberOfMutations, int maxNumberOfMutations);
}
