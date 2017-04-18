package databaseTableGateway;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import dataMapper.DataMapperTest;
import dataMapper.Review;

public class ReviewTableTest {
	int t_review_id=1;
	int t_score=3;
	String t_description="this is a description";
	int t_property_id=123;
	int t_user_id=8;
	public static Logger logger = LogManager.getLogger(DataMapperTest.class);
	
	
	

	public void testSelectByPropertyID() throws SQLException {
		
		Review r1=ReviewTableGateway.SelectByPropertyID(123);
		assertEquals(t_description,r1.getDescription());
		assertEquals(t_property_id,r1.getProperty_id());
		assertEquals(t_review_id,r1.getReview_id());
		assertEquals(t_score,r1.getScore());
		assertEquals(t_user_id,r1.getUser_id());
		
	}

	public void testSelectByReviewID() throws SQLException {
		
		Review r1=ReviewTableGateway.SelectByReviewID(1);
		assertEquals(t_description,r1.getDescription());
		assertEquals(t_property_id,r1.getProperty_id());
		assertEquals(t_review_id,r1.getReview_id());
		assertEquals(t_score,r1.getScore());
		assertEquals(t_user_id,r1.getUser_id());
		
	}
	

	public void testSelectByUserID() throws SQLException {
		
		Review r1=ReviewTableGateway.SelectByUserID(8);
		assertEquals(t_description,r1.getDescription());
		assertEquals(t_property_id,r1.getProperty_id());
		assertEquals(t_review_id,r1.getReview_id());
		assertEquals(t_score,r1.getScore());
		assertEquals(t_user_id,r1.getUser_id());
	}
	
	public void testInsertReivew() throws SQLException{
		Review r1= new Review(t_review_id, t_score, t_description, t_property_id, t_user_id);
		int r=ReviewTableGateway.insertReviewTable(r1);
		assertEquals(6,r);
	}

	
	public void testUpdatScore() throws SQLException{
		boolean r=ReviewTableGateway.updateScore(6, 4);
		assertEquals(true,r);
	}
	
//	@Test
	public void testUpdatDescription() throws SQLException{
		boolean r=ReviewTableGateway.updateDescription(6, "description is updated");
		assertEquals(true,r);
	}
	@Test
	public void testSetScoreToZero() throws SQLException{
		ReviewTableGateway.setScoreToZero(123);
	}
	
}
