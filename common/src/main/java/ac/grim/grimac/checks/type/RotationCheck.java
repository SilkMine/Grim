package ac.grim.grimac.checks.type;

import ac.grim.grimac.api.AbstractCheck;
import ac.grim.grimac.utils.anticheat.update.RotationUpdate;

public interface RotationCheck extends AbstractCheck, CanBeDisabled {

    default void process(final RotationUpdate rotationUpdate) {
    }
}
