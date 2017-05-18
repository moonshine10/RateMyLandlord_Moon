package db.dataMapper;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import db.databaseTableGateway.ReviewTableGateway;
import db.databaseTableGateway.UserTableGateway;

public class ReviewMapper extends AbstractMapper<Review>   implements ResultHandler{

	public static Logger logger = LogManager.getLogger(DataMapperTest.class);

	
	
	public Review load(int review_id) throws SQLException{ //load function here 
			
			
			Review r1=ReviewTableGateway.SelectByReviewID(review_id);
			//put in mapper
			
			loadedMap.put(r1.getReview_id(),r1);
			return r1;
		}
	
	public Review find(int review_id) throws SQLException{
		
		return (Review) abstractFindFromID(review_id);
		
	}
	
	
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
	
	public boolean updateScore(int review_id_in,int newScore ) throws SQLException{
		return ReviewTableGateway.updateScore(review_id_in, newScore);
		
	}
	
	public boolean updateDescription(int review_id_in,String newDes ) throws SQLException{
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
