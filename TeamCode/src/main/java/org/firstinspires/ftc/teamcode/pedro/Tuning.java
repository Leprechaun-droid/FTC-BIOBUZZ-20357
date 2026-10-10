package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.tuning.autotune.Procedure;
import com.pedropathing.tuning.autotune.Tuner;
//may remove if it causes too much strain or is not needed.
import com.pedropathing.tuning.autotune.*;

import org.firstinspires.ftc.teamcode.pedro.procedures.MecanumTuner;
import org.firstinspires.ftc.teamcode.pedro.procedures.Tests;

public class Tuning {
    // Tuners go here

    @Tuner
    public static Procedure mecanumTuner() {

        return new MecanumTuner();
    }



    @Tuner
    public static Procedure tests(){
        return new Tests(hardwareMap -> new Mecanum(hardwareMap, Constants.drivetrainConfig), null, null);
    }







}
