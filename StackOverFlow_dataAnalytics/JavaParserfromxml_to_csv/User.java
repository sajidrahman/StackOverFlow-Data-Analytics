import java.util.ArrayList;


 

public class User {
    String Id;
    String Reputation;
    String CreationDate;
    String DisplayName;
    String LastAccessDate;
    String WebsiteUrl;
    String Location;
    String AboutMe;
    String Views;
    String UpVotes;
    String DownVotes;
    String AccountId;
    public User(){
    
    }
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getReputation() {
		return Reputation;
	}
	public void setReputation(String reputation) {
		Reputation = reputation;
	}
	public String getCreationDate() {
		return CreationDate;
	}
	public void setCreationDate(String creationDate) {
		CreationDate = creationDate;
	}
	public String getDisplayName() {
		return DisplayName;
	}
	public void setDisplayName(String displayName) {
		DisplayName = displayName;
	}
	public String getLastAccessDate() {
		return LastAccessDate;
	}
	public void setLastAccessDate(String lastAccessDate) {
		LastAccessDate = lastAccessDate;
	}
	public String getWebsiteUrl() {
		return WebsiteUrl;
	}
	public void setWebsiteUrl(String websiteUrl) {
		WebsiteUrl = websiteUrl;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getAboutMe() {
		return AboutMe;
	}
	public void setAboutMe(String aboutMe) {
		AboutMe = aboutMe;
	}
	public String getViews() {
		return Views;
	}
	public void setViews(String views) {
		Views = views;
	}
	public String getUpVotes() {
		return UpVotes;
	}
	public void setUpVotes(String upVotes) {
		UpVotes = upVotes;
	}
	public String getDownVotes() {
		return DownVotes;
	}
	public void setDownVotes(String downVotes) {
		DownVotes = downVotes;
	}
	public String getAccountId() {
		return AccountId;
	}
	public void setAccountId(String accountId) {
		AccountId = accountId;
	}
    
}