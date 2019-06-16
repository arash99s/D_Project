import java.util.*;

public class Head {
    private int name;
    private int height;
    private ArrayList<Head> underHeads = new ArrayList<>();
    private ArrayList<Head> roundHeads = new ArrayList<>();
    private ArrayList<Integer> lines = new ArrayList<>();
    private ArrayList<Integer> minimum = new ArrayList<>();
    private int m =0;
    public Head(int name){
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }
        if(this==obj){
            return true;
        }
        if(obj instanceof Head){
            Head head = (Head) obj;
            if(this.name==head.name){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int prime = 17;
        return prime*prime + prime*name ;
    }

    public int getName() {
        return name;
    }

    public void addLines( int line ) {
        lines.add(line);
    }
    public void addUnderHeads( Head underHead ) {
        underHeads.add(underHead);
    }

    public ArrayList<Head> getUnderHeads() {
        return underHeads;
    }

    public ArrayList<Integer> findHeight(List<Head> heads ) {
        Stack<Head> sHeads = new Stack<>();
        Head curHead = this;
        sHeads.push(curHead);
        int numberOfHeads = heads.size();
        boolean[] opened = new boolean[numberOfHeads];
        for(int i=0;i<numberOfHeads;i++){
            opened[i] = false;
        }
        opened[curHead.getName()-1] = true;
        System.out.println("*******************names");
        while(!isFinished(opened)){
            curHead = sHeads.peek();
            System.out.println(curHead.getName());
            opened[curHead.getName()-1] = true;
            openHeads(curHead , sHeads,opened);
        }
        System.out.println("end");
        for (Head head : heads){
            minimum.add(head.getM());
        }

        return minimum;

    }
    public void addRoundHeads(Head head){
        roundHeads.add(head);
    }

    public ArrayList<Head> getRoundHeads() {
        return roundHeads;
    }
    private boolean isFinished(boolean[] openend){
        boolean b = true;
        for(int i=0;i<openend.length;i++){
            if(openend[i]==false)
                b=false;
        }
        return b;
    }
    private void openHeads(Head curHead , Stack<Head> sHeads , boolean[] opened){
        boolean exist = false ;
        for(Head head : curHead.roundHeads){
            if(opened[head.getName()-1]==false) {
                sHeads.push(head);
                System.out.println("added: "+ head.getName());
                exist = true;
                head.setM(curHead.getM()+1);
            }
        }

        if(!exist){
            sHeads.pop();
        }
    }

    public ArrayList<Integer> getMinimum() {
        return minimum;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getM() {
        return m;
    }
}