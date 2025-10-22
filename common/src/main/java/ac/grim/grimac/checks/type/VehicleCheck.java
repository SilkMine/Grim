package ac.grim.grimac.checks.type;

import ac.grim.grimac.api.AbstractCheck;
import ac.grim.grimac.utils.anticheat.update.VehiclePositionUpdate;

public interface VehicleCheck extends AbstractCheck, CanBeDisabled {

    void process(final VehiclePositionUpdate vehicleUpdate);
}
