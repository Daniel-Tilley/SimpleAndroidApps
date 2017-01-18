package model;

/**
 * Created by danieltilley on 18/01/2017.
 */

public class Weather {

    public String iconData;
    public Place place;
    public CurrentCondition currentCondition = new CurrentCondition();
    public Temperature temperature = new Temperature();
    public Wind wind = new Wind();
    public Snow snow = new Snow();
    public Clouds clouds = new Clouds();

}
