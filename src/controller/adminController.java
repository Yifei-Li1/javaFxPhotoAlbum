package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * Controller for admin user. Function include add user and delete user.
 * @author yz745
 * @author yl1160
 */
public class adminController implements Initializable {
	
    @FXML  TextField usernameInput;
    @FXML  Button add;
    @FXML  Button delete;
    @FXML  Button quit; 
    @FXML  ListView<String> userList;

    /**
     * Quit method that quit the program and save changes
     * @param e button onClick event
     * @throws IOException will never happen if program excute normally
     */
    public void quit(ActionEvent e) throws IOException{
    	Button b = (Button) e.getSource();
    	if(b==quit) {
   
    		Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
			stage.close();
			System.out.println("SUCCESS: QUIT");
    	}else {
    		System.out.println("FAIL: QUIT: UNKNOWN");
    	}
    }

    /**
     * Logout method switch admin interface to login window while saving changes
     * @param event button onClick action
     * @throws IOException Should not happen
     */
    public void logout(ActionEvent event) throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        Scene tableView = new Scene(view);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       
        
        window.setScene(tableView);
        window.setTitle("Login");
        window.show();
    }

    /**
     * Add user button can add user into userList with given name in TextField, create user folder in users directory.
     * @param event button onClick
     * @throws IOException FileWriter illegal input
     */
    public void addUser(javafx.event.ActionEvent event) throws IOException{
        File fp = new File("src/users/nameList.txt");

        String username = usernameInput.getText();
        System.out.println(username);
        String pathname = "src/users/"+ username;
        String albumList = pathname + "/gallery.txt";
        System.out.println(pathname);
        System.out.println(albumList);
        
        File tempFile = new File(pathname);
        boolean mkdir = tempFile.mkdir();
        if(!mkdir){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("FAIL: CANNOT CREATE NEW USER");
            a.show();
        }
        else {
            userList.getItems().add(username);
            FileWriter fw = new FileWriter(fp,true);
            BufferedWriter out = new BufferedWriter(fw);
            out.write(username+"\n");
            out.close();
            File userpt = new File(albumList);
            userpt.createNewFile();
        }
        

    }

    /**
     * Delete user from userList, remove user folder.
     * @param event delete button onClick action
     * @throws IOException BufferReader or BufferWriter illegal input
     */
    public void deleteUser(ActionEvent event) throws IOException{
    	Button b = (Button)event.getSource();
    	if(b==delete) {
    		File inputFile = new File("src/users/nameList.txt");
    		File tempFile =  new File("src/users/myTempFile.txt");
    		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
    		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
    		String lineToRemove = userList.getSelectionModel().getSelectedItem();

    		String currentLine;

    		while((currentLine = reader.readLine()) != null) {
    			// trim newline when comparing with lineToRemove
    			String trimmedLine = currentLine.trim();
    			if(trimmedLine.equals(lineToRemove)) continue;
    			writer.write(currentLine + System.getProperty("line.separator"));
    		}
    		writer.close();
    		reader.close();
    		tempFile.renameTo(inputFile);
    		int selectedIdx = userList.getSelectionModel().getSelectedIndex();
    		if (selectedIdx!=-1) userList.getItems().remove(selectedIdx);
    		String pathname = "src/users/"+lineToRemove;
            deleteDir(new File(pathname));
       
    	}else {
    		System.out.println("FAIL: DELETE USER: UNKNOWN");
    	}
        
    }

    /**
     * Delete give file directory
     * @param dir file directory
     * @return boolean value that indicate if deletion success
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
        if (dir.delete()) {
            //System.out.println("目录已被删除！");
            return true;
        } else {
            //System.out.println("目录删除失败！");
            return false;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File fp = new File("src/users/nameList.txt");
        Scanner myReader = null;
        try {
            myReader = new Scanner(fp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> list = new ArrayList<String>();
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            list.add(data);

        }
        ObservableList<String> temp = FXCollections.observableArrayList (list);
        userList.setItems(temp);
        myReader.close();
    }
    
    public static void clearInfoForFile(String fileName) {
        File file =new File(fileName);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
