package globaloutbreak.mutation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import globaloutbreak.model.mutation.Mutation;
import globaloutbreak.model.mutation.MutationFactoryImpl;
import globaloutbreak.model.mutation.TypeMutation;
import globaloutbreak.model.mutation.MutationData;
import globaloutbreak.model.mutation.MutationManager;
import globaloutbreak.model.mutation.MutationManagerImpl;
/**
 * test for mutation.
 */
final class MutationTest {
     /**
     * Test if mutation factory  work correctly.
     */
    @Test
    void testFactory() {
        final int cost = 5;
        final String name = "test";
        final float increase = 0.6f;
        final TypeMutation type = TypeMutation.AIR;
        final String desc = "desc";
        final MutationFactoryImpl factory = new MutationFactoryImpl();
        final Mutation mutation = factory.createMutation(cost, name, increase, type, desc);
        assertEquals(cost, mutation.getCost());
        assertEquals(name, mutation.getName());
        assertEquals(increase, mutation.getIncrease());
        assertEquals(type, mutation.getType());
        assertEquals(desc, mutation.getDescription());

    }
    /**
     * test mutation list.
     */
    @Test
    void testList() {
        final MutationFactoryImpl factory = new MutationFactoryImpl();
        final MutationData listMutation = new MutationData(factory);
        final int cost = 5;
        final String name = "test";
        final float increase = 0.6f;
        final TypeMutation type = TypeMutation.AIR;
        final String desc = "desc";
        listMutation.loadMutationFromJson(cost, name, increase, type, desc);
        final List<Mutation> listMutations = listMutation.getMutations();
        final List<Mutation> listTestMutation = new ArrayList<>();
        listTestMutation.add(0, factory.createMutation(cost, name, increase, type, desc));
        assertEquals(listMutations.size(), listTestMutation.size());
        assertEquals(listMutations.get(0).getName(), listTestMutation.get(0).getName());
        assertEquals(listMutations.get(0).getCost(), listTestMutation.get(0).getCost());
        assertEquals(listMutations.get(0).getDescription(), listTestMutation.get(0).getDescription());
        assertEquals(listMutations.get(0).getIncrease(), listTestMutation.get(0).getIncrease());
    }
    /**
     * test list activate mutation.
     */
    @Test
    void activateTest() {
        final MutationManager mutationManager = new MutationManagerImpl();
        final String test1 = "test1";
        final String test2 = "test2";
        final String test3 = "test3";
        mutationManager.addToActivate(test1);
        mutationManager.addToActivate(test2);
        mutationManager.addToActivate(test3);
        assertTrue(mutationManager.isActivate(test1));
        final Set<String> mySet = new HashSet<>();
        mySet.add(test1);
        mySet.add(test2);
        mySet.add(test3);
        assertEquals(mutationManager.getActivateMutation(), mySet);
        final Set<String> mySet1 = new HashSet<>();
        mySet1.add(test1);
        mySet1.add(test2);
        mutationManager.removeToActivate(test3);
        assertEquals(mutationManager.getActivateMutation(), mySet1);

    }
}
