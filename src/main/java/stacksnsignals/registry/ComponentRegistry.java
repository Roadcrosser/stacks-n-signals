package stacksnsignals.registry;

import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;
import stacksnsignals.Stacks_n_Signals;
import stacksnsignals.components.Diode;
import stacksnsignals.components.Wire;
import stacksnsignals.components._GenericComponent;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static stacksnsignals.Stacks_n_Signals.MOD_ID;



public class ComponentRegistry {
    private static Map<Identifier, _GenericComponent> component_registry = new HashMap<>();
    private static SortedMap<Integer, Identifier> component_order = new TreeMap<>();

    public ComponentRegistry() {
    }


    public static void register(Identifier identifier, _GenericComponent component, Integer order){
        if (!component.get_io_state().validate()){
            Stacks_n_Signals.log(Level.WARN, String.format("Component %s failed to load: I/O overlap", identifier.toString()));
            return;
        }
        component_registry.put(identifier, component);
        component_order.put(order, identifier);
    }

    private static void register_defaults() {
        register(new Identifier(MOD_ID, "wire"), new Wire(), -999);
        register(new Identifier(MOD_ID, "diode"), new Diode(), 100);
    }

    public static void initialize(){
        register_defaults();

    }


}
