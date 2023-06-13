package globaloutbreak.controller.mutation;

import java.util.ArrayList;
import java.util.List;

import globaloutbreak.model.Model;
import globaloutbreak.model.mutation.MutationFactoryImpl;
import globaloutbreak.model.mutation.Mutation;
import globaloutbreak.model.mutation.MutationData;
import globaloutbreak.model.mutation.MutationManager;
import globaloutbreak.model.mutation.MutationManagerImpl;
import globaloutbreak.mutationreader.MutationReader;
import globaloutbreak.mutationreader.MutationReaderImpl;
import globaloutbreak.controller.Controller;

/**
 * class Mutation controller impl.
 */
public final class MutationControllerImpl implements MutationController {

    private final MutationData mutationData;
    private final MutationManager mutationManager;

    /**
     * constructor.
     * 
     */
    public MutationControllerImpl() {
        final MutationFactoryImpl factory = new MutationFactoryImpl();
        this.mutationData = new MutationData(factory);
        this.mutationManager = new MutationManagerImpl();
        final MutationReader mutationReader = new MutationReaderImpl(this.mutationData);
        mutationReader.readMutation();
    }

    @Override
    public void displayMutationsName(final Controller controller) {
        final List<Mutation> mutations = mutationData.getMutations();
        final List<String> list = new ArrayList<>();
        for (final Mutation mutation : mutations) {
            list.add(mutation.getName());
        }
        controller.setMutationsName(list);
    }

    @Override
    public void displayMutationsDesc(final String name, final Controller controller) {
        final List<Mutation> mutations = mutationData.getMutations();
        for (final Mutation mutation : mutations) {
            if (mutation.getName().equals(name)) {
                controller.setMutationsDesc(mutation.getDescription(), mutationManager.isActivate(name),
                        mutation.getCost(), mutation.getIncrease());
            }
        }
    }

    @Override
    public void update(final String name, final Model model) {
        final List<Mutation> mutations = mutationData.getMutations();
        final Mutation mutationData = mutations.stream()
                .filter(mutation -> mutation.getName().equals(name))
                .findFirst().orElse(null);
        if (model.getInfo().getPoints() >= mutationData.getCost() || mutationManager.isActivate(name)) {
            if (mutationManager.isActivate(name)) {
                mutationManager.removeToActivate(name);
                model.getInfo().increasePoints(mutationData.getCost());
                mutationData.decrease(model.getDisease());
            } else {
                mutationManager.addToActivate(name);
                model.getInfo().decreasePoints(mutationData.getCost());
                mutationData.increase(model.getDisease());
            }
        }

    }
}
