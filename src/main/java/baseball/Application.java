package baseball;
import java.util.Arrays;
import nextstep.utils.Console;
import nextstep.utils.Randoms;

public class Application {
    public static void main(String[] args)  {
        // TODO 숫자 야구 게임 구현
        boolean isReRun=true;
        while(isReRun){
            boolean isCorrect=false;
            String computerNumber= makeComputerNumber();
            while(!isCorrect){
                //사용자 숫자 입력 메소드
                String userNumber = inputUserNumber();
                //컴퓨터 3자리 난수생성
                isCorrect=countBallAndStrike(userNumber,computerNumber);
            }
            isReRun = chooseGameReRun();
        }
    }

    //사용자 숫자입력 메소드
    public static String inputUserNumber(){
        //값 입력하기
        String inputUserNumber;
        while(true){
            System.out.print("숫자를 입력해 주세요 : ");
            inputUserNumber = Console.readLine();
            if(checkUserInputLength(inputUserNumber)==false||checkUserInputDuplication(inputUserNumber)==false||checkInputType(inputUserNumber)==false){
                System.out.println("ERROR 숫자를 다시 입력해주세요");
            }else{
                break;
            }
        }
        return inputUserNumber;
    }

    //입력받은 값이 숫자인지 확인하는 방법
    private static boolean checkInputType(String inputUserNumber) {
        char tmp;
        boolean isDigit=true;
        for(int i=0;i<inputUserNumber.length();i++){
            tmp=inputUserNumber.charAt(i);
            if(Character.isDigit(tmp)==false){
                isDigit=false;
            }
        }
        return isDigit;
    }

    //사용자 입력값 길이 확인
    public static boolean checkUserInputLength(String inputUserNumber){
        if(inputUserNumber.length()>3){
            return false;
        }else{
            return true;
        }
    }

    //사용자 입력값 중복확인
    public static boolean checkUserInputDuplication(String inputUserNumber){
        //중복숫자 확인 메서드
        Integer []checkInput=new Integer[10];
        Arrays.fill(checkInput,0);
        boolean isDuplicate=true;

        for(int i=0;i<3;i++){
            String substring = inputUserNumber.substring(i,i+1);
            checkInput[Integer.parseInt(substring)]++;
        }
        for(int i=0;i<10;i++){
            if(checkInput[i]>1){
                isDuplicate=false;
            }
        }
        return isDuplicate;
    }

    //숫자 랜덤으로 뽑기
    public static String makeComputerNumber(){
        boolean []check=new boolean[10];
        int count=0;
        String computerNumber="";
        while(count<3){
            int randomValue = Randoms.pickNumberInRange(0, 9);
            //같은 번호 뽑힘 x일때는 count++를 하여 뽑은 수를 체크해준다.
            if(check[randomValue]==false){
                count++;
                check[randomValue]=true;
                computerNumber+= String.valueOf(randomValue);
            }
        }
        return computerNumber;
    }

    //볼과 스트라이크수 계산
    public static boolean  countBallAndStrike(String inputUserNumber,String inputComputerNumber) {
        int ballCount=0;
        int strikeCount=0;
        for(int i=0;i<inputComputerNumber.length();i++){
            int findPos=inputComputerNumber.indexOf(inputUserNumber.charAt(i));
            if(findPos==i){
                strikeCount++;
            }else if(findPos!=-1){
                ballCount++;
            }
        }
        return printBallAndStrikeNumber(ballCount,strikeCount);
    }

    //스트라이크와 볼 개수 출력
    public static  boolean printBallAndStrikeNumber(int ballCount,int strikeCount){

        boolean isCorrect=false;
        if(ballCount!=0&&strikeCount!=0){
            System.out.println(strikeCount+"스트라이크"+' '+ballCount+"볼");
        }else if(ballCount==0&&strikeCount!=0){
            if(strikeCount==3){
                isCorrect=true;
                System.out.println(strikeCount+"스트라이크");
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
            }else{
                System.out.println(strikeCount+"스트라이크");
            }
        }else if(ballCount!=0&&strikeCount==0){
            System.out.println(+ballCount+"볼");
        }else{
            System.out.println("낫싱");
        }
        return isCorrect;
    }

    //게임 재시작 혹은 종료 선택
    private static boolean chooseGameReRun() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String gameState = Console.readLine();
        if(gameState.equals("1")){
            return  true;
        }else{
            return false;
        }
    }
}
