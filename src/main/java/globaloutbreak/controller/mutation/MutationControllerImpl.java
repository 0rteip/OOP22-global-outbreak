package globaloutbreak.controller.mutation;

import java.util.ArrayList;
//import java.io.IOException;
import java.util.List;
import java.util.Optional;

import globaloutbreak.model.disease.Disease;
import globaloutbreak.model.disease.DiseaseData;
import globaloutbreak.model.mutation.MutationFactoryImpl;
import globaloutbreak.model.mutation.Mutation;
import globaloutbreak.model.mutation.MutationData;
import globaloutbreak.model.mutation.MutationManager;
import globaloutbreak.model.mutation.MutationManagerImpl;
import globaloutbreak.mutationreader.MutationReader;
import globaloutbreak.mutationreader.MutationReaderImpl;
import globaloutbreak.view.View;
import globaloutbreak.view.scenecontroller.MutationViewController;

/**
 * class Mutation controller impl.
 */
public final class MutationControllerImpl implements MutationController {

    private final MutationData mutationData;
    private final MutationManager mutationManager;
    private final MutationReader mutationReader;
    private final MutationViewController mutationViewController; 
    private final View view;
    /**
     * constructor.
     * 
     * @param disease disease
     */
    public MutationControllerImpl(View view) {
        final MutationFactoryImpl factory = new MutationFactoryImpl();
        this.mutationData = new MutationData(factory);
        this.mutationManager = new MutationManagerImpl();
        this.mutationReader = new MutationReaderImpl(this.mutationData);
        this.mutationViewController = new MutationViewController();
        this.view = view;
        mutationReader.readMutation();
    }


    @Override
    public void displayMutationsName() {
        final List<Mutation> mutations = mutationData.getMutations();
        List<String> list = new ArrayList<>();
        for (final Mutation mutation : mutations) {
                list.add(mutation.getName());
        }
        view.setMutationsName(list);
    }

    @Override
    public void displayMutationsDesc(String name) {
        final List<Mutation> mutations = mutationData.getMutations();
        for (final Mutation mutation : mutations) {
            if(mutation.getName().equals(name) ){
                 view.setMutationsDesc(mutation.getDescription(), mutationManager.isActivate(name));
            }
        }
    }

    @Override
    public void update(String name) {
        //chiamata da view per eseguire il pot 
        final List<Mutation> mutations = mutationData.getMutations();
        System.out.println(mutations);
        final Mutation mutationData = mutations.stream()
        .filter(mutation -> mutation.getName().equals(name))
        .findFirst().orElse(null);
        System.out.println(name);
        System.out.println(mutationData);
        if (mutationManager.isActivate(name)) {
            mutationManager.removeToActivate(name);
            mutationData.decrease(null);////disease
        } else {
            mutationManager.addToActivate(name);
            mutationData.decrease(null);
        }
    }
}
