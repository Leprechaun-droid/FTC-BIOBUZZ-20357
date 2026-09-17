package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.tuning.autotune.Procedure;
import com.pedropathing.tuning.autotune.Tuner;
//may remove if it causes too much strain or is not needed.
import com.pedropathing.tuning.autotune.*;

import org.firstinspires.ftc.teamcode.pedro.procedures.MecanumTuner;

public class Tuning {
    // Tuners go here

    @Tuner
    public static Procedure mecanumTuner() {

        return new MecanumTuner();
    }










}
