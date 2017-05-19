package db.dataMapper;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import db.databaseTableGateway.ReviewTableGateway;
import db.databaseTableGateway.UserTableGateway;

public class ReviewMapper extends AbstractMapper<Review>    {

	public static Logger logger = LogManager.getLogger(DataMapperTest.class);
	
	
	/**
	 * Search review object based on ID in database
	 */
	public Review load(int review_id) throws SQLException{ //load function here 
			Review r1=ReviewTableGateway.SelectByReviewID(review_id);
			//put in mapper
			
			loadedMap.put(r1.getReview_id(),r1);
			return r1;
		}
	
	/**
	 * call abstract find 
	 * @param review_id
	 * @return
	 * @throws SQLException
	 */
	public Review find(int review_id) throws SQLException{
		
		return (Review) abstractFindFromID(review_id);
		
	}
	
	/**
	 * insert review into database and data mapper 
	 */
	public boolean insert(Review r1)throws SQLException{ 
		boolean out=false;
		int key=0;
		//insert in database
	
		key= ReviewTableGateway.insertReviewTable(r1);

		//put in db.dataMapper
		if(r1!=null){
				loadedMap.put(key, r1);
				out=true;
			}
			else{
				logger.error("Error: fail to put in loadMap");
			}	
		
		return out;
	}
	
	
	/**
	 * update review score based on review_id , in database and data mapper 
	 * @param review_id_in
	 * @param newScore
	 * @return
	 * @throws SQLException
	 */
	public boolean updateScore(int review_id_in,int newScore ) throws SQLException{
		//TODO: update in the table
		return ReviewTableGateway.updateScore(review_id_in, newScore);
		
	}
	
	
	/**
	 * update review description based on review_id, in database and data mapper
	 * @param review_id_in
	 * @param newDes
	 * @return
	 * @throws SQLException
	 */
	public boolean updateDescription(int review_id_in,String newDes ) throws SQLException{
		//TODO: update in the table
		return ReviewTableGateway.updateDescription(review_id_in, newDes);
		
	}














@Override
protected String findStatement() {
	// TODO Auto-generated method stub
	return null;
}

@Override
protected String insertStatement() {
	// TODO Auto-generated method stub
	return null;
}

@Override
protected String updateEmailStatement() {
	// TODO Auto-generated method stub
	return null;
}


}
