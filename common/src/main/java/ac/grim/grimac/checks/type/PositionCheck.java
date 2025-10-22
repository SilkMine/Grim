package ac.grim.grimac.checks.type;

import ac.grim.grimac.api.AbstractCheck;
import ac.grim.grimac.utils.anticheat.update.PositionUpdate;

public interface PositionCheck extends AbstractCheck, CanBeDisabled {

    default void onPositionUpdate(final PositionUpdate positionUpdate) {
    }
}
