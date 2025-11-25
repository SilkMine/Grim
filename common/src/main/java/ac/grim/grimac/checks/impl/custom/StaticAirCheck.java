package ac.grim.grimac.checks.impl.custom;

import ac.grim.grimac.api.config.ConfigManager;
import ac.grim.grimac.checks.Check;
import ac.grim.grimac.checks.CheckData;
import ac.grim.grimac.checks.type.PostPredictionCheck;
import ac.grim.grimac.player.GrimPlayer;
import ac.grim.grimac.utils.anticheat.update.PredictionComplete;
import org.jetbrains.annotations.NotNull;

@CheckData(name = "StaticAirCheck", stableKey = "grim.custom.static_air_check")
public class StaticAirCheck extends Check implements PostPredictionCheck {

    private double EPS = 0.001;

    public StaticAirCheck(@NotNull GrimPlayer player) {
        super(player);
    }

    public void onPredictionComplete(final PredictionComplete predictionComplete) {
        if (!predictionComplete.isChecked()) {
            return;
        }
        //only applies to gliding players
        if (player.isGliding) {
            // Use double values and a small epsilon to avoid fragile integer rounding and TPS assumptions
            final double horizontal = player.actualMovement.clone().setY(0).length();
            final double vertical = player.actualMovement.getY();
            final double predictedHorizontal = player.predictedVelocity.vector.clone().setY(0).length();
            final double predictedVertical = player.predictedVelocity.vector.getY();

            final boolean hZero = Math.abs(horizontal) <= EPS;
            final boolean phZero = Math.abs(predictedHorizontal) <= EPS;
            final boolean vZero = Math.abs(vertical) <= EPS;
            final boolean pvZero = Math.abs(predictedVertical) <= EPS;
            final boolean vDifferent = Math.abs(vertical - predictedVertical) > EPS;

            boolean flag = false;
            // If actual horizontal is essentially zero but predicted isn't, and vertical also mismatches
            if (hZero && !phZero && vZero && !pvZero) {
                flag = true;
            } else if (hZero && !phZero && vDifferent) {
                flag = true;
            }

            if (flag) {
                // Round values for readable logging only (two decimal places)
                flagAndAlert(String.format("Horz=%.2f, PredHorz=%.2f, Vert=%.2f, PredVert=%.2f", horizontal, predictedHorizontal, vertical, predictedVertical));
            }
        }
    }

    @Override
    public void onReload(ConfigManager config) {
        EPS = config.getDoubleElse("StaticAirCheck.EPS", 0.001);
    }

}
