package db.dataMapper;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class ReviewMapperTest {
	int t_review_id=1;
	int t_score=3;
	String t_description="this is a description";
	int t_property_id=123;
	int t_user_id=8;
	public static Logger logger = LogManager.getLogger(DataMapperTest.class);
	
	
	@Test
	public void testFind() throws SQLException {
		ReviewMapper rm1=new ReviewMapper();
		Review r1=rm1.find(1);
		assertEquals(t_description,r1.getDescription());
		assertEquals(t_property_id,r1.getProperty_id());
		assertEquals(t_review_id,r1.getReview_id());
		assertEquals(t_score,r1.getScore());
		assertEquals(t_user_id,r1.getUser_id());
	}
	
	@Test
	public void testInsert() throws SQLException{
		ReviewMapper rm1=new ReviewMapper();
		Review r1= new Review(t_review_id, t_score, t_description, t_property_id, t_user_id);
		boolean b1=rm1.insert(r1);
		assertTrue("insert fail",b1);

	}
	
	@Test
	public void testUpdate() throws SQLException{
		ReviewMapper rm1=new ReviewMapper();
		boolean b1=rm1.updateDescription(6, "1");
		boolean b2=rm1.updateScore(6, 1);
		assertTrue("insert fail",b1);
		assertTrue("insert fail",b2);


		
	}

}
