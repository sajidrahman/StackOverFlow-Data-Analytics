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

public class VotesParserUsingSAX extends DefaultHandler {
	List<Vote> rows;
	String votesXmlFileName;
	String tmpValue;
	Vote voteTmp;
	BufferedWriter bw;
	FileWriter fw;
	File f;

	public VotesParserUsingSAX(String bookXmlFileName) throws IOException {
		f = new File("votes_parsed.txt");
		fw = new FileWriter(f);
		bw = new BufferedWriter(fw);

		this.votesXmlFileName = bookXmlFileName;
		rows = new ArrayList<Vote>();
		parseDocument();
		printDatas();
	}

	private void parseDocument() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			parser.parse(votesXmlFileName, this);
		} catch (ParserConfigurationException e) {
			System.out.println("ParserConfig error");
		} catch (SAXException e) {
			System.out.println("SAXException : xml not well formed");
		} catch (IOException e) {
			System.out.println("IO error");
		}
	}

	private void printDatas() throws IOException {
		fw.write("Id"+","+"PostId"+","
				+"VoteTypeId"+","+"CreationDate"+"\n");

		for (Vote tmpV : rows) {
			// System.out.println(tmpV.toString());
			fw.write(tmpV.getId() + "," + tmpV.getPostId() + ","
					+ tmpV.getVoteTypeId() + "," + tmpV.getCreationDate() + "\n");
		
		}
		fw.close();
	}

	@Override
	public void startElement(String s, String s1, String elementName,
			Attributes attributes) throws SAXException {
		// if current element is book , create new book
		// clear tmpValue on start of element

		if (elementName.equalsIgnoreCase("row")) {
			voteTmp = new Vote();
			voteTmp.setId(attributes.getValue("Id"));
			voteTmp.setPostId(attributes.getValue("PostId"));
			voteTmp.setVoteTypeId(attributes.getValue("VoteTypeId"));
			voteTmp.setCreationDate(attributes.getValue("CreationDate"));
		}
	}

	@Override
	public void endElement(String s, String s1, String element)
			throws SAXException {
		// if end of book element add to list
		if (element.equals("row")) {
			rows.add(voteTmp);
		}
	}

	@Override
	public void characters(char[] ac, int i, int j) throws SAXException {
		tmpValue = new String(ac, i, j);
	}

	public static void main(String[] args) throws IOException {
		new VotesParserUsingSAX("/Users/pramod/Downloads/programmers.stackexchange.com/Votes.xml");
	}
}
