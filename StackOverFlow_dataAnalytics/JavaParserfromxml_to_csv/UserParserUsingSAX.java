import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class UserParserUsingSAX extends DefaultHandler {
    List<User> rows;
    String userXmlFileName;
    String tmpValue;
    User userTmp;
    BufferedWriter bw;
    FileWriter fw;
    File f;
    public UserParserUsingSAX(String bookXmlFileName) throws IOException {
        f = new File("users-parsed.csv");
    	 fw = new FileWriter(f);
      bw  = new BufferedWriter(fw);

    	this.userXmlFileName = bookXmlFileName;
        rows = new ArrayList<User>();
        parseDocument();
        printDatas();
    }
    private void parseDocument() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(userXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }
    private void printDatas() throws IOException {
      fw.write("Id"+","+"Reputation"+","+"Location"+","+"CreationDate"+","+"DisplayName"+","+"LastAccessDate"+","+"Views"+","+"Upvotes"+","+"Downvotes"+","+"AccountId"+"\n");
        for (User tmpB : rows) {
          //  System.out.println(tmpB.toString());
   fw.write(tmpB.getId()+","+tmpB.getReputation()+","+tmpB.getLocation()+","+tmpB.getCreationDate()+","+tmpB.getDisplayName()+","+tmpB.getLastAccessDate()+","+tmpB.getViews()+","+tmpB.getUpVotes()+","+tmpB.getDownVotes()+","+tmpB.getAccountId()+"\n");
          //fw.write(tmpB.getId()+","+tmpB.getReputation()+","+tmpB.getDisplayName()+","+tmpB.getViews()+","+tmpB.getUpVotes()+","+tmpB.getDownVotes()+","+"\n");

        }
        fw.close();
    }
    @Override
    public void startElement(String s, String s1, String elementName, Attributes attributes) throws SAXException {
        // if current element is book , create new book
        // clear tmpValue on start of element
 
        if (elementName.equalsIgnoreCase("row")) {
        	userTmp = new User();
        	userTmp.setId(attributes.getValue("Id"));
        	userTmp.setReputation(attributes.getValue("Reputation"));
        	userTmp.setCreationDate(attributes.getValue("CreationDate"));
        	userTmp.setDisplayName(attributes.getValue("DisplayName"));
        	userTmp.setLastAccessDate(attributes.getValue("LastAccessDate"));
        	userTmp.setViews(attributes.getValue("Views"));
        	userTmp.setUpVotes(attributes.getValue("UpVotes"));
        	userTmp.setDownVotes(attributes.getValue("DownVotes"));
        	userTmp.setAccountId(attributes.getValue("AccountId"));
        	userTmp.setLocation((attributes.getValue("Location"))!=null?(attributes.getValue("Location").contains(",")?
        			attributes.getValue("Location").substring(attributes.getValue("Location").indexOf(",")+1):attributes.getValue("Location"))
        			:null);

        }
    }
    @Override
    public void endElement(String s, String s1, String element) throws SAXException {
        // if end of book element add to list
        if (element.equals("row")) {
            rows.add(userTmp);
        }
    }
    @Override
    public void characters(char[] ac, int i, int j) throws SAXException {
        tmpValue = new String(ac, i, j);
    }
    public static void main(String[] args) throws IOException {
        new UserParserUsingSAX("/Users/pramod/Downloads/programmers.stackexchange.com/Users.xml");
    }
}
