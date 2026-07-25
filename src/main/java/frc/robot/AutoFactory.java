// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.BLine.*;
import frc.robot.subsystems.Drive.SwerveSubsystem;

public class AutoFactory extends SubsystemBase {
    private SwerveSubsystem m_swerveSubsystem;

    private FollowPath.Builder pathBuilder;
    
    /** Creates a new AutoFactory. */
    public AutoFactory() {
        pathBuilder = new FollowPath.Builder(
                    m_swerveSubsystem,
                    m_swerveSubsystem::getPose,
                    m_swerveSubsystem::getRobotVelocity,
                    m_swerveSubsystem::setChassisSpeeds,
                    new PIDController(2.0, 0.0, 0.0),
                    new PIDController(1.0, 0.0, 0.0),
                    new PIDController(0.2, 0.0, 0.0)
                ).withDefaultShouldFlip()
                .withTRatioBasedTranslationHandoffs(true);
    }

    public Command getMoveFromRightAuto() {
        Path moveFromRight = new Path("back_up_from_right");

        return Commands.sequence(pathBuilder.build(moveFromRight));
    }

    public Command getMoveFromLeftAuto() {
        Path moveFromLeft = new Path("back_up_from_left");

        return Commands.sequence(pathBuilder.build(moveFromLeft));
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
