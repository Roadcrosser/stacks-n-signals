package stacksnsignals.components;

import net.minecraft.util.Identifier;
import stacksnsignals.misc.ComponentIoState;
import stacksnsignals.misc.ComponentState;
import stacksnsignals.misc.IoStates;

import static stacksnsignals.Stacks_n_Signals.MOD_ID;

public class Diode extends _GenericComponent {

    public Diode(){
        io_state = new ComponentIoState(IoStates.OUT1, IoStates.NULL, IoStates.IN1, IoStates.NULL, IoStates.NULL, IoStates.NULL);
    }

    public static ComponentState on_tick(ComponentState current_state, int variant){
        return current_state;
    }

    public static Identifier get_texture(ComponentState current_state, int variant){
        return new Identifier(MOD_ID, "textures/components/generic.png");
    }
}
