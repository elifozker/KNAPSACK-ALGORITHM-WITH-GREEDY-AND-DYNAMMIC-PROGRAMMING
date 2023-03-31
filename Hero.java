package AlgorithmAnalysis;


public class Hero {
	private String name;
	private String statu;
	private int gold;
	private int ap;
	private double cost;
	private int statuNum;
	public Hero(String name, String statu, int gold, int ap) {
		super();
		this.name = name;
		this.statu = statu;
		this.gold = gold;
		this.ap = ap;
	}
	public Hero(String name, String statu, int gold, int ap,double cost,int statuNum) {
		super();
		this.name = name;
		this.statu = statu;
		this.gold = gold;
		this.ap = ap;
		this.cost = cost;
		this.statuNum = statuNum;
	}
	
	public int getStatuNum() {
		return statuNum;
	}
	public void setStatuNum(int statuNum) {
		this.statuNum = statuNum;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatu() {
		return statu;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getAp() {
		return ap;
	}
	public void setAp(int ap) {
		this.ap = ap;
	}

	
	

}
