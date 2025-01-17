package racingcar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Scanner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RaceGameTest {

    private static ByteArrayOutputStream outputMessage;

    @Test
    @DisplayName("carNameList: null에 대한 예외처리 성공")
    void setCarNamesListTest5(){
        RaceGame raceGame = new RaceGame();

        assertThrows(IllegalArgumentException.class, () -> raceGame.getNamesList(null));
    }

    @Test
    @DisplayName("carNameList: 빈 문자열에 대한 예외처리 성공")
    void setCarNamesListTest3(){
        RaceGame raceGame = new RaceGame();
        String input = "";

        assertThrows(IllegalArgumentException.class, () -> raceGame.getNamesList(input));
    }

    @Test
    @DisplayName("carNameList: 공백으로 이루어진 문자열에 대한 예외처리 성공")
    void setCarNamesListTest4(){
        RaceGame raceGame = new RaceGame();
        String input = "   ";

        assertThrows(IllegalArgumentException.class, () -> raceGame.getNamesList(input));
    }

    @Test
    @DisplayName("carNameList: 문자열 split을 ','를 기준으로 제대로 수행됨")
    void setCarNamesListTest1(){
        RaceGame raceGame = new RaceGame();
        String input = "pobi,jun,won";
        String result = "pobijunwon";

        assertDoesNotThrow(() -> raceGame.getNamesList(input));
        String compare = String.join("",raceGame.carNamesList);

        assertEquals(result, compare);
    }

    @Test
    @DisplayName("carNameList: 이름 문자열 앞, 뒤에 존재하는 공백이 올바르게 제거됨")
    void setCarNamesListTest2(){
        RaceGame raceGame = new RaceGame();
        String input = "pobi , jun, won ";
        String result = "pobijunwon";

        assertDoesNotThrow(() -> raceGame.getNamesList(input));
        String compare = String.join("",raceGame.carNamesList);

        assertEquals(result, compare);
    }

    @Test
    @DisplayName("carNameList: comma로 구분된 이름 중 빈 문자열이 있는 경우의 예외처리 성공")
    void setCarNamesListTest6(){
        RaceGame raceGame = new RaceGame();
        String input = "pobi,,jun";

        assertThrows(IllegalArgumentException.class, () -> raceGame.getNamesList(input));
    }

    @Test
    @DisplayName("carNameList: comma로 구분된 이름 중 공백문자로 구성된 문자열이 있는 경우의 예외처리 성공")
    void setCarNamesListTest7(){
        RaceGame raceGame = new RaceGame();
        String input = "pobi, ,jun";

        assertThrows(IllegalArgumentException.class, () -> raceGame.getNamesList(input));
    }

    @Test
    @DisplayName("carNameList: 같은 문자열이 있을 경우의 예외처리 성공")
    void setCarNamesListTest8(){
        RaceGame raceGame = new RaceGame();
        String input = "pobi, pobi,jun";

        assertThrows(IllegalArgumentException.class, () -> raceGame.getNamesList(input));
    }



    @Test
    @DisplayName("totalRounds: null 예외처리")
    void getTotalRoundsTest1(){
        RaceGame raceGame = new RaceGame();

        assertThrows(IllegalArgumentException.class, () -> raceGame.getTotalRounds(null));
    }

    @Test
    @DisplayName("totalRounds: 공백 예외처리")
    void getTotalRoundsTest2(){
        RaceGame raceGame = new RaceGame();
        String userInput = " ";

        assertThrows(IllegalArgumentException.class, () -> raceGame.getTotalRounds(userInput));
    }


    @Test
    @DisplayName("totalRounds: 빈 문자 예외처리")
    void getTotalRoundsTest3(){
        RaceGame raceGame = new RaceGame();
        String userInput = "";

        assertThrows(IllegalArgumentException.class, () -> raceGame.getTotalRounds(userInput));
    }


    @Test
    @DisplayName("totalRounds: 음수 예외처리")
    void getTotalRoundsTest4(){
        RaceGame raceGame = new RaceGame();
        String userInput = "-1";

        assertThrows(IllegalArgumentException.class, () -> raceGame.getTotalRounds(userInput));
    }

    @Test
    @DisplayName("totalRounds: 숫자가 아닌 문자 예외처리")
    void getTotalRoundsTest5(){
        RaceGame raceGame = new RaceGame();
        String userInput = "lds";

        assertThrows(IllegalArgumentException.class, () -> raceGame.getTotalRounds(userInput));
    }

    @Test
    @DisplayName("totalRounds: 매우 큰 수가 들어올 경우")
    void getTotalRoundsTest6(){
        RaceGame raceGame = new RaceGame();
        String userInput = "1000000000000000000000";
        BigInteger a = new BigInteger("1000000000000000000000");

        assertThrows(IllegalArgumentException.class, () -> raceGame.getTotalRounds(userInput));
    }

    @Test
    @DisplayName("totalRounds: 양의 정수가 들어올 경우")
    void getTotalRoundsTest7(){
        RaceGame raceGame = new RaceGame();
        String userInput = "1234";
        int a = 1234;

        assertDoesNotThrow(() -> raceGame.getTotalRounds(userInput));
        assertEquals(raceGame.totalRounds, a);
    }

    @Test
    @DisplayName("자동차 등록하기 테스트")
    void registerCarTest(){
        RaceGame raceGame = new RaceGame();
        String[] carNamesList = {"pobi", "woni", "jun"};
        int totalCars = 3;

        RacingCar[] carList = raceGame.registerCar(carNamesList, totalCars);

        assertEquals(carList[0].carName, carNamesList[0]);
        assertEquals(carList[1].carName, carNamesList[1]);
        assertEquals(carList[2].carName, carNamesList[2]);
    }

//    @Test
//    @DisplayName("경기 1회 진행하기")
//    void playOneRoundTest(){
//        RaceGame raceGame = new RaceGame();
//        RacingCar car1 = new RacingCar();
//        RacingCar car2 = new RacingCar();
//        RacingCar[] carList = {car1, car2};
////        car1.forwardCondition = true;
////        car2.forwardCondition = false;
//
//        raceGame.playOneRound(carList);
//        raceGame.playOneRound(carList);
//
//        assertAll(
//                () -> assertEquals("__", car1.currentLocation),
//                () -> assertEquals(2, car1.currentDistance),
//                () -> assertEquals("", car2.currentLocation),
//                () -> assertEquals(0, car2.currentDistance)
//        );
//
//    }

    @Test
    @DisplayName("매 round마다 출력할 경기 현황이 제대로 출력되는지 테스트")
    void printRoundResultTest(){
        RaceGame raceGame = new RaceGame();
        RacingCar car1 = new RacingCar();
        RacingCar car2 = new RacingCar();
        RacingCar[] carList = {car1, car2};
        car1.carName = "pobi";
        car2.carName = "woni";
        car1.currentLocation = "---";
        car1.currentDistance = 3;
        car2.currentLocation = "-------";
        car2.currentDistance = 7;

        outputMessage = new ByteArrayOutputStream(); // OutputStream 생성
        System.setOut(new PrintStream(outputMessage));

        raceGame.printRoundResult(carList);

        assertEquals("pobi : ---\nwoni : -------\n", outputMessage.toString());

    }

    @Test
    @DisplayName("최종 우승자를 찾기 - 우승자 1명")
    void findWinningCarsTest1(){
        RaceGame raceGame = new RaceGame();
        RacingCar car1 = new RacingCar();
        RacingCar car2 = new RacingCar();
        RacingCar car3 = new RacingCar();
        RacingCar car4 = new RacingCar();
        RacingCar[] carList = {car1, car2, car3, car4};
        car1.carName = "pobi";
        car2.carName = "woni";
        car3.carName = "jun";
        car4.carName = "lisa";
        car1.currentDistance = 3;
        car2.currentDistance = 7;
        car3.currentDistance = 10;
        car4.currentDistance = 7;
        String[] winners;
        String[] a = new String[]{"jun"};

        winners = raceGame.findWinningCars(carList);

        assertEquals(String.join("",a), String.join("",winners));
    }

    @Test
    @DisplayName("최종 우승자를 찾기 - 우승자 2명")
    void findWinningCarsTest2(){
        RaceGame raceGame = new RaceGame();
        RacingCar car1 = new RacingCar();
        RacingCar car2 = new RacingCar();
        RacingCar car3 = new RacingCar();
        RacingCar car4 = new RacingCar();
        RacingCar[] carList = {car1, car2, car3, car4};
        car1.carName = "pobi";
        car2.carName = "woni";
        car3.carName = "jun";
        car4.carName = "lisa";
        car1.currentDistance = 3;
        car2.currentDistance = 7;
        car3.currentDistance = 10;
        car4.currentDistance = 10;
        String[] winners;
        String[] a = new String[]{"jun", "lisa"};

        winners = raceGame.findWinningCars(carList);

        assertEquals(String.join("",a), String.join("",winners));
    }
}
