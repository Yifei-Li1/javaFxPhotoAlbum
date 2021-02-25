package model;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * PictureFile object, represent the each photo, include name, date, filePath, caption, Tags, and LastModifiedDate of this photo
 * @author yl1160
 * @author yz745
 */
public class PictureFile{
	
	private String name;
	private String date;
	private File imageFile;
	private String caption;
	private ArrayList<Tags> tags = new ArrayList<>();
	private long lastModifiedDate;
	String day;
	String year;
	String month;
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	SimpleDateFormat monthS = new SimpleDateFormat("MM");
	SimpleDateFormat dayS = new SimpleDateFormat("dd");
	SimpleDateFormat yearS = new SimpleDateFormat("yyyy");

	/**
	 * create a empty photo
	 */
	public PictureFile() {
		//design for empty picture
	}

	/**
	 * Create PictureFile with picture info
	 * @param imageFile .txt file that store picture info
	 */
	public PictureFile(File imageFile) { 
		
		this.imageFile = imageFile;
		this.name = imageFile.getName();
		this.date = sdf.format(imageFile.lastModified());
		this.lastModifiedDate = imageFile.lastModified();
		this.day = dayS.format(imageFile.lastModified());
		this.month = monthS.format(imageFile.lastModified());
		this.year = yearS.format(imageFile.lastModified());
		if(name.equals("")) {
			name = imageFile.getPath();
		}
	}

	/**
	 * Create PictureFile with given file path
	 * @param imagePath
	 */
    public PictureFile(String imagePath) {
    	this(new File(imagePath));
    }

	/**
	 * set caption for this photo
	 * @param caption caption String
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/**
	 * get caption String
	 * @return caption String
	 */
	public String getCaption() {
		return caption;
	}

	public boolean isPicture() {
    	if(this.name.toLowerCase().endsWith(".jpg")||
    	   this.name.toLowerCase().endsWith(".jpge")||
    	   this.name.toLowerCase().endsWith(".png")||
    	   this.name.toLowerCase().endsWith(".bmp")||
    	   this.name.toLowerCase().endsWith(".gif")	) {
    		return true;
    	}
    	return false;
    }

	/**
	 * Get directory for current photo
	 * @return
	 */
	public boolean isDirectory() {
    	return imageFile.isDirectory();
    }

	/**
	 * get isFile indicator
	 * @return boolean for isFile
	 */
	public boolean isFile() {
    	return imageFile.isFile();
    }

	/**
	 * get the file's content length
	 * @return file length of picture
	 */
	public long length() {
    	return imageFile.length();
    }

	/**
	 * getter for name of this pictureFile
	 * @return name String of PictureFile
	 */
	public String toString() {
		return name;
	}

	/**
	 * getter for lastModifiedDate
	 * @return long for lastModifiedDate
	 */
	public long getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * setter for lastModifiedDate
	 * @param lastModifiedDate long value of lastModifiedDate
	 */
	public void setLastModifiedDate(long lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * getter for date
	 * @return date
	 */
	public String getDate() {
    	return this.date;
    }

	/**
	 * getter for image File pointer
	 * @return File pointer
	 */
	public File getImageFile() {
		return imageFile;
	}

	/**
	 * getter for picture name
	 * @return name String
	 */
	public String getName() {
		return name;
	}

	/**
	 * getter for Tags arrayList
	 * @return tag arrayList
	 */
	public ArrayList<Tags> getTags() {
		return tags;
	}

	/**
	 * add Tag to Tags arrayList
	 * @param name name of Tag
	 * @param value value of Tag
	 */
	public void addTags(String name,String value){
		tags.add(new Tags(name,value));
	}

	/**
	 * remove given tag from Tags
	 * @param name name of tag
	 * @param value value of tag
	 */
	public void removeTags(String name,String value){
		tags.removeIf(tag->tag.getName().compareTo(name)==0 &&tag.getValue().compareTo(value)==0);
	}

	/**
	 * setter for picture name
	 * @param name name String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * setter for imageFile
	 * @param file file pointer
	 */
	public void setImageFile(File file) {
		this.imageFile = file;
	}

	/**
	 * setter for tags arrayList
	 * @param tags arrayList of tags
	 */
	public void setTags(ArrayList<Tags> tags) {
		this.tags = tags;
	}

	/**
	 * copy this PictureFile to a new PictureFile
	 * @return a identical pictureFile
	 */
	public PictureFile copyPicture() {
		
		PictureFile temp = new PictureFile(this.getImageFile());
		
		temp.setCaption(this.getCaption());
		temp.setLastModifiedDate(this.getLastModifiedDate());
		temp.setName(this.getName());
		temp.setTags(this.getTags());
		
		return temp;
	}

	/**
	 * getter for Day String
	 * @return Day String
	 */
	public String getDay() {
		return day;
	}

	/**
	 * getter for Year String
	 * @return Year String
	 */
	public String getYear() {
		return year;
	}

	/**
	 * getter for Month String
	 * @return Month String
	 */
	public String getMonth() {
		return month;
	}
	
	
}

