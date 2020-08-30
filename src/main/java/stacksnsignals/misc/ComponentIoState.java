package stacksnsignals.misc;

import java.util.HashSet;
import java.util.Set;

public class ComponentIoState {
    public final IoStates SIDE_FRONT;
    public final IoStates SIDE_RIGHT;
    public final IoStates SIDE_BACK;
    public final IoStates SIDE_LEFT;
    public final IoStates SIDE_TOP;
    public final IoStates SIDE_BOTTOM;

    public ComponentIoState(IoStates front, IoStates right, IoStates back, IoStates left, IoStates top, IoStates bottom){
        SIDE_FRONT = front;
        SIDE_RIGHT = right;
        SIDE_BACK = back;
        SIDE_LEFT = left;
        SIDE_TOP = top;
        SIDE_BOTTOM = bottom;
    }

    public boolean validate(){
        Set<IoStates> seen_states = new HashSet<>();

        IoStates[] states = {SIDE_FRONT, SIDE_RIGHT, SIDE_BACK, SIDE_LEFT, SIDE_TOP, SIDE_BOTTOM};

        for (IoStates s : states){
            if (s == IoStates.NULL){
                continue;
            }
            if (seen_states.contains(s)){
                return false;
            }

            seen_states.add(s);
        }

        return true;
    }
}