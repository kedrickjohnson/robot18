package org.usfirst.frc.team4674.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.cscore.UsbCamera;
//import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


public class Robot extends IterativeRobot 
	{
		Joystick stick = new Joystick(0);     // This is giving our joysticks life by creating them. //
		Joystick stick1 = new Joystick(1);
		Joystick stick2 = new Joystick(2);
	
		
		WPI_TalonSRX leftFront = new WPI_TalonSRX(0);     // This is giving our motors life and the ports variables such as 0,1,2 and three.   // 
		WPI_TalonSRX leftRear = new WPI_TalonSRX(1);
		WPI_TalonSRX rightFront = new WPI_TalonSRX(2);
		WPI_TalonSRX rightRear = new WPI_TalonSRX(3);

		/*double y = 0;
		double x = 0;
		double z = 0;
		double angle = 0;*/
	
		
		private final WPI_TalonSRX arm1 = new WPI_TalonSRX(4);
	    private final WPI_TalonSRX arm2 = new WPI_TalonSRX(5);
	    
	    //private final DigitalInput limitTop = new DigitalInput(1);
	    //private final DigitalInput limitBottom = new DigitalInput(0);
	    
	    
	    //private final VictorSP intake1 = new VictorSP(6);
	    //private final VictorSP intake2 = new VictorSP(7);

	    
	    //private static Solenoid grabber1= new Solenoid(0, 7);
	    //private static Solenoid grabber2= new Solenoid(0, 0);
	    
	    //private static Compressor compressor= new Compressor();
	    
	    private Timer timer = new Timer();
	    
	@Override
	public void robotInit() 
		{
			//compressor.setClosedLoopControl(true);
		
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(0);
			UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(1);
		}

 
	@Override
	public void autonomousInit()
		{
			timer.reset();
			timer.start();
			
			leftFront.set(0);
			leftRear.set(0);
			rightFront.set(0);
			rightRear.set(0);
		}


	@Override
	public void autonomousPeriodic() 										// This is where you give it instructions for not being operated //
		{
			if (timer.get() < 2.0)
				{
					leftFront.set(-0.5);
					leftRear.set(-0.5);
					rightFront.set(0.5);
					rightRear.set(0.5);
				}

			else
				{
					leftFront.set(0);
					leftRear.set(0);
					rightFront.set(0);
					rightRear.set(0);
				}
		}


	@Override
	public void teleopInit() 												// This is setting the motors to zero. //
		{		
			leftFront.set(0);
			leftRear.set(0);
			rightFront.set(0);
			rightRear.set(0);
			
			arm1.set(0);
			arm2.set(0);
			
			//intake1.set(0);
	        //intake2.set(0);
		}

	public void driveSystem()
		{
			if (Math.abs(stick.getY()) > 0.15) 
				{
					leftFront.set(-stick.getY());
					leftRear.set(-stick.getY());
				}
			
			if (Math.abs(stick1.getY()) > 0.15) 
				{
					rightFront.set(-stick1.getY());
					rightRear.set(-stick1.getY());
				}
			
			if (Math.abs(stick.getY()) < 0.15)
		    	{
					leftFront.set(0);
					leftRear.set(0);
		    	}
			
			if (Math.abs(stick1.getY()) < 0.15)
		    	{
					rightFront.set(0);
					rightRear.set(0);
		    	}
		}
	
	public void arm()
		{	
			/*
			System.out.append("limitTop"); 
			System.out.println(limitTop.get());
			System.out.append("limitBottom");
			System.out.println(limitBottom.get());
			&& limitTop.get() == true
			&& limitTop.get() == false
			&& limitBottom.get() == true
			&& limitBottom.get() == false
			*/
			
			if (stick2.getY() < -.25)
			    {
					arm1.set(0.7);
					arm2.set(0.7);
			    }
			
			if (stick2.getY() > .25)
				{
					arm1.set(-0.35);
					arm2.set(-.35);																							
				}
			
			if (stick2.getRawButtonPressed(11))
			    {
					arm1.set(-1);
					arm2.set(-1);
			    }

			if (Math.abs(stick2.getY()) < .25 && stick2.getRawButton(11) == false)
				{
					arm1.set(0);
					arm2.set(0);																						
				}
		}
	
	
	/*public void intake()
		{
			if (stick.getRawButtonPressed(1))
			    {
			        intake1.set(1);
			        intake2.set(-1);
			    }
			        
			if (stick.getRawButtonPressed(5))
				{
					intake1.set(1);
					intake2.set(1);
				}
			
			if (stick1.getRawButtonPressed(1))
			    {
			        intake1.set(-1);
			        intake2.set(1);
			    }
			
			if (stick1.getRawButtonPressed(6))
			    {
			        intake1.set(-1);
			        intake2.set(-1);
			    }
			
			if (stick.getRawButtonReleased(1))
			    {
					intake1.set(0);
					intake2.set(0);
			    }
			        
			if (stick.getRawButtonReleased(5))
				{
					intake1.set(0);
					intake2.set(0);
				}
			
			if (stick1.getRawButtonReleased(1))
			    {
					intake1.set(0);
					intake2.set(0);
			    }
			
			if (stick1.getRawButtonReleased(6))
			    {
					intake1.set(0);
					intake2.set(0);
			    }
		}*/

		
	
	/*public void grabber()
		{
			if (stick2.getRawButtonPressed(1))
				{
			        grabber1.set(true);
			        grabber2.set(true);
			    }
			
			if (stick2.getRawButtonPressed(5)) 
			    {
			        grabber1.set(false);
			        grabber2.set(false);
			    }
		}*/

	@Override
	public void teleopPeriodic() 											// This is so we can drive the robot. Thats why it is set to true. //
		{
			while (true && isOperatorControl () && isEnabled() ) 				// This is where if everything that says the robot can be run, it can be driven. //
				{
					driveSystem(); 	
					arm();
					//intake();
					//grabber();
				}	
		}


	@Override
	public void testPeriodic() 
	{
		LiveWindow.run();
	}
}