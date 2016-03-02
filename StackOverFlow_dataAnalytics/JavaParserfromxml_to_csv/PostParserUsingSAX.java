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

public class PostParserUsingSAX extends DefaultHandler {
	List<Post> rows;
	String postXmlFileName;
	String tmpValue;
	Post postTmp;
	BufferedWriter bw;
	FileWriter fw;
	File f;

	public PostParserUsingSAX(String bookXmlFileName) throws IOException {
		f = new File("posts_parsed.txt");
		fw = new FileWriter(f);
		bw = new BufferedWriter(fw);

		this.postXmlFileName = bookXmlFileName;
		rows = new ArrayList<Post>();
		parseDocument();
		printDatas();
	}

	private void parseDocument() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			parser.parse(postXmlFileName, this);
		} catch (ParserConfigurationException e) {
			System.out.println("ParserConfig error");
		} catch (SAXException e) {
			System.out.println("SAXException : xml not well formed");
		} catch (IOException e) {
			System.out.println("IO error");
		}
	}

	private void printDatas() throws IOException {
		fw.write("Id"+","+"PostTypeId"+","+"ParentId"+","
				+"AcceptedAnswerId"+","+"CreationDate"
				+","+"Score"+","+"ViewCount"+","
				+"OwnerUserId"+","
				+"LastEditorUserId"+","
				+"LastEditorDisplayName"+","
				+"LastEditDate"+","+"LastActivityDate"
				+","+"Tags"+","
				+"AnswerCount"+","+"CommentCount"
				+","+"FavoriteCount"+","
				+"CommunityOwnedDate"+"\n");
		for (Post tmpP : rows) {
			// System.out.println(tmpP.toString());
			fw.write(tmpP.getId() + "," + tmpP.getPostTypeId() +","+ tmpP.getParentId()+ ","
					+ tmpP.getAcceptedAnswerId() + "," + tmpP.getCreationDate()
					+ "," + tmpP.getScore() + "," + tmpP.getViewCount() + ","
					  + tmpP.getOwnerUserId() + ","
					+ tmpP.getLastEditorUserId() + ","
					+ tmpP.getLastEditorDisplayName() + ","
					+ tmpP.getLastEditDate() + "," + tmpP.getLastActivityDate()
					+ "," + tmpP.getTags() + ","
					+ tmpP.getAnswerCount() + "," + tmpP.getCommentCount()
					+ "," + tmpP.getFavoriteCount() + ","
					+ tmpP.getCommunityOwnedDate() + "\n");
		
		}
		fw.close();
	}

	@Override
	public void startElement(String s, String s1, String elementName,
			Attributes attributes) throws SAXException {
		// if current element is book , create new book
		// clear tmpValue on start of element

		if (elementName.equalsIgnoreCase("row")) {
			postTmp = new Post();
			postTmp.setId(attributes.getValue("Id"));
			postTmp.setPostTypeId(attributes.getValue("PostTypeId"));
			postTmp.setParentId(attributes.getValue("ParentId"));
			postTmp.setAcceptedAnswerId(attributes.getValue("AcceptedAnswerId"));
			postTmp.setCreationDate(attributes.getValue("CreationDate"));
			postTmp.setScore(attributes.getValue("Score"));
			postTmp.setViewCount(attributes.getValue("ViewCount"));
		//	postTmp.setBody(attributes.getValue("Body"));
			postTmp.setOwnerUserId(attributes.getValue("OwnerUserId"));
			postTmp.setLastEditorUserId(attributes.getValue("LastEditorUserId"));
			postTmp.setLastEditorDisplayName(attributes
					.getValue("LastEditorDisplayName"));
			postTmp.setLastEditDate(attributes.getValue("LastEditDate"));
			postTmp.setLastActivityDate(attributes.getValue("LastActivityDate"));
		//	postTmp.setTitle(attributes.getValue("Title"));
			postTmp.setTags(attributes.getValue("Tags"));
			postTmp.setAnswerCount(attributes.getValue("AnswerCount"));
			postTmp.setCommentCount(attributes.getValue("commentCount"));
			postTmp.setFavoriteCount(attributes.getValue("FavoriteCount"));
			postTmp.setCommunityOwnedDate(attributes
					.getValue("CommunityOwnedDate"));

		}
	}

	@Override
	public void endElement(String s, String s1, String element)
			throws SAXException {
		// if end of book element add to list
		if (element.equals("row")) {
			rows.add(postTmp);
		}
	}

	@Override
	public void characters(char[] ac, int i, int j) throws SAXException {
		tmpValue = new String(ac, i, j);
	}

	public static void main(String[] args) throws IOException {
		new PostParserUsingSAX("/Users/pramod/Downloads/programmers.stackexchange.com/Posts.xml");
	}
}
