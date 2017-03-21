package dataMapper;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;

import tool.*;

import org.junit.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class InteTest_RegAccount {
//This test is to test Unit of Work on one transation 
	
	
	static Logger logger = LogManager.getLogger(DataMapperTest.class);
	
	
	public void testBirthdayCheck(){
		String b="1993-10-8";
		assertEquals(true,testingTool.birthdayCheck(b));
	}
	
	
	@Test
	public void test() throws SQLException {
		
		String username="inteTest1";
		String password="123";
		String occupation="teacher";
		String birthday="1993-10-1";
		String email="xxx@gmail.com";
				
		int stage=1;
		while(stage!=100){
			switch(stage){
			case 0: 
				logger.error("Transation fail");
				stage=100;
				break;
			case 1: 
				logger.info("username check");
				if(testingTool.usernameCheck(username)){
					stage=2;
				}
				else{
					stage=0;
					logger.info("username check fail");
				}
				break;
			case 2: 
				logger.info("password check ");
				if(testingTool.passwordCheck(password)){
						stage=3;
					}
				else{
						stage=0;
						logger.info("password check fail");
					}
				break;
			case 3:
				logger.info("birthday check");
				if(testingTool.birthdayCheck(birthday)){
					stage=4;
				}
				else{
					stage=0;
					logger.info("Birthday check fail");
				}
				break;
			case 4:
				logger.info("email check");
				if(testingTool.emailCheck(email)){
					stage=9;
				}
				else{
					stage=0;
					logger.info("email check fail");
				}
				break;
			case 9: 
				logger.info("ALL CHECK PASSED !!!");
				//create user
				UnitOfWork.newCurrent();//create new Unit Of Work 
				User.creatInDB(1,username, password, occupation, birthday, email);
				UnitOfWork.getCurrent().commit();
				stage=100;//exit stage
				break;
			
			
			default: logger.error("stage number out of bound");
					break;
		
			}
		}
	}

}
