package Beehive;

public class Bee {
private static int count=0;
private int beeCount=0;
private int beeId = 0;
private String type;
private int age=0;
private int nectarCollectionSorties;
private boolean eaten;
private boolean alive;

public Bee() {
	count++;
	beeCount=count;
	beeId=190+count;
	this.type="Egg";
	this.eaten=false;
	this.alive=false;
	
}
public Bee(String type,int age,int nectarCollectionSorties , boolean eaten, boolean alive) {
	count++;
	beeCount=count;
	beeId=190+count;
	this.type=type;
	this.age=age;
	this.eaten=eaten;
	this.alive=alive;
	
}



public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public int getBeeCount() {
	return beeCount;
}


public void setBeeCount(int beeCount) {
	this.beeCount = beeCount;
}


public int getBeeId() {
	return beeId;
}


public void setBeeId(int beeId) {
	this.beeId = beeId;
}


public String getType() {
	return type;
}


public void setType(String type) {
	this.type = type;
}


public int getNectarCollectionSorties() {
	return nectarCollectionSorties;
}


public void setNectatCollectionSorties(int nectatCollectionSorties) {
	this.nectarCollectionSorties = nectatCollectionSorties;
}


public boolean isEaten() {
	return eaten;
}


public void setEaten(boolean eaten) {
	this.eaten = eaten;
}


public boolean isAlive() {
	return alive;
}


public void setAlive(boolean alive) {
	this.alive = alive;
}
public void incrementAge(int age) {
	this.age +=1;
}
@Override
public String toString() {
	return "Bee [beeCount=" + beeCount + ", beeId=" + beeId + ", type=" + type + ", age=" + age
			+ ", nectarCollectionSorties=" + nectarCollectionSorties + ", eaten=" + eaten + ", alive=" + alive + "]";
}



}
