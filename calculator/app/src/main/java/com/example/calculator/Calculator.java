package com.example.calculator;

//import java.lang.reflect.Array;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class Calculator {

    ArrayList<String> equationArr = new ArrayList<String>();
    int remainZero = 0;

    public String returnDisplay() {
        return String.join("", equationArr);
    }


    public String checkArrBeforeAddNum(String checkNum) {

        // Get the length of the Array List
        int checkForParenthesis = equationArr.lastIndexOf(")");
        int equationArrLength = equationArr.size();

        // If array contains no right parenthesis add the number
        if (checkForParenthesis == -1 && equationArr.lastIndexOf("(") != 0) {
            equationArr.add(checkNum);
            returnDisplay();
            return returnDisplay();
        } else if (checkForParenthesis + 1 == equationArrLength) {
            return returnDisplay();
        } else {
            equationArr.add(checkNum);
            return returnDisplay();
        }
    }

    public String checkArrBeforeAddSymbol(String checkSymbol) {
        String lastArrItem = equationArr.get(equationArr.size() - 1);

        if (equationArr.size() <= 0 && checkSymbol != "(") {
            return returnDisplay();
        } else if (lastArrItem == "^" && checkSymbol != "(") {
            return returnDisplay();
        } else if (!equationArr.contains("(") && checkSymbol == ")") {
            return returnDisplay();
        } else if (lastArrItem.matches("-?(0|[1-9[)]]\\d*)")) {
            equationArr.add(checkSymbol);
            return returnDisplay();
        } else if (!lastArrItem.matches("-?(0|[1-9[)]]\\d*)") && checkSymbol == "(") {
            equationArr.add(checkSymbol);
            return returnDisplay();
        } else {
            return returnDisplay();
        }
    }

    public String solveEquation() {

        ArrayList<String> myPList = groupNumbers();
        ArrayList<String> myList = new ArrayList<>();
        String returnVal;

        if (myPList.contains("(") || myPList.contains(")")) {
            int numOfPLeft = Collections.frequency(equationArr, "(");
            int numOfPRight = Collections.frequency(equationArr, ")");

            if (numOfPLeft == numOfPRight) {
                returnVal = consolidateParenthesis(myPList);
                return returnVal.substring(1, returnVal.length() - 1);
            } else {
                return "Parenthesis Error";
            }
        } else {
            returnVal = myEquationSolver(myPList);
            return returnVal.substring(1, returnVal.length() - 1);
        }
    }

    public ArrayList groupNumbers() {
        ArrayList<String> myNewList = new ArrayList<String>();
        int equationArrSize = equationArr.size();
        String myCurrentChar, mySymbol;
        myCurrentChar = mySymbol = "";

        for (int i = 0; i < equationArrSize; i++) {
            if (equationArr.get(i).matches("-?(0|[1-9]\\d*)")) {
                myCurrentChar += equationArr.get(i);
                if (i == equationArrSize - 1) {
                    myNewList.add(myCurrentChar);
                }
            } else {
                mySymbol += equationArr.get(i);
                if (myCurrentChar != "") {
                    myNewList.add(myCurrentChar);
                }
                myNewList.add(mySymbol);
                mySymbol = myCurrentChar = "";
            }
        }
        return myNewList;
    }

    public String consolidateParenthesis(ArrayList myNewList) {

        ArrayList<Integer> pLeftIndexList = new ArrayList<Integer>();
        ArrayList<Integer> pRightIndexList = new ArrayList<Integer>();
        ArrayList<String> myEquationList = new ArrayList<String>();

        for (int p = remainZero; p < myNewList.size(); p++) {
            if (myNewList.get(p).toString().matches("[(]")) {
                pLeftIndexList.add(p);
            } else if (myNewList.get(p).toString().matches("[)]")) {
                pRightIndexList.add(p);
            }
        }

//        if (pLeftIndexList.size() > 1) {
//            for (int i = 0; i < pLeftIndexList.size(); i++) {
//                if (pLeftIndexList.get(i) > pRightIndexList.get(i)) {
//                    int currentPPair = pLeftIndexList.get(i - 1);
//                    int currentPosition = currentPPair;
//                    for (int j = 0; j < myNewList.size(); j++) {
//                        if (!myNewList.get(currentPPair).toString().matches("[)]")) {
//                            myEquationList.add(String.valueOf(myNewList.get(currentPPair)));
//                            myNewList.remove(currentPPair);
//                        } else {
//                            myEquationList.add(String.valueOf(myNewList.get(currentPPair)));
//                            String nextComponent = myEquationSolver(myEquationList);
//                            myNewList.add(currentPosition, nextComponent);
//                            pLeftIndexList.remove(i - 1);
//                            pRightIndexList.remove(i);
//                            myEquationList.clear();
//                            i = 0;
//                            break;
//                        }
//                    }
//                }
//            }

            while (pRightIndexList.size() > 0) {
                int j = myNewList.indexOf("(");
                for (int k = j; k < pRightIndexList.get(0) + 1; k++) {
                    if (!myNewList.get(j).toString().matches("[)]")) {
                        myEquationList.add(String.valueOf(myNewList.get(j)));
                        myNewList.remove(j);
                    } else if (myNewList.get(j).toString().matches("[)]") && pRightIndexList.size() > 1){
                        myEquationList.add(String.valueOf(myNewList.get(j)));
                        myNewList.remove(j);
                    } else {
                        myEquationList.add(String.valueOf(myNewList.get(j)));
                        myNewList.remove(j);
                        break;
                    }
                }


                String nextComponent = myEquationSolver(myEquationList);
                String condensed = nextComponent.substring(1, nextComponent.length() - 1);
                pLeftIndexList.remove(0);
                pRightIndexList.remove(0);
                myNewList.add(j, condensed);
                myEquationList.clear();
            }
            return myEquationSolver(myNewList);
        }

//        } else {
//            int startingPoint = myNewList.indexOf("(");
//            int endingPoint = myNewList.indexOf(")");
//            String myCondensedString;
//
//            for (int start = startingPoint; start < endingPoint + 1; start++) {
//                String secludedEquationComponent = String.valueOf(myNewList.get(startingPoint));
//                myEquationList.add(secludedEquationComponent);
//                myNewList.remove(startingPoint);
//            }
//
//            for (int iterateString = remainZero; iterateString < myEquationList.size(); iterateString++) {
//                if (myEquationList.get(iterateString).matches("[(]")) {
//                    myEquationList.remove(iterateString);
//                } else if (myEquationList.get(iterateString).matches("[)]")) {
//                    myEquationList.remove(iterateString);
//                }
//            }
//            myCondensedString = myEquationSolver(myEquationList);
//            String myCondensedSubStr = myCondensedString.substring(1, myCondensedString.length() - 1);
//            myNewList.add(startingPoint, myCondensedSubStr);
//            String solved = myEquationSolver(myNewList);
//        }
//        return "";

    public String myEquationSolver(ArrayList equation) {

        if (equation.contains("(")) {
            for (int i = 0; i < equation.size(); i++) {
                if (equation.get(i).toString().matches("[(]") || equation.get(i).toString().matches("[)]")) {
                    equation.remove(i);
                }
            }
        }

        for (int checker = 0; checker < equation.size(); checker++) {
            if (equation.contains("^")) {
                for (int powCount = remainZero; powCount < equation.size(); powCount++) {
//                    String currentSymbol = String.valueOf(equation.get(powCount));
                    if (equation.get(powCount).toString().matches("[?^]")) {
                        long baseNum = Long.parseLong(String.valueOf(equation.get(powCount - 1)));
                        long exponentNum = Long.parseLong(String.valueOf(equation.get(powCount + 1)));
                        double myReturnedNum = Math.pow(baseNum, exponentNum);
//                        BigDecimal returnNumToList = new BigDecimal(myReturnedNum);
                        String returnNumToList = String.format("%.2f", myReturnedNum);
//                        BigInteger bigNum = new BigInteger(returnNumToList.toBigIntegerExact());
                        equation.remove(powCount + 1);
                        equation.remove(powCount);
                        equation.remove(powCount - 1);
                        equation.add(powCount - 1, returnNumToList);
                    }
                }
            }

            if (equation.contains("*") || equation.contains("/") || equation.contains("%")) {
                for (int i = remainZero; i < equation.size(); i++) {
                    switch (String.valueOf(equation.get(i))) {
                        case "*":
                            double myFirstNum = Double.parseDouble(String.valueOf(equation.get(i - 1)));
                            double mySecondNum = Double.parseDouble(String.valueOf(equation.get(i + 1)));
                            double combinedNums = myFirstNum * mySecondNum;
                            equation.remove(i + 1);
                            equation.remove(i);
                            equation.remove(i - 1);
                            equation.add(i - 1, String.format("%.2f", combinedNums));
                            break;
                        case "/":
                            myFirstNum = Double.parseDouble(String.valueOf(equation.get(i - 1)));
                            mySecondNum = Double.parseDouble(String.valueOf(equation.get(i + 1)));
                            combinedNums = myFirstNum / mySecondNum;
                            equation.remove(i + 1);
                            equation.remove(i);
                            equation.remove(i - 1);
                            equation.add(i - 1, String.valueOf(combinedNums));
                            break;
                        case "%":
                            myFirstNum = Double.parseDouble(String.valueOf(equation.get(i - 1)));
                            mySecondNum = Double.parseDouble(String.valueOf(equation.get(i + 1)));
                            combinedNums = myFirstNum % mySecondNum;
                            equation.remove(i + 1);
                            equation.remove(i);
                            equation.remove(i - 1);
                            equation.add(i - 1, String.valueOf(combinedNums));
                            break;
                        default:
                            continue;
                    }
                }
            }

            if (equation.contains("+") || equation.contains("-")) {

                for (int i = remainZero; i < equation.size(); i++) {
                    switch (String.valueOf(equation.get(i))) {
                        case "+":
                            double myFirstNum = Double.parseDouble(String.valueOf(equation.get(i - 1)));
                            double mySecondNum = Double.parseDouble(String.valueOf(equation.get(i + 1)));
                            double combinedNums = myFirstNum + mySecondNum;
                            equation.remove(i + 1);
                            equation.remove(i);
                            equation.remove(i - 1);
                            equation.add(i - 1, String.valueOf(combinedNums));
                            break;
                        case "-":
                            myFirstNum = Double.parseDouble(String.valueOf(equation.get(i - 1)));
                            mySecondNum = Double.parseDouble(String.valueOf(equation.get(i + 1)));
                            combinedNums = myFirstNum - mySecondNum;
                            equation.remove(i + 1);
                            equation.remove(i);
                            equation.remove(i - 1);
                            equation.add(i - 1, String.valueOf(combinedNums));
                            break;
                        default:
                            continue;
                    }
                }
            }
        }
        return String.valueOf(equation);
    }
}

//                for (int q = 0; q < pLeftIndexList.size(); q++) {
//
//                    // determines whether parenthesis are properly placed
//                    if (pLeftIndexList.get(q) > pRightIndexList.get(q)) {
//                        return "Parenthesis Error";
//                    } else {
//
////                        if (pLeftIndexList.size() == 1) {
////                            int startIndex = pLeftIndexList.get(0);
////                            int endIndex = pRightIndexList.get(0);
////
////                            for ()
////                        }
//
//                        // if parenthesis is nested
//                        if (pLeftIndexList.size() > 1 && pLeftIndexList.get(q + 1) < pRightIndexList.get(q)) {
//
//                            // finding the first inner parenthesis
//                            for (int pEquationCount = 1; pEquationCount < pLeftIndexList.size(); pEquationCount++) {
//
//                                // if the left parenthesis is larger than the right parenthesis
//                                if (pLeftIndexList.get(pEquationCount) > pRightIndexList.get(q) || pLeftIndexList.size() == 1) {
//
//                                    // create an int that retrieves the left parenthesis index
//                                    int starterIndex = pLeftIndexList.get(pEquationCount);
//
//                                    // create an int that retrieves the right parenthesis index
//                                    int endingIndex = pRightIndexList.get(q);
//
//                                    // for loop that will iterate through the objects within the parenthesis
//                                    for (int smallIndex = starterIndex; smallIndex < endingIndex; smallIndex++) {
//
//                                        // the equation will then be grouped into a single string
//                                        myEquation += myNewList.get(starterIndex);
//
//                                        // Character remove from the new array list
//                                        myNewList.remove(starterIndex);
//                                    }
//
//                                    // remove the ints containing parenthesis indexes from both array lists
//                                    pLeftIndexList.remove(starterIndex);
//                                    pRightIndexList.remove(endingIndex);
//
//                                    // Add the equation at the correct index
//                                    myNewList.add(starterIndex, myEquation);
//                                    System.out.println(myNewList);
//                                } else {
//                                    continue;
//                                }
//                            }
//
//                            System.out.println(myNewList);
//
//                        } else {
//
//                        }
//                    }
//                }

