package baseball;


import java.io.IOException;
import java.util.Arrays;
import nextstep.utils.Console;
import nextstep.utils.Randoms;

public class Application {
    public static void main(String[] args)  {
        // TODO 숫자 야구 게임 구현
    }

    //사용자 숫자입력 메소드
    public static String inputUserNumber(){
        //값 입력하기
        String inputUserNumber;
        while(true){
            boolean isValidation=true;
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

}
