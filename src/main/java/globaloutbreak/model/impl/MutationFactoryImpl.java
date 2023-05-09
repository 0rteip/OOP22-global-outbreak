package globaloutbreak.model.impl;

import globaloutbreak.model.api.Mutation;
import globaloutbreak.model.api.MutationFactory;

public class MutationFactoryImpl implements MutationFactory{

    public MutationFactoryImpl(){

    }
    
    @Override
    public Mutation createMutation() {
                
        return new Mutation() {

            @Override
            public int getCost() {
                int a =0;
                return a;
            }

            @Override
            public int getIncrement() {
                int a =0;
                return a;
            }
            
        };
    }
    
}
