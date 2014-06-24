package connection;

import java.util.Date;

import facebook4j.*;
import facebook4j.auth.AccessToken;

public class FacebookAdapter {

	private Facebook facebook;
	
	
	public FacebookAdapter(){
		String appId = "";
		String appSecret = "";
		
		String permissions = "user_groups,publish_actions";
		String accessToken = "CAACEdEose0cBAFBDFRpGpG9XPWm4xpTLsTbzZBXIlKT7ETQU928juZCZCNffrvRMAenvSUzn0mtmpcnhizBGyiiVdwlGQtuYPwvULBKSnboBZCSaHph2XAGCJZARw3hRgqu0ZBqPfyjDmuUGVR3ZB6esMXWZCNURsrdVZBZCLuTuMIW5fwZCibw7FLm3D6ftdo57HNmH6Y525Ay1wZDZD";
		
		facebook = new FacebookFactory().getInstance();
		
		facebook.setOAuthAppId(appId, appSecret);
		facebook.setOAuthPermissions(permissions);
		facebook.setOAuthAccessToken(new AccessToken(accessToken, null));
	}
	
	
	public ResponseList<Post> getGroupFeed(String group, Date since) throws FacebookException{
		Reading reading = new Reading().since(since);
		return facebook.getGroupFeed(group,reading);
	}
	
	
	public void sendMessageToPost(String postId, String message) throws FacebookException{
		facebook.commentPost(postId, message);
	}
	
}
