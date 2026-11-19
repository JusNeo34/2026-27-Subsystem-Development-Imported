package frc.robot.mechanisms.BasicRollers;

import java.util.function.Supplier;

import org.wpilib.command3.Command;
import org.wpilib.command3.Mechanism;
import org.wpilib.command3.NeedsNameBuilderStage;
import org.wpilib.command3.Scheduler;
import org.wpilib.units.measure.Voltage;

import frc.robot.mechanisms.Intake.IntakeRollers.IntakeRollersIO.IntakeRollersIOInputs;

public class BasicRollers extends Mechanism {

    private final BasicRollersIO io;
    private BasicRollersIOInputs inputs = new BasicRollersIOInputs();

    public BasicRollers(BasicRollersIO io) {
        this.io = io;

        Scheduler.getDefault().addPeriodic(() -> {
            io.updateInputs(inputs);
        });
    }

    private NeedsNameBuilderStage runVoltageCommand(Supplier<Voltage> volts) {
        return runRepeatedly(() -> io.setVoltage(volts.get()));
    }

    public Command runIntake() {
        return runVoltageCommand(() -> BasicRollersConstants.INTAKE_VOLTAGE)
                .named("BasicRollers/Intake");
    }

    public Command runEject() {
        return runVoltageCommand(() -> BasicRollersConstants.EJECT_VOLTAGE)
                .named("BasicRollers/Eject");
    }

    public Command runIdle() {
        return runVoltageCommand(() -> BasicRollersConstants.IDLE_VOLTAGE)
                .named("BasicRollers/Idle");
    }

}
// AVyan    