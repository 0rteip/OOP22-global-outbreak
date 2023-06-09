package globaloutbreak.controller.mutation;

import java.util.ArrayList;
//import java.io.IOException;
import java.util.List;
import globaloutbreak.model.disease.Disease;
import globaloutbreak.model.mutation.MutationFactoryImpl;
import globaloutbreak.model.mutation.Mutation;
import globaloutbreak.model.mutation.MutationData;
import globaloutbreak.model.mutation.MutationManager;
import globaloutbreak.model.mutation.MutationManagerImpl;
import globaloutbreak.mutationreader.MutationReader;
import globaloutbreak.mutationreader.MutationReaderImpl;
import globaloutbreak.view.View;
import globaloutbreak.controller.disease.DiseaseController;

/**
 * class Mutation controller impl.
 */
public final class MutationControllerImpl implements MutationController {

    private final MutationData mutationData;
    private final MutationManager mutationManager;
    private final Disease disease;
    private final View view;
    /**
     * constructor.
     * 
     * @param view 
     *              view
     * @param diseaseController 
     *                          disease controller
     */
    public MutationControllerImpl(final View view, final DiseaseController diseaseController) {
        final MutationFactoryImpl factory = new MutationFactoryImpl();
        this.mutationData = new MutationData(factory);
        this.mutationManager = new MutationManagerImpl();
        final MutationReader mutationReader = new MutationReaderImpl(this.mutationData);
        this.view = view;
        this.disease = diseaseController.getDisease();
        mutationReader.readMutation();
    }


    @Override
    public void displayMutationsName() {
        final List<Mutation> mutations = mutationData.getMutations();
        final List<String> list = new ArrayList<>();
        for (final Mutation mutation : mutations) {
                list.add(mutation.getName());
        }
        view.setMutationsName(list);
    }

    @Override
    public void displayMutationsDesc(final String name) {
        final List<Mutation> mutations = mutationData.getMutations();
        for (final Mutation mutation : mutations) {
            if (mutation.getName().equals(name)) {
                 view.setMutationsDesc(mutation.getDescription(), mutationManager.isActivate(name), mutation.getCost());
            }
        }
    }

    @Override
    public void update(final String name) {
        final List<Mutation> mutations = mutationData.getMutations();
        final Mutation mutationData = mutations.stream()
                                                .filter(mutation -> mutation.getName().equals(name))
                                                .findFirst().orElse(null);
        if (mutationManager.isActivate(name)) {
            mutationManager.removeToActivate(name);
            mutationData.decrease(disease);
        } else {
            mutationManager.addToActivate(name);
            mutationData.decrease(disease);
        }
    }
}
