# Status of Logged in Users

## Overview 
This is based on the user story: "As an admin, I wish to see currently logged in users so that is becomes easier to notify  
them of any potential destructive updates to the kit."  
Currently, there is no way to see if all users are connected to the kit. The only metric that is tracked are login times.  
This is quite cumbersome since we would like users to be notifed when there is a kit update as to prevent them from losing  
data. Currently in SessionService.java, it creates your own singular session that only has time and login time. It also  
doesn't track when a user has logged out. Here are some points as to hopefully guide you in how to track currently logged  
in users.
  
  
### TODO  
Figure out a way to save cookies in a list. This cookie can be tied to a session. The IP address can be utilized, however  
we are unaware whether this is different throughout users logging onto the same kit. Here we can save it on a table as well 
as keep a list of active users that only the admin can access. Then, whenever a user is logged out or timed out due to inactivity,
the list would be updated, maintaining the active users list and thus would be reflected on the table.  
First, take a look at SessionService.java and its complementary classes. Then, see if there are any libraries that you can make
use of. Hopefully it won't be too much and it's just a simple list an admin can only access.