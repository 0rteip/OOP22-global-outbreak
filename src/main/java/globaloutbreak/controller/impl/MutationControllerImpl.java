package globaloutbreak.controller.impl;

//import java.io.IOException;
import java.util.List;

import globaloutbreak.controller.api.MutationController;
import globaloutbreak.model.api.Disease;
import globaloutbreak.model.api.Mutation;
//import globaloutbreak.model.api.MutationFactory;
import globaloutbreak.model.impl.MutationData;
import globaloutbreak.model.impl.MutationFactoryImpl;

public class MutationControllerImpl implements MutationController{

    private MutationData mutationData;
    private Disease disease;

    public MutationControllerImpl(){

        MutationFactoryImpl factory = new MutationFactoryImpl();
        this.mutationData= new MutationData(factory);

    }



    @Override
    public void loadMutationFromFile(final String name) {
        //-chiamata qwuando uno schiaccia su un potenziamento
        //leggo dati completi da file
        //aggiorno la view


        {
            try {
                 mutationData.loadMutationFromJson(name);
                
                List<Mutation> mutations = mutationData.getMutations();
    
                // La lista dei potenziamenti è pronta per essere utilizzata
                // Esempio: visualizza i nomi dei potenziamenti
                for (Mutation mutation : mutations) {
                    System.out.println(mutation.getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }
    }

    @Override
    public List<Mutation> getMutation() {
        //aggiornare la view
        return mutationData.getMutations();
    }


    @Override
    public void increment(Mutation mutation) {
        mutation.increase(disease);
    }



  



   
    
}
