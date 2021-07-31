package repository;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.ibm.icu.text.Edits.Iterator;
import com.mysql.cj.xdevapi.JsonValue;

import model.collaboratorsModel;
import model.modelUser;

public class provider {
	static Dimension size;
	static String TermsAndCon;
	static Connection connection;
	static Statement statement;
	static ResultSet set;
	static PreparedStatement preparedStatement;
	List<String> eventList;
	Map<String, Boolean> map = new HashMap<String, Boolean>();
	modelUser user;
	String imagePath = "";
	public Color adminColor=new Color(0,153,153);
	public Color partnerColor=new Color(51, 0, 102);
	public Color userColor=new Color(0, 51, 102);
	public Cursor cursor=Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	
	// image Icons
	public ImageIcon search=new ImageIcon(ClassLoader.getSystemResource("search.png"));
	public ImageIcon prev = new ImageIcon(ClassLoader.getSystemResource("prev.png"));
	public ImageIcon next = new ImageIcon(ClassLoader.getSystemResource("next.png"));
	public ImageIcon coloredEmail = new ImageIcon(ClassLoader.getSystemResource("gmail.png"));
	public ImageIcon coloredPhone = new ImageIcon(ClassLoader.getSystemResource("phone.png")); 
	public ImageIcon delete = new ImageIcon(ClassLoader.getSystemResource("delete.png"));	
	public ImageIcon account=new ImageIcon(ClassLoader.getSystemResource("account.png"));
	public ImageIcon email=new ImageIcon(ClassLoader.getSystemResource("mail.png"));
	public ImageIcon password=new ImageIcon(ClassLoader.getSystemResource("password.png"));
	public ImageIcon userIcon=new ImageIcon(ClassLoader.getSystemResource("user.png"));
	public ImageIcon edit=new ImageIcon(ClassLoader.getSystemResource("edit.png"));
	public ImageIcon company=new ImageIcon(ClassLoader.getSystemResource("company.png"));
	public ImageIcon whitePhone=new ImageIcon(ClassLoader.getSystemResource("whitePhone.png"));
	public ImageIcon location=new ImageIcon(ClassLoader.getSystemResource("location.png"));
	public ImageIcon event=new ImageIcon(ClassLoader.getSystemResource("event.png"));
	public ImageIcon info=new ImageIcon(ClassLoader.getSystemResource("info.png"));
	public ImageIcon feedback=new ImageIcon(ClassLoader.getSystemResource("feedback.png"));
	
	// matte border
	public MatteBorder selectionBorder=new MatteBorder(0, 3, 0, 0, (Color) new Color(255, 255, 255));
	public Color menuSelectionColor= new Color(51, 51, 51);
	public Color menuUnSelectedColor=new Color(0, 0, 0);
	
	// no argument constructor
	public provider() {
		// TODO Auto-generated constructor stub
		size = Toolkit.getDefaultToolkit().getScreenSize();
	}

	// method to get screen width
	public int getScreenWidth() {
		return size.width;
	}

	// method to get screen height
	public int getScreenHeight() {
		return size.height;
	}

	// method to validate email using regex
	public boolean validateEmail(String email) {
		String pattern = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		boolean val = email.matches(pattern);
		return val;
	}

	// method to establish Oracle connection
	public Connection getdBConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/evento", "root", "9686");
		return connection;
	}

	// method to close Oracle Connection
	public void CloseConnection() {
		try {
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// method to check if email exists in database
	public Boolean checkEmailExists(Connection con, String email) throws SQLException {
		statement = con.createStatement();
		set = statement.executeQuery("select * from login where email=" + "'" + email + "'");
		return set.next();
	}

	// method to get current date time
	public String getCurrentDateTime() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return format.format(now);
	}

	// method to register user
	public String registerUser(Connection con, modelUser user) throws SQLException {
		String last_L_ID = "", last_U_ID = "";
		statement = con.createStatement();
		set = statement.executeQuery("select *from login");
		if (set.next()) {
			// implement if data exists
			System.out.println("Records Exists");
			set = statement.executeQuery("select login_id,user_id from users order by dateOfCreation Desc limit 1");

			if (set.next()) {
				last_L_ID = set.getString(1);
				last_U_ID = set.getString(2);
			}
			last_L_ID = incrementId(last_L_ID);
			last_U_ID = incrementId(last_U_ID);
			insertRegisterDataTodB(con, user, last_L_ID, last_U_ID);
		} else {
			// implement if no records are there in table
			System.out.println("No Records");
			insertRegisterDataTodB(con, user, "L001", "U001");
		}

		return last_U_ID;
	}

	// method to insert register data into db
	public void insertRegisterDataTodB(Connection con, modelUser user, String login_id, String user_id)
			throws SQLException {
		String query = "insert into login values(?,?,?,?)";
		// insert into login table
		preparedStatement = con.prepareStatement(query);
		preparedStatement.setString(1, login_id);
		preparedStatement.setString(2, user.getEmail());
		preparedStatement.setString(3, user.getPassword());
		preparedStatement.setString(4, user.getUsertype());
		preparedStatement.execute();
		// insert into user table
		String query1 = "insert into users values(?,?,?,?)";
		preparedStatement = con.prepareStatement(query1);
		preparedStatement.setString(1, user_id);
		preparedStatement.setString(2, user.getUsername());
		preparedStatement.setString(3, login_id);
		preparedStatement.setString(4, getCurrentDateTime());
		preparedStatement.execute();
	}

	// method to get image path
	public String getImagePath() {
		return imagePath;
	}

	// method to increment number
	public String incrementId(String id) {
		String returnString;
		int n = Integer.valueOf(id.substring(1));
		n += 1;
		if (String.valueOf(n).length() == 1) {
			returnString = id.substring(0, 1) + "00" + String.valueOf(n);
		} else if (String.valueOf(n).length() == 2) {
			returnString = id.substring(0, 1) + "0" + String.valueOf(n);
		} else {
			returnString = id.substring(0, 1) + String.valueOf(n);
		}
		return returnString;
	}

	// method to login user
	public ResultSet loginUser(Connection con, String email, String password) throws SQLException {
		statement = con.createStatement();
		set = statement.executeQuery(
				"select l.login_id,l.email,l.password,l.type,u.user_id from login l,users u where l.login_id=u.login_id and l.email="
						+ "'" + email + "'" + "and password=" + "'" + password + "'");
		return set;
	}

	// method to pick image
	public Image pickImage() throws Exception {
		Image image = null;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg", "png", "gif");
		fileChooser.addChoosableFileFilter(filter);
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			String path = selectedFile.getAbsolutePath();
			imagePath = path;
			ImageIcon myimage = new ImageIcon(path);
			image = myimage.getImage();
		} else if (result == JFileChooser.CANCEL_OPTION) {
			throw new Exception("Please select a image to proceed");
		}

		return image;
	}

	// method to get event list
	public List<String> getEventsList() {
		eventList = new ArrayList<String>();
		eventList.add("Wedding");
		eventList.add("Party");
		eventList.add("BirthDay Celebration");
		eventList.add("Death Ceremony");
		eventList.add("Travel");
		eventList.add("Naming Ceremony");
		return eventList;
	}

	// method to add items to list
	public void addEvents(String item) {
		eventList.add(item);
	}

	// method to create map of list selected and not selected
	public Map<String, Boolean> getSelectedMap(List<String> selectedItems) {
		map.clear();
		for (String listItem : getEventsList())
			map.put(listItem, false);

		for (String selected : selectedItems)
			map.put(selected, true);
		return map;
	}

	// method to addCompanyDetailsToDb
	public void addCompanyDetailsToDb(Connection con, collaboratorsModel model)
			throws SQLException, FileNotFoundException {
		String query = "insert into collaborators values(?,?,?,?,?,?,?,?,?)";
		preparedStatement = con.prepareStatement(query);
		preparedStatement.setString(1, model.getCompanyId());
		preparedStatement.setString(2, model.getCompanyName());
		preparedStatement.setString(3, model.getAddress());
		preparedStatement.setString(4, model.getPhone());
		preparedStatement.setString(5, model.getEmail());
		InputStream in = new FileInputStream(new File(model.getImageUrl()));
		preparedStatement.setBlob(6, in);
		String json = JSONValue.toJSONString(model.getCompatibilityMap());
		preparedStatement.setString(7, json);
		preparedStatement.setString(8, model.getUserId());
		preparedStatement.setString(9, model.getCurrentDateTime());
		preparedStatement.execute();
	}

	// method to get userid with login id
	public ResultSet getUserId(Connection con, String loginId) throws SQLException {
		statement = con.createStatement();
		set = statement.executeQuery("select user_id from users where login_id=" + "'" + loginId + "'");
		return set;
	}

	// method to validate Phone number
	public boolean validatePhone(String phone) {
		Pattern p = Pattern.compile("[7-9][0-9]{9}");
		Matcher m = p.matcher(phone);
		return (m.find() && m.group().equals(phone));
	}

	public ResultSet selectQuery(Connection con, String query) throws SQLException {
		statement = con.createStatement();
		return statement.executeQuery(query);
	}

	public ArrayList<collaboratorsModel> bindTable(Connection con,String query)
			throws SQLException, JsonMappingException, JsonParseException, IOException {
		ArrayList<collaboratorsModel> list = new ArrayList<collaboratorsModel>();
		list.clear();
		statement = con.createStatement();
		set = statement.executeQuery(query);
		collaboratorsModel model;
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		while (set.next()) {
			ObjectMapper obj = new ObjectMapper();
			map = new ObjectMapper().readValue(set.getString(7), HashMap.class);
			model = new collaboratorsModel(set.getString(1), set.getString(2), set.getString(3), set.getString(4),
					set.getString(5), map, set.getString(8), set.getString(9), set.getBytes(6));
			list.add(model);
		}

		return list;
	}
	
	public void logout() {
		int result = JOptionPane.showOptionDialog(new JFrame(), "Are you sure you want to Logout and Quit ?",
				"Exit from Application ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				new Object[] { "Yes", "No" },JOptionPane.YES_OPTION);
		
		if(result==JOptionPane.YES_OPTION) {
			System.exit(1);
		}else if(result==JOptionPane.NO_OPTION || result==JOptionPane.CLOSED_OPTION){
			JOptionPane.getRootFrame().dispose();
		}
	}
	
	public PreparedStatement updateValue(Connection con,String query) throws SQLException {
		return con.prepareStatement(query) ;
	}
}
