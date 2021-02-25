package model;


import java.io.File;
import java.util.ArrayList;

/**
 * User object include username, album list and admin indicator
 */
public class User {
	
    
    String username;
    ArrayList<Album> gallery = new ArrayList<Album>();
    boolean isAdmain;

    /**
     * construct user with given name
     * @param username username String
     */
    public User(String username) {
        this.username = username;
    }

    /**
     * getter for album list
     * @return album list
     */
    public ArrayList<Album> getGallery(){
    	return this.gallery;
    }

    /**
     * getter for isAdmain
     * @return boolean value that indicate if this user is admin
     */
    public boolean isAdmain() {
        return isAdmain;
    }

    /**
     * getter for username
     * @return username String
     */
    public String getName() {
        return username;
    }

    /**
     * add a new album into album list
     * @param album album object
     */
    public void addAlbum(Album album){
        gallery.add(album); 
    }

    /**
     * delete album with given index
     * @param index album index in Arraylist
     * @return boolean value that indicate if deletion success
     */
    public Boolean deleteAlbum(int index) {
    	String targetAlbumName = gallery.get(index).getName();
    	gallery.remove(index);
    	//修改文件夹
    	return deleteDir(new File("src/users/"+username+"/"+targetAlbumName));
    	
    }

    /**
     * setter for isAdmain
     * @param isAdmain true or false
     */
    public void setAdmain(boolean isAdmain) {
        this.isAdmain = isAdmain;
    }

    /**
     * Delete file at given directory
     * @param dir file directory
     * @return boolean value that indicate if the deletion is successful
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir
                (new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        if(dir.delete()) {
            //System.out.println("目录已被删除！");
            return true;
        } else {
            //System.out.println("目录删除失败！");
            return false;
        }
    }
    
}
