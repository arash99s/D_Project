import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );
        int n = scanner.nextInt();

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

            addUnderHead ( head1 , head2 );
            head1.addRoundHeads(head2);
            head2.addRoundHeads(head1);

        }

        for(Head head : heads){
            System.out.println(head.getName());
        }
        System.out.println("**********************");
        for(Head head : heads){
            if( head.getUnderHeads().size() == 0){
                System.out.println(head.getName());
            }
        }
        System.out.println("*********************minimum");
        for(Head head : heads){
            if(head.equals(new Head(2))){
                ArrayList<Integer> minimum = head.findHeight(heads);
                for(int i = 0;i<minimum.size();i++){
                    System.out.println(minimum.get(i));
                }
            }
        }


    }
    private static void addUnderHead ( Head head1 , Head head2 ){
        if( head1.getName() > head2.getName() ){
            head2.addUnderHeads(head1);
        }else{
            head1.addUnderHeads(head2);
        }
    }

}
