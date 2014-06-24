import java.util.Date;

import connection.*;
import moderation.*;

public class Main {

	public static void main(String[] args) {
		FacebookAdapter fb = new FacebookAdapter();
		Moderator moderator = new Moderator("102938293154037",fb);
		
		moderator.checkFeed();
		moderator.printStadistics();
	}

}
