package nl.digischool.wrts.api;

import java.io.StringWriter;
import java.util.ArrayList;

import nl.digischool.wrts.objects.Word;
import nl.digischool.wrts.objects.WordList;

import org.xmlpull.v1.XmlSerializer;

import android.os.AsyncTask;
import android.util.Xml;

public class XmlBuilder {
	
	private WordList dataList;
	
	/**
	 * Create new XmlBuilder without specified WordLst
	 */
	public XmlBuilder() {
		dataList = new WordList();
	}
	
	/**
	 * Create new XmlBuilder with specified WordList
	 * @param list
	 */
	public XmlBuilder(WordList list) {
		dataList = list;
	}
	
	/**
	 * Specify WordList title
	 * @param title
	 */
	public void setTitle(String title) {
		dataList.title = title;
	}
	
	/**
	 * Specify WordList id
	 * @param id
	 */
	public void setId(String id) {
		dataList.id = id;
	}
	
	/**
	 * Specify WordList language using string lang_id
	 * @param lang_id (a to j)
	 * @param lang
	 */
	public void setLang(String lang_id, String lang) {
		this.setLang("abcdefghij".indexOf(lang_id), lang);
	}
	
	/**
	 * Specify WordList language using lang_index
	 * @param lang_index (0 to 9)
	 * @param lang
	 */
	public void setLang(Integer lang_index, String lang) {
		switch(lang_index) {
			case 0:
				dataList.lang_a = lang;
				break;
			case 1:
				dataList.lang_b = lang;
				break;
			case 2:
				dataList.lang_c = lang;
				break;
			case 3:
				dataList.lang_d = lang;
				break;
			case 4:
				dataList.lang_e = lang;
				break;
			case 5:
				dataList.lang_f = lang;
				break;
			case 6:
				dataList.lang_g = lang;
				break;
			case 7:
				dataList.lang_h = lang;
				break;
			case 8:
				dataList.lang_i = lang;
				break;
			case 9:
				dataList.lang_j = lang;
				break;
		} 
	}
	
	/**
	 * Specify WordList words ArrayList containing all Word objects for the list
	 * @param words
	 */
	public void setWords(ArrayList<Word> words) {
		dataList.words = words;
	}
	
	/**
	 * Return WordList
	 * @return
	 */
	public WordList getList() {
		return dataList;
	}

	/**
	 * Return generated XML using WordList as source
	 * @return
	 */
	public String buildXml() {
		String returnXml = null;
				
		try {
			returnXml = new AsyncTask<Void, Void, String>() {

				@Override
				protected String doInBackground(Void... params) {
					XmlSerializer serializer = Xml.newSerializer();
				    StringWriter writer = new StringWriter();
				    try {
				        serializer.setOutput(writer);
				        serializer.startTag("", "list");
				        
				        	if(dataList.id != null) {
					        	serializer.startTag("", "id");
					        	serializer.text(dataList.id);
					        	serializer.endTag("", "id");
				        	}
				        	
				        	serializer.startTag("", "title");
				        	serializer.text(dataList.title);
				        	serializer.endTag("", "title");
				        
				        	String[] languages = new String[]{ dataList.lang_a, dataList.lang_b, dataList.lang_c, dataList.lang_d, dataList.lang_e, dataList.lang_f, dataList.lang_g, dataList.lang_h, dataList.lang_i, dataList.lang_j };
				        	for(int i = 0; i < languages.length; i++) {
				        		String str = languages[i];
				        		char current_lang = "abcdefghij".charAt(i);
				        		if(str != null) {
				        			serializer.startTag("", "lang-"+current_lang);
						        	serializer.text(str);
						        	serializer.endTag("", "lang-"+current_lang);
				        		}
				        	}
				        	
				        	if(dataList.words != null) {
					        	ArrayList<Word> wordsData = dataList.words;
					        	for(int i = 0; i < wordsData.size(); i++) {
					        		Word word = wordsData.get(i);
					        		String[] words = new String[]{ word.word_a, word.word_b, word.word_c, word.word_d, word.word_e, word.word_f, word.word_g, word.word_h, word.word_i, word.word_j };
						        	for(int j = 0; j < words.length; j++) {
						        		String str = words[j];
						        		char current_word = "abcdefghij".charAt(j);
						        		if(str != null) {
						        			serializer.startTag("", "word-"+current_word);
								        	serializer.text(str);
								        	serializer.endTag("", "word-"+current_word);
						        		}
						        	}
						        	
					        	}
				        	}
				        	
				        serializer.endTag("", "list");
				        serializer.endDocument();
				        return writer.toString();
				    } catch (Exception e) {
				        e.printStackTrace();
				    }
					return null;
				}
				
			}.execute().get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    return returnXml;
	}
	
}
