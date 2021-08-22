package pap21l.z09.weatherappserver.Utility;

//names of the variables depend on the json string
public class Chunk {
    public int dt;
    public MainData main;
    public Weather[] weather;
    public Clouds clouds;
    public Wind wind;
    public int visibility;
    public double pop;
    public Sys sys;
    public String dt_txt;
}
