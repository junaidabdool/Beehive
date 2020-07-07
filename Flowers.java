package Beehive;

public class Flowers {
private  String flowerName;
private  int nectarCollection ;
private  int dailyNectarProduction;
private  int currentNectarAvailable;


public Flowers(String flowerName , int nectarCollection , int dailyNectarProduction , int currentNectarAvailable) {
	this.flowerName=flowerName;
	this.nectarCollection=nectarCollection;
	this.dailyNectarProduction=dailyNectarProduction;
	this.currentNectarAvailable=currentNectarAvailable;
	
}


public String getFlowerName() {
	return flowerName;
}


public void setFlowerName(String flowerName) {
	this.flowerName = flowerName;
}


public int getNectarCollection() {
	return nectarCollection;
}


public void setNectarCollection(int nectarCollection) {
	this.nectarCollection = nectarCollection;
}


public int getDailyNectarProduction() {
	return dailyNectarProduction;
}


public void setDailyNectarProduction(int dailyNectarProduction) {
	this.dailyNectarProduction = dailyNectarProduction;
}


public int getCurrentNectarAvailable() {
	return currentNectarAvailable;
}


public void setCurrentNectarAvailable(int currentNectarAvailable) {
	this.currentNectarAvailable = currentNectarAvailable;
}


@Override
public String toString() {
	return "Flowers [flowerName=" + flowerName + ", nectarCollection=" + nectarCollection + ", dailyNectarProduction="
			+ dailyNectarProduction + ", currentNectarAvailable=" + currentNectarAvailable + "]";
}

}
