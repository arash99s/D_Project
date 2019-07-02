import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );
        int n = scanner.nextInt();
        int q = scanner.nextInt();

        List<Head> heads = new ArrayList<>(n);
        for ( int i = 0 ; i < n - 1 ; i++ ){
            int number1 = scanner.nextInt();
            Head head1 = new Head(number1);
            int number2 = scanner.nextInt();
            Head head2 = new Head(number2);

            if (!heads.contains(head1)) {
                heads.add(head1);
            }else {
                for(Head head : heads){
                    if(head.equals(head1)){
                        head1 = head;
                        head.addLines(i);
                    }
                }
            }


            if (!heads.contains(head2)) {
                heads.add(head2);
            }else{
                for(Head head : heads){
                    if(head.equals(head2)){
                        head2 = head;
                        head.addLines(i);
                    }
                }
            }

            addUpAndUnderHead( head1 , head2 );
            head1.addRoundHeads(head2);
            head2.addRoundHeads(head1);

        }
        for (Head head : heads){
            head.findHeight(heads);
        }

        int[] answers = new int[q];
        for(int i = 0 ; i < q ; i++){
            int number1 = scanner.nextInt();
            int number2 = scanner.nextInt();
            Head answer = findAnswer(new Head(number1),new Head(number2),heads);
            answers[i]=answer.getName();
        }
        for(int i=0;i<q;i++) {
            System.out.println(answers[i]);
        }

    }
    private static void addUpAndUnderHead(Head head1 , Head head2 ){
        if( head1.getName() > head2.getName() ){
            head2.addUnderHeads(head1);
            head1.addUpHead(head2);
        }else{
            head1.addUnderHeads(head2);
            head2.addUpHead(head1);
        }
    }

    public static Head findAnswer(Head head1 , Head head2 , List<Head> heads){
        ArrayList<Head> shared = new ArrayList<>();
        ArrayList<Head> upHeads1 = new ArrayList<>();
        ArrayList<Head> upHeads2 = new ArrayList<>();

        for(Head head : heads){
            if(head1.equals(head)){
                head1 = head ;
            }
            if(head2.equals(head)){
                head2 = head;
            }
        }

        Head temp = head1;
        upHeads1.add(head1);
        while(!temp.getUpHeads().isEmpty()){
            temp = temp.getUpHeads().get(0);
            upHeads1.add(temp);
        }
        temp = head2;
        upHeads2.add(head2);
        while(!temp.getUpHeads().isEmpty()){
            temp = temp.getUpHeads().get(0);
            upHeads2.add(temp);
        }


        for(Head head : heads){
            if(upHeads1.contains(head) && upHeads2.contains(head)){
                shared.add(head);
            }
        }
        Head answerhead = shared.get(shared.size()-1);
        for(Head head : shared){
            if(answerhead.getHeight()>head.getHeight()){
                answerhead = head;
            }
            if(answerhead.getHeight() == head.getHeight()){
                if(head.getName()>answerhead.getName()){
                    answerhead = head;
                }
            }
        }
        for(Head head : shared){
            System.out.println(head.getName());
        }
        return answerhead;
    }

}
