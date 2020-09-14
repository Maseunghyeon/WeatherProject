package com.ict.test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.tomcat.jdbc.pool.DataSource;

public class Test2 {

	public static void main(String[] args) throws IOException, TasteException {
														// 여기서 쓰인 File객체는 java.io의 File클래스 객체이다
		DataModel model = new FileDataModel(new File("C:\\Users\\ict01-07\\Desktop\\200914\\csv\\sampledata.csv"));
		//DataModel model = loadFromDB();										
		// C:\Users\MAIN\Desktop\test\csv\mahout_dataset_1.csv
		UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
		UserNeighborhood neighborhood = new NearestNUserNeighborhood(5, similarity, model);
		UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
		//Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
	
		List<RecommendedItem> recommendations = recommender.recommend(5,2);
		
		// (1) 2번유저에게 아이템3개 추천하기 테스트
		
		for (RecommendedItem recommendation : recommendations) {
			System.out.println(recommendation);	
		}
		
	}// end Main 메소드
	
	
	
	
	/*public static DataModel loadFromDB() throws IOException {

		DataSource dbsource = new DataSource();
		// 내 oracle DB명: XE
		dbsource.setUsername("ict03");
		dbsource.setPassword("ict03");	
		dbsource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dbsource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		
		DataModel dataModelDB = new MySQLJDBCDataModel(dbsource, "tbl_user_menu_hit", "u_idx", "m_idx", "hit", "reg_date");
	
		return dataModelDB;
	}// end loadFromDB()
	*/
	
	
}
