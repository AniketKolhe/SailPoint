 
  import sailpoint.object.*;
  import java.util.*;

  
  Custom InCorrectCustom = context.getObjectByName(Custom.class, "Custom - RiteAid - Users with Incorrect RACF mapping in Workday"); 
  List IncorrectMappingListlist = InCorrectCustom.getList("Identity Usernames");
  
  
 String identityName = String.valueOf(identity.getName()); 
 
    List userIDs = new ArrayList(); // Use generics for ArrayList

    List links = identity.getLinks();// Avoid variable shadowing
  
 

    for (Link link : links) {
      if ("RACF".equalsIgnoreCase(link.getApplicationName())) { // Use  for logical AND
        String userId = (String) link.getAttribute("USER_ID");
        if (userId != null) {
          userIDs.add(userId);
        }
      }
    }
   if (userIDs.isEmpty()) {
	   
      return "";
	  
    } else if (userIDs.size() == 1) {
		
		
      return userIDs.get(0); // Return the only userID
	  
	  
    } else {
		
		if(IncorrectMappingListlist.contains(identityName)){
      
	  String primaryUserId = null;
      for (Link link : links) { // Iterate through links to find the primary user ID
        if (link != null &amp;&amp; "RACF".equals(link.getApplicationName())) { 
          String data = (String) link.getAttribute("DATA"); // Cast attribute to String
          if (data != null &amp;&amp; !data.contains("AID -")) {
            primaryUserId = (String) link.getAttribute("USER_ID");
            return primaryUserId;
            break; // Once primary userID is found, break the loop
          }
        }
      }

      if (primaryUserId != null) {
        return primaryUserId; // Return the primary user ID
      }
    

  }else{
	   
	   for (Link link : links) {
      if ("RACF".equalsIgnoreCase(link.getApplicationName())) { // Use  for logical AND
        String userId = (String) link.getAttribute("USER_ID");
        if (userId != null) {
          return userId;
		  break;  
        }
      }
    }
	  
  }
	}