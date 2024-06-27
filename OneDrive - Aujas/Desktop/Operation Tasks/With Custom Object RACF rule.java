 import sailpoint.object.*;
  import java.util.*;

  Identity identity = context.getObjectByName(Identity.class, "1741107");

  Custom custom = context.getObjectByName(Custom.class, "Custom - RiteAid - Users with More than one primary RACF");

  List list = custom.getList("Identity Usernames");
  String identityName = String.valueOf(identity.getName());  


  return identity.getName();

  if(!list.contains(identityName)){

    List userIDs = new ArrayList(); // Use generics for ArrayList

    List links = identity.getLinks(); // Avoid variable shadowing

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
      String primaryUserId = null;
      for (Link link : links) { // Iterate through links to find the primary user ID
        if (link != null &amp;&amp; "RACF".equals(link.getApplicationName()) &amp;&amp; !link.isDisabled()) { // Use equals for string comparison
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
      } else {
        return ""; // If primary user ID not found, return empty string
      }
    }

  }