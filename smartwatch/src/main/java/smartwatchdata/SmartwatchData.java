package smartwatchdata;

public class SmartwatchData {
	
	private int id;
	private int userId;
	private int pas;
	private double tension;
	private double rythmeCardiaque;
	private String sommeil;
	private int calorie;
	
	public SmartwatchData(int id, int userId, int pas, double tension, double rythmeCardiaque, String sommeil, int calorie) {
		this.id = id;
		this.setUserId(userId);
		this.pas = pas;
		this.tension = tension;
		this.rythmeCardiaque = rythmeCardiaque;
		this.sommeil = sommeil;
		this.calorie = calorie;
	}
	
	
	public int getCalorie() {
		return calorie;
	}
	public void setCalorie(int calorie) {
		this.calorie = calorie;
	}
	public String getSommeil() {
		return sommeil;
	}
	public void setSommeil(String sommeil) {
		this.sommeil = sommeil;
	}
	public double getRythmeCardiaque() {
		return rythmeCardiaque;
	}
	public void setRythmeCardiaque(double rythmeCardiaque) {
		this.rythmeCardiaque = rythmeCardiaque;
	}
	public double getTension() {
		return tension;
	}
	public void setTension(double tension) {
		this.tension = tension;
	}
	public int getPas() {
		return pas;
	}
	public void setPas(int pas) {
		this.pas = pas;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}

}
