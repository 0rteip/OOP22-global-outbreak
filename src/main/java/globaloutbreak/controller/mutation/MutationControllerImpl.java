package globaloutbreak.controller.mutation;

//import java.io.IOException;
import java.util.List;
import globaloutbreak.model.api.Disease;
import globaloutbreak.model.mutation.MutationFactoryImpl;
import globaloutbreak.model.mutation.Mutation;
import globaloutbreak.model.mutation.MutationData;
import globaloutbreak.model.mutation.MutationManager;
import globaloutbreak.model.mutation.MutationManagerImpl;

/**
 * class Mutation controller impl.
 */
public final class MutationControllerImpl implements MutationController {

    private final MutationData mutationData;
    private final Disease disease;
    private final MutationManager mutationManager;

    /**
     * constructor.
     * 
     * @param disease disease
     */
    public MutationControllerImpl(final Disease disease) {
        this.disease = disease;
        final MutationFactoryImpl factory = new MutationFactoryImpl();
        this.mutationData = new MutationData(factory);
        this.mutationManager = new MutationManagerImpl();
    }

    @Override
    public void loadMutationFromFile(final String name) {
        //-chiamata qwuando si avvia
        //leggo dati completi da file
            try {
                //leggo file e chiamo la load
                mutationData.loadMutationFromJson(0, "", 0, null, "");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @Override
    public void displayMutationsName() {
        //aggiornare la view quando uno schiaccia per aprire menu pot
        final List<Mutation> mutations = mutationData.getMutations();
           for (final Mutation mutation : mutations) {
                    //System.out.println(mutation.getName());
                    //view.set qualcosa lista nomi
                }
        mutationData.getMutations();
    }

    @Override
    public void displayMutationsDesc() {
        //aggiornare la view quando uno schiaccia un pot menu pot
        final List<Mutation> mutations = mutationData.getMutations();
           for (final Mutation mutation : mutations) {
                    //System.out.println(mutation.getDescription());
                    //view.set qualcosa lista descriz
                    //view set bottone con potenzia o no
                }
        mutationData.getMutations();
    }

    @Override
    public void update(final Mutation mutation) {
        //chiamata da view per eseguire il pot 
        if (mutationManager.isActivate(mutation.getName())) {
            mutationManager.removeToActivate(mutation.getName());
            mutation.decrease(disease);
        } else {
            mutationManager.addToActivate(mutation.getName());
            mutation.increase(disease);
        }
    }
}
