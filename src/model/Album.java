package model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * album object include username it belongs to, album name, date range of photos in it, corresponding folder path,
 * arraylist of pictures in it.
 * @author yl1160
 * @author yz475
 */
public class Album{
	
	
	User currentUser;
    String name;
    String rangeOfDate; //earliest and latest date
    String path;
    String earlistDateFormat="-";
    String latestDateFormat="-";
    long earliestDate = Long.MAX_VALUE;
    long latestDate = Long.MIN_VALUE;
    ArrayList<PictureFile> photoCollection = new ArrayList<PictureFile>();
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

    /**
     * default constructor
     * @param name album name
     */
    public Album(String name){
 
        this.name = name;
        
    }

    /**
     * getter for pictureFiles
     * @return photoCollection of this album
     */
    public ArrayList<PictureFile> getPhotoCollection() {
        return photoCollection;
    }

    /**
     * setter for album name
     * @param name new name for album
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter for its userName it belongs to
     * @param c user object pointer
     */
    public void setUser(User c) {
    	this.currentUser = c;
    }

    /**
     * setter for folder of this album
     * @return
     */
    public boolean setFolder() {
    	this.path = "src/users/" + currentUser.getName()+"/"+this.name;
    	File temp = new File(this.path);
    	Boolean success = temp.mkdir();
    	//String parent = temp.getPath();
    	//System.out.println("Path: "+parent + " \nSuccess: "+success);
    	return success;
    }

    /**
     * add photo to this album
     * @param p pictureFile pointer
     */
    public void addPhoto(PictureFile p) {
    	photoCollection.add(p);
    	
    	updateLastModifiedDate();
    	
    	rangeOfDate = earlistDateFormat+"--"+ latestDateFormat;
    }

    /**
     * delete given photo in album
     * @param p PictureFile pointer
     */
    public void deletePhoto(PictureFile p){
        photoCollection.remove(p);
        updateLastModifiedDate();
        rangeOfDate = earlistDateFormat+"--"+ latestDateFormat;
   }

    /**
     * check all photos and update LastModifiedDate
     */
   	private void updateLastModifiedDate() {
	   earliestDate = Long.MAX_VALUE;
	   latestDate = Long.MIN_VALUE;
	   
	   if(photoCollection.size()==0) {
		   earlistDateFormat = "";
		   latestDateFormat = "";
		   return;
	   }
	   
	   
	   for (int i = 0; i<photoCollection.size(); i++) {
		   
		   
		   if( photoCollection.get(i).getLastModifiedDate() < earliestDate) {
	    		this.earliestDate  = photoCollection.get(i).getLastModifiedDate();
	    		this.earlistDateFormat = sdf.format(earliestDate);
	    	}
	    	
	    	if(photoCollection.get(i).getLastModifiedDate()>latestDate) {
	    		this.latestDate = photoCollection.get(i).getLastModifiedDate();
	    		this.latestDateFormat = sdf.format(latestDate);
	    	}
	   }
	   
   }

    /**
     * get the number of photos in this album
     * @return photo number
     */
    public int getSize() {
    	return photoCollection.size();
    }

    /**
     * getter for the range of Date of all photos in this album
     * @return range of date String
     */
    public String getRangeOfDate() {
    	
    	if(this.rangeOfDate == null ) {
    		return ("--");
    	}else {
    		return rangeOfDate;
    	}
    	
    }

    /**
     * getter for the User
     * @return user pointer
     */
    public User getUser() {
    	return this.currentUser;
    }

    /**
     * getter for the owner's name
     * @return String of user's name
     */
    public String getName() {
    	return this.name;
    }
    
    
    
 
    

}

