package moderation;

import java.util.Date;

import connection.FacebookAdapter;
import facebook4j.FacebookException;
import facebook4j.Post;
import facebook4j.ResponseList;

public class Moderator {

	private static Date maxSince = new Date();
	private Date lastChecked;
	private String objectId; //Object to moderate

	private int totalCounter;
	private int failCounter;
	
	private FacebookAdapter fb;
	
	public Moderator(String objectId,FacebookAdapter fb){
		totalCounter=0;
		failCounter=0;
		maxSince.setTime(1402802563659L);
		lastChecked = new Date(0L);
		this.objectId = objectId;
		this.fb = fb;
	}
	
	/**
	 * @param s the text
	 * @return false if the string has no hashtag else true
	 * TODO check hashtag from a dictionary
	 */
	public boolean checkHashtag(String s){
		return s.contains("#");
	}
	
	
	/**
	 * Sends a message to the post owner, notifying him/her 
	 * of the hashtag problem  
	 * @param p the faulty post
	 * @return true if it was notified correctly
	 */
	public boolean notifyHashtagPost(Post p ){
		try {
			fb.sendMessageToPost(p.getId(), "¡Hola!\n Por organización te pedimos que le agregues un hashtag a tu post. \nSaludos!");
		} catch (FacebookException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	public boolean checkFeed(){
		Date since;
		if (maxSince.before(lastChecked)){
			since = lastChecked;
		}else{
			since = maxSince;
		}
		try {
			ResponseList<Post> response = fb.getGroupFeed(objectId,since);
			for(Post p : response){
				if (! checkHashtag(p.getMessage())){
					notifyHashtagPost(p);
					failCounter++;
				}
				totalCounter++;
			}
		} catch (FacebookException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}



public void printStadistics(){
	System.out.println(objectId + " Failed= " + failCounter+ "/" + totalCounter);
}

}